package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author xianCan
 * @date 2020/10/11 18:55
 *
 * 380. 常数时间插入、删除和获取随机元素（中等）
 *
 *  设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。

    insert(val)：当元素 val 不存在时，向集合中插入该项。
    remove(val)：元素 val 存在时，从集合中移除该项。
    getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
    示例 :

    // 初始化一个空的集合。
    RandomizedSet randomSet = new RandomizedSet();

    // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
    randomSet.insert(1);

    // 返回 false ，表示集合中不存在 2 。
    randomSet.remove(2);

    // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
    randomSet.insert(2);

    // getRandom 应随机返回 1 或 2 。
    randomSet.getRandom();

    // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
    randomSet.remove(1);

    // 2 已在集合中，所以返回 false 。
    randomSet.insert(2);

    // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
    randomSet.getRandom();

 */
public class LeetCode380 {

    static class RandomizedSet {

        private HashMap<Integer, Integer> map;
        private List<Integer> list;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            this.map = new HashMap<>();
            this.list = new ArrayList<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)){
                return false;
            }
            int idx = list.size();
            map.put(val, idx);
            return list.add(val);
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)){
                return false;
            }
            Integer idx = map.get(val);
            int len = list.size();
            Integer value = list.get(len - 1);
            list.set(idx, value);
            map.put(value, idx);
            list.remove(len - 1);
            map.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int idx = (int) (new Random().nextDouble() * list.size());
            return list.get(idx);
        }
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        boolean insert = randomizedSet.insert(1);
        boolean remove = randomizedSet.remove(2);
        boolean insert1 = randomizedSet.insert(2);
        //int random = randomizedSet.getRandom();
        boolean remove1 = randomizedSet.remove(1);
        boolean insert2 = randomizedSet.insert(2);
        //int random1 = randomizedSet.getRandom();
        System.out.println();
    }
}
