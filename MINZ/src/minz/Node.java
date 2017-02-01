package minz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Node {

    private ArrayList<Node> children = new ArrayList<Node>();
    private int value;
    Board board;
    Move move;

    public Node(Board b, Move m, int depth) {
        board = b;
        move = m;
        if (b.check_for_win()) {
            if (b.get_winner() == b.get_cur_player()) {
                value = 1;
            } else {
                value = -1;
            }
        } else {
            value = 0;
        }

        fill_children(depth);
    }

    public void fill_children(int depth) {
        if (children.size() == 0) {
            for (int i = 0; i < board.SIZE; i++) {
                for (int j = 0; j < board.SIZE; j++) {
                    if (board.get_square(i, j) == ' ') {
                        Board next_frame = new Board(board);
                        Move next_move = new Move(i, j, board.get_cur_player());
                        next_frame.play(next_move);
                        if (depth > 0) {
                            children.add(new Node(next_frame, next_move, depth - 1));
                        }
                    }
                }
                Collections.sort(children, new Comparator<Node>(){

                    @Override
                    public int compare(Node n1, Node n2) {
                        return n1.get_value() - n2.get_value();
                    }
                    
                });
            }
        } else {
            for(Node child : children){
                child.fill_children(depth);
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
