package linkedlist;

/**
 * Created by hujianbin on 18/1/30.
 */
public class PracticeLinkedList {
    static Node head = new Node(9);

    /**
     * <p>
     *     9 -> 6 -> 8 -> 7 -> 4 -> 5
     * </p>
     */
    static {
        Node node2 = new Node(6);
        Node node3 = new Node(8);
        Node node4 = new Node(7);
        Node node5 = new Node(4);
        Node node6 = new Node(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = null;
    }

    public static void print(Node head) {
        Node node = head;
        while (node != null) {
            System.out.print(node.data + "\t");
            node = node.next;
        }
    }

    public static Node reverse(Node head) {
        Node last = null;
        Node node = head;
        Node target = null;
        while (node != null) {
            Node next = node.next;
            if (next == null) {
                target = node;
            }
            node.next = last;
            last = node;
            node = next;
        }

        return target;
    }

    public static void main(String[] args) {
        System.out.println("before...");
        print(head);
        System.out.println();

        System.out.println("after...");
        print(reverse(head));
    }
}
