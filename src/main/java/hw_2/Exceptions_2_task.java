package hw_2;

public class Exceptions_2_task {
	
//	2. Если необходимо, исправьте данный код

	public static void main(String[] args) {
		
		try {
			// Был добавлен массив intArray
			int[] intArray = new int[5];
			// int[] intArray = new int[10];
			int d = 0;
			// int d = 1;
			double catchedRes1 = intArray[8] / d;
			System.out.println("catchedRes1 = " + catchedRes1);
			} catch (ArithmeticException e) {
				System.out.println("Catching exception: " + e.getMessage());
			} catch (IndexOutOfBoundsException e) { // Добавлен новый catch
				System.out.println("За выход за границы получаете: " + e);
			}
		
	}
	
}
