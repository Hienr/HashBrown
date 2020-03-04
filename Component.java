/*
 * [Component] Object will manage all the functions to a component.
 * Components are anything in a mathematical expression such as in
 * || sin(3)^3 + 4 - 100 *
 * This expressions components are [sin],[(],[3],[^],[3],[+],[4],[-],[100],[*].
 * 
 * Features: 
 * * Stores each component as a string, and an included integer type
 * * Constructors for [],[String],[String, Type]
 * * Print function to print a component
 * * .out toString override
 * * Conversion of a component type OPERAND to a type double
 * * Ability to set/get component & type.
 * * Determine precedence, and associativity among type OPERATORS
 * * Arithmetic between components of type OPERAND
 * * Ability to set any string to a component type
 */
public class Component {
	//Private Member Variables
	private String _component;								//component source
	private int _type;										//type source			
	
	Component(){											//constructors
		_component = "";
		_type = Consts.NOTYPE;
	}
	Component(String component) throws SyntaxErrorException{
		_component = component;
		update_type();
	}
	Component(String component, int type) throws SyntaxErrorException{
		if (type == Consts.NOTYPE)
			throw new SyntaxErrorException("Bad type.");
		_component = component;
		_type = type;
	}
	
	public void print() {									//print function
		System.out.println(_component + " is type " + getTypeName());
	}
	
	public String toString() { return (_component); }		//.out override
	
	public double toDouble() {								//gets comp double
		if (_component.equals("pi"))
			return Consts._PI;
		if (_component.equals("e"))
			return Consts._E;
		return Double.parseDouble(_component);
	}
	
	//Accessor/Mutator Functions
	public void setComponent(String component) throws SyntaxErrorException {
		_component = component;
		update_type();
	}
	public void setType(int type) {
		_type = type;
	}
	public String getComponent() {
		return _component;
	}
	public int getType() {
		return _type;
	}
	public String getTypeName() {
		switch (_type) {
		case Consts.OPERAND : return "Operand.\n";
		case Consts.OPERATOR : return "Operator.\n";
		case Consts.VARIABLE : return "Variable.\n";
		case Consts.EQUALITY : return "Equality.\n";
		case Consts.FUNCTION : return "Function.\n";
		}
		return "No Type Available.\n";
	}
	
	public int getPrecedence() {
		if ((_type == Consts.OPERATOR) || (_type == Consts.FUNCTION)){
			switch (_component.charAt(0)) {
			case Consts.ADD : return Consts.Operator.ADD.getOperation();
			case Consts.SUBTRACT : return Consts.Operator.SUBTRACT.getOperation();
			case Consts.MULTIPLY : return Consts.Operator.MULTIPLY.getOperation();
			case Consts.DIVISION : return Consts.Operator.DIVIDE.getOperation();
			case Consts.POWER : return Consts.Operator.EXPONENT.getOperation();
			case Consts.LEFT_PAR : return Consts.Operator.LPAR.getOperation();
			case Consts.RIGHT_PAR : return Consts.Operator.RPAR.getOperation();
			default : return Consts.Operator.FNC.getOperation();
			}		
		}
		return -1;
	}
	
	public int getOperation() {
		if (getType() == Consts.OPERATOR) {
			switch (_component.charAt(0)) {
			case '+' : return Consts.O_ADD;
			case '-' : return Consts.O_SUBTRACT;
			case '*' : return Consts.O_MULTIPLY;
			case '/' : return Consts.O_DIVIDE;
			case '^' : return Consts.O_POWER;
			}	
		}
		else if (getType() == Consts.FUNCTION) {
			if (_component.equals("sin"))
				return Consts.O_SINE;
			else if (_component.equals("cos"))
				return Consts.O_COSINE;
			else if (_component.equals("tan"))
				return Consts.O_TANGENT;
			else if (_component.equals("log"))
				return Consts.O_LOG;
			else if (_component.equals("ln"))
				return Consts.O_LN;
			else if (_component.equals("sqrt"))
				return Consts.O_SQRT;
		}
		return -1;
	}
	
	public int getAssociativity() {
		if (_component.charAt(0) == '^')
			return Consts.RIGHT_ASSOCIATIVITY;
		else 
			return Consts.LEFT_ASSOCIATIVITY;
	}
	
	//Arithmetic 
	
	public Component add(Component x) throws SyntaxErrorException {
		if ((x.getType() != Consts.OPERAND) && (getType() != Consts.OPERAND))
			throw new ArithmeticException("Addition of invalid operands");
		double value = x.toDouble() + toDouble();
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
 	}
	
	public Component subtract(Component x) throws SyntaxErrorException {
		if ((x.getType() != Consts.OPERAND) && (getType() != Consts.OPERAND))
			throw new ArithmeticException("Subtraction of invalid operands");
		double value = x.toDouble() - toDouble();
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
	}
	
	public Component multiply(Component x) throws SyntaxErrorException {
		if ((x.getType() != Consts.OPERAND) && (getType() != Consts.OPERAND))
			throw new ArithmeticException("Multiplication of invalid operands");
		double value = x.toDouble() * toDouble();
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
	}
	
	public Component divide(Component x) throws SyntaxErrorException {
		if ((x.getType() != Consts.OPERAND) && (getType() != Consts.OPERAND))
			throw new ArithmeticException("Division of invalid operands");
		double value = x.toDouble() / toDouble();
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
	}
	
	public Component power(Component x) throws SyntaxErrorException {
		if ((x.getType() != Consts.OPERAND) && (getType() != Consts.OPERAND))
			throw new ArithmeticException("Exponentiation of invalid operands");
		double value = Math.pow(toDouble(), x.toDouble());
		System.out.println("Value : " + value);
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
		
	}
	
	public Component square() throws SyntaxErrorException {
		final double SQUARE = 2.0;
		if (getType() != Consts.OPERAND)
			throw new ArithmeticException("Squaring of invalid operands");
		double value = Math.pow(toDouble(), SQUARE);
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
	}
	
	public Component cubed() throws SyntaxErrorException {
		final double CUBE = 3.0;
		if (getType() != Consts.OPERAND)
			throw new ArithmeticException("Cubing of invalid operands");
		double value = Math.pow(toDouble(), CUBE);
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
	}
	
	public Component sqrt() throws SyntaxErrorException {
		if (getType() != Consts.OPERAND)
			throw new ArithmeticException("Square rooting of invalid operands");
		double value = Math.sqrt(toDouble());
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
	}
	
	public Component sin() throws SyntaxErrorException {
		if (getType() != Consts.OPERAND)
			throw new ArithmeticException("Taking sine of invalid operands");
		double value = Math.toRadians(toDouble());
		value = Math.sin(value);	
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
	}
	
	public Component cos() throws SyntaxErrorException {
		if (getType() != Consts.OPERAND)
			throw new ArithmeticException("Taking cosine of invalid operands");
		double value = Math.toRadians(toDouble());
		value = Math.cos(value);
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
	}
	
	public Component tan() throws SyntaxErrorException {
		if (getType() != Consts.OPERAND)
			throw new ArithmeticException("Taking tangent of invalid operands");
		double value = Math.toRadians(toDouble());
		value = Math.tan(toDouble());
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
	}
	
	public Component log() throws SyntaxErrorException {
		if (getType() != Consts.OPERAND)
			throw new ArithmeticException("Taking log of invalid operands");
		double value = Math.log10(toDouble());
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
	}
	
	public Component ln() throws SyntaxErrorException{
		if (getType() != Consts.OPERAND)
			throw new ArithmeticException("Taking ln of invalid operands");
		double value = Math.log(toDouble());
		Component return_comp = new Component(Double.toString(value));
		return return_comp;
	}
	
	//Private Member Functions
	
	//Updates the type of the component
	private void update_type() throws SyntaxErrorException{
		if (isTrig(_component)) 
			setType(Consts.FUNCTION);
		else if (isSqrt(_component))
			setType(Consts.FUNCTION);
		else if (isLog(_component))
			setType(Consts.FUNCTION);
		else if (isLn(_component))
			setType(Consts.FUNCTION);
		else if (isVariable(_component))
			setType(Consts.VARIABLE);
		else if (isEuler(_component))
			setType(Consts.OPERAND);
		else if (isPi(_component))
			setType(Consts.OPERAND);
		else if (isEqual(_component))
			setType(Consts.EQUALITY);
		else if (isLParentheses(_component))
			setType(Consts.L_PARENTHESES);
		else if (isRPArentheses(_component))
			setType(Consts.R_PARENTHESES);
		else if (isNum(_component))
			setType(Consts.OPERAND);
		else if (isOperator(_component))
			setType(Consts.OPERATOR);
		else {
			setType(Consts.NOTYPE);
			throw new SyntaxErrorException("No Proper operator or operand.\n");
		}
	}
	
	private Boolean isTrig(String keyword) {
		if (keyword.isBlank())
			return false;
		for (int i = 0; i < Consts.TRIG_FNC_SIZE; i++)
			if (keyword.equals(Consts.TRIG_FNC[i]))
				return true;
		return false;
	}
	
	private Boolean isSqrt(String keyword) {
		if (keyword.isBlank())
			return false;
		Boolean isSqrt = (keyword.equals(Consts.SQRT)) ? true : false;
		return isSqrt;
	}
	
	private Boolean isLn(String keyword) {
		if (keyword.isBlank())
			return false;
		Boolean isLn = (keyword.contentEquals(Consts.LN)) ? true : false;
		return isLn;
	}
	
	private Boolean isLog(String keyword) {
		if (keyword.isBlank())
			return false;
		Boolean isLog = (keyword.equals(Consts.LOG)) ? true : false;
		return isLog;
	}

	private Boolean isVariable(String keyword){
		if (keyword.length() == 1) {
			switch (keyword.charAt(0)) {
				case 'x' : return true;
				case 'y' : return true;
				case 'z' : return true;
			}	
		}
		return false;
	}
	
	private Boolean isEuler(String keyword) {
		if (keyword.isBlank())
			return false;
		Boolean isEuler = (keyword.charAt(0) == Consts.E) ? true : false;
		return isEuler;
	}

	private Boolean isPi(String keyword) {
		if (keyword.isBlank())
			return false;
		Boolean isPi = (keyword.equals(Consts.PI)) ? true : false;
		return isPi;
	}
	
	private Boolean isNum(String keyword) {
		try {	//if parseDouble fails keyword is not a number
			@SuppressWarnings("unused")
			double check = Double.parseDouble(keyword);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}
	
	private Boolean isEqual(String keyword) {
		if (keyword.isBlank())
			return false;
		Boolean isEqual = (keyword.charAt(0) == Consts.EQUAL) ? true : false;
		return isEqual;
	}
	
	private Boolean isLParentheses(String keyword) {
		if (keyword.isBlank())
			return false;
		Boolean isLParentheses = (keyword.charAt(0) == '(') ? true : false;
		return isLParentheses;
	}
	
	private Boolean isRPArentheses(String keyword) {
		if (keyword.isBlank())
			return false;
		Boolean isRParentheses = (keyword.charAt(0) == ')') ? true : false;
		return isRParentheses;
	}
	private Boolean isOperator(String keyword) {
		if (keyword.length() == 1)
			for (int i = 0; i < Consts.OPERATORS.length(); i++)
				if (keyword.charAt(0) == Consts.OPERATORS.charAt(i))
					return true;
		return false;
	}
	
}