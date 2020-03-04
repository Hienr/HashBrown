import java.util.Scanner;
public class Parser {
	private static final Component NULL = null;
	private String _buffer, _src;
	private int _pos;
	private EquationPreparer _preparer;
	
	Parser() throws SyntaxErrorException{
		_src = "";
		_preparer = new EquationPreparer(_src);
		_pos = 0;
		_buffer = _preparer.prepare();
	}
	Parser(String line) throws SyntaxErrorException{
		_src = line;
		_preparer = new EquationPreparer(_src);
		_pos = 0;
		_buffer = _preparer.prepare();
	}
	Parser(String line, int position) throws SyntaxErrorException{
		_src = line;
		_preparer = new EquationPreparer(_src);
		_pos = position;
		_buffer = _preparer.prepare();
	}
	
	public Component popComponent() throws SyntaxErrorException {
		return getNextToken();
	}
	
	public void getLine(String field) throws SyntaxErrorException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println(field);
		_src = in.nextLine();
		setBuffer(_src);
	}
	
	public void setBuffer(String setThis) throws SyntaxErrorException {
		EquationPreparer _preparer = new EquationPreparer(setThis);
		_buffer = _preparer.prepare();
	}
	public String getBuffer() {
		return _buffer;
	}
	
	public String getSrc() {
		return _src;
	}
	
	public int srcLength() {
		return _src.length();
	}
	
	public int bufferLength() {
		return _buffer.length();
	}
	
	public Boolean stillMore() {
		return (_pos < _buffer.length());
	}
	
	private Boolean isThereOperator(char c) {
		for (int i = 0; i < Consts.OPERATORS.length(); i++) {
			if (c == Consts.OPERATORS.charAt(i))
				return true;
		}
		return false;
	}
	
	private Boolean isThereNeg(char c) {
		Boolean isNeg = (c == ' ') ? true : false;
		return isNeg;
	}
	
	@SuppressWarnings("unused")
	private Boolean isNeg() {
		Boolean isNeg = (_pos == 0) ? true : false;
		if (_pos != 0)
			if (_buffer.charAt(_pos - 1) == ' ')
				isNeg = true;
		return isNeg;
	}
	
	private Component getNextToken() throws SyntaxErrorException {
		Component component = new Component();
		String operand = "";
		if (!stillMore()) {			
									//Check if there are remaining tokens
			
			return NULL;
		}
		else if (isThereOperator(_buffer.charAt(_pos)) && 
					!isThereNeg(_buffer.charAt(_pos - 1))) {
			
									//If current char is a operator
			
			String insert = Character.toString(_buffer.charAt(_pos));
			component.setComponent(insert);
			_pos++;
			return component;	
		}
									//If current char is not part of an operator
		
		for (int i = _pos; i < _buffer.length(); i++) {			
			if (isThereOperator(_buffer.charAt(i))
					&& !isThereNeg(_buffer.charAt(i - 1))) {
				component.setComponent(operand);
				return component;
			}
			else if (isThereNeg(_buffer.charAt(i))) {
				_pos++;
			}
			else {
				_pos++;
				operand += _buffer.charAt(i);
			}
		}
		component.setComponent(operand);
		_pos++;
		return component;
	}
}

/**
 * EquationPreparer Object will prepare a string into a
 * mathematical expression for parsing. 
 * 
 * Private member will be a string that will have its blanks
 * removed, converts to lower case ONLY, add multiplication
 * signs where needed, and check the signs of each component.
 *
 */
class EquationPreparer{
	private String source;							//stored source									
	
	EquationPreparer(){ source = ""; }				//constructor			
	EquationPreparer(String src){ source = src; }	//constructor
	
	public String prepare() throws SyntaxErrorException {
		//prepares the source string
		
		//scan();
		remove_blanks();							//all blanks removed
		convert_toLower();							//lower case every character
		addStar();									//separate 3x -> 3*x
		check_signs();								//fix 3 +- 3 -> 3 - 3
		
		return source;
	}
	
	//Remove all blanks from source
	private void remove_blanks(){ source = source.replaceAll("\\s", ""); }	
	
	//Converts all characters to lower case
	private void convert_toLower(){ source = source.toLowerCase(); }
	
	//Pops the last character from a string
	private String removeLastChar(String target) {
		return target.substring(0, target.length() - 1);
	}
	
//	private void scan() throws SyntaxErrorException{
//		System.out.println(source);
//		if (!source.matches("[pie1234567890-+*/=()logxyz]"))
//			throw new SyntaxErrorException("Invalid Character\n");
//	}
	
//	private void negative_functions(){
//		String new_line;
//		for (int i = 0; i < source.length(); i++)
//			if (source.charAt(i) == ' ')
//				if (!Character.isDigit(source.charAt(i + 2)))
//					
//						
//	}
	
	//Check the signs of the expression 
	private void check_signs() {
		String new_line = "";
		for (int i = 0; i < source.length(); i++) {
			if ((source.charAt(i) == '-') && (i == 0))
				new_line += " -";
			else if (source.charAt(i) == '-') {
				if (source.charAt(i + 1) == 'c'){
					new_line += " -1*";
				}
				else if (source.charAt(i + 1) == 's') {
					new_line += " -1*";
				}
				else if (source.charAt(i + 1) == 't') {
					new_line += " -1*";
				}
				else if (source.charAt(i + 1) == 'l') {
					new_line += " -1*";
				}
				else if (source.charAt(i - 1) == '-') {
					new_line = removeLastChar(new_line);
					new_line += '+';
				}
				else if (source.charAt(i - 1) == '+') {
					new_line = removeLastChar(new_line);
					new_line += '-';
				}
				else if (source.charAt(i - 1) == '*') {
					new_line += " -";
				}
				else if (source.charAt(i - 1) == '/') {
					new_line += " -";
				}
				else if (source.charAt(i - 1) == '(') {
					new_line += " -";
				}
				else if (source.charAt(i - 1) == '^'){
					new_line += " -";
				}
				else
					new_line += source.charAt(i);
			}
			else
				new_line += source.charAt(i);	
		}
		source = new_line;
	}
	
	//adds multiplication symbol for coefficients and variables
	private void addStar() {
		String new_line = "";
		for (int i = 0; i < source.length();i++) {
			if (i == 0)
				; //Do not check previous character
			else if (Character.isAlphabetic(source.charAt(i))) {
				if (Character.isDigit(source.charAt(i - 1)))
					new_line += "*";
			}
			new_line += source.charAt(i);	
		}
		source = new_line;
	}
	
}
