package com.javarush.task.task20.task2023;
import static java.lang.System.out;
public class App {

    public static void main(String[] args) {
        // Пример переопределения статических и обычных методов
        // и затенения полей

        B b = new B();  // Создать новый объект типа B
        out.printf("b.i    %5d %s\n", b.i, "\tСсылается на B.i; выводит 2");   // Ссылается на B.i; выводит 2
        out.printf("b.j    %5.1f %s\n", b.j, "\tСсылается на B.j; выводит 2.0");   // Ссылается на B.j; выводит 2.0
        out.printf("b.f()  %5d %s\n", b.f(), "\tСсылается на B.f(); выводит -2"); // Ссылается на B.f(); выводит -2
        out.printf("b.g()  %5s %s\n", b.g(), "\tСсылается на B.g(); выводит B"); // Ссылается на B.g(); выводит B
        out.printf("B.g()  %5s %s\n\n", B.g(), "\tЛучше вызывать B.g()"); // Лучше вызывать B.g()

        A a = (A) b;    // Привести b к экземпляру класса A
        out.printf("a.i   %5d %s\n", a.i, "\tТеперь ссылается на A.i; выводит 1");   // Теперь ссылается на A.i; выводит 1
        out.printf("a.j   %5.1f %s\n", a.j, "\tТеперь ссылается на A.j; выводит 1.0");   // Теперь ссылается на A.j; выводит 1.0
        out.printf("a.f() %5d %s\n", a.f(), "\tСсылается на B.f(); выводит -2"); // Ссылается на B.f(); выводит -2
        out.printf("a.g() %5s %s\n", a.g(), "\tСсылается на A.g(); выводит A"); // Ссылается на A.g(); выводит A
        out.printf("A.g() %5s %s\n", A.g(), "\tЛучше вызывать A.g()"); // Лучше вызывать A.g()

    }

    static class A {

        int i = 1; // Поле экземпляра
        static double j = 1.0; // поле класса

        // Метод экземпляра
        int f() {
            return i;
        }

        // Статический метод (метод класса)
        static char g() {
            return 'A';
        }
    }

    static class B extends A { // Определить подкласс класса A
        int i = 2; // Затеняет поле i класса A
        static double j = 2.0; // Затеняет статическое поле j класса A

        // Переопределяем метод f() класса A
        int f() {
            return -i;
        }

        // Переопределяем статический метод g() класса A
        static char g() {
            return 'B';
        }
    }
}