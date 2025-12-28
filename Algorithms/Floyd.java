package Algorithms;

// If there is an array of range[1, n] and
// there is only one duplicate number in the array then this algorithm works.

public class Floyd {
    public static void main(String[] args) {
        int[] nums = {4,1,3,1,2};
        System.out.println(duplicate(nums));
    }

    // Returns the duplicate number of an array
    public static int duplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);

        fast = nums[0];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}