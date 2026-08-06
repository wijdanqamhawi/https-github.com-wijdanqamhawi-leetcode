class Solution {

    private int[] parent;
    private int[] rank;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int node = 1; node <= n; node++) {
            parent[node] = node;
        }

        for (int[] edge : edges) {
            int first = edge[0];
            int second = edge[1];

            if (!union(first, second)) {
                return edge;
            }
        }

        return new int[0];
    }

    private int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }

        return parent[node];
    }

    private boolean union(int first, int second) {
        int rootFirst = find(first);
        int rootSecond = find(second);

        if (rootFirst == rootSecond) {
            return false;
        }

        if (rank[rootFirst] < rank[rootSecond]) {
            parent[rootFirst] = rootSecond;
        } else if (rank[rootFirst] > rank[rootSecond]) {
            parent[rootSecond] = rootFirst;
        } else {
            parent[rootSecond] = rootFirst;
            rank[rootFirst]++;
        }

        return true;
    }
}