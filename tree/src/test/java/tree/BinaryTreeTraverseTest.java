package tree;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTraverseTest {

    private static Node root;

    private static PNode rand;

    @Before
    public void initTree() {
        root = NodeManager.getInstance().getRoot();
//        rand = NodeManager.getInstance().getRandomPNode();
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

    @Test
    public void testConstructBinaryTreeFromPreMid() {
        int[] pre = BinaryTreeUtils.binaryTree2PreOrderArray(root);
        System.out.println();
        int[] mid = BinaryTreeUtils.binaryTree2MidOrderArray(root);

        Node node = BinaryTreeConstruct.constructBinaryTreeFromPreMid(pre, mid);

        System.out.println();
        BinaryTreeUtils.binaryTree2PreOrderArray(node);
        System.out.println();
        BinaryTreeUtils.binaryTree2MidOrderArray(node);

    }

    @Test
    public void testConstructBinaryTreeFromMidPos() {
        int[] pos = BinaryTreeUtils.binaryTree2PosOrderArray(root);
        System.out.println();
        int[] mid = BinaryTreeUtils.binaryTree2MidOrderArray(root);

        Node node = BinaryTreeConstruct.constructBinaryTreeFromMidPos(mid, pos);


        System.out.println();
        BinaryTreeUtils.binaryTree2PreOrderArray(node);
        System.out.println();
        BinaryTreeUtils.binaryTree2MidOrderArray(node);

    }

    @Test
    public void testFindNextNodeFromPreOrder() {
//        System.out.println("Current node is : " +rand.getValue());
//        PNode node = BinaryTreeTraverse.findNextNodeFromPreOrder(rand);
        for (int i = 0; i < 8; i++) {
            System.out.println("Current node is : " +NodeManager.getInstance().getRandomPNode(i).getValue());
            PNode node = BinaryTreeTraverse.findNextNodeFromPreOrder(NodeManager.getInstance().getRandomPNode(i));
            if (node != null) {
                System.out.println("Pre Order next node is : " + node.getValue());
            } else {
                System.out.println("next is null");
            }
            System.out.println();
        }
    }
}
