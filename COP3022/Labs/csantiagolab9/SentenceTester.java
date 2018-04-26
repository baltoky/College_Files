package csantiagolab9;

public class SentenceTester{
	public static void main(String args[]){

		final int TO_FIND = 5;
		Sentence s = new Sentence("I like pancakes with peanut butter on top.");
		String to_find[] = new String[TO_FIND];
		to_find[0] = "pan";
		to_find[1] = "I";
		to_find[2] = "after";
		to_find[3] = "top";
		to_find[4] = "later";

		for(int i = 0; i < TO_FIND; i++){
			System.out.printf("Sub-sentence to find is %s, [%b]%n", to_find[i],
					s.find(to_find[i], s.getSentence().length() - to_find[i].length()));
			System.out.printf("In index: [%d]%n",
					s.indexOf(to_find[i], 0));
		}

	}
}
