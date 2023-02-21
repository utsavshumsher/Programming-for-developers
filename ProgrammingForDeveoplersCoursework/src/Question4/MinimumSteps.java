//
//b)	Given a linked list containing an integer value, return the number of steps required to sort an array in ascending order by eliminating elements at each step
//        Note: at each step remove element a[i] where a[i-1]> a[i]


        package Question4;

//public class MinimumSteps {
//}
class Node {
    int val;
    Node next;
    Node(int val) {
        this.val = val;
    }
}

class LinkedList {
    Node head;

    public int sort() {
        int steps = 0;
        Node current = head;
        while (current.next != null) {
            if (current.val > current.next.val) {
                current.next = current.next.next;
                steps++;
            } else {
                current = current.next;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.head = new Node(5);
        list.head.next = new Node(2);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(4);
        list.head.next.next.next.next = new Node(1);

        int steps = list.sort();
        System.out.println("Steps required to sort the list: " + steps);
    }

}
