package task2;

import java.util.Objects;

public class Program {
    public static <T> boolean compareArrays(T[] array1, T[] array2){
        if (array1.length == array2.length){
            return true;
        }
        for (int i = 0; i < array1.length; i++) {
            if (Objects.equals(array1[i], array2[i])){
                return true;
            }
        }
        return false;
    }
}
