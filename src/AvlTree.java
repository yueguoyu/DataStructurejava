import jdk.nashorn.internal.ir.BinaryNode;

public class AvlTree<AnyType extends Comparable<? super AnyType>> {
    //    定义平衡查找二叉树
    private static class AvlNode<AnyType> {
        AnyType ement;
        AvlNode<AnyType> left;
        AvlNode<AnyType> right;
        int height;

        AvlNode(AnyType theElement) {
            this(theElement, null, null);
        }

        AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
            ement = theElement;
            left = lt;
            right = rt;
        }

    }

    //    计算树的高度
    private int height(AvlNode<AnyType> t) {
        return t == null ? -1 : t.height;
    }

    //    插入
    private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t) {
        if (t == null)
            return t;
        int compareResult = x.compareTo(t.ement);
        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else ;
        return balance(t);
    }

    //树的高度差不能大与1
    private static final int ALLOWED_IMBALANCE = 1;

    private AvlNode<AnyType> balance(AvlNode<AnyType> t) {
//        不是平衡二叉树
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
            if (height(t.left.left) >= height(t.left.right))
//                执行单旋转
                t = rotateWithLeftChild(t);
            else
//                双旋转
                t = dobleWithLeftChild(t);
        if (height(t.right.right) >= height(t.right.right))
            t = rotateWhithRightChild(t);
        else
            t = doubleWithRightChild(t);

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    //rl型  双旋转
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3) {
//将rl型转化为rr型
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWhithRightChild(k3);
    }

    //rr型  单旋转
    private AvlNode<AnyType> rotateWhithRightChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right));
        k1.height = Math.max(height(k1.right), k2.height) + 1;
        return k1;
    }

    //lr型  双旋转
    private AvlNode<AnyType> dobleWithLeftChild(AvlNode<AnyType> k3) {
//        将lr型转化为ll型
        k3.left = rotateWhithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    //ll型 单旋转
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right));
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    //    删除
    private AvlNode<AnyType> remove(AnyType x, AvlNode<AnyType> t) {
        if (t == null)
            return t;
        int compare = x.compareTo(t.ement);
        if (compare < 0)
            t.left = remove(x, t.left);
        else if (compare > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null)
//                要删除的节点有二儿子
        {
            t.ement = findMin(t.right).ement;
            t.right = remove(t.ement, t.right);
        }
        else
    t=(t.left!=null)?t.left:t.right;
        return t;
}

    private AvlNode<AnyType> findMin(AvlNode<AnyType> t) {
       if (t==null)
           return null;
       else if (t.left==null)
           return t;
       else
           return findMin(t.left);
    }
}
