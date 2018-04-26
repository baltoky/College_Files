package csantiagoproj4;

import java.util.*;

public class MenuTester{
	public static void main(String[] args){

		Menu m = new Menu();
		Customer c = setCustomer();
		m.readFromFile("csantiagoproj4/packOptions.txt");
		c.setPack(m.useMenu());
		c.printCustomerBill();
		c.printToFile("csantiagoproj4/Customer.txt");

	}

	public static Customer setCustomer(){
		String name;
		String address;
		String phoneNumber;

		Scanner scan = new Scanner(System.in);
		System.out.println("Input your name: ");
		name = scan.nextLine();
		System.out.println("Input your address (Street City State, zip): ");
		address = scan.nextLine();
		System.out.println("Input your phone number: ");
		phoneNumber = scan.nextLine();

		Customer c = new Customer(name, address, phoneNumber);
		return c;
	}
}
