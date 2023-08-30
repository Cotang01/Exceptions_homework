package hw_1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


public class RandomExceptionsClass {
    public static void main(String[] args) throws IOException {
        callMethod6("");
        try {
            ArrayList<String> stringArraysList = 
            		(ArrayList<String>) callMethod1();
		} catch (ClassCastException e) {
			System.out.println(
					"Нельзя переменной класса ArrayList присвоить объект "
					+ "класса LinkedList");
		}
        int a=10, b=0;
        callMethod6("");
        int div = callMethod2(a, b);
        System.out.println(div);
        callMethod6("");
        ArrayList<String> stringArraysList = null;
        try {
        	callMethod3(stringArraysList);
		} catch (Exception e) {
			System.out.println("Получился бесконечный самовызов");
		}
    }

    private static void callMethod6(String s) {
        System.out.println("Hello World, it's me!");
    }

    private static void callMethod3(ArrayList<String> stringArraysList) {
        callMethod6("");
        // callMethod3(stringArraysList); бесконечный самовызов
    }

    private static Collection<String> callMethod1() throws IOException {
        callMethod2(100000000, 10-10);
        return new LinkedList<>();
    }

    public static int callMethod2(int a, int b) throws IOException {
    	try {
            callMethod6("");
            FileInputStream fis = new FileInputStream("1.txt");
            fis.read();

            if(fis.available() > 0) throw new RuntimeException();
		} catch (FileNotFoundException e) {
			System.out.println("Файл не найден");
		} catch (RuntimeException e) {
			System.out.println("Выпал RuntimeException");
		} try {
            return a/b;
		} catch (ArithmeticException e) {
			System.out.println("Не дели на 0");
		}
		int num = callMethod4("124O"); // должен быть 0, а не О
		return 0;
    }

    private static int callMethod4(String s) {
        callMethod5(s);
        try {
        	return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.out.println("Не удалось преобразовать строку в число");
		}
		return 0;
    }

    private static void callMethod5(String s) {
        callMethod6("");
        String[] strings = new String[5];
        try {
            for (int i = 1; i <= strings.length; i++){
                strings[i] = s;
            }
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Неверный цикл! Ты вышел за границу");
		} finally {
            for (int i = 0; i < strings.length; i++){
                strings[i] = s;
            }
		}
    }
}