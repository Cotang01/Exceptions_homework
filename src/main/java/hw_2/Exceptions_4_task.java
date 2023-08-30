package hw_2;

import java.util.Scanner;

public class Exceptions_4_task {

//	Разработайте программу, которая выбросит Exception, когда пользователь 
//	вводит пустую строку. Пользователю должно показаться сообщение, 
//	что пустые строки вводить нельзя.
	
	public static void main(String[] args) throws IllegalStateException {
		
		Scanner scanner = new Scanner(System.in);
		try {
			String users_input = scanner.nextLine();
			if (users_input.equals("")) {
				throw new IllegalStateException();
			}
			System.out.println(users_input);
		} catch (IllegalStateException e) {
			System.out.println("Не водите пустую строку");
		}
		
	}

}
