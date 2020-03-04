public class Controller {
	
	/* DISPLAYS */
	private static Display _basic = new Display(true, "Hash Brown", 
			Display.BASIC_WIDTH, Display.BASIC_HEIGHT, true);
	private static Display_Scientific _sci = null;
	private static Display_Graph _grp = null;
	/* DISPLAYS */
	
	private static Scientific_Calculator _calculator;
	
	Controller(){
		_calculator = new Scientific_Calculator();
	}
	
	public void changeToBasic() {
		if (_basic != null)
			_basic.close();
		if (_sci != null)
			_sci.close();
		if (_grp != null)
			_grp.close();
		
		_basic = new Display(true, "Hash Brown",
				Display.BASIC_WIDTH, Display.BASIC_HEIGHT, true);
		
	}
	
	public void changeToSci() {
		if (_basic != null)
			_basic.close();
		if (_sci != null)
			_sci.close();
		
		_sci = new Display_Scientific(true);
	}
	
	public void graph() throws Exception {
		String input = Display.getLCD().getText();
		if (!input.isEmpty()) {
			if (_basic != null)
				_basic.close();
			if (_sci != null)
				_sci.close();
			changeToSci();
			
			_grp = new Display_Graph(input);
		}
		else {
			changeToSci();
			appendALCD("Missing Expression\n");
		}
	}
	
	public String getLCD() {
		return Display.getLCD().getText();
	}
	
	public String getALCD() {
		return Display.getALCD().getText();
	}
	
	public void appendLCD(String withThis) {
		Display.getLCD().append(withThis);
	}
	
	public void appendALCD(String withThis) {
		Display.getALCD().append(withThis);
	}
	
	public void setLCD(String withThis) {
		Display.getLCD().setText(withThis);
	}
	
	public void setALCD(String withThis) {
		Display.getALCD().setText(withThis);
	}
	
	public String showRpn() throws SyntaxErrorException{
		return " rpn: " + _calculator.getRpn();
	}
	
	public void equals() {
		try {
			System.out.println(" || Expression: " + Display.getLCD().getText());
			String user_input = Display.getLCD().getText();
			
			if (!user_input.isEmpty()) {
				_calculator.getInput(Display.getLCD().getText());
				
				String final_answer = Double.toString(_calculator.getAnswer());
				
				clear();
				System.out.println(" || [Answer was " + final_answer + "] \n");
				
				Display.getALCD().setText(final_answer);	
			}
			else {
				throw new SyntaxErrorException("|| Input was empty.\n");
			}
		} catch (IllegalArgumentException e) {
			setALCD(e.getMessage());
			reset(false);
		} catch (Exception e) {
			setALCD("Syntax Error");
			reset(false);
		}
		
	}
	
	public void clear_entry() {
		String previousLCD = getLCD();
		String newLCD;
		
		if (!previousLCD.isEmpty()) {
			newLCD = previousLCD.substring(0, previousLCD.length()-1);
			setLCD(newLCD);
		}
		else {
			;
		}
	}
	
	public void clear() {
		reset(true);
	}
	
	public void reset(Boolean isFullR) {
		setLCD("");
		if (isFullR)
			setALCD("");
	}
}
