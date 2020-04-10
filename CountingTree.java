
/**
 * Binary search tree, containing items of type String, which allows duplicates by having a counter
 * in each node (rather than inserting duplicate nodes)
 *
 */
public class CountingTree {

  private class TreeNode {
    String key;
    int count; // how many times key appears in the tree
    TreeNode left, right;

    TreeNode(String key) {
      this.key = key;
      left = right = null;
      count = 1;
    }
  }

  private TreeNode root = null;
  private int totalEntries = 0; // total of all counts
  private int distinctEntries = 0; // number of nodes

  /**
   * @return number of total entries (i.e., total of all counts)
   */
  public int getTotalEntries() {
    return totalEntries;
  }

  /**
   * @return number of distinct entries (i.e., number of nodes)
   */
  public int getDistinctEntries() {
    return distinctEntries;
  }

  /**
   * Inserts key into the tree. If key is already in, just increments the corresponding counter. If
   * not, creates a new node.
   * 
   * @param key the value to be inserted
   */
  public void insert(String key) {

    root = _insert(root, key);
    return;

  }// end of insert

  private TreeNode _insert(TreeNode node, String key) {

    //creates new node if node is null, updates variables as needed
    if (node == null) {
      totalEntries++;
      distinctEntries++;
      node = new TreeNode(key);
    } // end of if

    // if the keys are the same, increment count and total entries
    else if (key.compareTo(node.key) == 0) {
      node.count++;
      totalEntries++;
    } // end of else if

    // recursively call down the left or right side of the node
    else if (node.key.compareTo(key) > 0) {
      node.left = _insert(node.left, key);
    } // end of else if
    else {
      node.right = _insert(node.right, key);
    } // end of else

    return node;
  }// end of _insert

  /**
   * @param key
   * @return number of times key is in the tree, i.e., the count of key (0 if key not in the tree)
   */
  public int search(String key) {
    if (root == null) {
      return 0;
    } // end of if

    return _search(key, root);

  }// end of search

  private int _search(String key, TreeNode node) {

    if (node.key == key) {
      return node.count;
    }

    if (node.right == null && node.left == null) {
      return 0;
    }

    if (node.key.compareTo(key) > 0) {
      if (node.left == null) {
        return 0;
      } else {
        return _search(key, node.left);
      }
    } else {
      if (node.right == null) {
        return 0;
      } else {
        return _search(key, node.right);
      }
    }

  }// end of _search



  /**
   * Creates and returns a new tree, which allows lookup of entries in this tree by their counts.
   * Thus, in the new tree keys are integers, and data is String. The new tree has as many nodes as
   * the current tree. The new tree must allow insertion of nodes with duplicate keys, since counts
   * may repeat.
   * 
   * @return the new tree
   */
  public BSTWithDuplicates frequencyTree() {
    BSTWithDuplicates t = new BSTWithDuplicates();
    frequencyTree(root, t);
    return t;
  }

  /**
   * Private recursive helper for frequencyTree
   * 
   * @param root
   * @param t
   */
  private void frequencyTree(TreeNode root, BSTWithDuplicates t) {
    if (root != null) {
      // just do a traversal -- any order is fine
      t.insert(root.count, root.key);
      frequencyTree(root.left, t);
      frequencyTree(root.right, t);
    }
  }

}
