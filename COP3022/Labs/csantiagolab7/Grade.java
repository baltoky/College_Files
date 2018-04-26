package csantiagolab7;

public class Grade{
	private byte studentID;
	private double grade;

	public Grade(){
		setStudentID((byte)0);
		setGrade(0.0);
	}
	public Grade(byte studentID, double grade){
		setStudentID(studentID);
		setGrade(grade);
	}

	public void setStudentID(byte studentID){this.studentID = studentID;}
	public void setGrade(double grade){this.grade = grade;}

	public byte getStudentID(){return this.studentID;}
	public double getGrade(){return this.grade;}

	public String toString(){
		return "Student" + getStudentID() + "'s grade is: " + getGrade();
	}

}
