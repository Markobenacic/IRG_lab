package labos3;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

public class Line implements GLEventListener {

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
	    
		gl.glPointSize((float) 0.5);
		
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
		gl.glOrtho(0, width, height, 0, 0, 1);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
	}
}