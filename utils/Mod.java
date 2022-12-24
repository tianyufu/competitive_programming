class Mod {
    static final long MOD = (long) 1e9 + 7;

    // Calculate (1 / x) % MOD
    static long inv(long x) {
        return modpow(x, MOD - 2);
    }

    static long modpow(long base, long pow) {
        long res = 1;
        while (pow > 0) {
            if (pow % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            pow /= 2;
        }
        return res;
    }
}
