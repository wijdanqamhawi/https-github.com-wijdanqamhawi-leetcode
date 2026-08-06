class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        int[][] graph = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = -1;
            }
        }

        for (int[] edge : times) {
            int source = edge[0];
            int target = edge[1];
            int weight = edge[2];

            graph[source][target] = weight;
        }

        int[] distance = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        for (int node = 1; node <= n; node++) {
            distance[node] = Integer.MAX_VALUE;
        }

        distance[k] = 0;

        for (int count = 1; count <= n; count++) {
            int current = -1;
            int shortest = Integer.MAX_VALUE;

            for (int node = 1; node <= n; node++) {
                if (!visited[node] && distance[node] < shortest) {
                    shortest = distance[node];
                    current = node;
                }
            }

            if (current == -1) {
                break;
            }

            visited[current] = true;

            for (int neighbor = 1; neighbor <= n; neighbor++) {
                if (
                    graph[current][neighbor] != -1 &&
                    !visited[neighbor] &&
                    distance[current] != Integer.MAX_VALUE
                ) {
                    int newDistance =
                        distance[current] + graph[current][neighbor];

                    if (newDistance < distance[neighbor]) {
                        distance[neighbor] = newDistance;
                    }
                }
            }
        }

        int answer = 0;

        for (int node = 1; node <= n; node++) {
            if (distance[node] == Integer.MAX_VALUE) {
                return -1;
            }

            answer = Math.max(answer, distance[node]);
        }

        return answer;
    }
}