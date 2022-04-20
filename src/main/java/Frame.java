import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;




public class Frame extends JFrame{

	Frame(){
		
		JPanel panell = new JPanel();
		JPanel panel1 = new JPanel();

		JFrame frame = new JFrame("Worm Game"); 
		JFrame frame1 = new JFrame("Worm Game-Running "); 
	

		JButton play = new JButton("Start Game");
        JButton exit = new JButton("Exit");

        panell.add(play);
		panel1.add(exit);

		
		frame.add(panell, BorderLayout.CENTER);
		frame.add(panel1, BorderLayout.SOUTH);
        
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
        


		play.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
			
			  frame.setVisible(false);
			  frame1.add(new Game());
			  frame1.pack();
			  frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      frame1.setVisible(true);
		      frame1.setLocationRelativeTo(null);
			  
			}});

		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { 
			  System.exit(0); 
			}});

	}

	
}