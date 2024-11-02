class SegmentTree {

    static int init = 0;
    static int func(int op1, int op2) {
        return op1 + op2;
    }

    int[] t;
    int n;

    public SegmentTree(int[] a) {
        n = a.length;
        t = new int[n * 4];
        build(a, 1, 0, n - 1);
    }

    private void build(int[] a, int v, int tl, int tr) {
        if (tl == tr) {
            t[v] = a[tl];
        } else {
            int tm = (tl + tr) / 2;
            build(a, v * 2, tl, tm);
            build(a, v * 2 + 1, tm + 1, tr);
            t[v] = func(t[v * 2], t[v * 2 + 1]);
        }
    }

    // l and r are both inclusive
    int query(int l, int r) {
        return query(1, 0, n - 1, l, r);
    }

    private int query(int v, int tl, int tr, int l, int r) {
        if (l > r) return init;
        if (l == tl && r == tr) return t[v];
        int tm = (tl + tr) / 2;
        int op1 = query(v * 2, tl, tm, l, Math.min(r, tm));
        int op2 = query(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);
        return func(op1, op2);
    }

    void update(int pos, int newVal) {
        update(1, 0, n - 1, pos, newVal);
    }

    private void update(int v, int tl, int tr, int pos, int newVal) {
        if (tl == tr) {
            t[v] = newVal;
        } else {
            int tm = (tl + tr) / 2;
            if (pos <= tm) {
                update(v * 2, tl, tm, pos, newVal);
            } else {
                update(v * 2 + 1, tm + 1, tr, pos, newVal);
            }
            t[v] = func(t[v * 2], t[v * 2 + 1]);
        }
    }

    /******************************************/
    /**** addtion on segments, point query ****/
    private void build(int[] a, int v, int tl, int tr) {
        if (tl == tr) {
            t[v] = a[tl];
        } else {
            int tm = (tl + tr) / 2;
            build(a, v * 2, tl, tm);
            build(a, v * 2 + 1, tm + 1, tr);
            t[v] = 0;
        }
    }

    private void update(int v, int tl, int tr, int l, int r, int add) {
        if (l > r) return;
        if (l == tl && r == tr) {
            t[v] += add;
        } else {
            int tm = (tl + tr) / 2;
            update(v * 2, tl, tm, l, Math.min(r, tm), add);
            update(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, add);
        }
    }

    int get(int v, int tl, int tr, int pos) {
        if (tl == tr) return t[v];
        int tm = (tl + tr) / 2;
        if (pos <= tm) {
            return t[v] + get(v * 2, tl, tm, pos);
        } else {
            return t[v] + get(v * 2 + 1, tm + 1, tr, pos);
        }
    }
    /******************************************/


    /*********************************************/
    /**** assignment on segments, point query ****/
    int[] marked = new int[n * 4];

    private void build(int[] a, int v, int tl, int tr) {
        if (tl == tr) {
            t[v] = a[tl];
        } else {
            int tm = (tl + tr) / 2;
            build(a, v * 2, tl, tm);
            build(a, v * 2 + 1, tm + 1, tr);
            t[v] = 0;
        }
    }

    private void push(int v) {
        if (marked[v]) {
            t[v * 2] = t[v];
            t[v * 2 + 1] = t[v];
            marked[v * 2] = true;
            marked[v * 2 + 1] = true;
            marked[v] = false;
        }
    }

    private void update(int v, int tl, int tr, int l, int r, int newVal) {
        if (l > r) return;
        if (l == tl && r == tr) {
            t[v] = newVal;
            marked[v] = true;
        } else {
            push(v);
            int tm = (tl + tr) / 2;
            update(v * 2, tl, tm, l, Math.min(r, tm), newVal);
            update(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, newVal);
        }
    }

    int get(int v, int tl, int tr, int pos) {
        if (tl == tr) return t[v];
        push(v);
        int tm = (tl + tr) / 2;
        if (pos <= tm) {
            return get(v * 2, tl, tm, pos);
        } else {
            return get(v * 2 + 1, tm + 1, tr, pos);
        }
    }
    /*********************************************/


    /*****************************************/
    /**** Adding on segments, range query ****/
    int[] lazy = new int[n * 4];

    private void build(int[] a, int v, int tl, int tr) {
        if (tl == tr) {
            t[v] = a[tl];
        } else {
            int tm = (tl + tr) / 2;
            build(a, v * 2, tl, tm);
            build(a, v * 2 + 1, tm + 1, tr);
            t[v] = func(t[v * 2], t[v * 2 + 1]);
        }
    }

    private void push(int v) {
        t[v * 2] += lazy[v];
        t[v * 2 + 1] += lazy[v];
        lazy[v * 2] += lazy[v];
        lazy[v * 2 + 1] += lazy[v];

        lazy[v] = 0;
    }

    private void update(int v, int tl, int tr, int l, int r, int addend) {
        if (l > r) return;
        if (l == tl && r == tr) {
            t[v] += addend;
            lazy[v] += addend;
        } else {
            push(v);
            int tm = (tl + tr) / 2;
            update(v * 2, tl, tm, l, Math.min(r, tm), addend);
            update(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, addend);
            t[v] = func(t[v * 2], t[v * 2 + 1]);
        }
    }

    int query(int v, int tl, int tr, int l, int r) {
        if (l > r) return init;
        if (l == tl && tr == r) return t[v];
        push(v);
        int tm = (tl + tr) / 2;
        return func(
                query(v * 2, tl, tm, l, Math.min(r, tm)), 
                query(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r)
            );
    }
    /*********************************************/

}