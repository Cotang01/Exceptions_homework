package hw_2;

import java.util.Scanner;

public class Exceptions_1_task {
	
//	1. Реализуйте метод, который запрашивает у пользователя ввод дробного 
//	числа (типа float), и возвращает введенное значение. 
//	Ввод текста вместо числа не должно приводить к падению приложения, 
//	вместо этого, необходимо повторно запросить у пользователя ввод данных.
	
	public static void main(String[] args) {
		
		System.out.println(return_float());
		
	}
	
	public static float return_float() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите число: ");
		String users_input = scanner.nextLine();
		try {
			return Float.parseFloat(users_input);
		} catch (NumberFormatException e) {
			System.out.println("Это не число, получите " + e);
		}
		return return_float();
			
	}
	
}
