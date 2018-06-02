
// class to repaint the canvas in accordance to a timer
public class CanvasThread extends Thread {
	private CanvasGame canvas;
	private boolean running = true; 
	
	public CanvasThread(CanvasGame canvas) {
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
                        
			if (running == true){
                                canvas.paint(canvas.getGraphics());
                        }
                                
		}
	}
        
        public void setRunning(boolean running) {
                this.running = running;
        }

}