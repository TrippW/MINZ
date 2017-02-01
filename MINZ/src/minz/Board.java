package minz;

public class Board {

    public final int SIZE;
    private char board[][];
    private char cur_player;
    private char winner = ' ';
    private int M;

    public Board(int size, int M) {
        SIZE = size;
        this.M = M;
        board = new char[size][size];
        init();
        cur_player = MINZ.PLAYER1;
    }

    public Board(Board b) {
        SIZE = b.SIZE;
        M = b.get_win_len();
        cur_player = b.get_cur_player();
        board = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = b.get_square(i, j);
            }
        }
    }

    public int get_win_len() {
        return M;
    }

    public char get_winner() {
        return winner;
    }

    public char get_cur_player() {
        return cur_player;
    }

    public void play(Move m) {
        if (get_square(m.get_row(), m.get_col()) == ' ') {
            board[m.get_row()][m.get_col()] = m.get_symbol();
            if (cur_player == MINZ.PLAYER1) {
                cur_player = MINZ.PLAYER2;
            } else {
                cur_player = MINZ.PLAYER1;
            }
        }
    }

    private void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void print() {
        for (int k = 0; k < SIZE * 4; k++) {
            System.out.print('-');
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            for (int k = 0; k < SIZE * 4; k++) {
                System.out.print('-');
            }
            System.out.println();
        }
    }

    public char get_square(int row, int col) {
        return board[row][col];
    }

    public boolean check_for_win() {
        boolean k = (check_rows_for_win() || check_cols_for_win() || check_diagonal_for_win());
        return k;
    }

    private boolean check_rows_for_win() {
        for (int i = 0; i < SIZE; i++) {
            int count = 0;
            char cur_symbol = ' ';
            for (int j = 0; j < SIZE; j++) {
                char square = get_square(i, j);
                if (square != ' ') {
                    if (square == cur_symbol) {
                        if (++count >= M) {
                            winner = cur_symbol;
                            return true;
                        }
                    } else {
                        count = 1;
                        cur_symbol = square;
                    }
                } else {
                    cur_symbol = ' ';
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean check_cols_for_win() {
        for (int j = 0; j < SIZE; j++) {
            int count = 0;
            char cur_symbol = ' ';
            for (int i = 0; i < SIZE; i++) {
                char square = get_square(i, j);
                if (square != ' ') {
                    if (square == cur_symbol) {
                        if (++count >= M) {
                            winner = cur_symbol;
                            return true;
                        }
                    } else {
                        count = 1;
                        cur_symbol = square;
                    }
                } else {
                    cur_symbol = ' ';
                    count = 0;
                }
            }
        }
        return false;
    }

    //Reprogram so that it does all checks in the same loop
    private boolean check_diagonal_for_win() {
        //check left to right diagonals
        for (int i = 0; i <= SIZE - M; i++) {
            int count1 = 0, count2 = 0, count3 = 0, count4 = 0;
            char cur_symbol1 = ' ', cur_symbol2 = ' ', cur_symbol3 = ' ', cur_symbol4 = ' ';
            for (int j = 0; j < SIZE - i; j++) {
                char square1 = get_square(i + j, j);
                char square2 = get_square(j, i + j);
                char square3 = get_square(i + j, SIZE - j - 1);
                char square4 = get_square(j, SIZE - i - j - 1);
                if (square1 != ' ') {
                    if (cur_symbol1 == square1) {
                        if (++count1 >= M) {
                            winner = cur_symbol1;
                            return true;
                        }
                    } else {
                        count1 = 1;
                        cur_symbol1 = square1;
                    }
                } else {
                    count1 = 0;
                }
                if (square2 != ' ') {
                    if (cur_symbol2 == square2) {
                        if (++count2 >= M) {
                            winner = cur_symbol2;
                            return true;
                        }
                    } else {
                        count2 = 1;
                        cur_symbol2 = square2;
                    }
                } else {
                    count2 = 0;
                }
                if (square3 != ' ') {
                    if (cur_symbol3 == square3) {
                        if (++count3 >= M) {
                            winner = cur_symbol3;
                            return true;
                        }
                    } else {
                        count3 = 1;
                        cur_symbol3 = square3;
                    }
                } else {
                    count3 = 0;
                }
                if (square4 != ' ') {
                    if (cur_symbol4 == square4) {
                        if (++count4 >= M) {
                            winner = cur_symbol4;
                            return true;
                        }
                    } else {
                        count4 = 1;
                        cur_symbol4 = square4;
                    }
                } else {
                    count4 = 0;
                }

            }
        }

        return false;
    }
}
