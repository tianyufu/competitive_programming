class ModPow {
    static long modpow(long base, int pow) {
        long res = 1;
        while (pow > 0) {
            if (pow % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            pow /= 2;
        }
        return res;
    }
}
