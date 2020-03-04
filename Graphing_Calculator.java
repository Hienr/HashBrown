import java.util.ArrayList;

public class Graphing_Calculator {
	private Equation _expression;
	private RPN _rpn;
	private ArrayList<Double> x;
	private ArrayList<Double> y;
	
	private String original_string;
	
	public Graphing_Calculator(String input, int lo, int hi) throws SyntaxErrorException {
		_expression = new Equation();
		x = new ArrayList<Double>();
		y = new ArrayList<Double>();
		original_string = input;
		
		fillX(lo, hi);
		getInput(input);	
		fillY();
	}
	
	public void getInput(String line) throws SyntaxErrorException {
		System.out.println(" || Basic: Getting Input.");
		Parser _parser = new Parser(line);
		fillExpression(_parser);
		System.out.println(" || Basic: Input was received.\n "
				+ "|| Basic: Input was parsed.");
	}
	
	public ArrayList<Double> getX(){
		return x;
	}
	
	public ArrayList<Double> getY(){
		return y;
	}
	
	private double getY(String x) throws SyntaxErrorException {
		_rpn = new RPN(_expression);
		
		_rpn.fillVariable(new Component(x, Consts.OPERAND));
		
		Calculator _calc = new Calculator(_rpn);
		_calc.getCalculation();
		
		double ans = _calc.getAns();

		getInput(original_string);
		
		System.out.println(" || " + ans);
		return ans;
	}
	
	private void fillExpression(Parser _parser) throws SyntaxErrorException {
		System.out.println(" || Basic: Filling Equation.");
		while (_parser.stillMore()) {
			_expression.insert(_parser.popComponent());
		}
	}
	
	private void fillX(int lo, int hi) {
		for (double i = lo; i < hi; i++) 
			x.add(i);
	}
	
	private void fillY() throws SyntaxErrorException {
		for (int i = 0; i < x.size(); i++) {
			String n = x.get(i).toString();
			
			System.out.println(" || Filling Y coordinates with " + n);
			y.add(getY(n));
			
		}
	}
}
