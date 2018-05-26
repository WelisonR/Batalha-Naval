import java.awt.Graphics;

public class CanvasThread extends Thread {
        
	private CanvasJogo canvas;
	private boolean running = true; 
	
	public CanvasThread(CanvasJogo canvas) {
		this.canvas = canvas;
	}
        
	@Override
	public void run() {
		while(running) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			canvas.paint(canvas.getGraphics());
		}
	}

}