package csantiagoproj4;

import java.util.*;
import java.io.*;

public class Menu{

	private ArrayList<MenuObject> options;
	private String menuOutput;

	public Menu(){
		this.options = new ArrayList<MenuObject>();
	}

	public void addOption(MenuObject option){
		this.options.add(option);
	}
	public MenuObject getOption(int index){
		return this.options.get(index);
	}

	public ArrayList<MenuObject> useMenu(){
		String menu = menuOutput;
		String submenu = menu;
		ArrayList<MenuObject> output = new ArrayList<MenuObject>();
		int tempA = 0, tempB = 0, tempC = 0, tempD = 0;
		Scanner scan = new Scanner(System.in);

		do{
			tempA = menu.indexOf('-');
			menu = menu.substring(tempA + 1);
			if(tempA > 0){
				try{
				tempB = Character.getNumericValue(menu.charAt(0));
				submenu = menu.substring(1, menu.indexOf('-'));
				System.out.println(submenu + "Please input a number 1 - " + tempB + ":");
				}catch(IndexOutOfBoundsException e){
					System.out.println(menu + "Please input a number 1 - " + tempB + ":");
					menu = "";
				}
			}else{
				System.out.println(menu + "Please input a number 1 - " + tempB + ":");
			}
			tempC = scan.nextInt() - 1;
			output.add(this.getOption(tempC + tempD));
			tempD += tempB;
			System.out.println("\n");
		}while(!menu.isEmpty());

		return output;
	}

	public void readFromFile(String filepath){
		String menuPrint = "";

		int i = 0;
		try{

			String tempStr;
			int tempIntA, tempIntB;
			FileInputStream file = new FileInputStream(filepath);
			Scanner scan = new Scanner(file);
			while(scan.hasNext()){
				tempStr = scan.nextLine();
				if(tempStr.charAt(0) != '-'){
					tempIntA = tempStr.indexOf('$');
					tempIntB = tempStr.indexOf(',');
					options.add(
						new MenuObject(tempStr.substring(0, tempIntA),
							(long)Integer.parseInt(tempStr.substring(tempIntA + 1, tempIntB)),
							Integer.parseInt(tempStr.substring(tempIntB + 1))));
					menuPrint += tempStr + "\n";
				}else{
					menuPrint += "\n" + tempStr + "\n";
				}
			}

		}catch(FileNotFoundException e){
			System.out.println("Could not find file: " + filepath);
		}catch(NumberFormatException e){
			System.out.println("The file " + filepath + " is corrupted.");
		}
		this.menuOutput = menuPrint;
	}
}
