package java;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    String value;
    List<TreeNode> children;

    TreeNode(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
}
