class Solution {
    private static final long MOD = 911382323;
    private static final int N = 26;
    
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) return -1;
        if (needle.length() == 0) return 0;
        
        long windowHash = getHashCode(haystack, needle.length());
        long needleHash = getHashCode(needle, needle.length());        
        long msb = modpow(N, needle.length() - 1);
        
        int left = 0;
        for (int right = needle.length(); right < haystack.length(); ++right) {
            if (windowHash == needleHash && compare(haystack, left, needle)) {
                return left;
            }
            windowHash = (windowHash - (haystack.charAt(left) - 'a') * msb % MOD + MOD) % MOD;
            windowHash = (windowHash * N + haystack.charAt(right) - 'a') % MOD;
            ++left;
        }
        
        if (windowHash == needleHash && compare(haystack, left, needle)) {
            return left;
        }

        return -1;
    }
    
    private long getHashCode(String s, int len) {
        long hash = 0;
        for (int i = 0; i < len; ++i) {
            hash = (hash * N + s.charAt(i) - 'a') % MOD;
        }
        return hash;
    }
    
    private long modpow(long base, int pow) {
        long result = 1;
        while (pow > 0) {
            if (pow % 2 == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            pow = pow / 2;
        }
        return result;
    }
    
    private boolean compare(String s, int offset, String t) {
        for (int i = 0; i < t.length(); ++i) {
            if (s.charAt(offset++) != t.charAt(i)) return false;
        }
        return true;
    }
}