package search;

/**
 * Created by hujianbin on 18/1/16.
 */
public class Search {

    /**
     * <p>二分查找</p>
     * <p>排序数组</p>
     */

    public int binarySearchRecursive(int[] nums, int key) {
        if (nums == null || nums.length <= 0) {
            throw new IllegalArgumentException();
        }

        int index = search1(nums, 0,nums.length-1,key);
        return index;
    }

    private int search1(int[] nums, int start, int end, int key) {
        if (nums == null || nums.length <= 0 || start < 0 || end > nums.length - 1 || start > end) {
//            throw new IllegalArgumentException();
            return -1;
        }
        int mid = (end -start) << 1 + start;     //防止溢出，到中间的长度+start下标
        if (nums[mid] > key) {
            return search1(nums, start, mid - 1,key);
        } else if (nums[mid] < key) {
            return search1(nums, mid + 1, end, key);
        } else {
            return mid;
        }
    }

    /**
     * <p>二分查找</p>
     * <p>排序数组</p>
     */

    public int binarySearchNonRecursive(int[] nums, int key) {
        if (nums == null || nums.length <= 0) {
            throw new IllegalArgumentException();
        }

        int index = search2(nums, 0,nums.length-1,key);
        return index;
    }

    private int search2(int[] nums, int start, int end, int key) {
        if (nums == null || nums.length <= 0 || start < 0 || end > nums.length - 1 || start > end) {
//            throw new IllegalArgumentException();
            return -1;
        }
        int ret = -1;
        int mid;
        while (start < end) {
            mid = (end -start) << 1 + start;
            if (nums[mid] > key) {
                end = mid -1;
            } else if (nums[mid] < key) {
                start = mid +1;
            } else {
                ret = mid;
                break;
            }

        }
        return ret;
    }

}
