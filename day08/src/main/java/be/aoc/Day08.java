package be.aoc;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static java.nio.file.Files.lines;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Day08 {

    public static final String ACCUMULATOR_INSTRUCTION = "acc";
    public static final String JUMP_INSTRUCTION = "jmp";
    public static final String NO_OPERATION = "nop";

    public static void main(String[] args) throws Exception {
        final Path path = Paths.get(Day08.class.getClassLoader().getResource("input.txt").toURI());
        final List<Instruction> instructions = lines(path)
                .map(Instruction::toInstruction)
                .collect(toUnmodifiableList());

        System.out.println("Value of accumulator: " + solveOne(null, instructions));
        System.out.println("Value of accumulator: " + solveTwo(instructions));
    }

    static long solveOne(final UUID currentUUID, final List<Instruction> instructions) {
        final Set<Instruction> executedInstructions = new HashSet<>();

        int accumulator = 0;
        for (int instructionCount = 0; instructionCount < instructions.size(); instructionCount++) {
            Instruction instruction = instructions.get(instructionCount);

            if (executedInstructions.contains(instruction)) {
                return accumulator;
            }

            if (ACCUMULATOR_INSTRUCTION.equals(instruction.getOperation(currentUUID))) {
                accumulator += instruction.getArgument();
            } else if (JUMP_INSTRUCTION.equals(instruction.getOperation(currentUUID))) {
                instructionCount += instruction.getArgument() - 1;
            }

            executedInstructions.add(instruction);

        }
        return accumulator;
    }

    static long solveTwo(final List<Instruction> instructions) {
        for (Instruction currentInstruction : instructions) {
            if (checkForInfiniteLoops(currentInstruction, instructions)) {
                return solveOne(currentInstruction.id, instructions);
            }
        }

        return 0;
    }

    static boolean checkForInfiniteLoops(final Instruction currentInstruction, final List<Instruction> instructions) {
        Set<Instruction> executedInstructions = new HashSet<>();
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (executedInstructions.contains(instruction)) {
                return false;
            }
            if (JUMP_INSTRUCTION.equals(instruction.getOperation(currentInstruction.id))) {
                i += instruction.getArgument() - 1;
            }
            executedInstructions.add(instruction);
        }
        return true;
    }

    private static class Instruction {
        private final UUID id;
        private final String operation;
        private final int argument;

        Instruction(final String operation, int argument) {
            this.id = randomUUID();
            this.operation = operation;
            this.argument = argument;
        }

        static Instruction toInstruction(final String instruction) {
            final String[] splitted = instruction.split("\\s+");
            return new Instruction(splitted[0], Integer.parseInt(splitted[1]));
        }

        public String getOperation(final UUID id) {
            if (this.id.equals(id) && !ACCUMULATOR_INSTRUCTION.equals(operation)) {
                return JUMP_INSTRUCTION.equalsIgnoreCase(operation) ? NO_OPERATION : JUMP_INSTRUCTION;
            }
            return operation;
        }

        public int getArgument() {
            return argument;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Instruction that = (Instruction) o;
            return id.equals(that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}