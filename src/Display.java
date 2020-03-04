import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

//import Controller.ClickListener;

@SuppressWarnings("serial")
public class Display extends JFrame {
	
	public class Display_Memory {
		JFrame frame;
		JList<String> list;
		
		Display_Memory(Boolean visibility){
			start_frame();
			frame.setVisible(visibility);
			
			frame.add(list);
		}
		
		void close() {
			frame.setVisible(false);
			frame.dispose();
			frame = null;
		}
		
//		void open() {
//			frame.setVisible(true);
//		}
		
		//FRAME Settings
		@SuppressWarnings("unused")
		private final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 1000;
		/**
		 * Starts the frame for the program 
		 * Adds the size, title, close operation, and visibility
		 */
		protected void start_frame() {
			frame = new JFrame();
			frame.setTitle("Memory");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}
	static public Font input_f = new Font("Helvetica", Font.PLAIN, 38);
	static public Font buttons_f = new Font("Helvetica", Font.BOLD, 29);
//	static public Color button_c = new Color(129,116,188);
//	static public Color button_c = new Color(175, 82, 222);
	static public Color button_c = new Color(10, 132, 255);
	
	//FRAME Settings
	
	//Basic Calculator
	static public final int BASIC_WIDTH = 420, BASIC_HEIGHT = 600;
	
	protected JFrame frame;					//general frame
	
	private JPanel LHS;
	private JPanel displayPanel,			//display panels
		buttonPanel,						//displays buttons
		answerPanel;
	
	private JScrollPane LCD_scrollpane, aLCD_scrollpane;	
	private static JTextArea LCD;
	private static JTextArea aLCD;
	
	Display(boolean visibility, String title, int width, int height, boolean b){
		System.out.println(" || Booting up display.");
		start_frame(title);
		
		start_LCD();
		
		LHS = new JPanel();
		LHS.setBackground(Color.DARK_GRAY);
		LHS.setLayout(new BorderLayout());
		
		LHS.add(displayPanel, BorderLayout.PAGE_START);
		LHS.add(answerPanel, BorderLayout.CENTER);
		
		//Buttons -> Button JPANEL
		if (b) {
			start_buttons();
			LHS.add(buttonPanel, BorderLayout.SOUTH);
		}
		
		frame.add(LHS);
//		frame.add(LHS);
//		frame.add(RHS, BorderLayout.EAST);
		
		
		//Finalize Frame Size
		frame.setSize(width, height);
		frame.setVisible(visibility);
		
		System.out.println(" || Display Initialized.");
	}
	
	public void close() {
		frame.setVisible(false);
	}
	
	public void open() {
		frame.setVisible(true);
	}
	
	
	public void setLCD(String appendWith) {
		LCD.append(appendWith);
	}
	
	public void setaLCD(String appendWith) {
		aLCD.append(appendWith);
	}
	
	public static JTextArea getLCD() {
		return LCD;
	}
	
	public static JTextArea getALCD() {
		return aLCD;
	}
	
	//Append text to LCD
	public void appendLCD(String withThis) {
		LCD.append(withThis);
	}
	
	/**
	 * Starts the frame for the program 
	 * Adds the size, title, close operation, and visibility
	 */
	protected void start_frame(String title) {
		frame = new JFrame();
		frame.setTitle(title);
		frame.setBackground(Color.DARK_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
	}
	
	//DISPLAY PANEL Settings
	/**
	 * Starts the display for the program
	 * 
	 */
	protected void addTo_displayPanel() {
		//Input Text Area
		displayPanel = new JPanel();
		
		displayPanel.add(LCD_scrollpane);
		displayPanel.setBackground(Color.DARK_GRAY);
	}
	
	private void addTo_AnswerPanel() {
		//Answer Text Area
		answerPanel = new JPanel();
		
		answerPanel.add(aLCD_scrollpane);
		answerPanel.setBackground(Color.DARK_GRAY);
		
	}
	
	protected void start_LCD() {
		@SuppressWarnings("unused")
		final int DISPLAY_PANEL_WIDTH = 10, DISPLAY_PANEL_HEIGHT = 2,
				ANSWER_PANEL_WIDTH = 10, ANSWER_PANEL_HEIGHT = 1;
		
		//EXPRESSION LCD--------------------------------------------//
		LCD = new JTextArea("");
		LCD.setEditable(true);
		
		LCD.addKeyListener(addKeyListener());
		
		//BG Color of Component
		LCD.setBackground(Color.DARK_GRAY);
		LCD.setForeground(Color.WHITE);
		
		//Sets Font of component
		LCD.setFont(input_f);
		LCD.setLineWrap(true);
		
		LCD_scrollpane = new JScrollPane(LCD);
		LCD_scrollpane.setBorder(null);
		LCD_scrollpane.setPreferredSize(new Dimension(400, 150));
		
		//ANSWER LCD ----------------------------------------------//
		aLCD = new JTextArea();
		aLCD.setEditable(false);
		
		//BG Color
		aLCD.setBackground(Color.DARK_GRAY);
		aLCD.setForeground(Color.WHITE);
		
		//Sets Font
		aLCD.setFont(input_f);
		aLCD.setLineWrap(true);
		
		aLCD_scrollpane = new JScrollPane(aLCD);
		aLCD_scrollpane.setBorder(null);
		aLCD_scrollpane.setPreferredSize(new Dimension(400,100));
		
		addTo_displayPanel();
		addTo_AnswerPanel();
	}
	
	
	protected void start_buttons() {
		buttonPanel = new JPanel();

		buttonPanel.setLayout(new GridLayout(6,4));
		//modePanel.setLayout(new GridLayout(3,1));
		
//		buttonPanel.setPreferredSize(new Dimension(400,300));
		add_buttons();
	}
	
	protected void add_buttons() {
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		ArrayList<JButton> mode = new ArrayList<JButton>();
		
		//Row One
		JButton sci = new JButton("Sci");
		buttons.add(sci);
		JButton basic = new JButton("Bas");
		buttons.add(basic);
		JButton graph = new JButton("Gra");
		buttons.add(graph);
		JButton rpn = new JButton("RPN");
		buttons.add(rpn);
		
		//Row Two
		JButton square = new JButton("^2");
		buttons.add(square);
		JButton ce = new JButton("CE");
		buttons.add(ce);
		JButton clear = new JButton("C");
		buttons.add(clear);
		JButton divide = new JButton(" / ");
		buttons.add(divide);
		
		//Row Two
		JButton seven = new JButton("7");
		buttons.add(seven);
		JButton eight = new JButton("8");
		buttons.add(eight);
		JButton nine = new JButton("9");
		buttons.add(nine);
		JButton multi = new JButton(" x ");
		buttons.add(multi);
		
		//Row Three
		JButton four = new JButton("4");
		buttons.add(four);
		JButton five = new JButton("5");
		buttons.add(five);
		JButton six = new JButton("6");
		buttons.add(six);
		JButton minus = new JButton(" - ");
		buttons.add(minus);
		
		//Row Four
		JButton one = new JButton("1");
		buttons.add(one);
		JButton two = new JButton("2");
		buttons.add(two);
		JButton three = new JButton("3");
		buttons.add(three);
		JButton plus = new JButton(" + ");
		buttons.add(plus);
		
		//Row Five
		JButton zero = new JButton("0");
		buttons.add(zero);
		JButton decimal = new JButton(".");
		buttons.add(decimal);
		JButton neg = new JButton("(-)");
		buttons.add(neg);
		JButton equal = new JButton("=");
		buttons.add(equal);
		
		ActionListener listener = new ClickListener();
		for (int i = 0, c = 1; i < buttons.size(); i++, c++) {
			JButton temp = buttons.get(i);
			temp.setFont(buttons_f);
			if ((c % 4) == 0)
				temp.setBackground(button_c);
			else
				temp.setBackground(Color.DARK_GRAY);
			temp.setForeground(Color.WHITE);
			temp.addActionListener(listener);
			buttonPanel.add(temp);
		}	
		
		for (int i = 0; i < mode.size(); i++) {
			JButton temp = mode.get(i);
			temp.setFont(buttons_f);
			temp.setBackground(Color.ORANGE);
			temp.setForeground(Color.white);
			temp.addActionListener(listener);
		}
	}
	
	protected KeyListener addKeyListener() {
		KeyListener listener = new KeyAdapter();
		return listener;
	}
}
