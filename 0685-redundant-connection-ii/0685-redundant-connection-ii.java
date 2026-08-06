class Solution {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;

        int[] directParent = new int[n + 1];

        int[] firstCandidate = null;
        int[] secondCandidate = null;

        
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            if (directParent[child] == 0) {
                directParent[child] = parent;
            } else {
                firstCandidate = new int[] {
                    directParent[child],
                    child
                };

                secondCandidate = new int[] {
                    parent,
                    child
                };
            }
        }

        int[] parent = new int[n + 1];

        for (int node = 1; node <= n; node++) {
            parent[node] = node;
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            if (
                secondCandidate != null &&
                from == secondCandidate[0] &&
                to == secondCandidate[1]
            ) {
                continue;
            }

            int rootFrom = find(parent, from);
            int rootTo = find(parent, to);

            if (rootFrom == rootTo) {
           
                if (firstCandidate != null) {
                    return firstCandidate;
                }

                
                return edge;
            }

            parent[rootTo] = rootFrom;
        }

      
        return secondCandidate;
    }

    private int find(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = find(parent, parent[node]);
        }

        return parent[node];
    }
}