package minz;

public class Algorithm {
    public static Node alphabeta(Node node, int depth) {
        Node best_node = alphabeta(node, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        
        return best_node;
    }

    private static Node alphabeta(Node node, int depth, int a, int b, boolean is_maximizing) {
        //if(node.move != null)System.out.printf("%3d:%s\t%d%n", depth, node.move.toString(),node.get_value());
        if (depth == 0 || node.get_children().isEmpty()) {
            return node; //value?
        }
        Node n, v = null;

        if (is_maximizing) {
            for (Node child : node.get_children()) {
                n = alphabeta(child, depth - 1, a, b, false);
                if(n == null) n = child;
                if(a < n.get_value()){
                    a = n.get_value();
                    v = child;
                }
                //a = Math.max(a, v);
                if (b <= a) {
                    break; //cut off
                }
            }
            return v;
        } else {
             for (Node child : node.get_children()) {
                n = alphabeta(child, depth - 1, a, b, false);
                if(n == null) n = child;
                if(b > n.get_value()){
                    b = n.get_value();
                    v = child;
                }
                if (b <= a) {
                    break; //cut off
                }
            }
            return v;
        }
    }
}
