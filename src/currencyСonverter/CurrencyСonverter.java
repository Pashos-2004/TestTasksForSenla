package currencyСonverter;

import java.util.Scanner;


public class CurrencyСonverter {
	

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int currencyIndex1 = 1;
		int currencyIndex2 = -2;
		double amountOfCurrency = 0.0;
		String temp  = "";
		Currency[] currencies = {
				new Currency("RUB", "Российский рубль", 1.0),
				new Currency("USD", "Доллар США", 83.6118),
				new Currency("EUR", "Евро", 97.6823),
				new Currency("CNY", "Юань", 11.6751),
				new Currency("KZT", "Тенге", 0.154356)
		};
		
		while(currencyIndex1!=-1) {
			try {
				System.out.println("Выберите валюту для конвертирования");
				printAllCurrenciesFromArray(currencies);
				System.out.println("0. Выход");
				System.out.print("<< ");
				currencyIndex1=input.nextInt()-1;
				
				if(currencyIndex1 > -1 && currencyIndex1<=currencies.length) {
					do {
					try {	
							System.out.println("Выберите вторую валюту");
							printAllCurrenciesFromArray(currencies);
							System.out.print("<< ");
							currencyIndex2=input.nextInt()-1;
							if(currencyIndex2 < 0 || currencyIndex2>currencies.length) System.out.println("Такого пункта нет, повторите ввод:");
						}
					catch (Exception e) {
						currencyIndex2 = -2;
						input.nextLine();
						System.out.println("Ошибка ввода");
						}
					} while (currencyIndex2 < 0 || currencyIndex2>currencies.length);
					
					do {
						try {
								System.out.print("Выведите количество валюты >> ");
								input = new Scanner(System.in);
								temp = input.nextLine();
								amountOfCurrency = Double.parseDouble(temp.replace(',', '.'));
								if(amountOfCurrency<=0) System.out.println("Количество должно быть больше 0");
						}catch (Exception e) {
							amountOfCurrency = 0 ;
							System.out.println("Ошибка ввода");
						}
					} while(amountOfCurrency<=0);
					
					System.out.println(currencies[currencyIndex1].getShortName()+ " : "+amountOfCurrency + " = " + currencies[currencyIndex2].getShortName() 
							+" : "+currencies[currencyIndex1].convertToAnotherCurrency(amountOfCurrency, currencies[currencyIndex2]));
					System.out.println("Нажмите enter для продлолжения");
					input.nextLine();
					
				}else if(currencyIndex1!=-1){
					System.out.println("Такого пункта нет, повторите ввод:");
				}
				
			}catch (Exception e) {
				currencyIndex1 = 1;
				input.nextLine();
				System.out.println("Ошибка ввода");
			}
			
		}
		
		System.out.println("Работа завершена!");
		input.close();
	}
	
	public static void printAllCurrenciesFromArray(Currency[] currencies) {
		for(int i = 0 ; i < currencies.length ; i++) {
			System.out.println((i+1)+". "+currencies[i].getShortName()+" ("+currencies[i].getFullName()+")");
		}
	}
	
}

class Currency{
	
	private String shortName;
	private String fullName;
	private double exchangeRateToRuble;
	
	public Currency(String shortName, String fullName, double exchangeRateToRuble) {
		this.shortName = shortName;
		this.fullName = fullName;
		this.exchangeRateToRuble = exchangeRateToRuble;
	}

	
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public void setExchangeRateToRuble(Double exchangeRateToRuble) {
		this.exchangeRateToRuble = exchangeRateToRuble;
	}
	
	public String getShortName() {
		return this.shortName;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
	public double getExchangeRateToRuble() {
		return this.exchangeRateToRuble;
	}
	
	public double convertToAnotherCurrency(double amountOfCurrency, Currency newCurrency) {
		if(newCurrency.equals(this)) return amountOfCurrency;
		return amountOfCurrency*exchangeRateToRuble/newCurrency.getExchangeRateToRuble();
	}
	
}
