package tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class BinaryTreeTraverse {
    private static Logger logger = LoggerFactory.getLogger(BinaryTreeTraverse.class);

    public static void print(Node node) {
        System.out.print(node.getValue() +  "\t");
    }

    public static void visit(Node node) {
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
    
}
