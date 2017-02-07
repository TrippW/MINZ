package minz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Node {

    private ArrayList<Node> children = new ArrayList<Node>();
    private float value;
    Board board;
    Move move;

    public Node(Board b, Move m, int depth) {
        board = b;
        move = m;
        value = 0;
        
        if (b.check_for_win()) {
            if (b.get_winner() == b.get_cur_player()) {
                value = Integer.MAX_VALUE;
            } else {
                value = Integer.MIN_VALUE;
            }
        } else if(move != null){
            int row = m.get_row(),
                col = m.get_col();
            int min_col = 0,
                min_row = 0,
                max_col = board.SIZE - 1,
                max_row = board.SIZE - 1;
            if((min_col = col -1) == -1){
                value -= 1.5f;
                min_col++;
            }
            if((max_col = col+1) == board.SIZE){
                value -= 1.5f;
                max_col--;
            }
            if((min_row = row - 1) == -1){
                value -= 1.5f;
                min_row++;
            }
            if((max_row = row - 1) == board.SIZE){
                value -= 1.5f;
                max_row--;
            }
            if(value == -3.0f) value+=.5f;
            
            for(int cur_row = min_row; cur_row < max_row; cur_row++){
                for(int cur_col = min_col; cur_col < max_col; cur_col++){
                    if(cur_row == row && cur_col == col) continue;
                    char square = board.get_square(cur_row, cur_col);
                    if(square == move.get_symbol())
                        value += 1;
                    else if(square == ' ') 
                        value += 0.5f;
                    else
                        value -= 1.5;
                }
            }
            value *= -1;
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
        return (int) value;
    }

    public ArrayList<Node> get_children() {
        return children;
    }

}
