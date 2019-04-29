import java.util.Arrays;
import java.util.Comparator;

public class Ordenador<T> {

    private T t;

    public T getT() {
        return t;
    }

    public static <T extends Comparable<T>> T[] ordenarPorCriterio(T[] array, Comparator<T> comparator){
        Arrays.sort(array, comparator);
        return array;
    }

}
