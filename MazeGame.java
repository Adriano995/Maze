import java.util.Scanner;

public class MazeGame {
    public static void main(String[] args) {
        final int WIDTH = 21;
        final int HEIGHT = 21;

        Maze maze = new Maze(HEIGHT, WIDTH);

        // Trova prima cella libera (PATH) per posizionare il player
        int playerRow = 1;
        int playerCol = 1;
        outer:
        for (int r = 1; r < HEIGHT - 1; r++) {
            for (int c = 1; c < WIDTH - 1; c++) {
                if (maze.getCell(r, c) == Maze.PATH) {
                    playerRow = r;
                    playerCol = c;
                    break outer;
                }
            }
        }

        Player player = new Player(playerRow, playerCol);
        maze.setCell(playerRow, playerCol, Player.PLAYER);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            maze.printMaze();
            System.out.print("Muoviti (WASD): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;
            char move = input.charAt(0);

            if (player.move(move, maze)) {
                if (player.getRow() == HEIGHT - 2 && player.getCol() == WIDTH - 2) {
                    System.out.println("Hai raggiunto l'uscita!");
                    break;
                }
            }
        }
        scanner.close();
    }
}
