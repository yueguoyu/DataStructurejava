
import java.util.Iterator;
import java.util.List;

public class test {
    public static void main(String[] args) {
        MyArrayList<Integer> in=  new MyArrayList<Integer>();
        for(int i=0;i<9;i++)
            in.add(i,i);
       for (int i=0;i<9;i++)
           System.out.println(in.iterator().next());

    }



}
