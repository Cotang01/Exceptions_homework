package hw_3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Exceptions_hw3_ver3 {

	public static void main(String[] args) {
		data_parse_load();
	}
	
	public static void data_parse_load() {
		// Жмышенко Валерий Альбертович 22.04.1988 7980324433 m
		// Зубенко Михаил Петрович 22.04.1988 7980324433 m
		// 7980324433 Жмышенко Валерий Альбертович 22.04.1988 m
		// m 7980324433 22.04.1988 Зубенко Михаил Петрович
		System.out.println("Введите ФИО, дату рождения, номер телефона и "
				+ "пол через пробел");
		String[] parsed_data = new Scanner(System.in).nextLine().split(" ");
		String[] correct_placed_data = new String[6];
		
		
		// Проверка количества зафиксированных данных
		try {
			if (parsed_data.length != 6) {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println("Введено неправильное количество данных или "
					+ "не все из них введены через пробел");
			e.printStackTrace();
			data_parse_load();
		}
		
		// Поиск ФИО
		try {
			List<String> fio = new ArrayList<>();
			for (int i = 0; i < parsed_data.length; i++) {
				if (parsed_data[i].matches("^[a-zA-Zа-яА-Я]+$") && 
					parsed_data[i].length() > 1) {
					fio.add(parsed_data[i]);
				}
			}
			correct_placed_data[0] = fio.get(0);
			correct_placed_data[1] = fio.get(1);
			correct_placed_data[2] = fio.get(2);
			if (correct_placed_data[0] == null || 
				correct_placed_data[1] == null ||
				correct_placed_data[2] == null) {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println("Не удалось зафиксировать ФИО");
			e.printStackTrace();
			data_parse_load();
		}
		
		// Поиск даты рождения
		String[] birth_date_buffer = new String[3];
		for (int i = 0; i < parsed_data.length; i++) {
			if (parsed_data[i].length() == 10 &&
				parsed_data[i].charAt(2) == '.' && 
				parsed_data[i].charAt(5) == '.') {
				birth_date_buffer = parsed_data[i].split("\\.");
			}
			try {
				if (Integer.parseInt(birth_date_buffer[1]) > 12 || 
					Integer.parseInt(birth_date_buffer[2]) > 
					Calendar.getInstance().get(Calendar.YEAR) || 
					Integer.parseInt(birth_date_buffer[2]) < 1900) {
					throw new NumberFormatException();
				} else if (birth_date_buffer[0].length() != 2 || 
						birth_date_buffer[1].length() != 2 || 
						birth_date_buffer[2].length() != 4) {
						throw new InputMismatchException();
				} else {
					correct_placed_data[3] = String.join(".", birth_date_buffer);
				}
			} catch (InputMismatchException e) {
				System.out.println("Не удалось зафиксировать дату рождения"
						+ "или она введена неверно");
				e.printStackTrace();
				data_parse_load();
			} catch (NumberFormatException e) {
			}
		}
		
		// Поиск номера телефона
		try {
			for (int i = 0; i < parsed_data.length; i++) {
				if (parsed_data[i].matches("\\d+")) {
					correct_placed_data[4] = parsed_data[i];
				}
			}
			if (correct_placed_data[4] == null) {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println("Не удалось обнаружить номер телефона");
			e.printStackTrace();
			data_parse_load();
		}
		
		// Поиск пола
		try {
			for (int i = 0; i < parsed_data.length; i++) {
				if (parsed_data[i].equals("m") ||
					parsed_data[i].equals("f")) {
						correct_placed_data[5] = parsed_data[i];
					}
				}
			if (correct_placed_data[5] == null) {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println("Не удалось зафиксировать пол или он не"
					+ "соответствует требуемым условиям");
			e.printStackTrace();
			data_parse_load();
		}
		
		// Запись данных в файл
		try {
			FileWriter writer = new FileWriter(correct_placed_data[0] + ".txt", true);
			writer.write("<");
			writer.write(String.join("><", correct_placed_data));
			writer.write(">\n");
			writer.close();
			System.out.println("Данные успешно записаны");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
