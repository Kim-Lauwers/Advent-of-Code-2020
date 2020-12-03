package be.aoc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Day01 {

    public static void main(String[] args) throws Exception {
        final Path path = Paths.get(Day02.class.getClassLoader().getResource("input.txt").toURI());
        final List<Integer> integers = lines(path)
                .map(Integer::parseInt)
                .collect(toUnmodifiableList());

        integers.forEach(i ->
                integers.stream()
                        .filter(x -> i + x == 2020)
                        .mapToInt(x -> i * x)
                        .forEach(System.out::println));
    }
}