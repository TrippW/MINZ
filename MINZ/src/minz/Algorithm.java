package minz;

public class Algorithm {

    public static Node alphabeta(Node node, int depth) {
        return alphabeta(node, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
    }

    private static Node alphabeta(Node node, int depth, int a, int b, boolean is_maximizing) {
        if (depth == 0 || node.get_children().isEmpty()) {
            return node; //value?
        }
        if (is_maximizing) {
            int v = Integer.MIN_VALUE;
            Node max_node = node;
            for (Node child : node.get_children()) {
                Node n;
                if(v < (n = alphabeta(child, depth - 1, a, b, false)).get_value()){
                    v = n.get_value();
                    max_node = child;
                }
                if(a < v){
                    a = v;

                }
                //a = Math.max(a, v);
                if (b <= a) {
                    break; //cut off
                }
            }
            return max_node;
        } else {
            int v = Integer.MAX_VALUE;
            Node min_node = node;
            for (Node child : node.get_children()) {
                Node n;
                if(v > (n = alphabeta(child, depth - 1, a, b, true)).get_value()){
                    v = n.get_value();
                    min_node = child;
                }
                if(a > v){
                    a = v;
                }
//                a = Math.min(a, v);
                if (b <= a) {
                    break; //cut off
                }
            }
            return min_node;
        }
    }
}
