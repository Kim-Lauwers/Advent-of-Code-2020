package be.aoc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;
import static java.nio.file.Files.readString;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableList;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class Day06 {
    public static void main(final String[] args) throws Exception {
        final Path path = Paths.get(Day06.class.getClassLoader().getResource("input.txt").toURI());

        final String[] input = readString(path).split("\n\n");
        System.out.println("answer A: " + process(input, Day06::processGroup));
        System.out.println("answer B: " + process(input, Day06::processGroupAllPeopleAnsweredQuestionYes));
    }

    private static int process(final String[] input, final Function<String, Integer> process) {
        final Stream<String> stream = stream( input);
        return stream
                .map(process)
                .mapToInt(i -> i)
                .sum();
    }

    private static int processGroup(final String groupAnswers) {
        return groupAnswers.replace("\n", "")
                .chars()
                .mapToObj(c -> (char) c)
                .collect(toUnmodifiableSet())
                .size();
    }

    private static int processGroupAllPeopleAnsweredQuestionYes(String groupAnswers) {
        String[] answers = groupAnswers.split("\n");
        return (int) stream(answers)
                .map(answer ->
                        answer.chars().mapToObj(c -> (char) c)
                                .collect(toUnmodifiableSet()))
                .collect(toUnmodifiableList())
                .stream().flatMap(Collection::stream)
                .collect(groupingBy(c -> c))
                .values().stream()
                .filter(list -> list.size() == answers.length)
                .count();
    }
}