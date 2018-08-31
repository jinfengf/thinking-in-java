package generics;

/**
 * Created by jiguang on 2018/8/13.
 */

public class GenericArray<T> {
    private T[] array;
    public GenericArray(int sz) {
        array = (T[]) new Object[sz];
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

    public static void main(String[] args) {
        GenericArray<Integer> gai = new GenericArray<>(10);
        gai.put(0, 1);
        System.out.println(gai.rep().getClass().getName());
    }
}
