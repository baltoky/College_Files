package csantiagoproj4;

import java.util.*;
import java.io.*;

public class Customer{
	private static int IDnumber = 1000;
	private ArrayList<MenuObject> pack;
	private String name;
	private String address;
	private String phoneNumber;
	private int customerID;

	public Customer(){
		this.pack = new ArrayList<MenuObject>();
		setName("");
		setAddress("");
		setPhoneNumber("");
		setCustomerID();
	}
	public Customer(String name, String address, String phoneNumber){
		this.pack = new ArrayList<MenuObject>();
		setName(name);
		setAddress(address);
		setPhoneNumber(phoneNumber);
		setCustomerID();
	}
	public void addToPack(MenuObject option){
		this.pack.add(option);
	}
	public MenuObject getFromPack(int index){
		return this.pack.get(index);
	}

	public void setPack(ArrayList<MenuObject> pack){this.pack = pack;}
	public void setName(String name){this.name = name;}
	public void setAddress(String address){this.address = address;}
	public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
	public void setCustomerID(){this.customerID = IDnumber++;}

	public ArrayList<MenuObject> getPack(){return this.pack;}
	public String getName(){return this.name;}
	public String getAddress(){return this.address;}
	public String getPhoneNumber(){return this.phoneNumber;}
	public int getCustomerID(){return this.customerID;}

	public void printCustomerBill(){
		for(MenuObject o: pack){
			System.out.println(o.toString());
		}
	}

	public void printToFile(String filepath){
		try{
			FileOutputStream file = new FileOutputStream(filepath);
			byte[] data = null;
			String cust = null;
			for(MenuObject o: pack){
				cust += o.toString() + "\n";
			}
			data = cust.getBytes();
			file.write(data);

		}catch(FileNotFoundException e){
			System.out.println("No file was found.");
		}catch(IOException e){
			System.out.println("Could not write to file");
		}
	}

}
