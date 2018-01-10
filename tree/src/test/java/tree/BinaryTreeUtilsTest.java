package tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class BinaryTreeUtilsTest {

    private static Node root;

    @Before
    public void initTree() {
        root = NodeManager.getInstance().getRoot();
    }

    @Test
    public void testBinaryTree2Array() {
        int[] preArray = BinaryTreeUtils.binaryTree2PreOrderArray(root);
        System.out.println();
        System.out.println(Arrays.toString(preArray));

        int[] midArray = BinaryTreeUtils.binaryTree2MidOrderArray(root);
        System.out.println();
        System.out.println(Arrays.toString(midArray));

        int[] posArray = BinaryTreeUtils.binaryTree2PosOrderArray(root);
        System.out.println();
        System.out.println(Arrays.toString(posArray));
    }
}
