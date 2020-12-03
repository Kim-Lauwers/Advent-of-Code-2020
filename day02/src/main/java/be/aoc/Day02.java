package be.aoc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Day02 {

    public static void main(String[] args) throws Exception {
        final Path path = Paths.get(Day02.class.getClassLoader().getResource("input.txt").toURI());
        Scanner scr = new Scanner(path);
        Pattern delimPattern = Pattern.compile("[\\:\\-\\s]");
        scr.useDelimiter(delimPattern);

        Pattern charPattern = Pattern.compile("\\w");

        int numValid1 = 0;
        int numValid2 = 0;

        while(scr.hasNextLine()) {

            int lowBd = scr.nextInt();
            int highBd = scr.nextInt();
            String key = scr.next(charPattern);
            String buffer = scr.next();
            String line = scr.nextLine(); // For some reason this line gets " [password]"

            // ============= PART ONE ============== //
            int count = 0;
            for(int i = 0; i < line.length(); i++) {
                if(line.charAt(i) == key.charAt(0))
                    count++;
            }

            if(count >= lowBd && count <= highBd)
                numValid1++;

            // ============= PART TWO ============== //
            // the extra space at the start of each line from the scanner allows me to use the bounds as exact indexes to check
            if(line.charAt(lowBd) == key.charAt(0) ^ line.charAt(highBd) == key.charAt(0))
                numValid2++;

        }

        System.out.println("The total number of valid passwords in part 1 is: " + numValid1);
        System.out.println("The total number of valid passwords in part 2 is: " + numValid2);


        scr.close();
    }
}