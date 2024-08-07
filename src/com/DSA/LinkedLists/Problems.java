package com.DSA.LinkedLists;

import java.util.List;

public class Problems {
    private ListNode head;
    private ListNode tail;
    private int size;
    public class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int value){ this.val = value; }
        public ListNode(int value, ListNode next){
            this.val = value;
            this.next = next;
        }
        public ListNode() {}
    }

    //83. Remove Duplicates from Sorted List
    //Given the head of a sorted linked list,
    //delete all duplicates such that each element appears only once.
    //Return the linked list sorted as well.
    public ListNode deleteDuplicates(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode temp = head;
        while(temp.next != null){
            if(temp.val == temp.next.val){
                temp.next = temp.next.next;
            }
            else{
                temp = temp.next;
            }
        }
        return head;
    }

    //21. Merge Two Sorted Lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next;
    }

    //141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    // find the length of the cycle in the LinkedList
    public int lengthOfCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                int length = 0;
                do{
                    slow = slow.next;
                    length += 1;
                }while (slow != fast);
                return length;
            }
        }

        return 0;
    }

    //142. Linked List Cycle II
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }

    //202. Happy Number
    // Method to determine if a number is happy
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do{
            slow = sumOfSquares(n);
            fast = sumOfSquares(sumOfSquares(n));
        }while (slow != fast);
        if(slow == 1){
            return true;
        }
        return false;
    }
    public static int sumOfSquares(int num) {
        int sum = 0;
        while (num > 0) {
            int digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

    //876. Middle of the Linked List
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        while(fast != null && fast.next != null){
            head = head.next;
            fast = fast.next.next;
        }
        return head;
    }
    public ListNode getMid(ListNode head) {
        ListNode midPrev = null;
        while(head != null || head.next != null){
            midPrev = (midPrev == null) ? head :midPrev.next;
            head = head.next.next;
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }
    public ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode mid = getMid(head);
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(mid);

        return mergeTwoLists(left, right);
    }

    // Recursion reverse
    private void reverseRec(ListNode node){
        if(node == tail){
            head = tail;
            return;
        }
        reverseRec(node.next);
        tail.next = node;
        tail = node;
        tail.next = null;
    }
    //reverse
    public ListNode reverseList(ListNode head) {
        ListNode p=null;
        ListNode n=null;

        ListNode curr=head;
        while(curr!=null){
            n=curr.next;
            curr.next=p;
            p=curr;
            curr=n;
        }
        head=p;
        return head;
    }

    //92. Reverse Linked List II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right){
            return head;
        }
        ListNode current = head;
        ListNode prev = null;
        for(int i = 0; current != null && i < left - 1; i++){
            prev = current;
            current = current.next;
        }

        ListNode last = prev;
        ListNode newEnd = current;

        ListNode next = current.next;
        for(int i = 0; current != null && i < right - left + 1; i++){
            current.next = prev;
            prev = current;
            current = next;
            if(next != null){
                next = next.next;
            }
        }
        if(last != null){
            last.next = prev;
        } else {
            head = prev;
        }
        newEnd.next = current;
        return head;
    }
    //143. Reorder List
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }
        ListNode mid = middleNode(head);
        ListNode hs = reverseList(mid);
        ListNode hf = head;

        while (hf !=null && hs != null){
            ListNode temp = hf.next;
            hf.next = hs;
            hf = temp;

            temp = hs.next;
            hs.next = hf;
            hs = temp;
        }
        if(hf != null){
            hf.next = null;
        }
    }

    //61. Rotate List
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode temp = head;
        int length = 1;
        while(temp.next != null){
            length += 1;
            temp = temp.next;
        }
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = head.next;
        for(int i = 0; i < (k%length); i++){
            prev = null;
            curr = head;
            next = head.next;
            while(next != null){
                prev = curr;
                curr = next;
                next = next.next;
            }
            curr.next = head;
            head = curr;
            prev.next = null;
        }
        return head;
    }
    public static void main(String[] args) {

    }
}
