class UnionFind {
    int count;
    int[] parent;
    int[] size;

    UnionFind(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    boolean union(int p, int q) {
        p = find(p);
        q = find(q);
        if (p == q) return false;
        if (size[p] > size[q]) {
            size[p] += size[q];
            parent[q] = p;
        } else {
            size[q] += size[p];
            parent[p] = q;
        }
        count--;
        return true;
    }
}