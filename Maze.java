import java.util.Arrays;
import java.util.Random;

/**
 * Created by Matt Mancuso on 1/29/2015.
 *
 * Maze class is the container for everything having to do with the actual maze.
 * Contains enum for difficulty assignment and numeric extraction (difficulty must
 * be between 1 and 10). No variable setters because Maze is  simply a static entity.
 */
public class Maze {
    public static enum Difficulty {
        EASY(1),
        MEDIUM(5),
        HARD(8),
        EXTREME(10);

        private int difficulty;

        Difficulty(int difficulty) {
            this.difficulty = difficulty;
        }

        public int getValue() {
            return difficulty;
        }
    }

    long seed = new Random().nextLong();
    Random random;
    int difficulty = Difficulty.MEDIUM.getValue();
    int sizeX = 16;
    int sizeY = 16;
    Integer[] startingCoord;
    Layout[][] layout;
    Integer[][] correctPath;

    public String getAsciiMaze(String printChar, String correctChar) {
        String[][] strings;
        StringBuffer buffer = new StringBuffer();
        int stringsX;
        int stringsY;

        strings = new String[sizeY*2+1][sizeX*2+1];

        for (int i=0; i<strings.length; i++) {
            Arrays.fill(strings[i], " ");
        }

        strings[0][0] = printChar;

        for (int row=0; row<sizeY; row++) {
            for (int col=0; col<sizeX; col++) {
                stringsX = 1 + col*2;
                stringsY = 1 + row*2;

                if (row == 0) {
                    strings[0][stringsX+1] = printChar;
                    if (layout[row][col].borderIs(Layout.Border.TOP, Layout.Type.WALL)) {
                        strings[0][stringsX] = printChar;
                    }
                }
                if (col == 0) {
                    strings[stringsY+1][0] = printChar;
                    if (layout[row][col].borderIs(Layout.Border.LEFT, Layout.Type.WALL)) {
                        strings[stringsY][0] = printChar;
                    }
                }

                strings[stringsY+1][stringsX+1] = printChar;
                if (layout[row][col].borderIs(Layout.Border.BOTTOM, Layout.Type.WALL)) {
                    strings[stringsY+1][stringsX] = printChar;
                }
                if (layout[row][col].borderIs(Layout.Border.RIGHT, Layout.Type.WALL)) {
                    strings[stringsY][stringsX+1] = printChar;
                }
            }
        }

        if (correctChar != null && correctPath != null) {
            for (Integer[] coord : correctPath) {
                strings[1 + coord[1] * 2][1 + coord[0] * 2] = correctChar;
            }
        }

        for (String[] row : strings) {
            for (String col : row) {
                buffer.append(col);
            }
            buffer.append("\n");
        }

        return buffer.toString();
    }

    public String getAsciiMaze(String printChar) {
        return getAsciiMaze(printChar, null);
    }

    public String getAsciiMaze() {
        return getAsciiMaze("#");
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int[] getSize() {
        return new int[] {sizeX, sizeY};
    }

    public Integer[] getStartingCoord() {
        return startingCoord;
    }

    public long getSeed() {
        return seed;
    }
}