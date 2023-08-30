package hw_2;

public class Exceptions_3_task {
	
//	3. Дан следующий код, исправьте его там, где требуется
	
	public static void main(String[] args) {
		   try {
		       int a = 90;
		       int b = 3;
		       System.out.println(a / b);
		       printSum(23, 234);
		       int[] abc = {1, 2};
		       abc[3] = 9;
		   } catch (IndexOutOfBoundsException e) {
		       System.out.println("Вы вышли за пределы массива: " + 
		    		   			  e.getMessage());
		   }
		}
		public static void printSum(Integer a, Integer b) {
			// Убран throws FileNotFoundException, так как в данном методе 
			// нет работы с файлами
			System.out.println(a + b);
		}

}
