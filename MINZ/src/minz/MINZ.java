package minz;

import static minz.Algorithm.alphabeta;

public class MINZ {

    public static void main(String[] args) {
        MINZ game = new MINZ(3, 4);
        game.print();
        int i = 0;
        while (!game.board.check_for_win() && !game.cur_node.get_children().isEmpty()) {
            game.play(i++ % 2);

        }
    }

    public void play(int i) {
        cur_node = alphabeta(cur_node, 4);// + (2 * i));
        board.play(cur_node.move);
        cur_node.fill_children(1);
        print();
    }

    private Board board;
    private Node cur_node;

    public static final char PLAYER1 = 'x',
            PLAYER2 = 'o';

    public MINZ(int m, int z) {
        board = new Board(z, m);
        cur_node = new Node(board, null,5);//new Move(z/2,z/2,PLAYER1),3);
    }

    public void print() {
        board.print();
    }
}
