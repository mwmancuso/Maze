/**
 * Created by Matt Mancuso on 2/6/2015.
 *
 * Handles calls for mazes.
 */
public class Handler {
    public static void main(String[] args) {
        Builder builder = new Builder();

        Maze maze = builder.setSize(25,25).setDifficulty(Maze.Difficulty.EXTREME).saveCorrectPath(true).build();

        String print = maze.getAsciiMaze("#", ".");

        System.out.print(print);
    }
}
