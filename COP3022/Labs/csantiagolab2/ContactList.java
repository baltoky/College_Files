package csantiagolab2;
import java.util.ArrayList;

class ContactList{

	public static final int ARR_SIZE = 5;
	public static void main(String[] args){
		ArrayList<ContactListEntry> list = new ArrayList<ContactListEntry>(ARR_SIZE);

		list.add(new ContactListEntry("Cesar0", "0", "cs0@uwf.edu"));
		list.add(new ContactListEntry("Cesar1", "1", "cs1@uwf.edu"));
		list.add(new ContactListEntry("Cesar2", "2", "cs2@uwf.edu"));
		list.add(new ContactListEntry("Cesar3", "3", "cs3@uwf.edu"));
		list.add(new ContactListEntry("Cesar4", "4", "cs2@uwf.edu"));

		for(int i = 0; i < list.size(); i++)
			System.out.println(list.get(i).format());
		System.out.println("\n");

		list.remove(0);

		for(ContactListEntry contact : list)
			System.out.println(contact.format());
		System.out.println("\n");

		list.add(1, new ContactListEntry("CesarNew2", "0-2", "csn2@uwf.edu"));
		list.add(1, new ContactListEntry("CesarNew3", "0-3", "csn3@uwf.edu"));

		for(ContactListEntry contact : list)
			System.out.println(contact.format());
		System.out.println("\n");

		list.set(list.size() - 1, new ContactListEntry("CesarLast", "10", "last@uwf.edu"));

		for(int i = 0; i < list.size(); i++)
			System.out.println(list.get(i).format());
		System.out.println("\n");

	}

}
