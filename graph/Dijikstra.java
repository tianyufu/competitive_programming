class Dijkstra {
    // u -> [v, weight]
    // Nodes are 0, 1, 2, ... n - 1
    // Graph does not contain negative edges
    Map<Integer, List<int[]>> g;
    int n;

    // Shortest path from u to all nodes in graph
    int[] dijkstra(int u) {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[u] = 0;

        // [d, v]
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[0], b[0]);
        });
        pq.offer(new int[] {0, u});

        boolean[] visited = new boolean[n];

        while (!pq.isEmpty()) {
            int a = pq.poll()[1];
            if (visited[a]) continue;
            visited = true;
            for (int[] next : g.get(a)) {
                int b = next[0], w = next[1];
                if (distance[a] + w < distance[b]) {
                    distance[b] = distance[a] + w;
                    pq.offer(new int[] {distance[b], b});
                }
            }
        }

        return distance;
    }
}
