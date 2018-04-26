package csantiagolab4;

class User{
	private int age;
	private double salary;
	private String email;
	private String hackerName;

	public User(){
		this.age = 0;
		this.salary = 0.0;
		this.email = "";
	}

	public void setAge(int age){this.age = age;}

	public boolean setSalary(String salary){
		try{
			this.salary = Double.parseDouble(salary);
		}
		catch(NumberFormatException e){
			System.out.println("Please give accurate salary.\n");
			return false;
		}
		return true;
	}

	public boolean setEmail(String email){
		try{
			if(!(email.contains("@")) || email.charAt(0) == '@' || email.charAt(email.length() - 1) == '@'){
				throw new MalformedEmailAddressException();
			}
			this.email = email;
		}catch(MalformedEmailAddressException e){
			System.out.println("Please enter a valid e-mail address.\n");
			return false;
		}
		return true;
	}

	public void setHackerName(){
		this.hackerName = this.getEmail().substring(0, this.email.indexOf('@'));
		this.hackerName += age;
	}

	public int getAge(){return this.age;}
	public double getSalary(){return this.salary;}
	public String getEmail(){return this.email;}
	public String getHackerName(){return this.hackerName;}

	

}
