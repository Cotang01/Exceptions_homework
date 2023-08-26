package hw_1;

public class Exceptions_1 {

	public static void main(String[] args) {
		
		int[][] array_1 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}; // 3 x 3 - true
		System.out.println(is_row_and_col_equal(array_1));
		int[][] array_2 = {{1, 1}, {1, 1}};  // 2 x 2 - true
		System.out.println(is_row_and_col_equal(array_2));
		int[][] array_3 = {{1, 1}, {1, 1}, {1, 1}}; // 2 x 3 - false
		System.out.println(is_row_and_col_equal(array_3));
		int[][] array4 = null;
		System.out.println(is_row_and_col_equal(array4));
	}
	
	// 1. Реализуйте метод, принимающий в качестве аргументов двумерный массив.
	// Метод должен проверить что длина строк и столбцов с одинаковым индексом 
	// одинакова, детализировать какие строки со столбцами не требуется.
	
	public static boolean is_row_and_col_equal(int[][] arr) {
		try {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].length != arr.length) {
					return false;
				}
			}	return true;
		} catch (NullPointerException e) {
			System.out.print("Получен null -> ");
		}
		return false;
	}
	

}
