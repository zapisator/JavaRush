package com.javarush.task.task39.task3913;

import static com.javarush.task.task39.task3913.Event.DONE_TASK;
import static com.javarush.task.task39.task3913.Event.DOWNLOAD_PLUGIN;
import static com.javarush.task.task39.task3913.Event.SOLVE_TASK;
import static com.javarush.task.task39.task3913.Event.WRITE_MESSAGE;
import static com.javarush.task.task39.task3913.DataType.DATE;
import static com.javarush.task.task39.task3913.DataType.EVENT;
import static com.javarush.task.task39.task3913.DataType.IP;
import static com.javarush.task.task39.task3913.DataType.STATUS;
import static com.javarush.task.task39.task3913.DataType.USER;
import static com.javarush.task.task39.task3913.Status.ERROR;
import static com.javarush.task.task39.task3913.Status.FAILED;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import com.javarush.task.task39.task3913.LogData.DateData;
import com.javarush.task.task39.task3913.LogData.EventData;
import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.EventQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.QLQuery;
import com.javarush.task.task39.task3913.query.UserQuery;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.util.Pair;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {

    private final Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return ipsStreamByFilter(after, before, logData -> true)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return ipsStreamByFilter(after, before, logData -> logData.getUser().equals(user))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return ipsStreamByFilter(after, before, logData -> toEvent(logData) == event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return ipsStreamByFilter(after, before, logData -> logData.getStatus() == status)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllUsers() {
        return usersStreamByFilter(null, null, logData -> true)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return usersStreamByFilter(after, before, logData -> true)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return logDataStreamByFilter(after, before, logData -> logData.getUser().equals(user))
                .map(LogData::getEventData)
                .map(EventData::getEvent)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return usersStreamByFilter(after, before, logData -> logData.getIp().equals(ip)
        )
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return usersStreamByFilter(after, before, logData
                -> toEvent(logData) == DOWNLOAD_PLUGIN
        )
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return usersStreamByFilter(after, before,
                logData -> toEvent(logData) == DOWNLOAD_PLUGIN
        )
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return usersStreamByFilter(after, before,
                logData -> toEvent(logData) == WRITE_MESSAGE
        )
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return usersStreamByFilter(after, before,
                logData -> toEvent(logData) == SOLVE_TASK
        )
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return usersStreamByFilter(after, before, logData
                -> (toEvent(logData) == SOLVE_TASK)
                && (logData.getEventData().getNumber() == task)
        )
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return usersStreamByFilter(after, before, logData
                -> toEvent(logData) == DONE_TASK
        )
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return usersStreamByFilter(after, before, logData
                -> (toEvent(logData) == DONE_TASK)
                && (logData.getEventData().getNumber() == task)
        )
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return dateStreamByFilter(after, before, logData
                -> logData.getUser().equals(user)
                && toEvent(logData).equals(event)
        )
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return dateStreamByFilter(after, before, logData -> logData.getStatus().equals(FAILED))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return dateStreamByFilter(after, before, logData -> logData.getStatus().equals(ERROR))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return dateStreamByFilter(after, before, logData
                -> toEvent(logData).equals(WRITE_MESSAGE)
                && logData.getUser().equals(user))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return dateStreamByFilter(after, before, logData
                -> toEvent(logData).equals(DOWNLOAD_PLUGIN)
                && logData.getUser().equals(user))
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return earliestDate(after, before, logData
                -> toEvent(logData).equals(Event.LOGIN)
                && logData.getUser().equals(user));
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return earliestDate(after, before, logData
                -> toEvent(logData).equals(SOLVE_TASK)
                && logData.getUser().equals(user)
                && logData.getEventData().getNumber() == task);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return earliestDate(after, before, logData
                -> toEvent(logData).equals(DONE_TASK)
                && logData.getUser().equals(user)
                && logData.getEventData().getNumber() == task);
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return logDataStreamByFilter(after, before, logData -> true)
                .map(this::toEvent)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return logDataStreamByFilter(after, before, logData -> true)
                .map(this::toEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return logDataStreamByFilter(after, before, logData -> logData.getIp().equals(ip))
                .map(this::toEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return logDataStreamByFilter(after, before, logData -> logData.getUser().equals(user))
                .map(this::toEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return logDataStreamByFilter(after, before, logData -> logData.getStatus().equals(FAILED))
                .map(this::toEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return logDataStreamByFilter(after, before, logData -> logData.getStatus().equals(ERROR))
                .map(this::toEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) logDataStreamByFilter(after, before, logData
                -> toEvent(logData).equals(SOLVE_TASK)
                && toTask(logData) == task
        )
                .map(LogData::getEventData)
                .map(EventData::getEvent)
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) logDataStreamByFilter(after, before, logData
                -> toEvent(logData).equals(DONE_TASK)
                && (toTask(logData) == task)
        )
                .map(LogData::getEventData)
                .map(EventData::getEvent)
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return logDataStreamByFilter(after, before,
                logData -> toEvent(logData).equals(SOLVE_TASK)
        )
                .collect(groupingBy(this::toTask, summingInt(logData -> 1)));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return logDataStreamByFilter(after, before,
                logData -> toEvent(logData).equals(DONE_TASK))
                .collect(groupingBy(this::toTask, summingInt(logData -> 1)));
    }

    @Override
    public Set<?> execute(String query) {
        final QueryParser parser = QueryParser.parse(query);
        final String mapperObject = parser.getOutputType();
        final Predicate<LogData> filter = filter(parser.getParameters());
        final Function<LogData, Object> extractor = extractor(mapperObject);

        return logData().stream()
                .filter(filter)
                .map(extractor)
                .collect(Collectors.toSet());
    }

    private Predicate<LogData> paremeterFilter(List<Pair<String, String>> parameters) {
        final String type = parameters.get(0).getKey();
        final String value = parameters.get(0).getValue();

        return logData -> extractor(type)
                .apply(logData)
                .equals(creator(type).apply(value));
    }

    private Predicate<LogData> filter(List<Pair<String, String>> parameters) {
        if (parameters.isEmpty()) {
            return logData -> true;
        }
        final Predicate<LogData> parameterFilter = paremeterFilter(parameters);

        if (parameters.size() > 1) {
            final Date after = DateData.create(parameters.get(1).getValue()).getDate();
            final Date before = DateData.create(parameters.get(2).getValue()).getDate();

            return dateFilterNotIncluding(after, before).and(parameterFilter);
        }
        return parameterFilter;
    }

    private Function<String, Object> creator(String type) {
        final Function<String, Object>[] creators = new Function[DataType.values().length];

        creators[IP.ordinal()] = string -> string;
        creators[USER.ordinal()] = string -> string;
        creators[DATE.ordinal()] = string -> DateData.create(string).getDate();
        creators[EVENT.ordinal()] = string -> EventData.create(string).getEvent();
        creators[STATUS.ordinal()] = Status::valueOf;
        return creators[DataType.valueOf(type.toUpperCase(Locale.ROOT)).ordinal()];
    }


    private Function<LogData, Object> extractor(String name) {
        final Function<LogData, Object>[] extractors = new Function[DataType.values().length];

        extractors[IP.ordinal()] = this::toIp;
        extractors[USER.ordinal()] = this::toUser;
        extractors[DATE.ordinal()] = this::toDate;
        extractors[EVENT.ordinal()] = this::toEvent;
        extractors[STATUS.ordinal()] = this::toStatus;
        return extractors[DataType.valueOf(name.toUpperCase(Locale.ROOT)).ordinal()];
    }

    private String toIp(LogData logData) {
        return logData.getIp();
    }

    private String toUser(LogData logData) {
        return logData.getUser();
    }

    private Date toDate(LogData logData) {
        return logData.getDateData().getDate();
    }

    private Event toEvent(LogData logData) {
        return logData.getEventData().getEvent();
    }

    private int toTask(LogData logData) {
        return logData.getEventData().getNumber();
    }

    private Status toStatus(LogData logData) {
        return logData.getStatus();
    }

    private Stream<String> ipsStreamByFilter(Date after, Date before, Predicate<LogData> filter) {
        return logDataStreamByFilter(after, before, filter)
                .map(LogData::getIp);
    }

    private Stream<String> usersStreamByFilter(Date after, Date before, Predicate<LogData> filter) {
        return logDataStreamByFilter(after, before, filter)
                .map(LogData::getUser);
    }

    private Stream<Date> dateStreamByFilter(Date after, Date before, Predicate<LogData> filter) {
        return logDataStreamByFilter(after, before, filter)
                .map(this::toDate);
    }

    private Date earliestDate(Date after, Date before, Predicate<LogData> filter) {
        return dateStreamByFilter(after, before, filter)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    private Stream<LogData> logDataStreamByFilter(Date after, Date before,
            Predicate<LogData> filter) {
        return logData().stream()
                .filter(logData -> dateFilter(after, before).and(filter).test(logData));
    }

    private Predicate<LogData> dateFilterNotIncluding(Date after, Date before) {
        if (after == null && before == null) {
            return (logData -> true);
        }

        final Predicate<LogData> filterBefore = (logData -> toDate(logData).before(before));
        final Predicate<LogData> filterAfter = (logData -> toDate(logData).after(after));

        if (after == null) {
            return filterBefore;
        }
        if (before == null) {
            return filterAfter;
        }
        return (logData -> filterAfter.test(logData) && filterBefore.test(logData));
    }

    private Predicate<LogData> dateFilter(Date after, Date before) {
        if (after == null && before == null) {
            return (logData -> true);
        }

        final Predicate<LogData> filterBefore = (logData
                -> toDate(logData).before(before) || toDate(logData).equals(before));
        final Predicate<LogData> filterAfter = (logData
                -> toDate(logData).after(after) || toDate(logData).equals(after));

        if (after == null) {
            return filterBefore;
        }
        if (before == null) {
            return filterAfter;
        }
        return (logData -> filterAfter.test(logData) && filterBefore.test(logData));
    }

    private List<LogData> logData() {
        final LogVisitor visitor = new LogVisitor();

        try {
            Files.walkFileTree(logDir, visitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return visitor.getLogData();
    }

    static class LogVisitor extends SimpleFileVisitor<Path> {

        final List<LogData> logData = new LinkedList<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String fileName = file.getFileName().toString();

            if (attrs.isRegularFile() && fileName.endsWith(".log")) {
                try (final Stream<String> lines = Files.lines(file)) {
                    lines.map(line -> line.split("\t"))
                            .map(LogData::create)
                            .forEach(logData::add);
                }
            }
            return FileVisitResult.CONTINUE;
        }

        public List<LogData> getLogData() {
            return logData;
        }
    }

}