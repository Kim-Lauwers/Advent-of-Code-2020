package be.aoc;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.stream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.lines;
import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Day09 {
    public static void main(String[] args) throws Exception {
        final Path path = Paths.get(Day09.class.getClassLoader().getResource("input.txt").toURI());
        final List<Long> numbers = lines(path)
                .map(Long::parseLong)
                .collect(toUnmodifiableList());

        System.out.println("part1 " + part1(numbers));
        System.out.println("part1 " + part2(numbers, part1(numbers)));
    }

    public static long part1(final List<Long> numbers) {
        for (int k = 0; k < numbers.size() - 25; k++) {
            final Set<Long> sums = new HashSet<>();
            for (int i = k; i < k + 25; i++) {
                for (int j = i + 1; j < k + 25; j++) {
                    sums.add(numbers.get(i) + numbers.get(j));
                }
            }
            if (!sums.contains(numbers.get(k + 25))) {
                return numbers.get(k + 25);
            }
        }
        return 0;
    }

    public static long part2(final List<Long> numbers, Long invalidNumber) {
        long[] array = numbers.stream().mapToLong(x -> x).toArray();
        for(int i = 2; i< numbers.size(); i++){
            for(int j = 0; j<= numbers.size()-i; j++){
                if(stream(array, j, j + i).sum() == invalidNumber){
                    long[] window = copyOfRange(array, j, j+i+1);
                    return stream(window).max().getAsLong() + stream(window).min().getAsLong();
                }
            }
        }
        return 0;
    }
}