package be.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04 {
    public static void main(final String[] args) throws Exception {
        final Path path = Paths.get(Day03.class.getClassLoader().getResource("input.txt").toURI());

        final List<String> lines = new ArrayList<>();
        try (final BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line = "";
            String readLine;
            while ((readLine = br.readLine()) != null) {
                if (readLine.isEmpty()) {
                    lines.add(line);
                    line = "";
                } else {
                    line += " " + readLine;
                }
            }
            lines.add(line);
        }

        int numberThatHasRequired = 0, required = 0;
        for (final String passportData : lines) {
            boolean hasRequired = hasRequired(passportData);
            numberThatHasRequired += hasRequired ? 1 : 0;
            required += hasRequired && isValid(passportData) ? 1 : 0;
        }

        System.out.println(numberThatHasRequired);
        System.out.println(required);
    }

    static boolean hasRequired(final String passport) {
        final var requiredFields = Arrays.asList(
                "byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:");
        return requiredFields.stream().allMatch(passport::contains);
    }

    static boolean isValid(final String passport) {
        List<String> validPatterns = Arrays.asList(
                "byr:(19[2-9][0-9]|200[0-2])",
                "iyr:(201[0-9]|2020)",
                "eyr:(202[0-9]|2030)",
                "hgt:(1[5-8][0-9]|19[0-3])cm",
                "hgt:(59|6[0-9]|7[0-6])in",
                "hcl:#[0-9a-f]{6}",
                "ecl:(amb|blu|brn|gry|grn|hzl|oth)",
                "pid:[0-9]{9}",
                "cid:.*"
        );

        final String[] fields = passport.trim().split(" ");
        return Arrays
                .stream(fields)
                .allMatch(field -> validPatterns.stream().anyMatch(field::matches));
    }
}