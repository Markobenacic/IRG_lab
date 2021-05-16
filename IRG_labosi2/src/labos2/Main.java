package labos2;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

import java.awt.Point;
import java.util.Scanner;

import javax.swing.JFrame;
import java.awt.event.MouseListener;

class Line2 implements GLEventListener {

	int x1, y1, x2, y2;

	public Line2(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();

		gl.glBegin(GL.GL_LINES);// static field
		gl.glVertex2i(x1,y1 + 20);

		gl.glVertex2i(x2,y2 + 20);
		gl.glEnd();
		
		

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// method body
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// method body
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		
		
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0, width-1, height-1, 0, 0, 1);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);

	}
}

class Line implements GLEventListener {

	int x1, y1, x2, y2;

	public Line(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	
	public void bresenham(GL2 gl, int xs, int ys, int xe, int ye) {
		if(xs <= xe) {
			if(ys <= ye) {
				bresenham2(gl, xs, ys, xe, ye);
			}else {
				bresenham3(gl, xs, ys, xe, ye);
			}
		}else {
			if(ys >= ye) {
				bresenham2(gl, xe, ye, xs, ys);
			}else {
				bresenham3(gl, xe, ye, xs, ys);
			}
		}
	}
	
	public void bresenham2(GL2 gl, int xs, int ys, int xe, int ye) {
		int pom, yc, korekcija;
		int a,yf;
		
		
		
		if(ye-ys <= xe - xs) {
			a = 2 * (ye-ys);
			yc = ys;
			yf = -(xe-xs);
			korekcija = -2 * (xe-xs);
			for(pom = xs; pom <= xe; pom++) {
				//osvijetliPixel(pom,yc)
					
				
				gl.glBegin(GL.GL_POINTS);
				gl.glVertex2i(pom,yc);
				gl.glEnd();
				gl.glFlush();
				
				
				yf = yf + a;
				if(yf >= 0) {
					yf = yf + korekcija;
					yc = yc + 1;
				}
			}
		}else {
			pom = xe;
			xe = ye;
			ye = pom;
			pom = xs;
			xs = ys;
			ys = pom;
			a = 2 * (ye-ys);
			yc = ys;
			yf = -(xe-xs);
			korekcija = -2*(xe-xs);
			for(pom = xs; pom <= xe; pom++) {
				//osvijetliPixel(yc,pom)
				
				gl.glBegin(GL.GL_POINTS);
				gl.glVertex2i(yc,pom);
				gl.glEnd();
				gl.glFlush();
				
				
				yf = yf + a;
				if(yf >= 0) {
					yf = yf + korekcija;
					yc = yc + 1;
				}
			}
		}
		
	}
	
	public void bresenham3(GL2 gl, int xs, int ys, int xe, int ye) {
		int pom,yc,korekcija;
		int a,yf;
		
		
		if(-(ye-ys) <= xe-xs) {
			a = 2 * (ye-ys);
			yc = ys;
			yf = (xe-xs);
			korekcija = 2 * (xe-xs);
			for(pom = xs; pom <= xe; pom++) {
				//osvijetlipixel(pom,yc)
				
				
				gl.glBegin(GL.GL_POINTS);
				gl.glVertex2i(pom,yc);
				gl.glEnd();
				gl.glFlush();
				
				
				yf = yf + a;
				if(yf <= 0) {
					yf = yf + korekcija;
					yc = yc - 1;
				}
			}
		}else {
			pom = xe;
			xe = ys;
			ys = pom;
			pom = xs;
			xs = ye;
			ye = pom;
			a = 2 * (ye - ys);
			yc = ys;
			yf = (xe-xs);
			korekcija = 2 * (xe-xs);
			for(pom = xs; pom <= xe; pom++) {
				//osvijetli pixel(yc,pom)
				
				gl.glBegin(GL.GL_POINTS);
				gl.glVertex2i(yc,pom);
				gl.glEnd();
				gl.glFlush();
				
				
				yf = yf + a;
				if(yf <= 0 ) {
					yf = yf + korekcija;
					yc = yc - 1;
				}
			}
		}
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
	    
		bresenham(gl, x1, y1, x2, y2);

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// method body
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// method body
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		
		GL2 gl = drawable.getGL().getGL2();
		
		
		gl.glViewport(0, 0, 400, 400);
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0, width-1, height-1, 0, 0, 1);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
	}
}

public class Main {

	public static int x1, y1, x2, y2 = 0;
	public static void main(String[] args) {

		//int	x1, y1, x2, y2;

		Scanner sc = new Scanner(System.in);
		
		
		
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		
		System.out.println("preko tipkovnice? ");
		
		String a = "a";
		a = sc.next();
		
		if(a.equals("y")) {
			System.out.println("učitaj prvu točku: ");

			x1 = sc.nextInt();
			y1 = sc.nextInt();

			System.out.println("Učitaj drugu točku: ");

			x2 = sc.nextInt();
			y2 = sc.nextInt();
			
			
		}else {
			glcanvas.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					
					
				}

				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					
					Point p = e.getPoint();
					
					x1 = p.x;
					y1 = p.y;
					System.out.println("x1,y1: " + x1 + " " + y1);
				}

				@Override
				public void mouseReleased(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					Point p = e.getPoint();
					
					x2 = p.x;
					y2 = p.y;
					System.out.println("x2,y2: " + x2 + " " + y2);
				}

				@Override
				public void mouseEntered(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		
		
		sc.close();

		glcanvas.setSize(400, 400);

		// creating frame
		final JFrame frame = new JFrame("Linija, pravac");

		// adding canvas to frame
		frame.getContentPane().add(glcanvas);

		frame.setSize(400,400);//frame.getContentPane()).getPreferredSize());
		frame.setVisible(true);
		
		
		while(x1 == 0 || x2 == 0) {
			//System.out.println("Cekam");
		}
		
		
		Line l = new Line(x1, y1, x2, y2);
		Line2 l2 = new Line2(x1, y1, x2, y2);
		glcanvas.addGLEventListener(l);
		glcanvas.addGLEventListener(l2);
		//glcanvas.display();

	}

}
