import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Binary search tree, keyed by integers with String data, which allows for insertion of the same
 * key twice (or more), and will create a new node for it each time. The new node may be either to
 * the right or to the left of a higher node with the same key; the direction for a duplicate key is
 * chosen at random, else duplicate keys could create very long chains and hamper performance (or
 * even cause stack overflow errors on recursion). Has a special search, which returns a LinkedList
 * of data for all the keys within range.
 * 
 */

public class BSTWithDuplicates {
  private class TreeNode {
    int key;
    String data;
    TreeNode left, right;

    TreeNode(int k, String d) {
      key = k;
      data = d;
      left = right = null;
    }
  }

  private TreeNode root = null;
  Random random = new Random();

  /**
   * Adds (key, data) pair to the tree, even if the same key already exists.
   * 
   * @param key
   * @param data
   */
  public void insert(int key, String data) {
    root = insert(key, data, root);
  }// end of insert


  private TreeNode insert(int key, String data, TreeNode subtree) {
    // TODO: implement
    // Use random.nextBoolean to decide whether to go right or left
    // when key is equal to subtree.key

    boolean dir = false;

    if (subtree == null) {
      return new TreeNode(key, data);
    } // end of if
    if (subtree.key == key) {
      dir = random.nextBoolean();
      if (dir) {
        subtree.right = insert(key, data, subtree.right);
      } else {
        subtree.left = insert(key, data, subtree.left);
      } // end of else
    } // end of outer if
    if (subtree.key < key) {
      subtree.right = insert(key, data, subtree.right);
    } else if (subtree.key > key) {
      subtree.left = insert(key, data, subtree.left);
    } // end of else if

    return subtree;

  }// end of insert(int key, String data, TreeNode subtree)

  /**
   * Assumes low<=high
   * 
   * @param low
   * @param high
   * @return a collection of all data elements whose keys are between low and high (inclusive)
   */
  public LinkedList<String> rangeSearch(int low, int high) {
    LinkedList<String> ret = new LinkedList<String>();
    rangeSearch(root, low, high, ret);
    return ret;
  }// end of rangeSearch(int low, int high)

  private void rangeSearch(TreeNode subtree, int low, int high, LinkedList<String> ret) {
    //check if subtree key is less than low, greater than high,
    // or between the two; use this check to decide what to add
    // to ret and whether to recurse right or left or both

    if (subtree == null) {
      return;
    } // end of if
    if (subtree.key >= low) {
      rangeSearch(subtree.left, low, high, ret);
    }
    if (subtree.key >= low && subtree.key <= high) {
      ret.add(subtree.data);
    } // end of if

    if (subtree.key <= high) {
      rangeSearch(subtree.right, low, high, ret);
    } // end of if
  }// end of rangeSearch(TreeNode subtree, int low, int high, LinkedList<String> ret)

  /**
   * @return the largest key in the tree; 0 if the key is empty
   */
  public int maxKey() {

    if (root == null) {
      return 0;
    }

    Queue<TreeNode> q = new LinkedList<>();

    q.add(root);
    int max = root.key;

    //use queue to compare all children to max and if it is greator than max, replace max
    while (!q.isEmpty()) {
      TreeNode compare = q.remove();
      if (compare.key > max) {
        max = compare.key;
      }
      if (compare.left != null && compare.right != null) {
        q.add(compare.left);
        q.add(compare.right);
      } else if (compare.left == null && compare.right != null) {
        q.add(compare.right);
      } else if (compare.left != null && compare.right == null) {
        q.add(compare.left);
      } else {
        ;
      }
    }//end of while
    return max;
  }// end of maxKey
}
