package SlidingWindow;

public class SlidingWindow {
    public static int pattern1(int[] arr, int k) {                      // Fixed Size Window Problem
        int max_sum = Integer.MIN_VALUE, sum = 0, l = 0, r = k - 1;
        for (int i = l; i <= r; i++) {
            sum += arr[i];
        }
        max_sum = sum;

        while(r < arr.length - 1) {
            sum -= arr[l++];
            sum += arr[++r];
            max_sum = Math.max(max_sum, sum);
        }
        return max_sum;
    }

    public static int pattern2(int[] arr, int k) {                  // Longest Subarray / Substring with Condition (Variable Window)
        int max_len = Integer.MIN_VALUE, sum = 0, l = 0, r = 0;
        while(r < arr.length) {
            sum += arr[r++];
            while(sum > k) {
                sum -= arr[l];
                l++;
            }
            max_len = Math.max(max_len, r - l + 1);
            r++;
        }
        return max_len;
    }

    public int pattern3(int[] nums, int k) {                        // Number of Subarray with Given Condition
        return func(nums, k) - func(nums, k - 1);
    }

    private int func(int[] nums, int k) {
        if(k < 0) return 0;
        int l = 0, count = 0, odd = 0, n = nums.length;
        for(int r = 0; r < n; r++) {
            if(nums[r] % 2 == 1) odd++;
            while(odd > k) {
                if(nums[l] % 2 == 1) odd--;
                l++;
            }
            count += (r - l + 1);
        }
        return count;
    }

    public String pattern4(String s, String t) {                     // Minimum Window Problem
        int[] hash = new int[58];
        for(int i = 0; i < t.length(); i++) hash[t.charAt(i) - 'A']++;

        int l = 0, min_len = Integer.MAX_VALUE, count = 0, startIdx = -1;
        for(int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if(hash[c - 'A'] > 0) count++;
            hash[c - 'A']--;

            while(count == t.length()) {
                if(r - l + 1 < min_len) {
                    min_len = r - l + 1;
                    startIdx = l;
                }
                hash[s.charAt(l) - 'A']++;
                if(hash[s.charAt(l) - 'A'] > 0) count--;
                l++;
            }
        }
        return startIdx == -1 ? "" : s.substring(startIdx, startIdx + min_len);
    }
}

/*  Basically, there are 4 different patterns of Sliding Window Algorithm.
    1. Fixed Size Window Problem
    2. Longest Subarray / Substring with Condition (Variable Window)
    3. Number of Subarray with Given Condition
    4. Minimum Window Problem

    Note :-
    When Sliding Window Needs Caution with Negatives
    Sliding window may fail or need adjustment when:
    - You're trying to maximize/minimize a sum with a variable window size.
    - You're using greedy expansion based on positive sums (like Kadane’s algorithm logic).
    Example:
    Find the longest subarray with sum ≤ target.

*/
