class Solution {

    private List<List<Integer>> graph;
    private int[] state;

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] pair : prerequisites) {
            graph.get(pair[0]).add(pair[1]);
        }

        state = new int[numCourses];

        for (int course = 0; course < numCourses; course++) {
            if (state[course] == 0 && hasCycle(course)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(int course) {

        if (state[course] == 1) {
            return true;
        }

        if (state[course] == 2) {
            return false;
        }

        state[course] = 1;

        for (int prerequisite : graph.get(course)) {
            if (hasCycle(prerequisite)) {
                return true;
            }
        }

        state[course] = 2;

        return false;
    }
}