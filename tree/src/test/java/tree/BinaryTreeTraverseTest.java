package tree;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTraverseTest {

    private static Node root;

    @Before
    public void initTree() {
        root = NodeManager.getInstance().getRoot();
    }

    @Test
    public void testPreOrderRecursiveTraverse() {
        BinaryTreeTraverse.preOrderRecursiveTraverse(root);
    }

    @Test
    public void testMidOrdeRecursiveTraverse() {
        BinaryTreeTraverse.midOrderRecursiveTraverse(root);
    }

    @Test
    public void testPosOrderRecursiveTraverse() {
        BinaryTreeTraverse.posOrderRecursiveTraverse(root);
    }

    @Test
    public void testThreeRecursiveTraverse() {
        testPreOrderRecursiveTraverse();
        System.out.println();
        testMidOrdeRecursiveTraverse();
        System.out.println();
        testPosOrderRecursiveTraverse();
    }

    @Test
    public void testPreOrderNonRecursiveTraverse() {
        BinaryTreeTraverse.preOrderNonRecursiveTraverse(root);
    }

    @Test
    public void testTwoPreOrderRecurseiveTraverse() {
        testPreOrderRecursiveTraverse();
        System.out.println();
        testPreOrderNonRecursiveTraverse();
    }

    @Test
    public void testMidOrderNonRecursiveTraverse() {
        BinaryTreeTraverse.midOrderNonRecursiveTraverse(root);
    }

    @Test
    public void testPosOrderNonRecursiveTraverse() {
        BinaryTreeTraverse.posOrderNonRecursiveTraverse(root);
    }
}
