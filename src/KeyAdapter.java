import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAdapter implements KeyListener{
	static private Controller _controller = new Controller();
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		char action = e.getKeyChar();
		switch (action) {
			case '\n' :  _controller.equals();
						break;
			case '`' : try {
				_controller.graph();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
						break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
