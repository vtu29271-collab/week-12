import java.util.*;

public class Main {

    public static int maxDistinctInWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return 0;
        }

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int maxDistinct = 0;

        for (int i = 0; i < k; i++) {
            frequencyMap.put(nums[i], frequencyMap.getOrDefault(nums[i], 0) + 1);
        }

        maxDistinct = frequencyMap.size();

        for (int i = k; i < nums.length; i++) {
            int outgoing = nums[i - k];
            if (frequencyMap.get(outgoing) == 1) {
                frequencyMap.remove(outgoing);
            } else {
                frequencyMap.put(outgoing, frequencyMap.get(outgoing) - 1);
            }

            int incoming = nums[i];
            frequencyMap.put(incoming, frequencyMap.getOrDefault(incoming, 0) + 1);

            maxDistinct = Math.max(maxDistinct, frequencyMap.size());
        }

        return maxDistinct;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 4, 2, 3};
        int k = 4;

        int result = maxDistinctInWindow(nums, k);

        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Window Size (K): " + k);
        System.out.println("Maximum distinct elements in any window: " + result);
    }
}