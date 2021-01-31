package leetCode;

/**
 * @author xianCan
 * @date 2021/1/31 16:39
 *
 * 839. 相似字符串组（困难）
 *
 *  如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。

    例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。

    总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。

    给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？

    示例 1：

    输入：strs = ["tars","rats","arts","star"]
    输出：2

    示例 2：

    输入：strs = ["omv","ovm"]
    输出：1
     
    提示：
    1 <= strs.length <= 100
    1 <= strs[i].length <= 1000
    sum(strs[i].length) <= 2 * 104
    strs[i] 只包含小写字母。
    strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
     
    备注：
      字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。

 */
public class LeetCode839 {

    public int numSimilarGroups(String[] strs) {
        int len = strs.length;

        UnionFind unionFind = new UnionFind(len);
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){
                if (isSimilarString(strs[i], strs[j])){
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.getCount();
    }

    private boolean isSimilarString(String str1, String str2){
        int len = str1.length(), len2 = str2.length();
        if (len != len2){
            return false;
        }

        int diffIdx = -1, count = 1;
        for (int i = 0; i < len; i++){
            char ch1 = str1.charAt(i), ch2 = str2.charAt(i);
            if (ch1 != ch2 && diffIdx >= 0){
                if (ch1 != str2.charAt(diffIdx) || str1.charAt(diffIdx) != ch2){
                    return false;
                }
            } else if (ch1 != ch2 && count == 1){
                diffIdx = i;
                count--;
            } else if (ch1 != ch2 && count == 0){
                return false;
            }
        }

        return true;
    }

    class UnionFind{
        private int[] parent;
        private int count;

        public UnionFind(int n){
            this.parent = new int[n];
            this.count = n;
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }

        public int getCount(){
            return this.count;
        }

        public int find(int x){
            if (parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY){
                return;
            }

            parent[rootX] = rootY;
            count--;
        }
    }

    public static void main(String[] args) {
        //"tars", "rats", "arts", "star"
        /*int i = new LeetCode839().numSimilarGroups(new String[]{"omv","ovm"});
        System.out.println(i);*/
        boolean similarString = new LeetCode839().isSimilarString("star", "tars");
        System.out.println(similarString);
    }
}
