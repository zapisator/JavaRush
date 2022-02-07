package com.javarush.task.task22.task2201;

public class OurUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        final String template = "%s : %s : %s";
        if (Solution.FIRST_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForFirstThread(t, e, template));
        } else if (Solution.SECOND_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForSecondThread(t, e, template));
        } else {
            System.out.println(getFormattedStringForOtherThread(t, e, template));
        }
    }

    protected String getFormattedStringForOtherThread(Thread t, Throwable e, String template) {
        return String.format(
                template,
                e.getClass().getSimpleName(), e.getCause(), t.getName()
        );
    }

    protected String getFormattedStringForSecondThread(Thread t, Throwable e, String template) {
        return String.format(
                template,
                e.getCause().toString(), e.getClass().getSimpleName(),  t.getName()
        );
    }

    protected String getFormattedStringForFirstThread(Thread t, Throwable e, String template) {
        return String.format(
                template,
                t.getName(), e.getClass().getSimpleName(), e.getCause().toString()
        );
    }
}

