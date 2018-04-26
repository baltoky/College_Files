package csantiagolab10;

// JComboBox that displays a list of image names.
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class ComboBoxFrame extends JFrame 
{
   private static final long serialVersionUID = 1L;
   private JComboBox<String> imagesJComboBox; // combobox to hold names of icons
   private JPanel labelPanel, boxPanel;
   private JLabel label; // label to display selected icon

   private static final String[] names = 
   { "747.jpg", "707.jpg",  "Concorde.jpg" };
  

// ComboBoxFrame constructor adds JComboBox to JFrame
   public ComboBoxFrame()
   {
	
	//Sets title of the window.
	setTitle("Combo Box Lab 10");

	//Sets the type of layout.
	setLayout(new FlowLayout());

	//Builds both panels.
	buildLabelPanel();
	buildBoxPanel();

	//Adds the panels to the window instance.
	add(labelPanel);
	add(boxPanel);

	//Packs and sets visible.
	pack();
	setVisible(true);
   
   } // end ComboBoxFrame constructor

   /**
    * Builds the panel that controls the ComboBox with an aciton listener.
    * */
   public void buildBoxPanel(){
	   //Creates a new JPanel instance.
	   boxPanel = new JPanel();

	   //Creates a new JComboBox instance with the names as a parameter.
	   imagesJComboBox = new JComboBox<String>(names);

	   //Sets the action listener as the class further explained.
	   imagesJComboBox.addActionListener(new ImageComboListener());

	   //Adds the box to the panel.
	   boxPanel.add(imagesJComboBox);
   }
   	
    /**
    * Builds a panel that controls the position of the label to be used as an image.
    * */
   public void buildLabelPanel(){
	   //Creates a new JPanel instance.
	   labelPanel = new JPanel();

	   //Creates a new JLabel instance.
	   label = new JLabel();

	   //Adds the label to the panel.
	   labelPanel.add(label);
   }

   /**
    * Listener class for the ComboBox.
    * */
   public class ImageComboListener implements ActionListener{
	   public void actionPerformed(ActionEvent e){
		   //Sets the current selection on the comboBox to the selection string.
		   String selection = (String) imagesJComboBox.getSelectedItem();

		   //Sets the icon to an image described by a string.
		   label.setIcon(new ImageIcon(getClass().getResource(selection)));
	   }
   }

   /**
    * Main method that will run a window.
    * */
   public static void main(String[] args){
	   ComboBoxFrame cbf = new ComboBoxFrame();
   }

} // end class ComboBoxFrame


