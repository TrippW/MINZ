package minz;

import java.util.ArrayList;


public class Node {

    private ArrayList<Node> children = new ArrayList<Node>();
    private int value;
    Board board;
    Move move;

    public Node(Board b, Move m) {
        board = b;
        move = m;
     //   b.print();
        if(b.check_for_win()){
            if(b.get_winner() == b.get_cur_player())
                value = 1;
            else
                value = -1;
        } else
            value = 0;
        
        for (int i = 0; i < b.SIZE; i++) {
            for (int j = 0; j < b.SIZE; j++) {
                if(b.get_square(i, j) == ' '){
                    Board next_frame = new Board(b);
                    Move next_move = new Move(i,j, b.get_cur_player());
                    next_frame.play(next_move);
                    children.add(new Node(next_frame, next_move));
                }
            }
        }
    }

    public int get_value() {
        return value;
    }

    public ArrayList<Node> get_children() {
        return children;
    }

}
