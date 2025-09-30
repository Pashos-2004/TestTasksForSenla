package randomPasswords;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class RandomPasswords {

	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int lengthOfPassword = 8;
		boolean isInputRight = false; 
		boolean isNextRandomPasswordNeeded = true;

		while(isNextRandomPasswordNeeded) {
			isInputRight = false;
			
			while(!isInputRight) {
				try {
					System.out.println("Введите количество символов в пароле (не менее 8 и не боллее 12)");
					System.out.print(">> ");
					lengthOfPassword = input.nextInt();
					
					if (lengthOfPassword >= 8 && lengthOfPassword <= 12) {
						isInputRight = true;
					}else {
						System.out.println("Длина пароля не подходит");
					}
					
				}catch (Exception e) {
					System.out.println("Некорректный ввод, повторите попытку");
					input.nextLine();
					continue;
				}
			}
			
			System.out.println("Ваш пароль: "+PasswordGenerator.generatePassword(lengthOfPassword));
			System.out.println("Сгенирировать ещё пароль ? (введите \"Д\" для подтверждения, любой другой символ для отказа)");
			System.out.print(">> ");
			input = new Scanner(System.in);
			isNextRandomPasswordNeeded = input.nextLine().equals("Д");
		}
		System.out.println("Завершение работы!");
		input.close();
		
	}
	
}

class PasswordGenerator{
	
	private static final char SPECIAL_SYMBOLS [] = {'"', '!', '\'', '№', ';', '%', ':', '?', '*', '(', ')', '_',
			'-', '+', '=', ',', '.', '/', '«', '»', '@', '#', '$', 
			'{', '}', '[', ']', '\\', '|', '~', '^', '&', '<', '>'};
	
	private static Character generateDigit() {
		Random rnd = new Random();
		return (char) (48+rnd.nextInt(10));
	}
	
	private static Character generateSpecialSymbol() {
		Random rnd = new Random();
		return SPECIAL_SYMBOLS[rnd.nextInt(SPECIAL_SYMBOLS.length)];
	}
	
	private static Character generateUppercase() {
		Random rnd = new Random();
		return (char) (65+rnd.nextInt(24));
	}
	
	private static Character generateLowercase() {
		Random rnd = new Random();
		return (char) (97+rnd.nextInt(24));
	}
	
	
	public static String generatePassword(int lengthOfPassword) {
		String password = "";
		Random rnd = new Random();
		int passwordElementIndexInArray = 0;
		ArrayList<Stack<Character>> passwordElements = new ArrayList<>();
		Stack<Character> digits= new Stack<>();
		Stack<Character> specialSymbols= new Stack<>();
		Stack<Character> uppercase= new Stack<>();
		Stack<Character> lowercase= new Stack<>();
		
		digits.push(generateDigit());
		specialSymbols.push(generateSpecialSymbol());
		uppercase.push(generateUppercase());
		lowercase.push(generateLowercase());
		
		for(int i = 0 ; i < lengthOfPassword-4 ; i++) {
			
			switch (rnd.nextInt(4)) {
			case 0: 
				digits.push(generateDigit());
				break;
			case 1: 
				specialSymbols.push(generateSpecialSymbol());
				break;
			case 2: 
				uppercase.push(generateUppercase());
				break;
			case 3:
				lowercase.push(generateLowercase());
				break;
			}
		}
		
		passwordElements.add(digits);
		passwordElements.add(specialSymbols);
		passwordElements.add(uppercase);
		passwordElements.add(lowercase);
		
		for(int i = 0 ; i < lengthOfPassword ; i++) {
			passwordElementIndexInArray = rnd.nextInt(passwordElements.size());
			password+=(passwordElements.get(passwordElementIndexInArray).pop());
			if(passwordElements.get(passwordElementIndexInArray).empty()) {
				passwordElements.remove(passwordElementIndexInArray);
			}
		}
		
		return password;
	}
}