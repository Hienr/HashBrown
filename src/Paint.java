import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Paint extends JPanel{
	Graphics _curve;
	ArrayList<Double> _xData;
	ArrayList<Double> _yData;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Paint(ArrayList<Double> x, ArrayList<Double> y) {
		_xData = new ArrayList(x);
		_yData = new ArrayList(y);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		@SuppressWarnings("unused")
		final int PAD = 20;
		
		
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        @SuppressWarnings("unused")
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD,_xData.size());
        
        int w = getWidth();
        int h = getHeight();
        
        g2.drawLine(w/2, 0, w/2, h);
        g2.drawLine(0, h/2, w, h/2);
        
        
        // The origin location.
        int x0 = w/2;
        int y0 = h/2;
        
        int prevx = 0, prevy = 0;
        
        System.out.println(" || Paint: Going from 0 to " + _xData.size());
        
        
        for(int j = 0; j < _xData.size(); j++) {
        	int x = (int) (x0 + _xData.get(j) * 50), y = (int) (y0 - _yData.get(j) * 50);

        	if (j > 0) {
        		g2.setStroke(new BasicStroke(5));
        		g2.setPaint(Color.BLUE);
            	g2.drawLine(prevx, prevy, x, y);
        	}
        	
        	g2.setStroke(new BasicStroke(5));
        	g2.setPaint(Color.WHITE);
        	g2.drawOval(x, y, 1, 1);
        	g2.fillOval(x, y, 1, 1);
        	
        	prevx = (int) (x0 + _xData.get(j) * 50);
        	prevy = (int) (y0 - _yData.get(j) * 50);
        	
        }
        
	}
}
