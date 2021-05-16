package labos5;

import java.util.ArrayList;


import org.joml.Matrix4d;
import org.joml.Vector3d;
import org.joml.Vector4d;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

public class Tijelo implements GLEventListener {

	public Vector3d ociste;
	public Vector3d glediste;
	public ArrayList<Vector3d> vrhovi;
	public ArrayList<String[]> poligoniPozicije;
	public Matrix4d T1, T2, T3, T4, T5, P;
	public Matrix4d T;

	public Tijelo(Vector3d ociste, Vector3d glediste, ArrayList<Vector3d> vrhovi,
		ArrayList<String[]> poligoniPozicije) {
		this.ociste = ociste;
		this.glediste = glediste;
		this.vrhovi = vrhovi;
		this.poligoniPozicije = poligoniPozicije;
		matriceTransformacijeIProjekcije();
		return;
	}

	public void matriceTransformacijeIProjekcije() {
		// Transformacija pogleda
		this.T1 = new Matrix4d(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -ociste.x, -ociste.y, -ociste.z, 1.0);
		double xg1 = glediste.x - ociste.x;
		double yg1 = glediste.y - ociste.y;
		double zg1 = glediste.z - ociste.z;
		double xg2 = Math.sqrt(xg1 * xg1 + yg1 * yg1);
		double yg2 = 0.0;
		double zg2 = zg1;
		double sinAlpha = yg1 / xg2;
		double cosAlpha = xg1 / xg2;
		this.T2 = new Matrix4d(cosAlpha, -sinAlpha, 0.0, 0.0, sinAlpha, cosAlpha, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0);
		double xg3 = 0.0;
		double yg3 = 0.0;
		double zg3 = Math.sqrt(xg2 * xg2 + zg2 * zg2);
		double sinBeta = xg2 / zg3;
		double cosBeta = zg2 / zg3;
		this.T3 = new Matrix4d(cosBeta, 0.0, sinBeta, 0.0, 0.0, 1.0, 0.0, 0.0, -sinBeta, 0.0, cosBeta, 0.0, 0.0, 0.0, 0.0, 1.0);
		this.T4 = new Matrix4d(0.0, -1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0);
		this.T5 = new Matrix4d(-1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0);
		
		System.out.println(T1.m00());
		
		
		this.T = T1.mul(T2).mul(T3).mul(T4).mul(T5);

		// Perspektivna projekcija
		double H = zg3;
		double xp = (ociste.x / ociste.z) * H;
		double yp = (ociste.y / ociste.z) * H;
		this.P = new Matrix4d(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 / H, 0.0, 0.0, 0.0, 0.0);
		
		
		return;
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
		GL2 gl = drawable.getGL().getGL2();
		
		for(String s[] : poligoniPozicije) {
			Vector3d tocka1 = vrhovi.get(Integer.parseInt(s[1]) - 1);
			Vector3d tocka2 = vrhovi.get(Integer.parseInt(s[2]) - 1);
			Vector3d tocka3 = vrhovi.get(Integer.parseInt(s[3]) - 1);
			
			// pretvorba tocki iz [x,y,z] u [x,y,z,1]
			Vector4d tocka1a = new Vector4d(tocka1, 1.0);
			Vector4d tocka2a = new Vector4d(tocka2, 1.0);
			Vector4d tocka3a = new Vector4d(tocka3, 1.0);
			
			
			//transformacija
			Vector4d tocka1Trans = tocka1a.mul(T);
			Vector4d tocka2Trans = tocka2a.mul(T);
			Vector4d tocka3Trans = tocka3a.mul(T);
			
			//projiciranje
			
			Vector4d tocka1Proj = tocka1Trans.mul(P);
			Vector4d tocka2Proj = tocka2Trans.mul(P);
			Vector4d tocka3Proj = tocka3Trans.mul(P);
			
			
			gl.glBegin(GL.GL_TRIANGLES);
			gl.glVertex3d(tocka1Proj.x, tocka1Proj.y, 0);
			gl.glVertex3d(tocka2Proj.x, tocka2Proj.y, 0);
			gl.glVertex3d(tocka3Proj.x, tocka3Proj.y, 0);
			gl.glEnd();
		}

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
