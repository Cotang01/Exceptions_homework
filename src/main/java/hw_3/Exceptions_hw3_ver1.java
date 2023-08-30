package hw_3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exceptions_hw3_ver1 {
	
	public static void main(String[] args) {
		data_parse_load();
	}
	
	public static void data_parse_load() {
		
		// Жмышенко Валерий Альбертович 22.04.1988 7980324433 m
		// Зубенко Михаил Петрович 22.04.1988 7980324433 m
		System.out.println("Введите ФИО, дату рождения, номер телефона и "
				+ "пол через пробел");
		String[] parsed_data = new Scanner(System.in).nextLine().split(" ");
		boolean correct = true;
		try {
			if (parsed_data.length != 6 || 
					!parsed_data[0].matches("^[a-zA-Zа-яА-Я]+$") || 
					!parsed_data[1].matches("^[a-zA-Zа-яА-Я]+$") || 
					!parsed_data[2].matches("^[a-zA-Zа-яА-Я]+$")) {
				correct = false;
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println("Введено неправильное количество данных");
			e.printStackTrace();
			System.out.println("Попробуйте ещё раз");
			data_parse_load();
		}
		
		try {
			String[] parsed_data_buffer = parsed_data[3].split("\\.");
			if (parsed_data[3].charAt(2) != '.' || 
				parsed_data[3].charAt(5) != '.' ||
				parsed_data[3].length() != 10) {
				throw new InputMismatchException();
			} else if (Integer.parseInt(parsed_data_buffer[1]) > 12 || 
					Integer.parseInt(parsed_data_buffer[2]) > 
			Calendar.getInstance().get(Calendar.YEAR) || 
			Integer.parseInt(parsed_data_buffer[2]) < 1900) {
				throw new NumberFormatException();
			} else if (parsed_data_buffer[0].length() != 2 || 
					parsed_data_buffer[1].length() != 2 || 
					parsed_data_buffer[2].length() != 4) {
				throw new InputMismatchException();
			} 
		} catch (InputMismatchException e) {
			correct = false;
			System.out.println("Неправильно введена дата рождения");
			e.printStackTrace();
			System.out.println("Попробуйте ещё раз");
			data_parse_load();
		} catch (NumberFormatException e) {}
		
		try {
			if (!parsed_data[4].matches("\\d+")) {
				throw new InputMismatchException();
			}
		} catch (NumberFormatException e) {
			correct = false;
			System.out.println("В должны быть только числа");
			e.printStackTrace();
			System.out.println("Попробуйте ещё раз");
			data_parse_load();
		}
		
		try {
			if (parsed_data[5].equals("m") || parsed_data[5].equals("f")) {
			} else {
				correct = false;
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println(
					"Пол должен записываться только латинскими 'm' или 'f'");
			e.printStackTrace();
			System.out.println("Попробуйте ещё раз");
			data_parse_load();
		}
		
		if (correct) {
			FileWriter writer = null;
			try {
				writer = new FileWriter(parsed_data[0] + ".txt", true);
				writer.write("<");
				writer.write(String.join("><", parsed_data));
				writer.write(">\n");
				writer.close();
				System.out.println("Данные успешно записаны");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}

