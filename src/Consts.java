/*
 * [Consts] Object will store all the necessary CONSTANT values needed for the
 * Hash Brown calculator.
*/
public final class Consts{
	
	// * Operators with Precedence
	enum Operator{
		ADD(0), SUBTRACT(0), MULTIPLY(1), DIVIDE(1),
		EXPONENT(2), LPAR(3), RPAR(3), FNC(3);
		
		final int precedence;
		Operator(int operation) { this.precedence = operation; }
		
		public int getOperation() {
			return precedence;
		}
	}
	
	final static int LEFT_ASSOCIATIVITY = -1, RIGHT_ASSOCIATIVITY = 1;
	
	final static double _PI = Math.PI, _E = Math.E;
	
	// * Component Types: 
	final static int NOTYPE = -1, OPERAND = 0, OPERATOR = 1, VARIABLE = 2,
			EQUALITY = 3, FUNCTION = 4, L_PARENTHESES = 5, R_PARENTHESES = 6;
	
	final static int O_ADD = 0, O_SUBTRACT = 1, O_MULTIPLY = 2, O_DIVIDE = 3,
			O_POWER = 4, O_SINE = 5, O_COSINE = 6, O_TANGENT = 7, O_LOG = 8, 
			O_SQRT = 9, O_SQUARE = 10, O_CUBE = 11, O_LN = 12;
	
	
	// * Operators:
	final static String OPERATORS = "+-*/^()=";
	final static char EQUAL = '=', ADD = '+',
			SUBTRACT = '-', MULTIPLY = '*',
			DIVISION = '/', POWER = '^', 
			LEFT_PAR = '(', RIGHT_PAR = ')';
	
	// * Doubles:
	final static char DECIMAL = '.';

	// * Functions: 
	static final String SQRT = "sqrt";
	static final String LOG = "log";
	static final String LN = "ln";
	
	//* * TRIG FUNCTIONS
	final static String[] TRIG_FNC = {"sin" , "cos", "tan"};
	final static int TRIG_FNC_SIZE = 3;
	
	//* Special Numbers:
	static final char E = 'e';
	static final String PI = "pi";
	
	// * Variables: 
	static final char XVAR = 'x';
	static final char YVAR = 'y';
	static final char ZVAR = 'z';
}

