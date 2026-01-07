package Algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Moore {

    public static void main(String[] args) {
        int[] nums1 = {34, 65, 76, 65, 65, 32, 65};
        int[] nums2 = {42, 61, 61, 22, 22, 61, 22};

        System.out.println(majorityElement(nums1));
        System.out.println(majorityElements(nums2));
    }

    // Moore's Algorithm for Majority Elements which are more than (N / 2)
    public static int majorityElement(int[] nums) {
        int candidate = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        count = 0;
        for (int num : nums) {
            if (num == candidate) count++;
        }
        return (count > nums.length / 2) ? candidate : -1;
    }

    // Extended Moore's Algorithm for Majority Elements which are more than (N/3) times.
    // There can be at most 2 elements which can occurs more than N/3 times.
    public static List<Integer> majorityElements(int[] nums) {
        int cand1 = 0, cand2 = 1;
        int count1 = 0, count2 = 0;

        // Step 1: Find potential candidates
        for (int num : nums) {
            if (num == cand1) {
                count1++;
            } else if (num == cand2) {
                count2++;
            } else if (count1 == 0) {
                cand1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                cand2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        
        count1 = count2 = 0;
        for (int num : nums) {
            if (num == cand1) count1++;
            else if (num == cand2) count2++;
        }

        List<Integer> result = new ArrayList<>();
        if (count1 > nums.length / 3) result.add(cand1);
        if (count2 > nums.length / 3) result.add(cand2);

        return result;
    }
}
