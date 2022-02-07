package com.javarush.task.task39.task3913;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

class LogData {

    private final String ip;
    private final String user;
    private final DateData dateData;
    private final EventData eventData;
    private final Status status;

    private LogData(String ip, String user,
            DateData dateData, EventData eventData, Status status) {
        this.ip = ip;
        this.user = user;
        this.dateData = dateData;
        this.eventData = eventData;
        this.status = status;
    }

    public static LogData create(String[] words) {
        final String ip = words[0];
        final String user = words[1];
        final DateData dateData = DateData.create(words[2]);
        final EventData eventData = EventData.create(words[3]);
        final Status status = Status.valueOf(words[4]);

        return new LogData(ip, user, dateData, eventData, status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, user, dateData, eventData, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogData)) {
            return false;
        }
        final LogData logData = (LogData) obj;

        return Objects.equals(ip, logData.getIp())
                && Objects.equals(user, logData.getUser())
                && Objects.equals(dateData, logData.getDateData())
                && Objects.equals(eventData, logData.getEventData())
                && Objects.equals(status, logData.getStatus());
    }

    @Override
    public String toString() {
        return "'" + ip + '\'' +
                ", '" + user + '\'' +
                ", " + dateData +
                ", " + eventData +
                ", " + status;
    }

    public String getIp() {
        return ip;
    }

    public String getUser() {
        return user;
    }

    public DateData getDateData() {
        return dateData;
    }

    public EventData getEventData() {
        return eventData;
    }

    public Status getStatus() {
        return status;
    }

    protected static class DateData {

        private final String dateString;
        private final Date date;

        private DateData(String dateString, Date date) {
            this.dateString = dateString;
            this.date = date;
        }

        public static DateData create(String dateString) {
            try {
                final Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
                        .parse(dateString);

                return new DateData(dateString, date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(dateString, date);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DateData)) {
                return false;
            }

            final DateData dateData = (DateData) obj;

            return Objects.equals(dateString, dateData.getDateString())
                    && Objects.equals(date, dateData.getDate());
        }

        @Override
        public String toString() {
            return "" + date;
        }

        public String getDateString() {
            return dateString;
        }

        public Date getDate() {
            return date;
        }
    }

    protected static class EventData {

        private final String eventString;
        private final Event event;
        private final int number;

        private EventData(String eventString, Event event, int number) {
            this.eventString = eventString;
            this.event = event;
            this.number = number;
        }

        public static EventData create(String eventString) {
            final String[] eventWords = eventString.split(" ");
            final Event event = Event.valueOf(eventWords[0]);
            final int number;

            if (eventWords.length == 2) {
                number = Integer.parseInt(eventWords[1]);
            } else {
                number = Integer.MIN_VALUE;
            }
            return new EventData(eventString, event, number);
        }

        @Override
        public int hashCode() {
            return Objects.hash(eventString, event, number);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EventData)) {
                return false;
            }

            final EventData eventData = (EventData) obj;

            return Objects.equals(eventString, eventData.getEventString())
                    && Objects.equals(event, eventData.getEvent())
                    && Objects.equals(number, eventData.getNumber());
        }

        @Override
        public String toString() {
            return "" + event +
                    ", " + number;
        }

        public String getEventString() {
            return eventString;
        }

        public Event getEvent() {
            return event;
        }

        public int getNumber() {
            return number;
        }
    }

}
