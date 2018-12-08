import java.util.*;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T>
    implements SearchTreeInterface<T>, java.io.Serializable {

  public BinarySearchTreeWithDups() {
    super();
  }

  public BinarySearchTreeWithDups(T rootEntry) {
    super(rootEntry);
    setRootNode(new BinaryNode<T>(rootEntry));
  }

  @Override
  public T add(T newEntry) {
    if (newEntry != null) {
      if (this.isEmpty()) setRootNode(new BinaryNode<T>(newEntry));
      else addEntryHelperNonRecursive(newEntry);
      return newEntry;
    }
    return null;
  }

  private void addEntryHelperNonRecursive(T newEntry) {
    BinaryNodeInterface<T> currentNode = getRootNode();
    assert currentNode != null;
    boolean found = false;

    while (!found) {
      T currentEntry = currentNode.getData();
      int comparison = newEntry.compareTo(currentEntry);

      if (comparison == 0 || comparison < 0) { // newEntry matches currentEntry;
        if (currentNode.hasLeftChild())
          currentNode = currentNode.getLeftChild();
        else {
          found = true;
          currentNode.setLeftChild(new BinaryNode<T>(newEntry));
        } // end if
      } else {
        if (currentNode.hasRightChild()) {
          currentNode = currentNode.getRightChild();
        } else {
          found = true;
          currentNode.setRightChild(new BinaryNode<T>(newEntry));
        }
      }
    }
  }

  public int countEntriesNonRecursive(T target) {
    int count = 0;
    BinaryNodeInterface currentNode = getRootNode();
    Stack<BinaryNodeInterface> nodeStack = new Stack<>();

    while (currentNode != null || !nodeStack.empty()) {
      while (currentNode != null) {
        nodeStack.push(currentNode);
        currentNode = currentNode.getLeftChild();
      }
      currentNode = nodeStack.pop();
      if (currentNode.getData().equals(target)) {
        count += 1;
      }
      currentNode = currentNode.getRightChild();
    }
    return count;
  }

  public int countGreaterRecursive(T target) {
    int count = 0;
    BinaryNodeInterface<T> rootNode = getRootNode();

    if (target != null) {
      count += countGreaterRecursiveHelper(rootNode, target);
    }
    return count;
  }

  private int countGreaterRecursiveHelper(BinaryNodeInterface<T> currentNode, T target) {
    if (currentNode == null) {
      return 0;
    } else if (currentNode.getData().compareTo(target) > 0) {
      return 1 + countGreaterRecursiveHelper(currentNode.getLeftChild(), target) + countGreaterRecursiveHelper(currentNode.getRightChild(), target);
    }
    return countGreaterRecursiveHelper(currentNode.getLeftChild(), target) + countGreaterRecursiveHelper(currentNode.getRightChild(), target);
  }

  public int countGreaterWithStack(T target) {
    int count = 0;
    BinaryNodeInterface<T> currentNode = getRootNode();
    Stack<BinaryNodeInterface<T>> nodeStack = new Stack<BinaryNodeInterface<T>>();

    while (currentNode != null || !nodeStack.empty()) {
      while (currentNode != null) {
        nodeStack.push(currentNode);
        currentNode = currentNode.getLeftChild();
      }
      currentNode = nodeStack.pop();
      if (currentNode.getData().compareTo(target) > 0) {
        count += 1;
      }
      currentNode = currentNode.getRightChild();
    }
    return count;
  }

  public int countUniqueValues() {
    BinaryNodeInterface<T> currentNode = getRootNode();
    Stack<BinaryNodeInterface<T>> nodeStack = new Stack<>();
    ArrayList<T> dataList = new ArrayList<>();

    while (currentNode != null || !nodeStack.empty()) {
      while (currentNode != null) {
        nodeStack.push(currentNode);
        currentNode = currentNode.getLeftChild();
      }
      currentNode = nodeStack.pop();
      T data = currentNode.getData();
      if (!dataList.contains(data)) {
        dataList.add(data);
      }
      currentNode = currentNode.getRightChild();
    }
    return dataList.size();
  }

  public int getLeftHeight() {
    BinaryNodeInterface<T> rootNode = getRootNode();
    if (rootNode == null) {
      return 0;
    } else if (!rootNode.hasLeftChild()) {
      return 0;
    } else {
      return rootNode.getLeftChild().getHeight();
    }
  }

  public int getRightHeight() {
    BinaryNodeInterface<T> rootNode = getRootNode();
    if (rootNode == null) {
      return 0;
    } else if (!rootNode.hasRightChild()) {
      return 0;
    } else {
      return rootNode.getRightChild().getHeight();
    }
  }
}