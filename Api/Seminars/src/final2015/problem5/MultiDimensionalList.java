package final2015;

import java.util.ArrayList;
import java.util.List;

public class MultiDimensionalList<T> {
    private List[] lists;
    private final static int DEFAULT_SIZE = 20;

    MultiDimensionalList(){
        createLists(DEFAULT_SIZE);
    }
    MultiDimensionalList(int size){
        createLists(size);
    }
    public T get(int i , int j){
        if(i<lists.length){
            return  (T)lists[i].get(j);
        }
        return null;
    }

    public void set(int i, int j,T newElement){
        if(lists.length<i){
            lists = new List[lists.length *2];
        }
        lists[i].add(newElement);
    }
    private void createLists(int size){
        lists = new List[size];
        for(int i = 0 ; i < size;i++){
            lists[i] = new ArrayList();
        }
    }
}
