class Mod {
    static final long MOD = (long) 1e9 + 7;

    // Calculate (1 / x) % MOD
    static long inv(long x) {
        return modpow(x, MOD - 2);
    }

    // Calculate (base ^ pow) % MOD
    static long modpow(long base, long pow) {
        long res = 1;
        while (pow > 0) {
            if (pow % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            pow /= 2;
        }
        return res;
    }

    long nck(int n, int k) {
        if (k < 0 || k > n) {
            return 0;
        }

        if (k > n - k) {
            return nck(n, n - k);
        }

        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = (result * (n - k + i)) % MOD;
            result = (result * inv(i)) % MOD;
        }
        return result;
    }

    /**
        C(n, k) = C(n-1, k-1) + C(n-1, k)
        C(n, 0) = C(n, n) = 1
    **/
}
