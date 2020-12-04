package be.aoc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Day01Part2 {

    public static void main(String[] args) throws Exception {
        final Path path = Paths.get(Day01Part2.class.getClassLoader().getResource("input.txt").toURI());
        final List<Integer> integers = lines(path)
                .map(Integer::parseInt)
                .collect(toUnmodifiableList());

        integers.forEach(i ->
                integers.forEach(x ->
                        integers.stream()
                                .filter(y -> i + x + y == 2020)
                                .distinct()
                                .mapToInt(w -> i * x * w)
                                .forEach(System.out::println)));
    }
}