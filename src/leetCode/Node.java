package leetCode;

import java.util.List;

/**
 * @author xianCan
 * @date 2020/8/10 21:03
 */
public class Node {

    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
