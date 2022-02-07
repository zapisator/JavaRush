package com.javarush.task.task39.task3913;

import static com.javarush.task.task39.task3913.Event.LOGIN;
import static com.javarush.task.task39.task3913.Event.SOLVE_TASK;
import static com.javarush.task.task39.task3913.Status.FAILED;
import static java.lang.System.out;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.util.Pair;

public class Solution {

    public static void main(String[] args) {
//        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        LogParser logParser = new LogParser(Paths.get(
                "/home/sb_work/Загрузки/JavaRushTasks/"
                        + "4.JavaCollections/src/com/"
                        + "javarush/task/task39/task3913/logs"));
//        out.println(
//                (logParser.getNumberOfUniqueIPs(null, null) == 5)
//                        + "\tgetNumberOfUniqueIPs");
//        out.println(
//                Arrays.equals(
//                        logParser.getUniqueIPs(null, null).toArray(),
//                        new String[]{
//                                "120.120.120.122",
//                                "146.34.15.5",
//                                "192.168.100.2",
//                                "12.12.12.12",
//                                "127.0.0.1"})
//                        + "\tgetUniqueIPs"
//        );
//        out.println(
//                Arrays.equals(
//                        new String[]{"146.34.15.5", "127.0.0.1"},
//                        logParser.getIPsForUser("Eduard Petrovich Morozko", null, null).toArray())
//                        + "\tgetIPsForUser"
//        );
//        out.println(
//                logParser.getIPsForEvent(LOGIN, null, new Date())
//                        .toString()
//                        .equals("[146.34.15.5, 127.0.0.1]")
//                        + "\tgetIPsForEvent"
//        );
//        out.println(
//                logParser.getIPsForStatus(FAILED, null, null)
//                        .toString()
//                        .equals("[146.34.15.5, 127.0.0.1]")
//                        + "\tgetIPsForStatus"
//        );
//        out.println(
//                logParser.getAllUsers()
//                        .toString()
//                        .equals("[Amigo, Vasya Pupkin, Eduard Petrovich Morozko]")
//                        + "\tgetAllUsers"
//        );
//        out.println(
//                (logParser.getNumberOfUsers(null, null) == 3)
//                        + "\tgetNumberOfUsers"
//        );
//        out.println(
//                (logParser.getNumberOfUserEvents("Amigo", null, null) == 2)
//                        + "\tgetNumberOfUserEvents"
//        );
//        out.println(
//                logParser.getUsersForIP("192.168.100.2", null, null)
//                        .toString()
//                        .equals("[Vasya Pupkin]")
//                        + "\tgetUsersForIP"
//        );
//        out.println(
//                logParser.getLoggedUsers(null, null)
//                        .toString()
//                        .equals("[Eduard Petrovich Morozko]")
//                        + "\tgetLoggedUsers"
//        );
//        out.println(
//                logParser.getDownloadedPluginUsers(null, null)
//                        .toString()
//                        .equals("[Eduard Petrovich Morozko]")
//                        + "\tgetDownloadedPluginUsers"
//        );
//        out.println(
//                logParser.getWroteMessageUsers(null, null)
//                        .toString()
//                        .equals("[Vasya Pupkin, Eduard Petrovich Morozko]")
//                        + "\tgetWroteMessageUsers"
//        );
//        out.println(
//                logParser.getSolvedTaskUsers(null, null)
//                        .toString()
//                        .equals("[Amigo, Vasya Pupkin]")
//                        + "\tgetSolvedTaskUsers"
//        );
//        out.println(
//                logParser.getSolvedTaskUsers(null, null, 18)
//                        .toString()
//                        .equals("[Amigo, Vasya Pupkin]")
//                        + "\tgetSolvedTaskUsers"
//        );
//        out.println(
//                logParser.getDoneTaskUsers(null, null)
//                        .toString()
//                        .equals("[Vasya Pupkin, Eduard Petrovich Morozko]")
//                        + "\tgetDoneTaskUsers"
//        );
//        out.println(
//                logParser.getDoneTaskUsers(null, null, 48)
//                        .toString()
//                        .equals("[Eduard Petrovich Morozko]")
//                        + "\tgetDoneTaskUsers"
//        );
//        out.println(
//                logParser.getDatesForUserAndEvent("Vasya Pupkin", SOLVE_TASK, null, null)
//                        .toString()
//                        .equals("[Sat Mar 19 00:00:00 MSK 2016, Thu Jan 30 12:56:22 MSK 2014]")
//                        + "\tgetDatesForUserAndEvent"
//        );
//        out.println(
//                logParser.getDatesWhenSomethingFailed(null, null)
//                        .toString()
//                        .equals("[Wed Dec 11 10:11:12 MSK 2013, Tue Jan 05 20:22:55 MSK 2021]")
//                        + "\tgetDatesWhenSomethingFailed"
//        );
//        out.println(
//                logParser.getDatesWhenErrorHappened(null, null)
//                        .toString()
//                        .equals("[Thu Jan 30 12:56:22 MSK 2014]")
//                        + "\tgetDatesWhenErrorHappened"
//        );
//        out.println(
//                logParser.getDatesWhenUserWroteMessage("Vasya Pupkin", null, null)
//                        .toString()
//                        .equals("[Sat Nov 14 07:08:01 MSK 2015]")
//                        + "\tgetDatesWhenUserWroteMessage"
//        );
//        out.println(
//                logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, null)
//                        .toString()
//                        .equals("[Fri Sep 13 05:04:50 MSK 2013]")
//                        + "\tgetDatesWhenUserDownloadedPlugin"
//        );
//        out.println(
//                logParser.getDateWhenUserLoggedFirstTime("Amigo", null, null)
//                        .toString()
//                        .equals("Thu Aug 30 16:08:13 MSK 2012")
//                        + "\tgetDateWhenUserLoggedFirstTime"
//        );
//        out.println(
//                logParser.getDateWhenUserSolvedTask("Vasya Pupkin", 18, null, null)
//                        .toString()
//                        .equals("Thu Jan 30 12:56:22 MSK 2014")
//                        + "\tgetDateWhenUserSolvedTask"
//        );
//        out.println(
//                logParser.getDateWhenUserDoneTask("Eduard Petrovich Morozko", 48, null, null)
//                        .toString()
//                        .equals("Tue Jan 05 20:22:55 MSK 2021")
//                        + "\tgetDateWhenUserDoneTask"
//        );
//        out.println(
//                (logParser.getNumberOfAllEvents(null, null) == 5)
//                        + "\tgetNumberOfAllEvents"
//        );
//        out.println(
//                logParser.getAllEvents(null, null)
//                        .toString()
//                        .equals("[SOLVE_TASK, LOGIN, DONE_TASK, WRITE_MESSAGE, DOWNLOAD_PLUGIN]")
//                        + "\tgetAllEvents"
//        );
//        out.println(
//                logParser.getEventsForIP("146.34.15.5", null, null)
//                        .toString()
//                        .equals("[LOGIN, DONE_TASK, WRITE_MESSAGE, DOWNLOAD_PLUGIN]")
//                        + "\tgetEventsForIP"
//        );
//        out.println(
//                logParser.getEventsForUser("Amigo", null, null)
//                        .toString()
//                        .equals("[SOLVE_TASK, LOGIN]")
//                        + "\tgetEventsForUser"
//        );
//        out.println(
//                logParser.getFailedEvents(null, null)
//                        .toString()
//                        .equals("[DONE_TASK, WRITE_MESSAGE]")
//                        + "\tgetFailedEvents"
//        );
//        out.println(
//                logParser.getErrorEvents(null, null)
//                        .toString()
//                        .equals("[SOLVE_TASK]")
//                        + "\tgetErrorEvents"
//        );
//        out.println(
//                (logParser.getNumberOfAttemptToSolveTask(18, null, null) == 3)
//                        + "\tgetNumberOfAttemptToSolveTask"
//        );
//        out.println(
//                (logParser.getNumberOfSuccessfulAttemptToSolveTask(15, null, null) == 1)
//                        + "\tgetNumberOfSuccessfulAttemptToSolveTask"
//        );
//        out.println(
//                logParser.getAllSolvedTasksAndTheirNumber(null, null)
//                        .toString()
//                        .equals("{1=1, 18=3}")
//                        + "\tgetAllSolvedTasksAndTheirNumber"
//        );
//        out.println(
//                logParser.getAllDoneTasksAndTheirNumber(null, null)
//                        .toString()
//                        .equals("{48=1, 15=1}")
//                        + "\tgetAllDoneTasksAndTheirNumber"
//        );

//        out.println("\nexecute:");
//        out.println(
//                logParser.execute("get IP")
//                        .toString()
//                        .equals("[120.120.120.122, 146.34.15.5, 192.168.100.2, 12.12.12.12, 127.0.0.1]")
//                        + "\tget IP"
//        );
//        out.println(
//                logParser.execute("get user")
//                        .toString()
//                        .equals("[Amigo, Vasya Pupkin, Eduard Petrovich Morozko]")
//                        + "\tget user"
//        );
//        out.println(
//                logParser.execute("get date")
//                        .toString()
//                        .equals("[Thu Dec 12 21:56:30 MSK 2013, "
//                                + "Fri Jan 03 03:45:23 MSK 2014, "
//                                + "Wed Dec 11 10:11:12 MSK 2013, "
//                                + "Thu Oct 14 11:38:21 MSK 2021, "
//                                + "Fri Sep 13 05:04:50 MSK 2013, "
//                                + "Thu Oct 21 19:45:25 MSK 2021, "
//                                + "Thu Jan 30 12:56:22 MSK 2014, "
//                                + "Sat Nov 14 07:08:01 MSK 2015, "
//                                + "Sat Mar 19 00:00:00 MSK 2016, "
//                                + "Tue Feb 29 05:04:07 MSK 2028, "
//                                + "Thu Aug 30 16:08:40 MSK 2012, "
//                                + "Tue Jan 05 20:22:55 MSK 2021, "
//                                + "Thu Aug 30 16:08:13 MSK 2012]"
//                        )
//                        + "\tget date"
//        );
//        out.println(
//                logParser.execute("get event")
//                        .toString()
//                        .equals("[SOLVE_TASK, LOGIN, DONE_TASK, WRITE_MESSAGE, DOWNLOAD_PLUGIN]")
//                        + "\tget event"
//        );
//        out.println(
//                logParser.execute("get status")
//                        .toString()
//                        .equals("[OK, FAILED, ERROR]")
//                        + "\tget status"
//        );
//
        out.println("\nexecute expanded:");

        out.println(logParser.execute("get ip for user = \"Vasya\""));
        out.println(logParser.execute("get user for event = \"DONE_TASK\""));
        out.println(logParser.execute("get event for date = \"03.01.2014 03:45:23\""));

        out.println(logParser.execute("get ip for user = \"Amigo\""));
        out.println(logParser.execute("get ip for date = \"30.08.2012 16:08:13\""));
        out.println(logParser.execute("get ip for event = \"DONE_TASK\""));
        out.println(logParser.execute("get ip for status = \"OK\""));
        out.println(logParser.execute("get user for ip = \"120.120.120.122\""));
        out.println(logParser.execute("get user for date = \"14.11.2015 07:08:01\""));
        out.println(logParser.execute("get user for event = \"WRITE_MESSAGE\""));
        out.println(logParser.execute("get user for status = \"ERROR\""));
        out.println(logParser.execute("get date for ip = \"146.34.15.5\""));
        out.println(logParser.execute("get date for user = \"Vasya Pupkin\""));
        out.println(logParser.execute("get date for event = \"SOLVE_TASK\""));
        out.println(logParser.execute("get date for status = \"FAILED\""));
        out.println(logParser.execute("get event for ip = \"127.0.0.1\""));
        out.println(logParser.execute("get event for user = \"Eduard Petrovich Morozko\""));
        out.println(logParser.execute("get event for date = \"03.01.2014 03:45:23\""));
        out.println(logParser.execute("get event for status = \"ERROR\""));
        out.println(logParser.execute("get status for ip = \"192.168.100.2\""));
        out.println(logParser.execute("get status for user = \"Vasya Pupkin\""));
        out.println(logParser.execute("get status for date = \"11.12.2013 10:11:12\""));
        out.println(logParser.execute("get status for event = \"SOLVE_TASK\""));

        out.println("\nexecute with date:");

        out.println(logParser.execute("get ip "
                + "for ip = \"192.168.100.2\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get ip "
                + "for user = \"Eduard Petrovich Morozko\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get ip "
                + "for date = \"12.12.2013 21:56:30\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get ip "
                + "for event = \"WRITE_MESSAGE\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get ip "
                + "for status = \"ERROR\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );

        out.println(logParser.execute("get user "
                + "for ip = \"192.168.100.2\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get user "
                + "for user = \"Eduard Petrovich Morozko\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get user "
                + "for date = \"12.12.2013 21:56:30\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get user "
                + "for event = \"WRITE_MESSAGE\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get user "
                + "for status = \"ERROR\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );

        out.println(logParser.execute("get date "
                + "for ip = \"192.168.100.2\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get date "
                + "for user = \"Eduard Petrovich Morozko\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get date "
                + "for date = \"12.12.2013 21:56:30\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get ip "
                + "for event = \"WRITE_MESSAGE\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get date "
                + "for status = \"ERROR\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );

        out.println(logParser.execute("get event "
                + "for ip = \"192.168.100.2\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get event "
                + "for user = \"Eduard Petrovich Morozko\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get event "
                + "for date = \"12.12.2013 21:56:30\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get event "
                + "for event = \"WRITE_MESSAGE\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get event "
                + "for status = \"ERROR\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );

        out.println(logParser.execute("get status "
                + "for ip = \"192.168.100.2\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get status "
                + "for user = \"Eduard Petrovich Morozko\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get status "
                + "for date = \"12.12.2013 21:56:30\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get status "
                + "for event = \"WRITE_MESSAGE\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );
        out.println(logParser.execute("get status "
                + "for status = \"ERROR\" "
                + "and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"")
        );

    }

}