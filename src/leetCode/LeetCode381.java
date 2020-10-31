package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2020/10/31 0:16
 *
 *  381.O(1) 时间插入、删除和获取随机元素 - 允许重复
 *
 *  设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。

    注意: 允许出现重复元素。

    insert(val)：向集合中插入元素 val。
    remove(val)：当 val 存在时，从集合中移除一个 val。
    getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
    示例:

    // 初始化一个空的集合。
    RandomizedCollection collection = new RandomizedCollection();

    // 向集合中插入 1 。返回 true 表示集合不包含 1 。
    collection.insert(1);

    // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
    collection.insert(1);

    // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
    collection.insert(2);

    // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
    collection.getRandom();

    // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
    collection.remove(1);

    // getRandom 应有相同概率返回 1 和 2 。
    collection.getRandom();
 */
public class LeetCode381 {

    static class RandomizedCollection{
        private Map<Integer, Set<Integer>> map;
        private List<Integer> list;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            this.map = new HashMap<>();
            this.list = new ArrayList<>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            list.add(val);
            Set<Integer> set = map.getOrDefault(val, new HashSet<>());
            set.add(list.size()-1);
            map.put(val, set);
            return set.size() == 1;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if (map.containsKey(val)){
                Iterator<Integer> it = map.get(val).iterator();
                Integer i = it.next();
                Integer lastNum = list.get(list.size() - 1);
                list.set(i, lastNum);
                map.get(val).remove(i);
                map.get(lastNum).remove(list.size()-1);
                if (i < list.size()-1){
                    map.get(lastNum).add(i);
                }
                if (map.get(val).size() == 0){
                    map.remove(val);
                }
                list.remove(list.size()-1);
                return true;
            }
            return false;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            int idx = (int) (new Random().nextDouble() * list.size());
            return list.get(idx);
        }
    }

    public static void main(String[] args) {
        RandomizedCollection randomizedSet = new RandomizedCollection();
        boolean insert = randomizedSet.insert(1);
        boolean remove1 = randomizedSet.remove(1);
        boolean insert2 = randomizedSet.insert(1);
        int random1 = randomizedSet.getRandom();
        System.out.println();
    }
}
