package tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>BinaryTree Traverse 6 methods</p>
 *
 * preOrder
 *        |_Recursive
 *        |_NonRecursive
 * midOrder
 *        |_Recursive
 *        |_NonRecursive
 * posOrder
 *        |_Recursive
 *        |_NonRecursive
 *
 * @author hujb
 */
public class BinaryTreeTraverse {
    private static Logger logger = LoggerFactory.getLogger(BinaryTreeTraverse.class);

    protected static List<Integer> list = new ArrayList<>();

    public static void print(Node node) {
        System.out.print(node.getValue() +  "\t");
    }

    public static void visit(Node node) {
        list.add(node.getValue());
        print(node);
    }

    public static void preOrderRecursiveTraverse(Node node) {
        if (node == null) {
            return;
        }

        //visit current node
        visit(node);

        //Traverse all left leaf
        preOrderRecursiveTraverse(node.getLeft());

        //Traverse all right leaf
        preOrderRecursiveTraverse(node.getRight());
    }

    public static void midOrderRecursiveTraverse(Node node) {
        if (node == null) {
            return;
        }

        //Traverse all left leaf
        midOrderRecursiveTraverse(node.getLeft());

        //visit current node
        visit(node);

        //Traverse all right leaf
        midOrderRecursiveTraverse(node.getRight());
    }

    public static void posOrderRecursiveTraverse(Node node) {
        if (node == null) {
            return;
        }

        //Traverse all left leaf
        posOrderRecursiveTraverse(node.getLeft());

        //Traverse all right leaf
        posOrderRecursiveTraverse(node.getRight());

        //visit current node
        visit(node);
    }

    public static void preOrderNonRecursiveTraverse(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null || !stack.empty()) {
            while (node != null) {
                visit(node);
                stack.push(node);
                node = node.getLeft();
            }
            if (!stack.empty()) {
                node = stack.pop();
                node = node.getRight();
            }
        }
    }

    public static void midOrderNonRecursiveTraverse(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }

            if (!stack.empty()) {
                node = stack.pop();
                visit(node);
                node = node.getRight();
            }
        }
    }

    public static void posOrderNonRecursiveTraverse(Node node) {
        Stack<Node> stack = new Stack<>();
        Node prev = node;
        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            if (!stack.empty()) {
                Node temp = stack.peek().getRight();
                if (temp == null || temp == prev) {
                    prev = stack.pop();
                    visit(prev);
                    node = null;
                } else {
                    node = temp;
                }
            }
        }
    }

    /**
     * <p>1.if has left leaf , next is left leaf么</p>
     * <p>2.if has no left leaf
     *      2.1 current is its parent's left leaf , next is parent's right leaf
     *      2.1 current is its parent's right leaf , must search it's parent
     *      util which has right leaf and the right leaf is next
     *</p>
     * @param current
     */
    public static PNode findNextNodeFromPreOrder(PNode current) {
        if (current == null) {
            throw new NullPointerException();
        }
        PNode next;
        if (current.getLeft() != null) {
            next = current.getLeft();
        } else if (current == current.getParent().getLeft() && current.getRight() == null) {
            next = current.getParent().getRight();
        } else {
            current = current.getParent();
            while (current.getParent().getRight() == null) {
                current = current.getParent();
            }
            next = current.getParent().getRight();
        }


        return next;
    }

    public static void findNextNodeFromMidOrder(PNode node, int value) {

    }

    public static void findNextNodeFromPosOrder(PNode node, int value) {

    }

    public static void findPrevNodeFromPreOrder(PNode node, int value) {

    }

    public static void findPrevNodeFromMidOrder(PNode node, int value) {

    }

    public static void findPrevNodeFromPosOrder(PNode node, int value) {

    }

}
