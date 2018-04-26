package csantiagolab4;
import java.util.*;

class UserTester{

	public static void main(String [] args){
		Scanner scan = new Scanner(System.in);
		User u = new User();

		System.out.println("Enter your age: ");
		String temp = scan.next();
		u.setAge(Integer.parseInt(temp));

		System.out.println("Enter your salary: ");
		do{
			temp = scan.next();
		}
		while(!u.setSalary(temp));

		System.out.println("Enter a valid email: ");
		do{
			temp = scan.next();
		}while(!u.setEmail(temp));

		u.setHackerName();

		System.out.println("Age: "+ u.getAge() + "\n");
		System.out.println("Salary: "+ u.getSalary() + "\n");
		System.out.println("Email: "+ u.getEmail() + "\n");
		System.out.println("Hacker Name: "+ u.getHackerName() + "\n");

	}
}
