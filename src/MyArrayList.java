import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<AnyType> implements Iterable<AnyType>{
    private static final int DEFAULT_CAPACITY =10;
    private int thesize;
    private AnyType [] theItems;
    //无参构造
    public MyArrayList(){
        doClear();
    }
    public void clear(){doClear();}

    private void doClear() {
        thesize=0;
        ensureCapacity(DEFAULT_CAPACITY);
    }
    //链表大小
    public int size(){ return thesize;}
    //是否为空
    public boolean isEmpty(){
        return size()==0;
    }
    //去掉预留
    public void trimToSize(){
        ensureCapacity(size());
    }
    //get
    public AnyType get(int idx){
        if (idx<0||idx>size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }
    //set
    public AnyType set(int idx,AnyType newVal){
        if (idx<0||idx>size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType old=theItems[idx];
        theItems[idx]=newVal;
        return old;
    }
      //自动更新
    private void ensureCapacity(int newCapacity) {
        if (newCapacity<thesize)
            return;
        AnyType [] old=theItems;
        theItems=(AnyType[]) new Object[newCapacity];
        for (int i=0;i<size();i++)
            theItems[i]=old[i];
    }
    //尾部add
    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }
    //add
    public void add(int idx,AnyType x){
        if (theItems.length==size()) {
            ensureCapacity(size() * 2 + 1);
        }
        for (int i=thesize;i>idx;i--) {
            theItems[i] = theItems[i - 1];
        }
            theItems[idx] = x;

            thesize++;

    }
    //remove
    public AnyType remove(int idx){
       AnyType remove= theItems[idx];
       for (int i=idx;i<size()-1;i++) {         //将删除的项移到数组最后删除
           theItems[i] = theItems[i + 1];
       }
       thesize--;
       return remove;
    }
    //迭代器
    public Iterator<AnyType> iterator(){
        return new ArrayListIterator();
    }
    //内部类
    private class ArrayListIterator implements Iterator<AnyType> {
       private int current=0;

       //hasNext
        public boolean hasNext(){
            return current<size();
        }
        //next
        public AnyType next(){
            if (!hasNext())
                throw new NoSuchElementException();
            return theItems[current++];
        }
        public void remove(){
            MyArrayList.this.remove(--current);
        }
    }
}
