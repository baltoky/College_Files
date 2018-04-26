package csantiagolab10;

import javax.swing.JFrame;

public class ComboBoxWindowTest
{
   public static void main( String[] args )
   { 
      ComboBoxWindow comboBoxWindow = new ComboBoxWindow(); 
      comboBoxWindow.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      comboBoxWindow.setSize( 500, 400 ); // set frame size
      comboBoxWindow.setVisible( true ); // display frame
   } // end main
} // end class ComboBoxTest
