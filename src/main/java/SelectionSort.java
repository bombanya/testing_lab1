import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectionSort {

    public static <T extends Comparable<? super T>> List<T[]> sort(T[] array){
        Objects.requireNonNull(array);
        List<T[]> iterations = new ArrayList<>();
        for (int i = 0; i < array.length - 1; i++){
            int minPos = i;
            for (int j = i + 1; j < array.length; j++){
                if (array[j].compareTo(array[minPos]) < 0) minPos = j;
            }
            T tmp = array[i];
            array[i] = array[minPos];
            array[minPos] = tmp;
            iterations.add(array.clone());
        }
        return iterations;
    }
}
