import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.awt.Image;
import java.awt.BorderLayout;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;





public class Game extends JPanel implements ActionListener{

	static final int windowWidth = 1500;
	static final int windowHeight = 750;
	static final int pixel = 50;
	static final int pixelSquare = (windowWidth*windowHeight)/(pixel*pixel);
	static final int DELAY = 175;

	final int x[] = new int[pixelSquare];
	final int y[] = new int[pixelSquare];

	
	int over = 0;

	int score = 0;
	int bodyLength = 5;
	int foodX;
	int foodY;
	
	Random random = new Random();

    int x1 = random.nextInt((int)(windowWidth/pixel))*pixel;
	int y1 = random.nextInt((int)(windowHeight/pixel))*pixel;
	int x2 = random.nextInt((int)(windowWidth/pixel))*pixel;
	int y2 = random.nextInt((int)(windowHeight/pixel))*pixel;

	int x3 = random.nextInt((int)(windowWidth/pixel))*pixel;
	int y3 = random.nextInt((int)(windowHeight/pixel))*pixel;
	int x4 = random.nextInt((int)(windowWidth/pixel))*pixel;
	int y4 = random.nextInt((int)(windowHeight/pixel))*pixel;	

	Mushroom m1 = new Mushroom(5, x1, y1);
	Mushroom m2 = new Mushroom(15, x2, y2);

	Superfood s1 = new Superfood(20, x3, y3);
	Superfood s2 = new Superfood(5, x4, y4);

	Image i, mi1, mi2, im, ii, iii, si1, si2;
	

    Image image = new ImageIcon("src/main/resources/leaf.jpg").getImage();
    Image leaf = image.getScaledInstance(pixel, pixel, Image.SCALE_DEFAULT);
    

	char direction = 'D';
	boolean running = false;
	Timer timer;
	
	{
	
	if(m1.deadly())
	{
		 i = new ImageIcon("src/main/resources/deadly.jpg").getImage();
	    
	}
	else{
		 i = new ImageIcon("src/main/resources/poison.jpg").getImage();
		
	}

	

	if(m2.deadly())
	{
		 im = new ImageIcon("src/main/resources/deadly.jpg").getImage();
	   
	}
	else{
		 im = new ImageIcon("src/main/resources/poison.jpg").getImage();
		
	}

	mi1 = i.getScaledInstance(pixel, pixel, Image.SCALE_DEFAULT);
	mi2 = im.getScaledInstance(pixel, pixel, Image.SCALE_DEFAULT);


	if(s1.friend())
	{
		 ii = new ImageIcon("src/main/resources/worm.jpg").getImage();
	    
	}
	if(s1.leaves()){
		ii = new ImageIcon("src/main/resources/leaves.jpg").getImage();

	}
	
	if(s2.friend())
	{
		 iii = new ImageIcon("src/main/resources/worm.jpg").getImage();
	    
	}
	if(s2.leaves()){
		iii = new ImageIcon("src/main/resources/leaves.jpg").getImage();

	}	
	
	si1 = ii.getScaledInstance(pixel, pixel, Image.SCALE_DEFAULT);
	si2 = iii.getScaledInstance(pixel, pixel, Image.SCALE_DEFAULT);

    }


	Game(){
		
		this.setPreferredSize(new Dimension(windowWidth,windowHeight));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() {
		newFood();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		if(running) {
			
            g.drawImage(leaf, foodX, foodY, null);

			
			g.drawImage(mi1, m1.mushroomX, m1.mushroomY, null);
			g.drawImage(mi2, m2.mushroomX, m2.mushroomY, null);
			

			if(score <= -10){
				g.drawImage(si1, s1.bonusX, s1.bonusY, null);
				
			}
           
			if(score <= -5 ){
				g.drawImage(si2, s2.bonusX, s2.bonusY, null);
				
			}
		
		
			for(int i = 0; i< bodyLength;i++) {
				if(i == 0) {
					g.setColor(new Color(249, 65, 96));
					g.fillRect(x[i], y[i], pixel, pixel);             
				}
				else  {
				     g.setColor(new Color(250,117,139));
				     g.fillRect(x[i], y[i], pixel, pixel);     
				}     
                		
			}

			

			g.setColor(Color.white);
			g.setFont( new Font("Consolas",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+score, (windowWidth - metrics.stringWidth("Score: "+score))/2, g.getFont().getSize());
		}
		else {
			gameOver(g);
			
		}
		
	}
	
	public void move(){
		for(int i = bodyLength;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - pixel;
			break;
		case 'D':
			y[0] = y[0] + pixel;
			break;
		case 'L':
			x[0] = x[0] - pixel;
			break;
		case 'R':
			x[0] = x[0] + pixel;
			break;
		}
		
	}

    public void newFood(){
		foodX = random.nextInt((int)(windowWidth/pixel))*pixel;
		foodY = random.nextInt((int)(windowHeight/pixel))*pixel;
	}

	public void newMushroom1(){
		m1.mushroomX = random.nextInt((int)(windowWidth/pixel))*pixel;
		m1.mushroomY = random.nextInt((int)(windowHeight/pixel))*pixel;
	}
	public void newMushroom2(){
		m2.mushroomX = random.nextInt((int)(windowWidth/pixel))*pixel;
		m2.mushroomY = random.nextInt((int)(windowHeight/pixel))*pixel;
	}

	public void newSuper1(){
		s1.bonusX = random.nextInt((int)(windowWidth/pixel))*pixel;
		s1.bonusY= random.nextInt((int)(windowHeight/pixel))*pixel;
	}
	public void newSuper2(){
		s2.bonusX = random.nextInt((int)(windowWidth/pixel))*pixel;
		s2.bonusY= random.nextInt((int)(windowHeight/pixel))*pixel;
	}

	public void Scoring() {
		if((x[0] == foodX) && (y[0] == foodY)) {
			bodyLength++;
			score++;
			newFood();
		}

		else if((x[0] == m1.mushroomX) && (y[0] == m1.mushroomY)){
			if(m1.deadly()){
				score = score - m1.poisonLevel;
			    running = false;
			}else{
				score = score - m1.poisonLevel;
			    newMushroom1();
			}
		}
	     


	   else if((x[0] == m2.mushroomX) && (y[0] == m2.mushroomY)){
			if(m2.deadly()){
			    score = score - m2.poisonLevel;
			    running = false;
			}else{
				score = score - m2.poisonLevel;
			    newMushroom2();
			}
			
		}


		else if((x[0] == s1.bonusX) && (y[0] == s1.bonusY)){
			if(s1.friend()){
			    score = score + s1.bonus;
		        newSuper1();
			}
			else{
				score = score + s1.bonus;
			    newSuper1();
			}
			
		}
		
	    else if((x[0] == s2.bonusX) && (y[0] == s2.bonusY)){
			if(s2.friend()){
			    score = score + s2.bonus;
		        newSuper2();
			}
			else {
				score = score + s2.bonus;
			    newSuper2();
			}
			
		}
		

	}
	public void checkCollisions() {
		
		for(int i = bodyLength;i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				running = false;
			}
		}
		
		if(x[0] < 0) {
			running = false;
		}
		
		if(x[0] > windowWidth) {
			running = false;
		}
		
		if(y[0] < 0) {
			running = false;
		}
		
		if(y[0] > windowHeight) {
			running = false;
		}
		
		if(!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont( new Font("Consolas",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+score, (windowWidth - metrics1.stringWidth("Score: "+score))/2, g.getFont().getSize());
		
		g.setColor(Color.red);
		g.setFont( new Font("Consolas",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (windowWidth - metrics2.stringWidth("Game Over"))/2, windowHeight/2);

		
		over = 1;

		if(over==1){
			GameOverFrame();
		}
	}
	

	public ArrayList<User> uList(){
        ArrayList<User> list = new ArrayList<User>();
    
        try{
    
			int nr = 1;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/worm", "root", "");
            Statement stmt = null;
            String query = "SELECT * FROM score_db";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
    
            User user;
    
            while(rs.next()){
                user = new User(rs.getString("player"), rs.getInt("score"));
                list.add(user);
				nr++;
            }

			String insert = "INSERT INTO score_db VALUES (?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(insert);
           
            int id = nr+1;
            int sc = score;
            

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, sc);
        
            pstmt.execute();
			User u = new User(name, sc);
            list.add(u);
    
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    
        return list;
    }
    


    public void ScoreFrame(){

		
        ArrayList<User> list = uList();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Player");
        model.addColumn("Score");
        Object[] row = new Object[2];
    
        for (int i=0; i<list.size(); i++) {
          row[0] = list.get(i).getplayer();
          row[1] = list.get(i).getscore();

          model.addRow(row);
          }
    

		  JButton exit = new JButton("Exit");
          JLabel c1 = new JLabel("Player");
          JLabel c2 = new JLabel("Score");

          JTable table = new JTable(model);
          JFrame score = new JFrame("Score table");
          JPanel p = new JPanel();
          JPanel p1 = new JPanel();
		  JPanel p2 = new JPanel();

          p1.add(c1);
          p1.add(c2);

          p.add(table);
		  p2.add(exit);
          score.add(p1, BorderLayout.NORTH);
          score.add(p, BorderLayout.CENTER);
		  score.add(p2, BorderLayout.SOUTH);
          score.pack();
		  score.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  score.setVisible(true);
		  score.setLocationRelativeTo(null);

		  exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { 
			  System.exit(0); 
			}});
    }
    
	

	String name = new String();
	public void GameOverFrame(){
		JFrame gameover = new JFrame("Game Over");

		JLabel label = new JLabel("Enter username");
		JTextField username = new JTextField();
		username.setPreferredSize(new Dimension(200, 24));
        JButton send = new JButton("Submit");
		JButton exit = new JButton("Exit");
		JPanel pan = new JPanel();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		pan.add(label);
		pan.add(username);
		pan1.add(send);
		pan2.add(exit);

		gameover.add(pan, BorderLayout.NORTH);
		gameover.add(pan1, BorderLayout.CENTER);
		gameover.add(pan2, BorderLayout.SOUTH);
		gameover.pack();
		gameover.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameover.setVisible(true);
		gameover.setLocationRelativeTo(null);
		
		
         
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { 
				name = username.getText();
				ScoreFrame(); 
				gameover.setVisible(false);
			}});

			exit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) { 
				  System.exit(0); 
				}});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			Scoring();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}
}