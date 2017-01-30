package minz;

import static minz.Algorithm.alphabeta;

public class MINZ {

    
    public static void main(String[] args) {
        MINZ game = new MINZ(3, 4);
        game.print();
        while(!game.board.check_for_win() && !game.cur_node.get_children().isEmpty()){
            game.cur_node = alphabeta(game.cur_node, 5);
            game.board.play(game.cur_node.move);
            game.print();
        }
    }

    private Board board;
    private Node cur_node;
    
    public static final char PLAYER1 = 'x',
            PLAYER2 = 'o';

    public MINZ(int m, int z) {
        board = new Board(z, m);
        cur_node = new Node(board, null);
    }

    public void print() {
        board.print();
    }
    
    

}
