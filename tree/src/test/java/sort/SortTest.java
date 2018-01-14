package sort;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by hujianbin on 18/1/13.
 */
public class SortTest {

    private int[] source;

    @Before
    public void init() {
//        source = new int[]{6, 2, 9, 5, 1, 3, 7,4};
        source = new int[]{6, 2, 19,33,1,99, 5, 1, 3, 7,4};
    }

    @Test
    public void testQuickSortRecurive() {
        Sort.quickSort1(source);
        System.out.println(Arrays.toString(source));
    }

    @Test
    public void testQuickSortRecursive2() {
        Sort.quickSort2(source);
        System.out.println(Arrays.toString(source));
    }

    @Test
    public void test() {
        Random random = new Random();
        int start =2;
        int end = 10;
        int rand = random.nextInt(11);//生成0到10的数
        int temp = rand % (end - start +1); //对9进行取余得到0到8的数
        temp = temp + start; //得到2到10的数
    }
}
