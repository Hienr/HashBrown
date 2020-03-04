import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display_Graph extends JPanel{
	//Scientific 
	static public final int G_WIDTH = 800, G_HEIGHT = 700;
	
	JFrame _frame;
	JPanel _graph;
	
	ArrayList<Double> _xData;
	ArrayList<Double> _yData;
	Graphing_Calculator _grp;
	
	public Display_Graph(String input) throws SyntaxErrorException {
		System.out.println(" || Booting up graphing.");
		_grp = new Graphing_Calculator(input, -30, 30);
		
		_xData = _grp.getX();
		_yData = _grp.getY();
		
		start_frame();
		_frame.setSize(G_WIDTH, G_HEIGHT);
		
		_frame.setVisible(true);
		
		_frame.getContentPane().add(new Paint(_xData, _yData));
	}
	
	public void start_frame() {
		_frame = new JFrame();
		_frame.setTitle("Graph Window");
		_frame.setBackground(Color.DARK_GRAY);
		_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		_frame.setVisible(true);
	}
	
	public void close() {
		_frame.dispose();
	}
}
