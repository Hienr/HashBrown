import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClickListener implements ActionListener{
	static private Controller _controller = new Controller();
	
	//Processes what happens when a button is pressed
	public void actionPerformed(ActionEvent event) {
		
		//Get action in string
		String action = event.getActionCommand();	
		System.out.println(" || Command Inputted: " + action);
		
		if (action == "=")
			_controller.equals();
		else if (action == "C")
			_controller.clear();
		else if (action == "CE")
			_controller.clear_entry();
		else if (action == "Sci")
			_controller.changeToSci();
		else if (action == "Bas")
			_controller.changeToBasic();
		else if (action == "Gra")
			try {
				_controller.graph();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else if (action == " x ")
			_controller.appendLCD("*");
		else if (action == "(-)")
			_controller.appendLCD(" -");
		else if (action == "π")
			_controller.appendLCD("pi");
		else if (action == "√")
			_controller.appendLCD("sqrt(");
		else if (action == "Sin")
			_controller.appendLCD("sin(");
		else if (action == "Cos")
			_controller.appendLCD("cos(");
		else if (action == "Tan")
			_controller.appendLCD("tan(");
		else if (action == "Log")
			_controller.appendLCD("log(");
		else if (action == "RPN")
			try {
				_controller.appendALCD(_controller.showRpn());
			} catch (SyntaxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else							
			_controller.appendLCD(action);	
		
	}
}
