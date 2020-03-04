/**
 * [Equation] Object will feature the ability to store a [Queue] of [Component],
 * and the number in the [Queue].
 * 
 *  Features:
 *  * Constructors [],[Queue<Component]
 *  * Insertion into the equation
 *  * Popping the queue
 *  * the Head of the equation
 *  * check if equation is empty
 *  * Check size of equation
 *  * Printing the Equation 
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
public class Equation{
	private Queue<Component> _equation;
	private int _components;
	
	Equation() {
		_equation = new LinkedList<Component>();
		_components = 0;
	}
	
	Equation(Queue<Component> equation){
		_equation.addAll(equation);
		_components = equation.size();
	}
	
	public Boolean insert(Component comp) {
		if (!_equation.add(comp))
			return false;
		_components++;
		return true;
	}
	
	public Component pop(){
		if (!is_Empty()) {
			--_components;
			return _equation.remove();
		}
		return null;
	}
	
	public Component head() {
		if (!is_Empty())
			return _equation.element();
		return null;
	}
	
	public Boolean is_Empty() {
		return _equation.isEmpty();
	}
	
	public int components() {
		return _components;
	}
	
	public String getEqStr() {
		String eqStr = _equation.toString();
		System.out.println(" || Equation: Getting EQ Str: " + eqStr);
		return eqStr;
	}
	
	public Queue<Component> getEquation(){
		return _equation;
	}
	
	public void print() {
		Iterator<Component> it = _equation.iterator();
		while (it.hasNext()) {
			Component element = (Component) it.next();
			System.out.print(element + "  ");
		}
	}
	
	public void println() {
		Iterator<Component> it = _equation.iterator();
		while (it.hasNext()) {
			Component element = (Component) it.next();
			System.out.print(element + "  ");
		}
		System.out.print("\n");
	}
}

