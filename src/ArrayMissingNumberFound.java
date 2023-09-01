import java.util.ArrayList;
import java.util.List;

public class ArrayMissingNumberFound {

    public static void main(String[] args) {

        int[] arr = randomInputArrayGenerator();
        String arrString = "";
        for (int i : arr) {
            arrString = arrString + i + " ";
        }
        System.out.println("Missing number: " + missingNumberFounder(arr));
        System.out.println("Input array: " + arrString);
    }
    //Идея решения в том, что искомое число можно найти если сложить сумму всех чисел исходного массива,
//а после этого вычесть её из суммы всех чисел идущих по порядку от самого меньшего числа исходного массива до самого большого
    public static int missingNumberFounder(int[] array) {
        //Здесь требуется проверка на array.length<2, если так, то вернуть какой-нибудь код ошибки или бросить исключение
        int max = array[0];
        int min = array[0];
        int summ = 0;
        for (int i = 0; i < array.length; i ++){ //проходим по исходному массиву, попутно складываем все числа и ищем минимальное и максимальное
            if (array[i]>max) max = array[i];
            if (array[i]<min) min = array[i];
            summ += array[i];
        }

        int summWithMissingNumber = 0;

        for (int j=min; j<=max; j++){ //ищем сумму последовательности от наименьшего до наибольшего числа
            summWithMissingNumber += j; //вместо цикла можно использовать формулу арифметической прогрессии (max + min) * count / 2, что позволит избежать time complexity O(n^2)
        }
        return summWithMissingNumber-summ;
    }
    //В условии указано, что массив содержит целочисленные числа от 0 до N (min должен быть 0).
    //Генерация рандомного массива позволяет избежать проверку на пустой массив чисел, на 1 элемент в массиве
    public static int[] randomInputArrayGenerator(){ //метод для теста, создаем случайный упорядоченный массив с одним недостающим числом
        int min = -1000; //так как для выбранного метода рещения порядок числе в массиве не имеет значения, в генерации перемешиванием чисел пренебрег
        int max = 1000;
        int minArraySize = 5;
        int maxArraySize = 20;
        int randomFirstNumber = (int)Math.floor(Math.random() * (max - min + 1) + min);
        int randomArraySize = (int)Math.floor(Math.random() * (maxArraySize - minArraySize + 1) + minArraySize);
        List<Integer> list = new ArrayList<>();
        list.add(randomFirstNumber);
        for (int i = 0; i < randomArraySize; i ++){
            randomFirstNumber += 1;
            list.add(randomFirstNumber);
        }

        int randomRemoveNumber = (int)Math.floor(Math.random() * (list.size()-1 - 0 + 1) + 0);

        list.remove(randomRemoveNumber);
        int[] result = new int[list.size()];
        for (int j = 0; j < list.size(); j ++){
            result[j] = list.get(j);
        }
        return result;
    }
}
