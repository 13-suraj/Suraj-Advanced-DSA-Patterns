package Algorithms;

import java.util.Arrays;

public class Kadane {
    public static void main(String[] args) {
        int[] nums = {4, -1, 2, -7, 3, 4};
        System.out.println(maximumSubarraySum(nums));
        System.out.println(Arrays.toString(maximumSubarraySum2(nums)));
        System.out.println(minimumSubarraySum(nums));
        System.out.println(Arrays.toString(minimumSubarraySum2(nums)));
        System.out.println(maxSubarraySumCircular(nums));
    }


    //Returns Maximum Subarray Sum
    public static int maximumSubarraySum(int[] nums) {
        int maxSum = nums[0], currSum = 0;
        for(int num : nums) {
            if(currSum < 0) currSum = 0;
            currSum += num;
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    //Returns Maximum Subarray Sum Index {l, r(exclusive)}
    public static int[] maximumSubarraySum2(int[] nums) {
        int maxSum = nums[0], currSum = 0, maxL = 0, maxR = 0, l = 0;
        for(int r = 0; r < nums.length; r++) {
            if(currSum < 0) {
                currSum = 0;
                l = r;
            }
            currSum += nums[r];
            if(maxSum < currSum) {
                maxSum = currSum;
                maxL = l;
                maxR = r;
            }
        }
        return new int[] {maxL, maxR + 1};
    }

    //Returns Minimum Subarray Sum
    public static int minimumSubarraySum(int[] nums) {
        int minSum = nums[0], currSum = 0;
        for(int num : nums) {
            if(currSum > 0) currSum = 0;
            currSum += num;
            minSum = Math.min(minSum, currSum);
        }
        return minSum;
    }

    // Returns Minimum Subarray Sum Index {l, r(exclusive)}
    public static int[] minimumSubarraySum2(int[] nums) {
        int minSum = nums[0], currSum = 0, minL = 0, minR = 0, l = 0;
        for(int r = 0; r < nums.length; r++) {
            if(currSum > 0) {
                currSum = 0;
                l = r;
            }
            currSum += nums[r];
            if(minSum > currSum) {
                minSum = currSum;
                minL = l;
                minR = r;
            }
        }
        return new int[] {minL, minR + 1};
    }

    //Returns Maximum Sum Circular Subarray
    public static int maxSubarraySumCircular(int[] nums) {
        int globMax = nums[0], globMin = nums[0];
        int currMax = 0, currMin = 0, total = 0;

        for(int num : nums) {
            currMax = Math.max(currMax + num, num);
            currMin = Math.min(currMin + num, num);
            total += num;
            globMax = Math.max(globMax, currMax);
            globMin = Math.min(globMin, currMin);
        }
        return globMax > 0 ? Math.max(globMax, total - globMin) : globMax;
    }
}
