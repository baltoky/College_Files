package csantiagoproj1;

class GolferTester{

	//public Score(String courseName, int score, String date, double courseRating, int courseSlope){
	//public Golfer(String name, String homeCourse, int inNumber, Score score){
	public static String newDate(int month, int day, int year){
		String date = "" + month + "/" + day + "/" + year;
		return date;
	}

	public static void main(String[] args){

		Score s1 = new Score("Bay Hill CC", 75, newDate(6, 3, 12), 69.5, 123);
		Score s2 = new Score("AC Read\t", 77, newDate(7, 23, 12), 70.4, 128);

		Golfer g1 = new Golfer("John Smith", "Bay Hill CC", s1);
		g1.addScore(s2);

		Score s3 = new Score("Bay Hill CC", 65, newDate(6, 13, 12), 68.3, 103);
		Score s4 = new Score("AC Read\t", 88, newDate(7, 23, 12), 79.4, 78);

		Golfer g2 = new Golfer("John Doe", "AC Read\t", s3);
		g2.addScore(s4);
		String lowestScoreG2 = "" + g2.lowestScore().toString();

		System.out.println( g1.toString() );
		System.out.println( g2.toString() );
		System.out.println( "Lowest Score for " + g2.getName() + " :\n" + lowestScoreG2);

	}

}
