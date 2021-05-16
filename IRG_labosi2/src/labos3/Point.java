package labos3;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

public class Point implements GLEventListener{
	public int x;
	public int y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
	    
	//	gl.glPointSize((float) 5);
		gl.glBegin(GL.GL_POINTS);
		gl.glVertex2i(this.x, this.y);
		gl.glEnd();
		
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
