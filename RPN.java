import java.util.Stack;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

public class RPN {
	private static Equation _source;
	private Stack<Component> _operators;
	private Queue<Component> _output;
	private String _output_str;
	
	RPN(){
		_source = new Equation();
		_output = new LinkedList<Component>();
		_output_str = "";
	}
	
	RPN(Equation src){
		_operators = new Stack<Component>();
		_output = new LinkedList<Component>();
		_source = src;
		to_RPN();
	}
	
	public void print() {
		System.out.println(_output);
	}
	
	public String getOutputString() {
		System.out.println(" || RPN: Returning RPN String" + _output_str);
		return _output_str;
	}
	
	public Stack<Component> getStack() {
		return _operators;
	}
	
	public Queue<Component> getRpn() {
		return _output;
	}
	
	public String get_rpn_string() {
		return _output_str;
	}
	
	public Equation getSrc() {
		return _source;
	}
	
	public void fillVariable(Component num) throws SyntaxErrorException {
		Iterator<Component> it = _output.iterator();
		while (it.hasNext()){
			Component current = it.next();
			if (current.getType() == Consts.VARIABLE) {
				current.setComponent(num.getComponent());
			}
		}
	}
	
	private void to_RPN() {			//Utilizes the Shunting Yard Algorithm
		System.out.println(" || RPN: Converting to RPN.");
		Stopwatch timer = new Stopwatch();
		while (!_source.is_Empty()) {
			Component token = _source.pop();
			int token_type = token.getType();
			
			if ((token_type == Consts.OPERAND) 
					|| (token_type == Consts.VARIABLE)) {
				_output.add(token);
			}
			if (token_type == Consts.FUNCTION) {
				_operators.add(token);
			}
			if (token_type == Consts.OPERATOR) {
				while (toPop(token, token_type))
					_output.add(_operators.pop());
				_operators.add(token);
			}
			if (token_type == Consts.L_PARENTHESES){
				_operators.add(token);
			}
			if (token_type == Consts.R_PARENTHESES) {
				while (!isTopLeftPar()) {
					_output.add(_operators.pop());
				}
				if (isTopLeftPar()) {
					_operators.pop();
				}
			}
		}
		if(_source.is_Empty()) {
			while (!_operators.isEmpty()) {
				_output.add(_operators.pop());
			}
		}
		timer.end();
		_output_str = _output.toString();				//saving string version
		System.out.println(" || RPN: RPN String Stored: " + _output_str);
		System.out.println(" || RPN: Converted to RPN..." 
				+ timer.sec_elapsed());
	}
	
	private Boolean toPop(Component currentToken, int currentType) {
		Boolean firstCheck = false, toPop = true;
		if (_operators.isEmpty())
			return false;
		else if (isTop(Consts.FUNCTION)) {
				firstCheck = true;
		}
		else if (isTopGreater(currentToken.getPrecedence())) {
			firstCheck = true;
		}
		else if ( (isTopEqual(currentToken.getPrecedence())) && (isTopLeft()) ) {
			firstCheck = true;
		}
		// AND 
		if (isTopLeftPar())
			toPop = false;
		
		return (toPop == true && firstCheck == true);
	}
	
	
	//PRIVATE: 
	//is TOP of the STACK this type
	private Boolean isTop(int type) {
		if (_operators.isEmpty())
			return false;
		return (_operators.peek().getType() == type);
	}
	
	//is TOP of the STACK equal precedence to this operator
	private Boolean isTopEqual(int operator) {
		if (_operators.isEmpty())
			return false;
		if (_operators.peek().getPrecedence() == operator)
			return true;
		return false;
	}
	
	//is TOP of the STACK greater PRECEDENCE than passed PRECEDENCE
	private Boolean isTopGreater(int operator) {
		if (_operators.isEmpty())
			return false;
		Boolean isGreater = (_operators.peek().getPrecedence() > operator) ? true : false;
		if (isGreater)
			return isGreater;
		return isGreater;
	}
	
	//is TOP of the STACK a LEFT ASSOCIATIVITY
	private Boolean isTopLeft()
	{
		if (_operators.isEmpty())
			return false;
		Boolean isLeftAssociative = (_operators.peek().getAssociativity() == Consts.LEFT_ASSOCIATIVITY)
				? true : false;
		return isLeftAssociative;
	}
	
	//is TOP of the STACK not '('
	private Boolean isTopLeftPar() {
		if (_operators.isEmpty())
			return false;
		String check = _operators.peek().getComponent();
		if (check.equals("("))
				return true;
		return false;
	}
}