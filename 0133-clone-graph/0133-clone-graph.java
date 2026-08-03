/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {

    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        return dfs(node);
    }

    private Node dfs(Node current) {
        if (visited.containsKey(current)) {
            return visited.get(current);
        }

        Node copy = new Node(current.val);

        visited.put(current, copy);

        for (Node neighbor : current.neighbors) {
            copy.neighbors.add(dfs(neighbor));
        }

        return copy;
    }
}