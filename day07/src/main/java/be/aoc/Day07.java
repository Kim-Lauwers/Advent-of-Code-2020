package be.aoc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Files.readString;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableList;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class Day07 {
    public static void main(final String[] args) throws Exception {
        System.out.println(day7_1());
    }

    static int day7_1() throws IOException, URISyntaxException {
        String shinyGoldPattern = "(.*)shiny gold(.*)";
        final Path path = Paths.get(Day07.class.getClassLoader().getResource("input.txt").toURI());
        Collection<String> bagRules = readAllLines(path);

        Set<String> patterns = new HashSet();
        patterns.add(shinyGoldPattern);

        int bagCount = 0;
        while(bagCount < patterns.size()) {
            bagCount = patterns.size();
            Set<String> newRules = new HashSet<String>();
            for(String pattern : patterns) {
                for(String bagRule : bagRules) {
                    if( bagRule.split(" ", 3)[2].matches(pattern) ) {
                        String bagPattern = "(.*)" + bagRule.split(" ")[0] + " " + bagRule.split(" ")[1] + "(.*)";
                        newRules.add(bagPattern);
                    }
                }
            }
            patterns.addAll(newRules);
        }

        return patterns.size() - 1; // Golden start pattern
    }
}