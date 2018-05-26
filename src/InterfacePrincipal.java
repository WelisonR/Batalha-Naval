import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InterfacePrincipal extends JFrame {

	private CanvasJogo canvas = new CanvasJogo();
	CanvasThread updateScreenThread = new CanvasThread(canvas);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacePrincipal frame = new InterfacePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InterfacePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Centralizar Janela
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		setTitle("Batalha Naval");
		getContentPane().add("Center", canvas);
		
		// Define largura e altura da janela principal
		setSize(canvas.RECT_WIDTH * canvas.getCanvasNumberOfRows(), canvas.RECT_HEIGHT * canvas.getCanvasNumberOfLines());
		
		setVisible(true);
		
		// Inicia Thread com timer para redesenhar a tela.
		updateScreenThread.start();
		
		canvas.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
		        int x=e.getX();
		        int y=e.getY();
				
		        int x_pos = x/canvas.RECT_WIDTH;
		        int y_pos = y/canvas.RECT_HEIGHT;

		        canvas.setShot(x_pos, y_pos);
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}


		});
	}

}