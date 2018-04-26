package csantiagoproj3;

public abstract class Player{
	private String name;
	private int IDNum;

	private static int nextIDNum = 1000;


	public Player(){
		this.name = "";
		this.IDNum = nextIDNum++;
	}
	public Player(String name){
		this.name = name;
		this.IDNum = nextIDNum++;
	}

	public void setName(String name){this.name = name;}
	public void setIDNum(){this.IDNum = nextIDNum++;}

	public String getName(){return this.name;}
	public int getIDNum(){return this.IDNum;}

	public String toString(){
		return "Name: " + this.name + ", ID: " + this.IDNum + "\n";
	}

	public abstract double calculateHandicap();
		
}
