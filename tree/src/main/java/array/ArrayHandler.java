package array;

/**
 * 数组结构相关操作
 * Created by hujianbin on 18/1/15.
 */
public class ArrayHandler {

    /**
     * <h1>#找出数组中重复的数字</h1>
     * <p>一、在一个长度为n的数组里所有数字都在0~n-1的范围内。数组中的某些数字是重复的，但不知道有几个数字是重复了，也不知道每个数字重复了</p>
     * <p>几次。请找出数组中的任意一个重复的数字。例如，如果输入长度为7的数组{2，3，1，0，2，5，3}，那么对应的输出是重复数字2或者3.</p>
     */

    /**
     * <p>借助一个相同长度的数组实现</p>
     * <p>时间复杂度为O(n)</p>
     * <p>空间复杂度为O(n)[额外消耗]</p>
     * <p>可以找到所有重复的值</p>
     * <p>思路：借助一个等长的数组作为哈希表，将源数组的值放到哈希表中，（哈希表里的值跟数组下标是相等的）</p>
     * <p>如果当前位置的哈希表里的值已经存在就代表找到了重复数字</p>
     * <p>将源数组中的值作为 哈希表（临时数组）的下标，有重复值就意味着临时数组有下标相同</p>
     * <p>那么放入时就可以判断，当前临时数组位置里的值与将要放入的值是否相等，相等就找到了，不相等就放入，继续遍历</p>
     * @param a
     * @return duplicateNumber
     */
    public int findDuplicateNumberFromArray0(int[] a) {
        int[] b = new int[a.length];            //定义等长的数组辅助空间
        int target = -1;                        //要返回的重复数字变量
        for (int i = 0; i < a.length; i++) {    //从头开始扫描
            if (a[i] == i) {                    //如果当前遍历指针指向的源数组值与指针值相等
                if (b[i] == i) {                //并且当前遍历指针指向的临时数组值与指针值相等
                    target = b[i];              //那么表示找到了重复值跳出循环返回
                    break;
                }
            } else {                            //如果当前遍历指针指向的源数组值与指针值 不 相等
                if (a[i] == b[a[i]]) {          //如果当前遍历指针指向的源数组值等于与以当前源数值为下标的临时数组中的值
                    target = a[i];              //那么表示找到了重复值跳出循环返回
                    break;
                } else {                        //否则
                    b[a[i]] = a[i];             //将以当前源数值作为临时数组下标位置 ，并将其对应值设置为前遍历指针指向的源数组值
                }
            }
        }

        return target;
    }

    /**
     *  <p>代码尽管有一个两重循环，但每个数字最多只要交换两次就能找到它的位置</p>
     *  <p>时间复杂度：O(n)</p>
     *  <p>空间复杂的：O(1)</p>
     * @param a
     * @return duplicate number
     */
    public int findDuplicateNumberFromArray1(int[] a) {
        if (a == null || a.length <= 0) {           //数组合法性校验
            return -1;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0 || a[i] > a.length - 1) {  //数组合法性校验
                return -1;
            }
        }

        int target = -1;
        for (int i = 0; i < a.length; i++) {        //从前往后遍历扫描
            while (a[i] != i) {                     //只要当前指针指向的数组值不等于指针值,就开始判断和交换
                if (a[i] == a[a[i]]) {              //直到指针到达一个位置，这个位置满足，这个位置里的值跟以这个位置为下标的数组值相等的时候
                    target = a[i];                  //就代表重复值找到了
                    return target;
                }
                int temp = a[i];                    //如果当前指针指向的数组值a[i] 与 a[当前指针指向数组值]不相等
                a[i] = a[temp];                     //那么这两个值就交换位置
                a[temp] = temp;
            }
        }

        return target;
    }


    /**
     * <h1>二、不修改数组找出重复数字</h1>
     * <p>在一个长度为n+1的数组里所有数字都在1~n的范围内，所以数组至少有一个数字是重复的</p>
     * <p>找出重复数字但不能修改数组，{2，3，5，4，3，2，6，7}</p>
     * <p>思路：1.借助一个等长的数组作为哈希表，跟上述一个算法类似</p>
     * <p>2.二分查找+统计数字</p>
     * <p>把1~n的数字分为两半，1~m和m+1~n,如果1~m的数字超过m，那么重复数字一定在1~m之间，否则在m+1~n之间</p>
     * @param nums
     * @return
     */
    public static int findDuplicateNumberFromArray2(int[] nums) {
        int start = 1;                                  //下标开始值为1
        int end = nums.length - 1;                      //结束值为length -1

        while (end >= start) {                          //只要下标开始值还小于等于结束值
            int mid = (end - start) >> 1 + start;       //防止溢出，结束-开始 / 2 + 开始，即到中间长度+开始=mid
            int count = countRange(nums, start, mid);   //遍历整个数组，统计 start~mid这区间内有多少个数字
            if (end == start) {                         //如果开始下标跟结束下标相等，即就同一个数字
                if (count > 1) {                        //并且统计个数大于1那么该数字是重复的
                    return start;
                } else {
                    break;
                }
            }

            if (count > (mid - start + 1)) {            //如果统计个数超过 mid - start +1 （超过范围长度）个
                end = mid;                              //那么仅把下一次统计的结束位置设置成mid
            } else {
                start = mid + 1;                        //否则仅把下一次统计的开始位置设置成mid+1
            }
        }
        return -1;
    }

    private static int countRange(int[] nums, int start, int end) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= start && nums[i] <= end) {
                count++;
            }
        }
        return count;
    }



}
