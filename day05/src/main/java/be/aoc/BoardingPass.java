package be.aoc;

public final class BoardingPass {
        private final String pathToSeat;

        public BoardingPass(final String pathToSeat) {
            this.pathToSeat = pathToSeat;
        }

        public final Seat getSeat() {
            return new Seat(
                    getRow(pathToSeat.substring(0, 7)),
                    getColumn(pathToSeat.substring(7, 10))
            );
        }

        private int getRow(final String rowPath) {
            return calculatePath(rowPath, 0, 127, 'F', 'B');
        }

        private int getColumn(final String columnPath) {
            return calculatePath(columnPath, 0, 7, 'L', 'R');
        }

        private int calculatePath(final String path,final  int lowerBound,final int upperBound,final char lowerInstruction, final char upperInstruction) {
            SpacePartition partition = new SpacePartition(lowerBound, upperBound);

            char[] chars = path.toCharArray();
            for (char c : chars) {
                if (c == lowerInstruction) {
                    partition = partition.takeLowerHalf();
                } else if (c == upperInstruction) {
                    partition = partition.takeUpperHalf();
                }
            }

            return partition.middle();
        }
    }
