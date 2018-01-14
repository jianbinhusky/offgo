package sort;

import java.util.Random;

/**
 * Created by hujianbin on 18/1/13.
 */
public class Sort {


    public static void quickSort1(int[] a) {
        quickSortRecursive1(a, 0, a.length - 1);
    }

    private static void quickSortRecursive1(int[] a, int start, int end) {
        if (start < end) {
            int index = parttion1(a, start, end);
            quickSortRecursive1(a, start, index - 1);
            quickSortRecursive1(a, index + 1, end);
        }
    }

    private static int parttion1(int[] a, int start, int end) {
        int flag = a[start];                        //每次将第一个位置上的值 暂时存起来作为标杆
        while (start < end) {                       //只要前指针跟后指针没有重合，说明还没找到标杆最终位置
            while (start < end && a[end] >= flag) { //指向最后元素的指针先从后面开始逐一遍历直到找到第一个比标杆小的值
                end--;                              //没找到就往前移动指针
            }
            a[start] = a[end];                      //找到或者最后的指针跟指向头的指针重合后（代表没找到），将后指针指向的值覆盖到标杆位置（即开始位置）
            while (start < end && a[start] <= flag) {//指向最前的元素的指针从前往后开始遍历，直到找到第一个比标杆大的值
                start++;                             //没找到就往后移动指针
            }
            a[end] = a[start];                       //找到或者最前的指针与当前最后的指针重合后（代表没找到），将前指针指向的值覆盖到后指针指向的位置（即之前被移动的值）
        }
        a[start] = flag;                             //前指针与后指针重合后，将标杆的值放在前指针当前指向的位置
        return start;
    }

    public static void quickSort2(int[] a) {
        quickSortRecursive2(a, 0, a.length - 1);
    }

    private static void quickSortRecursive2(int[] a, int start, int end) {
        if (start == end) {
            return;
        }

        int index = parttion2(a, start, end);
        if (index > start) {
            quickSortRecursive2(a, start, index - 1);
        }
        if (index < end) {
            quickSortRecursive2(a, index + 1, end);
        }
    }

    /**
     * 将比标杆小的数字全部交换到最左边
     * 有个指针一直指向下一个左边的位置
     * @param a
     * @param start
     * @param end
     * @return
     */
    private static int parttion2(int[] a, int start, int end) {
        if (a == null || a.length <= 0 || start < 0 || end > a.length) {
            throw new IllegalArgumentException();
        }
        int index = randomRange(start, end);//随机产生一个[start,end]范围内的数作为标杆的数组下标
        swap(a, index, end);                //将该随机数与最后的数字交换位置暂时放到最后位置作为标杆
        int small = start - 1;              //定义一个变量（small指针，初始为左NULL，为假设没有遍历前没有比标杆小的位置）一直标识比标杆小的下一个低位位置
        for (index = start; index < end; index++) {//从左往右，开始到结束位置，遍历每一个数字
            if (a[index] < a[end]) {        //碰到比标杆数字小的
                small++;                    //将small 指针 右移一位 标识 该位置 为比 标杆小的数字
                if (small != index) {       //当small 指针与遍历下标不等（若相等的话便不用交换）
                    swap(a,index,small);    //将遍历到的比标杆小的数字交换到 small 指针指向的位置
                }
            }
        }
        small++;                            //将small 指针指向 标杆最终的位置
        swap(a, small, end);                //将放在最后作为标杆的值与现占在标杆最终位置的值交换位置

        return small;
    }

    public static int randomRange(int start, int end) {
        Random random = new Random();
        int rand = random.nextInt(end + 1);
        rand = rand % (end - start + 1) + start;

        return rand;
    }

    private static void swap(int[]a,int pre, int pos) {
        int temp = a[pre];
        a[pre] = a[pos];
        a[pos] = temp;
    }


}
