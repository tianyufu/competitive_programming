class NextPermutation {
    static void nextPermutation(int[] nums) {
        int n = nums.length;

        // 1 2 5 4 3 1

        // find first index left from tail such that nums[left] < nums[left + 1]
        int left = n - 2;
        while (left >= 0 && nums[left] >= nums[left + 1]) {
            --left;
        }

        if (left < 0) {
            reverse(nums, 0, n - 1);
            return;
        }

        // find first index right from tail such that nums[right] > nums[left]
        int right = n - 1;
        while (right > left && nums[right] <= nums[left]) {
            --right;
        }

        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;

        reverse(nums, left + 1, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            ++i;
            --j;
        }
    }
}
