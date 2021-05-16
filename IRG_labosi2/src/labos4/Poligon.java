package labos4;

import org.joml.Vector3d;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

public class Poligon implements GLEventListener{
	public Vector3d v1;
	public Vector3d v2;
	public Vector3d v3;
	
	public Poligon(Vector3d v1, Vector3d v2, Vector3d v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
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
		
		
		gl.glBegin(GL.GL_LINES);
		gl.glVertex2d(v1.x, v1.y);
		gl.glVertex2d(v2.x, v2.y);
		gl.glEnd();
		
		gl.glBegin(GL.GL_LINES);
		gl.glVertex2d(v2.x, v2.y);
		gl.glVertex2d(v3.x, v3.y);
		gl.glEnd();
		
		gl.glBegin(GL.GL_LINES);
		gl.glVertex2d(v3.x, v3.y);
		gl.glVertex2d(v1.x, v1.y);
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
