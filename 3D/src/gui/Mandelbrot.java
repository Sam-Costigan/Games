package gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;

import shapes.KochLine;

public class Mandelbrot extends JFrame {
	
	private final int MAX_ITER = 570;
	private final double ZOOM = 150;
	private BufferedImage I;
	private double zx, zy, cX, cY, temp;
	private List<KochLine> lines = new ArrayList<KochLine>();
	
	public Mandelbrot() {
		super("Mandelbrot Set");
		
		setBounds(100, 100, 800, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		
		init();
	}
	
	public void init() {
		for(int y = 0; y < getHeight(); y++) {
			for(int x = 0; x < getWidth(); x++) {
				zx = zy = 0;
				cX = (x - 400) / ZOOM;
				cY = (y - 400) / ZOOM;
				int iter = MAX_ITER;
				while(zx * zx + zy * zy < 4 && iter > 0) {
					temp = zx * zx - zy * zy + cX;
					//temp =  zy * zy - zx * zx + cX;
					zy = 2.0 * zx * zy + cY;
					zx = temp;
					iter--;
				}
				I.setRGB(x, y, iter | (iter << 8));
			}
		}
		
		/*KochLine start = new KochLine(new Point(0, 200), new Point(getWidth(), 200));
		lines.add(start);
		
		generate();
		generate();
		//generate();*/
	}
	
	public void generate() {
		List<KochLine> next = new ArrayList<KochLine>();
		
		for(KochLine line : lines) {
			
			Point a = line.getA();
			Point b = line.getB();
			Point c = line.getC();
			Point d = line.getD();
			Point e = line.getE();
			
			next.add(new KochLine(a, b));
			next.add(new KochLine(b, c));
			next.add(new KochLine(c, d));
			next.add(new KochLine(d, e));
		}
		
		lines = next;
	}
	
	@Override
	public void paint(Graphics g) {
		//g.drawImage(I, 0, 0, this);
		int x = getWidth() / 2;
		int y = getHeight() / 2;
		
		//cantor(100, 100, 600, g);
		drawCircle(x, y, 300, g);
		
		/*for(KochLine line : lines) {
			line.draw(g);
		}*/
	}
	
	public void cantor(int x, int y, int len, Graphics g) {
		if(len >= 1) {
			g.drawLine(x, y, x + len, y);
			
			y += 20;
			
			cantor(x, y, len / 3, g);
			cantor(x + (len * 2/3), y, len / 3, g);
		}
	}
	
	public void drawCircle(int x, int y, float radius, Graphics g) {
		g.drawArc(x - (int)(radius / 2), y - (int)(radius / 2), (int)radius, (int)radius, 0, 360);
		
		if(radius > 20) {
			radius = radius * 0.60f;
			drawCircle(x + (int)radius, y, radius, g);
			drawCircle(x - (int)radius, y, radius, g);
			drawCircle(x, y + (int)radius, radius, g);
			drawCircle(x, y - (int)radius, radius, g);
		}
	}
	
	public static void main(String[] args) {
		Mandelbrot fractal = new Mandelbrot();
		fractal.setVisible(true);
	}
	
}
