import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display_Scientific extends Display{
	//Scientific 
	static public final int SCI_WIDTH = 420, SCI_HEIGHT = 750;
	
	@SuppressWarnings("unused")
	private static JPanel sciButtons, LHS;
	
	public Display_Scientific(boolean visibility) {
		super(visibility, "Hash Brown", 
				SCI_WIDTH, SCI_HEIGHT, false);
		System.out.println(" || Booting up scientific");
		
		start_scibuttons();
		
		frame.add(sciButtons, BorderLayout.SOUTH);
		frame.setVisible(true);
		
	}
	
	protected void start_scibuttons() {
		sciButtons = new JPanel();
		sciButtons.setBackground(Color.DARK_GRAY);

		sciButtons.setLayout(new GridLayout(9,4));
		
		add_scibuttons();
	}
	
	protected void add_scibuttons() {
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
		JButton oneover = new JButton("1/");
		buttons.add(oneover);
		JButton ce = new JButton("CE");
		buttons.add(ce);
		JButton clear = new JButton("C");
		buttons.add(clear);
		JButton pi = new JButton("π");
		buttons.add(pi);
		
		//Row Three
		JButton sin = new JButton("Sin");
		buttons.add(sin);
		JButton cos = new JButton("Cos");
		buttons.add(cos);
		JButton tan = new JButton("Tan");
		buttons.add(tan);
		JButton carat = new JButton(" ^");
		buttons.add(carat);
		
		//Row Four
		JButton log = new JButton("Log");
		buttons.add(log);
		JButton e = new JButton("e");
		buttons.add(e);
		JButton square = new JButton("^2");
		buttons.add(square);
		JButton sqrt = new JButton("√");
		buttons.add(sqrt);
		
		//Row Five
		JButton lpar = new JButton("(");
		buttons.add(lpar);
		JButton rpar = new JButton(")");
		buttons.add(rpar);
		JButton cubed = new JButton("^3");
		buttons.add(cubed);
		JButton divide = new JButton(" / ");
		buttons.add(divide);
		
		//Row Six
		JButton seven = new JButton("7");
		buttons.add(seven);
		JButton eight = new JButton("8");
		buttons.add(eight);
		JButton nine = new JButton("9");
		buttons.add(nine);
		JButton multi = new JButton(" x ");
		buttons.add(multi);
		
		//Row Seven
		JButton four = new JButton("4");
		buttons.add(four);
		JButton five = new JButton("5");
		buttons.add(five);
		JButton six = new JButton("6");
		buttons.add(six);
		JButton minus = new JButton(" - ");
		buttons.add(minus);
		
		//Row Eight
		JButton one = new JButton("1");
		buttons.add(one);
		JButton two = new JButton("2");
		buttons.add(two);
		JButton three = new JButton("3");
		buttons.add(three);
		JButton plus = new JButton(" + ");
		buttons.add(plus);
		
		//Row Nine
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
			sciButtons.add(temp);
		}	
		
		
		for (int i = 0; i < mode.size(); i++) {
			JButton temp = mode.get(i);
			temp.setFont(buttons_f);
			temp.setBackground(Color.ORANGE);
			temp.setForeground(Color.white);
			temp.addActionListener(listener);
		}
	}
	
}
