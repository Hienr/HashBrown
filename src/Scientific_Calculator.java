import java.util.Queue;

public class Scientific_Calculator {
	private Equation _expression;
	private RPN _rpn;
	private String _rpn_str;
	
	Scientific_Calculator(){
		_expression = new Equation();
		_rpn = new RPN();
		_rpn_str = new String();
	}
	public void getInput() throws SyntaxErrorException {
		Parser _parser = new Parser();
		_parser.getLine("|| Basic: Getting Expression");
		fillExpression(_parser);
	}
	
	public void getInput(String line) throws SyntaxErrorException {
		System.out.println(" || Basic: Getting Input.");
		Parser _parser = new Parser(line);
		fillExpression(_parser);
		System.out.println(" || Basic: Input was received.\n "
				+ "|| Basic: Input was parsed.");
	}
	
	public String getRpn() {
		return _rpn_str;
	}
	
	public String getEq() {
		return _expression.getEqStr();
	}
	
	public double getAnswer() throws SyntaxErrorException {
		System.out.println(" || Basic: Getting Answer");
		RPN _rpn = new RPN(_expression);
		_rpn_str = _rpn.get_rpn_string();
		
		Calculator calculator = new Calculator(_rpn);
		double answer = calculator.getCalculation();
		System.out.println(" || Basic: Answer was evaluted to be: " + answer);
		return answer;
	}
	
	public String getRpnAns() throws SyntaxErrorException{
		System.out.println(" || Basic: Getting RPN & Answer");
		String line = "";
		
		RPN _rpn = new RPN(_expression);
		
		line = _rpn.getOutputString();
		
		Calculator calculator = new Calculator(_rpn);
		double answer = calculator.getCalculation();
		System.out.println(" || Basic: Answer was evaluted to be: " + answer);
		line.concat(Double.toString(answer));
		return line;
	}
	
	public Queue<Component> showRpn() {
		return _rpn.getRpn();
	}
	
	public String getRpnString() {
		System.out.println(" || Basic: Getting RPN String.");
		return _rpn.getOutputString();
	}
	
	private void fillExpression(Parser _parser) throws SyntaxErrorException {
		System.out.println(" || Basic: Filling Equation.");
		while (_parser.stillMore()) {
			_expression.insert(_parser.popComponent());
		}
	}
}
