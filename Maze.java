import java.util.Random;

public class Maze {
    public static final char WALL = '#';
    public static final char PATH = ' ';
    public static final char EXIT = 'E';

    private final int width;
    private final int height;
    private final char[][] maze;
    private final boolean[][] visited;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        maze = new char[height][width];
        visited = new boolean[height][width];
        generateMaze(1, 1);
        maze[height - 2][width - 2] = EXIT;
    }

    private void generateMaze(int row, int col) {
        // riempi tutto di muri
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                maze[y][x] = WALL;
            }
        }
        dfs(row, col);
    }

    private void dfs(int row, int col) {
        visited[row][col] = true;
        maze[row][col] = PATH;

        int[][] directions = {
            {0, 2},   // destra
            {0, -2},  // sinistra
            {2, 0},   // giù
            {-2, 0}   // su
        };

        shuffleArray(directions);

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (isInBounds(newRow, newCol) && !visited[newRow][newCol]) {
                maze[row + dir[0] / 2][col + dir[1] / 2] = PATH;
                dfs(newRow, newCol);
            }
        }
    }

    private boolean isInBounds(int row, int col) {
        return row > 0 && row < height - 1 && col > 0 && col < width - 1;
    }

    private void shuffleArray(int[][] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int[] temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public void printMaze() {
        System.out.println();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(maze[y][x]);
            }
            System.out.println();
        }
    }

    // Metodi getter/setter per celle
    public char getCell(int row, int col) {
        return maze[row][col];
    }

    public void setCell(int row, int col, char c) {
        maze[row][col] = c;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
