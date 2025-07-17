public class Player {
    public static final char PLAYER = 'P';

    private int row;
    private int col;

    public Player(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Muovi il player e ritorna true se il movimento è valido
    public boolean move(char direction, Maze maze) {
        int newRow = row;
        int newCol = col;

        switch (Character.toUpperCase(direction)) {
            case 'W': newRow--; break;
            case 'S': newRow++; break;
            case 'A': newCol--; break;
            case 'D': newCol++; break;
            default: return false;
        }

        // Controlla che la nuova posizione sia valida
        if (newRow >= 0 && newRow < maze.getHeight() &&
            newCol >= 0 && newCol < maze.getWidth()) {
            char targetCell = maze.getCell(newRow, newCol);
            if (targetCell == Maze.PATH || targetCell == Maze.EXIT) {
                // Aggiorna la posizione sulla matrice
                maze.setCell(row, col, Maze.PATH); // vecchia posizione diventa PATH
                row = newRow;
                col = newCol;
                maze.setCell(row, col, PLAYER);    // nuova posizione diventa PLAYER
                return true;
            }
        }
        return false;
    }
}
