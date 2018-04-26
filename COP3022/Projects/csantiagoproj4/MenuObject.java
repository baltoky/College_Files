package csantiagoproj4;

public class MenuObject{

	public class Money{
		private long dollars;
		private int cents;

		public Money(){
			this.dollars = 0;
			this.cents = 0;
		}
		public Money(long dollars, int cents){
			this.dollars = 0;
			this.cents = 0;
			setMoney(dollars, cents);
		}

		public void setMoney(long dollars, int cents){
			this.cents += cents;
			this.dollars += dollars;
			if(this.cents >= 100){
				this.dollars += this.cents / 100;
				this.cents = this.cents % 100;
			}
		}
		public long getDollars(){return this.dollars;}
		public int getCents(){return this.cents;}

		public String toString(){
			return "$" + dollars + "." + cents;
		}
	}

	private String name;
	private Money cost;

	public MenuObject(){
		setName(null);
		setCost(0, 0);
	}
	public MenuObject(String name, long dollars, int cents){
		setName(name);
		setCost(dollars, cents);
	}

	public void setName(String name){this.name = name;}
	public void setCost(long dollars, int cents){
		this.cost = new Money(dollars, cents);
	}

	public String getName(){return this.name;}
	public String getCost(){return this.cost.toString();}

	public String toString(){
		return "" + getName() + ", " + getCost();
	}
}
