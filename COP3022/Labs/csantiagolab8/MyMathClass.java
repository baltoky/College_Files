package csantiagolab8;

import java.lang.Number;
import java.util.ArrayList;

public class MyMathClass <T extends Number>{
	public double average(ArrayList<T> arr){
		double avg = 0;
		for(T t: arr){
			avg += t.doubleValue();
		}
		return (avg / arr.size());
	}

	public double stdDeviation(ArrayList<T> arr){
		double Sum_i = 0;
		double avg = this.average(arr);
		for(T t: arr){
			Sum_i += Math.abs(t.doubleValue() - avg);
		}
		return (Math.sqrt(Sum_i) / arr.size());
	}
}
