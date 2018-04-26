package csantiagolab2;
class ContactListEntry{

	private String name;
	private String phoneNumber;
	private String email;


	public ContactListEntry(String name, String phoneNumber, String email){
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public ContactListEntry(){
		this.name = null;
		this.phoneNumber = null;
		this.email = null;
	}

	public void setName(String name){this.name = name;}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	public void setEmail(String email){this.email = email;}

	public String getName(){return this.name;}
	public String getPhoneNumber(){return this.phoneNumber;}
	public String getEmail(){return this.email;}

	public String format() {
		return "Name: " + getName() + " Phone Number: " + getPhoneNumber() + " Email: " + getEmail();
	}

}
