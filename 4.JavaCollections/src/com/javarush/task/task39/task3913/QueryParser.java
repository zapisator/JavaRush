package com.javarush.task.task39.task3913;

import static com.javarush.task.task39.task3913.DataType.DATE;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.util.Pair;

public class QueryParser {

    private final String outputType;
    private final List<Pair<String, String>> parameters = new LinkedList<>();

    private QueryParser(String outputType) {
        this.outputType = outputType;
    }

    public static QueryParser parse(String query) {
        final String outputType = outputType(query);
        final QueryParser parser = new QueryParser(outputType);

        parser.addParametersIfPresent(query);
        return parser;
    }

    private void addParametersIfPresent(String query) {
        final Matcher parameterMatcher = Pattern.compile("(?<=for )[a-zA-Z]+").matcher(query);

        if (parameterMatcher.find()) {
            final String parameterType = parameterMatcher.group().toUpperCase(Locale.ROOT);
            final List<String> values = values(query);

            parameters.add(new Pair<>(parameterType, values.get(0)));
            for (int i = 1; i < values.size(); i++) {
                parameters.add(new Pair<>(DATE.toString(), values.get(i)));
            }
        }
    }

    private List<String> values(String query) {
        final Matcher matcher = Pattern.compile("(\\\".*?\\\")").matcher(query);
        final List<String> values = new LinkedList<>();

        while (matcher.find()) {
            values.add(matcher.group().replace("\"", ""));
        }
        return values;
    }

    private static String outputType(String query) {
        final Matcher matcher = Pattern.compile("(?<=get )[a-zA-Z]+")
                .matcher(query);

        if (matcher.find()) {
            return matcher.group().toUpperCase(Locale.ROOT);
        }
        return "";
    }

    public String getOutputType() {
        return outputType;
    }

    public List<Pair<String, String>> getParameters() {
        return parameters;
    }
}
