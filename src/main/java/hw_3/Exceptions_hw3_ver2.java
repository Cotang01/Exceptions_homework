package hw_3;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exceptions_hw3_ver2 {
	
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
		start_data_write();
	}
	
	public static void start_data_write() {
		
		// Жмышенко Валерий Альбертович 22.04.1988 7980324433 m
		// Зубенко Михаил Петрович 22.04.1988 7980324433 m
		String[] parsed_data = data_get();
		FileWriter writer = null;
		try {
			writer = new FileWriter(parsed_data[0] + ".txt", true);
			writer.write("<");
			writer.write(String.join("><", parsed_data));
			writer.write(">\n");
			writer.close();
			System.out.println("Данные успешно записаны");
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		
	}
	
	public static String[] data_get() {
		boolean fio_unfilled = true;
		boolean birth_date_unfilled = true;
		boolean phone_number_unfilled = true;
		boolean gender_unfilled = true;
		String[] final_data = new String[6];
		System.out.println(
		"Какие данные хотите ввести?\n"
		+ "1. ФИО\n"
		+ "2. Дата рождения\n"
		+ "3. Номер телефона\n"
		+ "4. Пол\n"
		);
		String choice = get_choice();
		while (fio_unfilled || birth_date_unfilled || 
				phone_number_unfilled || gender_unfilled) {
			try {
				if (choice.equals("1")) {
					if (fio_unfilled) {
						String[] fio_got = get_fio();
						final_data[0] = fio_got[0];
						final_data[1] = fio_got[1];
						final_data[2] = fio_got[2];
						fio_unfilled = false;
						System.out.println("Выберите следующий пункт");
						choice = get_choice();
					} else {
						System.out.println("Фамилия уже заполнена");
						System.out.println("Выберите новый пункт");
						choice = get_choice();
					}
				} else if (choice.equals("2")) {
					if (birth_date_unfilled) {
						final_data[3] = get_birth_date();
						birth_date_unfilled = false;
						System.out.println("Выберите следующий пункт");
						choice = get_choice();
					} else {
						System.out.println("Дата рождения уже заполнена");
						System.out.println("Выберите новый пункт");
						choice = get_choice();
					}
				} else if (choice.equals("3")) {
					if (phone_number_unfilled) {
						final_data[4] = get_phone_number();
						phone_number_unfilled = false;
						System.out.println("Выберите следующий пункт");
						choice = get_choice();
					} else {
						System.out.println("Номер телефона уже заполнен");
						System.out.println("Выберите новый пункт");
						choice = get_choice();
					}
				} else if (choice.equals("4")) {
					if (gender_unfilled) {
						final_data[5] = get_gender();
						gender_unfilled = false;
						System.out.println("Выберите следующий пункт");
						choice = get_choice();
					} else {
						System.out.println("Пол уже заполнен");
						System.out.println("Выберите новый пункт");
						choice = get_choice();
					}
				} else {
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Выберите пункты от 1 до 4");
				choice = get_choice();
			}
		}
		return final_data;

	}
	
	public static String[] get_fio() {
		try {
			System.out.println("Введите ФИО через пробел");
			String[] fio = new Scanner(System.in).nextLine().split(" ");
			if (fio.length != 3 || 
					!fio[0].matches("^[a-zA-Z]+$") || 
					!fio[1].matches("^[a-zA-Z]+$") || 
					!fio[2].matches("^[a-zA-Z]+$")) {
				throw new InputMismatchException();
			}
			return fio;
		} catch (InputMismatchException e) {
			System.out.println("Введённые данные меньше или больше 3 слов"
					+ " или содержат не буквы");
		}
		return get_fio();
	}
	
	public static String get_birth_date() {
		try {
			System.out.println("Введите дату рождения формата dd.mm.yyyy");
			String birth_date = new Scanner(System.in).nextLine();
			String[] birth_date_buffer = birth_date.split("\\.");
			if (birth_date.charAt(2) != '.' || 
				birth_date.charAt(5) != '.' ||
				birth_date.length() != 10) {
				throw new InputMismatchException();
			} else if (Integer.parseInt(birth_date_buffer[1]) > 12 || 
					Integer.parseInt(birth_date_buffer[2]) > 
			Calendar.getInstance().get(Calendar.YEAR) || 
			Integer.parseInt(birth_date_buffer[2]) < 1950) {
				throw new NumberFormatException();
			} else if (birth_date_buffer[0].length() != 2 || 
					birth_date_buffer[1].length() != 2 || 
					birth_date_buffer[2].length() != 4) {
				throw new InputMismatchException();
			} 
			return birth_date;
		} catch (InputMismatchException e) {
			System.out.println("Дата рождения введена неправильно");
		} catch (NumberFormatException e) {}
		
		return get_birth_date();
	}
	
	public static String get_phone_number() {
		System.out.println("Введите номер телефона");
		Scanner scan_phone_number = new Scanner(System.in);
		String phone_number = scan_phone_number.nextLine();
		try {
			if (!phone_number.matches("\\d+")) {
				throw new InputMismatchException();
			}
			return phone_number;
		} catch (InputMismatchException e) {
			System.out.println("В номере должны быть только числа");
		}
		
		return get_phone_number();
	}
	
	public static String get_gender() {
		System.out.println("Введите свой пол");
		Scanner scan_gender = new Scanner(System.in);
		String gender = scan_gender.nextLine();
		try {
			if (gender.equals("m") || gender.equals("f")) {
				return gender;
			} else {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			System.out.println(
					"Пол должен записываться только латинскими 'm' или 'f'");
		}
		return get_gender();
	}
	
	@SuppressWarnings("resource")
	public static String get_choice() {
		return new Scanner(System.in).next();
	}
	
}


