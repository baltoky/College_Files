package csantiagolab4;

class MalformedEmailAddressException extends Exception{
	private static final long serialVersionUID = 100000;

	public MalformedEmailAddressException(){
		super("MalformedEmailAddressException thrown.\n");
	}
}
