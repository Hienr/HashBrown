import java.util.Stack;
import java.util.Queue;

public class Calculator {
	private Stack<Component> _operands;
	private Queue<Component> _source;
	private double _ans;
	
	Calculator(Queue<Component> src){
		_source = src;
		_operands = new Stack<Component>();
	}
	
	Calculator(RPN src){
		_source = src.getRpn();
		_operands = new Stack<Component>();
	}
	
	public double getCalculation() throws SyntaxErrorException {
		while (!_source.isEmpty()) {
			if (isTopNumber()) {
				_operands.push(_source.poll());
			}
			else {
				int function = _source.poll().getOperation();
				switch (function) {
				case Consts.O_ADD : _operands.push(add());
									break;
				case Consts.O_SUBTRACT : _operands.push(subtract());
									break;
				case Consts.O_MULTIPLY : _operands.push(multiply());
									break;
				case Consts.O_DIVIDE : _operands.push(divide());
									break;
				case Consts.O_POWER : _operands.push(pow());
									break;
				case Consts.O_SINE : _operands.push(sin());
									break;
				case Consts.O_COSINE : _operands.push(cos());
									break;
				case Consts.O_TANGENT : _operands.push(tan());
									break;
				case Consts.O_LOG : _operands.push(log());
									break;
				case Consts.O_LN : _operands.push(ln());
									break;
				case Consts.O_SQRT : _operands.push(sqrt());
									break;
				default : throw new ArithmeticException("Invalid Arithmetic Occured");
				}
			}	
		}
		
		_ans = answer();
		
		/* Answer validation */
		
		//Check for divide by zero's
		if (Double.isInfinite(_ans)) 
			throw new IllegalArgumentException("Divide by Zero Error");
		
		return _ans;
	}
	
	public double getAns() {
		return _ans;
	}
	
	public String getStringAns() {
		return Double.toString(_ans);
	}
	
	private double answer() {
		return _operands.peek().toDouble();
	}
	
	private Boolean isTopNumber() {
		Boolean isTopNumber = (_source.peek().getType() == Consts.OPERAND) ?
				true : false;
		return isTopNumber;
	}
	
	private Component add() throws SyntaxErrorException {
		Component x = _operands.pop(), y = _operands.pop();
		return x.add(y);
	}
	
	private Component subtract() throws SyntaxErrorException {
		Component x = _operands.pop(), y = _operands.pop();
		return x.subtract(y);
	}
	
	private Component multiply() throws SyntaxErrorException {
		Component x = _operands.pop(), y = _operands.pop();
		return x.multiply(y);
	}
	
	private Component divide() throws SyntaxErrorException {
		Component x = _operands.pop(), y = _operands.pop();
		return x.divide(y);
	}
	
	private Component sin() throws SyntaxErrorException {
		Component theta = _operands.pop();
		return theta.sin();
	}
	
	private Component cos() throws SyntaxErrorException {
		Component theta = _operands.pop();
		return theta.cos();
	}
	
	private Component tan() throws SyntaxErrorException {
		Component theta = _operands.pop();
		return theta.tan();
	}
	
	private Component log() throws SyntaxErrorException {
		Component theta = _operands.pop();
		return theta.log();
	}
	
	private Component ln() throws SyntaxErrorException {
		Component fnc = _operands.pop();
		return fnc.ln();
	}
	
	private Component sqrt() throws SyntaxErrorException {
		Component x = _operands.pop();
		return x.sqrt();
	}
	
	private Component pow() throws SyntaxErrorException {
		Component x = _operands.pop(), y = _operands.pop();
		if (x.toDouble() == 2.0)
			return y.square();
		else if (x.toDouble() == 3.0)
			return y.cubed();
		else
			return y.power(x);
	}
	
	
}
