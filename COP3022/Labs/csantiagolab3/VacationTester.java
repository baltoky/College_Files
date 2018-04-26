package csantiagolab3;

import java.util.*;

public class VacationTester{
	public static void main(String[] args){

		Vacation v1 = new AllInclusive("Havana/Cuba", 3000.0, "Sandals", 5, 2600.0);
		Vacation v2 = new ALaCarte("Denver/Colorado", 5000.0, "HotelInn", 70.4, "AmericanAirlines", 1549.96, 2642.88);

		System.out.println(v1);
		System.out.println(v2);

	}
}
