package csantiagolab8;

import java.util.ArrayList;

public class MyMathClassTester{
	public static void main(String[] args){
		ArrayList<Integer> I = new ArrayList<Integer>();
		ArrayList<Double> D = new ArrayList<Double>();
		ArrayList<Long> L = new ArrayList<Long>();

		MyMathClass<Integer> I1 = new MyMathClass<Integer>();
		MyMathClass<Double> D1 = new MyMathClass<Double>();
		MyMathClass<Long> L1 = new MyMathClass<Long>();
		//MyMathClass<String> S1 = new MyMathClass<String>();
		
		double I2 = 0, D2 = 0, L2 = 0;
		double I3 = 0, D3 = 0, L3 = 0;

		I.add(52);
		I.add(38);
		I.add(75);

		D.add(12.45);
		D.add(92.32);
		D.add(77.54);

		L.add(12352L);
		L.add(771232L);
		L.add(56923L);

		I2 = I1.average(I);
		D2 = D1.average(D);
		L2 = L1.average(L);

		I3 = I1.stdDeviation(I);
		D3 = D1.stdDeviation(D);
		L3 = L1.stdDeviation(L);

		System.out.printf("Integer [%f], Double [%f], Long [%f]%n",
				I3, D3, L3);
	}
}

/*
 * For the String variable in a MyMathClass object it throws a syntax error
 * 	saying: "type argument String is not within bounds of type-
 * 	variable T". 
 * 	We strictly expect a Number object.
 * 	Therefore we cannot input a String object in the MyMathClass object.
 * */
