import java.util.Arrays;
import java.util.Comparator;

public class Ordenador<T> {

    private T t;

    public T getT() {
        return t;
    }

    public static <T extends Comparable<T>> T[] ordenar(T[] array, Comparator<T> comparador){
        Arrays.sort(array, comparador);
        return array;
    }

}
