package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ConsoleHelper.writeMessage("Введите порт сервера.(целое число)");

        try (final ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());) {
            ConsoleHelper.writeMessage("Сервер запущен.");

            while (true) {
                new Handler(serverSocket.accept())
                        .start();
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage(e.toString());
        }

    }

    public static void sendBroadcastMessage(Message message) {
        connectionMap.forEach((name, connection) -> sendMessage(message, connection));
    }


    private static void sendMessage(Message message, Connection connection) {
        try {
            connection.send(message);
        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.toString());
        }
    }

    private static class Handler extends Thread {

        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом "
                    + socket.getRemoteSocketAddress());
            String userName = null;

            try (final Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage(
                        "Произошла ошибка при обмене данными с удаленным адресом: "
                                + socket.getRemoteSocketAddress());
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
                ConsoleHelper.writeMessage(
                        "Соединение с удаленным адресом "
                                + socket.getRemoteSocketAddress() + " закрыто.");
            }


        }

        private String serverHandshake(Connection connection)
                throws IOException, ClassNotFoundException {
            connection.send(new Message(MessageType.NAME_REQUEST));
            Message message = connection.receive();

            while (!checkType(message.getType()) || !checkData(message.getData())) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                message = connection.receive();
            }
            connectionMap.put(message.getData(), connection);
            connection.send(new Message(MessageType.NAME_ACCEPTED, "Ваше имя принято."));
            return message.getData();
        }

        private boolean checkData(String userName) {
            boolean result = true;

            if (userName.isEmpty()) {
                ConsoleHelper.writeMessage("Попытка подключения к серверу с пустым именем от "
                        + socket.getRemoteSocketAddress());
                result = false;
            }
            if (result && connectionMap.containsKey(userName)) {
                ConsoleHelper.writeMessage(
                        "Попытка подключения к серверу с уже используемым именем от "
                                + socket.getRemoteSocketAddress());
                result = false;
            }
            return result;
        }

        private boolean checkType(MessageType type) {
            boolean result = true;
            if (type != MessageType.USER_NAME) {
                ConsoleHelper.writeMessage(
                        "Получено сообщение от " + socket.getRemoteSocketAddress()
                                + ". Тип сообщения не соответствует протоколу.");
                result = false;
            }
            return result;
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Entry<String, Connection> entry : connectionMap.entrySet()) {
                if (!entry.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName)
                throws IOException, ClassNotFoundException {
            while (true) {
                final Message message = connection.receive();

                if (message.getType() == MessageType.TEXT) {
                    sendBroadcastMessage(
                            new Message(MessageType.TEXT, userName + ": " + message.getData()));
                } else {
                    ConsoleHelper.writeMessage("Полученое сообщение от " + userName
                            + "не соответствует протоколу. Должно быть текстовым.");
                }
            }
        }

    }

}
