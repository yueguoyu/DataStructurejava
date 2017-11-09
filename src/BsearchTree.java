import java.nio.BufferUnderflowException;

public class BsearchTree<AnyType extends Comparable<? super AnyType>> {
    //生成二叉树
    private static class BinaryNode<AnyType> {
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }
    }

    private BinaryNode<AnyType> root;

    public BsearchTree() {
        root = null;
    }

    //    清空数据结构
    public void makeEmpty() {
        root = null;
    }

    //是否为空
    public boolean isEmpty() {
        return root == null;
    }

    //    判断节点
    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    //找最小节点
    public AnyType findMin() {
        if (isEmpty()) throw new BufferUnderflowException();
        return findMin(root).element;
    }

    //    找最大的节点
    public AnyType findMax() {
        if (isEmpty()) throw new BufferUnderflowException();
        return findMin(root).element;
    }

    //    插入
    public void insert(AnyType x) {
        root = insert(x, root);
    }

    //    删除
    public void remove(AnyType x) {
        root = remove(x, root);
    }

    //树的遍历(中序遍历)
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }
    private void printTree(BinaryNode<AnyType> t){
          if (t!=null){

              printTree(t.left);
              System.out.println(t.element);
              printTree(t.right);
          }

    }
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) return false;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null) return null;
        else if (t.left == null)
            return t;
        else
            return findMin(t.left);
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t == null) return null;
        else if (t.right == null)
            return t;
        else
            return findMax(t.right);
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<AnyType>(x, null, null);

        int compare = x.compareTo(t.element);
        if (compare < 0)
            t.left = insert(x, t.left);
        else if (compare > 0)
            t.right = insert(x, t.right);
        else
            ;
        return t;
    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return t;

        int compare = x.compareTo(t.element);
        if (compare < 0)
            t.left = remove(x, t.left);
        else if (compare > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;

    }
}
