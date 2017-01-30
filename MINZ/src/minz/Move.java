package minz;

class Move {
    private int row,col;
    private char symbol;
    
    public Move(int i,int j, char playerPiece){
        row = i;
        col = j;
        symbol = playerPiece;
    }
    
    public int get_row(){
        return row; 
    }
    
    public int get_col(){
        return col;
    }
    
    public char get_symbol(){
        return symbol;
    }
    
    public String toString(){
        return "(" + row + ","+col+","+symbol+")";
    }
}
