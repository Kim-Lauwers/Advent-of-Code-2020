package be.aoc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import static java.nio.file.Files.lines;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Day05 {
    public static void main(final String[] args) throws Exception {
        final Path path = Paths.get(Day05.class.getClassLoader().getResource("input.txt").toURI());

        var boardingPasses = lines(path)
                .map(BoardingPass::new)
                .collect(toUnmodifiableList());

        System.out.printf("Highest Seat ID is %d.%n", findHighestSeat(boardingPasses).getId());
        System.out.printf("My Seat ID is %d.%n", findMySeatId(boardingPasses));
    }

    private static Seat findHighestSeat(final Collection<BoardingPass> boardingPasses) {
        return boardingPasses
                .stream()
                .map(BoardingPass::getSeat)
                .max(comparingInt(Seat::getId))
                .orElseThrow(() -> new RuntimeException("Problem, could not find highest Seat ID"));
    }

    private static Integer findMySeatId(final Collection<BoardingPass> boardingPasses) {
        List<Seat> orderedSeats = boardingPasses
                .stream()
                .map(BoardingPass::getSeat)
                .sorted(comparingInt(Seat::getId))
                .collect(toUnmodifiableList());

        for (int i = 0; i + 3 < orderedSeats.size(); i += 3) {
            Seat first = orderedSeats.get(i), middle = orderedSeats.get(i + 1), last = orderedSeats.get(i + 2);

            final boolean lower = middle.getId() - first.getId() > 1;
            final boolean upper = last.getId() - middle.getId() > 1;
            if (lower || upper) {
                return lower ? first.getId() + 1 : middle.getId() + 1;
            }
        }

        throw new RuntimeException("Problem, you fucked up Kim");
    }
}