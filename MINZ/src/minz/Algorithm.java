package minz;

public class Algorithm {
    private static Node best_node;
    public static Node alphabeta(Node node, int depth) {
        alphabeta(node, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return best_node;
    }

    private static int alphabeta(Node node, int depth, int a, int b, boolean is_maximizing) {
        if (depth == 0 || node.get_children().isEmpty()) {
            return node.get_value(); //value?
        }
        if (is_maximizing) {
            int n = 0;
            for (Node child : node.get_children()) {
                n = alphabeta(child, depth - 1, a, b, false);
                if(a < n){
                    a = n;
                    best_node = child;
                }
                //a = Math.max(a, v);
                if (b <= a) {
                    break; //cut off
                }
            }
            return n;
        } else {
            int n = 0;
            for (Node child : node.get_children()) {
                n = alphabeta(child, depth - 1, a, b, false);
                if(b > n){
                    b = n;
                    //best_node = child;
                }
                if (b <= a) {
                    break; //cut off
                }
            }
            return n;
        }
    }
}
