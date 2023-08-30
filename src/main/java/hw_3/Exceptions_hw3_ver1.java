package hw_3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exceptions_hw3_ver1 {

//	Напишите приложение, которое будет запрашивать у пользователя следующие 
//	данные в произвольном порядке, разделенные пробелом:
//	Фамилия Имя Отчество датарождения номертелефона пол
//	Форматы данных:
//	фамилия, имя, отчество - строки
//	датарождения - строка формата dd.mm.yyyy
//	номертелефона - целое беззнаковое число без форматирования
//	пол - символ латиницей f или m.
	
//	Приложение должно проверить введенные данные по количеству. 
//	Если количество не совпадает с требуемым, вернуть код ошибки, 
//	обработать его и показать пользователю сообщение, что он ввел меньше 
//	и больше данных, чем требуется.

//	Приложение должно попытаться распарсить полученные значения и выделить 
//	из них требуемые параметры. Если форматы данных не совпадают, нужно 
//	бросить исключение, соответствующее типу проблемы. Можно использовать 
//	встроенные типы java и создать свои. Исключение должно быть корректно 
//	обработано, пользователю выведено сообщение с информацией, что именно 
//	неверно.

//	Если всё введено и обработано верно, должен создаться файл с названием, 
//	равным фамилии, в него в одну строку должны записаться полученные данные, 
//	вида:
//	<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>

//	Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
	
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
					!parsed_data[0].matches("^[a-zA-Z]+$") || 
					!parsed_data[1].matches("^[a-zA-Z]+$") || 
					!parsed_data[2].matches("^[a-zA-Z]+$")) {
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
			Integer.parseInt(parsed_data_buffer[2]) < 1950) {
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

