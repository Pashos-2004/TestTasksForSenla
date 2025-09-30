package gallowsGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;


public class GallowsGame {

	static final String PATH_FOR_FILE_WITH_WORDS = "./src/gallowsGame/words/words.txt";
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<String> words = readWordsFromFile();
		Random rnd = new Random();
		boolean continueGame = true;
	
		while(continueGame) {
			StartGame(words.get(rnd.nextInt(words.size())));
			System.out.println("Продолжит играть? (введите \"Д\" для подтверждения, любой другой символ для отказа)");
			System.out.print(">> ");
			input = new Scanner(System.in);
			continueGame = input.nextLine().equals("Д");
		}
		System.out.println("Завершение работы!");
		input.close();
	}
	
	public static void StartGame(String word) {
		Scanner input = new Scanner(System.in);
		ArrayList<Character> usedLetters = new ArrayList<Character>();
		int lifes = 7;
		boolean[] isLetterGuessed = new boolean[word.length()];
		for(int i = 0; i < isLetterGuessed.length; i++) isLetterGuessed[i] = false; 
		boolean isWordGuessed = false;
		String letter = "";
		boolean isNewLetterOpened = false;
				
		while(!isWordGuessed && lifes>0) {
			isNewLetterOpened = false;
			printGallows(lifes);
			System.out.print("\nОсталось жизней : "+lifes);
			System.out.print("\n\nЗагаданное слово: ");
			for(int i = 0; i < isLetterGuessed.length; i++) 
				if(isLetterGuessed[i]) System.out.print(word.charAt(i)+" "); 
				else System.out.print("_ ");
			System.out.print("\n\nВведите букву : ");
			letter = input.nextLine();
			
			if(letter.length()>1 || letter.equals("")) continue;
			if((int) letter.charAt(0) >= (int)'А' && (int) letter.charAt(0) <= (int)'А'+33) letter = ""+ (char)((int) letter.charAt(0) - ((int) 'А' - (int) 'а') );
			if((int) letter.charAt(0) < (int)'а' || (int) letter.charAt(0) > (int)'а'+33) continue;
			if(usedLetters.contains(letter.charAt(0))) continue;
			else usedLetters.add(letter.charAt(0)); 
			
			for(int i = 0; i < word.length(); i++) {
				if(word.charAt(i) == letter.charAt(0)) {
					isNewLetterOpened = true;
					isLetterGuessed[i] = true;		
				}
			}
			if(!isNewLetterOpened) lifes-=1;
			isWordGuessed = true;
			for(int i = 0; i < isLetterGuessed.length; i ++) isWordGuessed &= isLetterGuessed[i];
		}
		
		printGallows(lifes);
		if(isWordGuessed) System.out.println("\nВы угадали слово : " + word);
		else System.out.println("Вы проиграли.\n" + "Загаданное словов : " + word);
		
	}
	
	public static void printGallows(int lifes) {
		System.out.print("____\n");
		System.out.print("|");
		if(lifes<=6) System.out.print("   |");
		System.out.print("\n");
		System.out.print("|");
		if(lifes<=5) System.out.print("   o");
		System.out.print("\n");
		System.out.print("|");
		if(lifes==4) System.out.print("   O");
		else if (lifes<=4) System.out.print("  /O");
		if(lifes<=2) System.out.print("\\");
		System.out.print("\n");
		System.out.print("|");
		if(lifes<=1) System.out.print("  /");
		if(lifes<=0) System.out.print(" \\");
		System.out.print("\n");
		System.out.print("|");
		System.out.print("_____\n");
	}
	
	
	public static ArrayList<String> readWordsFromFile() {
		ArrayList<String> words = new ArrayList<String>();
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader(PATH_FOR_FILE_WITH_WORDS));
			String line;
			while ((line = reader.readLine()) != null) words.add(line.toLowerCase());
		}
		catch(IOException ex){
			System.out.println("Ошибка загрузки слов, проверьте файл");
		}   
		
		return words;
	}

}
