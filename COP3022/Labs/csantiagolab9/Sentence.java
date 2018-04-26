package csantiagolab9;

/**
 * Sentence Class
 * */
public class Sentence{
	private String sentence;

	/**
	 * Sentence default constructor.
	 * */
	public Sentence(){
		this.sentence = null;
	}

	/**
	 * Sentence constructor
	 * @param String sentence
	 * */
	public Sentence(String sentence){
		this.sentence = sentence;
	}

	/**
	 * Sentence mutator method for the sentence variable
	 * @param String sentence
	 * */
	public void setSentence(String sentence){this.sentence = sentence;}

	/**
	 * Sentence accessor method for the sentence variable
	 * */
	public String getSentence(){return this.sentence;}

	/**
	 * Find method
	 * @param String subSentence
	 * @param int position
	 * @return whether the substrign was found within the sentence.
	 * */
	public boolean find(String subSentence, int position){
		if(this.sentence.substring(position, position + subSentence.length()).compareTo(subSentence) == 0){
			return true;
		}
		else if(position != 0){
			return find(subSentence, position - 1);
		}
		else{
			return false;
		}
	}

	/**
	 * IndexOf method
	 * @param String subSentence
	 * @param int position
	 * @return the index position where the substring starts within a given sentence,
	 * 	returns -1 if not found, otherwise it is an unsigned integer.
	 * */
	public int indexOf(String subSentence, int position){
		if(this.sentence.substring(position, position + subSentence.length()).compareTo(subSentence) == 0){
			return position;
		}
		else if(position + subSentence.length() != this.sentence.length()){
			return indexOf(subSentence, position + 1);
		}
		else{
			return -1;
		}
	}

}
