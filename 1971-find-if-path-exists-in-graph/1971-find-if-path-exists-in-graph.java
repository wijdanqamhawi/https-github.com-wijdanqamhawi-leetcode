class Solution {

    private int[] parent;
    private int[] rank;

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }

        return find(source) == find(destination);
    }

    private int find(int node) {

        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }

        return parent[node];
    }

    private void union(int first, int second) {

        int rootFirst = find(first);
        int rootSecond = find(second);

        if (rootFirst == rootSecond) {
            return;
        }

        if (rank[rootFirst] < rank[rootSecond]) {
            parent[rootFirst] = rootSecond;
        } else if (rank[rootFirst] > rank[rootSecond]) {
            parent[rootSecond] = rootFirst;
        } else {
            parent[rootSecond] = rootFirst;
            rank[rootFirst]++;
        }
    }
}