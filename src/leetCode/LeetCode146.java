package leetCode;

import java.util.HashMap;

/**
 * @author xianCan
 * @date 2022/2/13 11:37
 *
 * 146. LRU 缓存（中等）
 *
 *  请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
    实现 LRUCache 类：
    LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
    int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

     

    示例：

    输入
    ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
    [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
    输出
    [null, null, null, 1, null, -1, null, -1, 3, 4]

    解释
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // 缓存是 {1=1}
    lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
    lRUCache.get(1);    // 返回 1
    lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
    lRUCache.get(2);    // 返回 -1 (未找到)
    lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
    lRUCache.get(1);    // 返回 -1 (未找到)
    lRUCache.get(3);    // 返回 3
    lRUCache.get(4);    // 返回 4
     

    提示：

    1 <= capacity <= 3000
    0 <= key <= 10000
    0 <= value <= 105
    最多调用 2 * 105 次 get 和 put

 */
public class LeetCode146 {

    //基于哈希表和双向链表
    class LRUCache {

        private HashMap<Integer, Node> map;
        private Node head, tail;
        private int n;

        public LRUCache(int capacity) {
            n = capacity;
            map = new HashMap<>();
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (map.containsKey(key)){
                Node node = map.get(key);
                refresh(node);
                return node.v;
            }
            return -1;
        }

        public void put(int key, int value) {
            Node node;
            if (map.containsKey(key)){
                node = map.get(key);
                node.v = value;
            } else {
                if (map.size() == n){
                    Node del = tail.prev;
                    map.remove(del.k);
                    delete(del);
                }
                node = new Node(key, value);
                map.put(key, node);
            }
            refresh(node);
        }

        // refresh 操作分两步：
        // 1. 先将当前节点从双向链表中删除（如果该节点本身存在于双向链表中的话）
        // 2. 将当前节点添加到双向链表头部
        private void refresh(Node node){
            delete(node);
            Node next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;
        }

        // delete 操作：将当前节点从双向链表中移除
        // 由于我们预先建立 head 和 tail 两位哨兵，因此如果 node.l 不为空，则代表了 node 本身存在于双向链表（不是新节点）
        private void delete(Node node){
            if (node.prev != null){
                Node prev = node.prev;
                Node next = node.next;
                next.prev = prev;
                prev.next = next;
            }
        }
    }

    class Node {
        int k, v;
        Node prev, next;

        public Node(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }
}
