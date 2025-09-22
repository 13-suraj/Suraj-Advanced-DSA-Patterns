package MonotonicStack;

import java.util.*;
public class MonotonicStack {
    public static int[] nextGreater(int[] nums) {    // Right To Left Traversal
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {    // write only stack.peek() in case of element pushing
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();    // write nums[stack.peek()] in case for assigning peek element into result[i]
            stack.push(i);                                            // stack.push(nums[i]) in case for element
        }
        return result;
    }

    public static int[] nextGreater2(int[] nums) {          // Left to Right Traversal
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int idx = stack.pop();
                result[idx] = i;     // write result[idx] = nums[i] in case for element assigning to result
            }
            stack.push(i);
        }
        return result;
    }

    public static int[] nextSmaller(int[] nums) {      // Right to Left
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return result;
    }

    public static int[] nextSmaller2(int[] nums) {      // Left to Right
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int idx = stack.pop();
                result[idx] = i;
            }
            stack.push(i);
        }
        return result;
    }

    public static int[] prevGreater(int[] nums){       // Left to Right
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] <=  nums[i]){
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return result;
    }

    public static int[] prevGreater2(int[] nums){        // Right to Left
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];
        Arrays.fill(result, -1);
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                int idx = stack.pop();
                result[idx] = i;
            }
            stack.push(i);
        }
        return result;
    }

    public static int[] prevSmaller(int[] nums){       // Left to Right
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i]){
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return result;
    }

    public static int[] prevSmaller2(int[] nums){       // Right to left
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];
        Arrays.fill(result, -1);
        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peek()] > nums[i]){
                int idx = stack.pop();
                result[idx] = i;
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {76,87,34,42,42,78,68,68,13,14};
        //    idx =  0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        System.out.println(Arrays.toString(nextGreater(arr))); //result = [1, -1, 3, 5, 5, -1, -1, 8, -1]
        System.out.println(Arrays.toString(nextGreater2(arr))); //result = [1, -1, 3, 5, 5, -1, -1, 8, -1]
        System.out.println(Arrays.toString(nextSmaller(arr)));  //result = [2, 2, 8, 8, 8, 6, 8, 8, -1, -1]
        System.out.println(Arrays.toString(nextSmaller2(arr)));  //result = [2, 2, 8, 8, 8, 6, 8, 8, -1, -1]
        System.out.println(Arrays.toString(prevGreater(arr)));   // result = [-1, -1, 1, 1, 1, 1, 5, 5, 7, 7]
        System.out.println(Arrays.toString(prevGreater2(arr)));   // result = [-1, -1, 1, 1, 1, 1, 5, 5, 7, 7]
        System.out.println(Arrays.toString(prevSmaller(arr)));   // result = [-1, 0, -1, 2, 2, 4, 4, 4, -1, 8]
        System.out.println(Arrays.toString(prevSmaller2(arr)));   // result = [-1, 0, -1, 2, 2, 4, 4, 4, -1, 8]
    }

    /* From above algorithms, We can say:
        Next Greater (Right to left) === Previous Greater (Left to Right)
        Next Greater (Left to Right) === Previous Greater (Right to Left)

        Next Smaller (Right to left) === Previous Smaller (Left to Right)
        Next Smaller (Left to Right) === Previous Smaller (Right to Left)

    */
}
