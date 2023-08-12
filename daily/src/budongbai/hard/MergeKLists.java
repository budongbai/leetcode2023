package budongbai.hard;

import budongbai.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/description/">23. 合并 K 个升序链表</a>
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 思路1： 两两合并，利用 21. 合并两个有序链表 解法。
 * <p>
 * <p>
 * 相关题目 <a href="https://leetcode.cn/problems/merge-two-sorted-lists/description/">21. 合并两个有序链表</a>
 *
 * 题解link https://leetcode.cn/problems/merge-k-sorted-lists/solutions/2384843/xiao-bai-ti-jie-he-bing-kge-sheng-xu-lia-zygk/
 */
public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        return partition(lists, 0, lists.length - 1);
    }


    private ListNode partition(ListNode[] lists, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            ListNode p1 = partition(lists, start, mid);
            ListNode p2 = partition(lists, mid + 1, end);
            return mergeTwoLists(p1, p2);
        } else if (start == end) {
            return lists[start];
        }
        return null;
    }

    /**
     * 优先队列合并
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 使用优先队列维护n个列表当前未合并部分的节点
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));

        for (ListNode node : lists) {
            if (node == null) {
                continue;
            }
            queue.add(node);
        }
        // 创建一个dummy节点，用于链接最终结果的头节点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (!queue.isEmpty()) {
            ListNode n = queue.poll();
            // 当前链表存在下一个节点，加入优先队列，和其他节点排序
            if (n.next != null) {
                queue.add(n.next);
            }
            n.next = null;
            p.next = n;
            p = p.next;
        }
        return dummy.next;
    }


    private ListNode mergeTwoLists(ListNode n1, ListNode n2) {
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (n1 != null && n2 != null) {
            if (n1.val <= n2.val) {
                p.next = n1;
                n1 = n1.next;

            } else {
                p.next = n2;
                n2 = n2.next;
            }
            p.next.next = null;
            p = p.next;
        }

        if (n1 != null) {
            p.next = n1;
        }
        if (n2 != null) {
            p.next = n2;
        }

        return dummy.next;
    }

}
