package labos5;

import java.io.IOException; 
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import org.joml.Vector3d;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class Main {

	public static String KOCKA_PATH = "kocka.obj";
	public static String TETRAHEDRON_PATH = "tetrahedron.obj";
	public static String TEDDY_PATH = "teddy.obj";
	public static String BIRD_PATH = "bird.obj";
	public static String PORSCHE_PATH = "porsche.obj";
	public static String DRAGON_PATH = "dragon.obj";
	public static String TEAPOT_PATH = "teapot.obj";
	public static String ARENA_PATH = "arena.obj";
	public static String SKULL_PATH = "skull.obj";
	public static String TEMPLE_PATH = "tsd00.obj";
	public static String ALL_PATH = "all.obj";
	public static String FROG_PATH = "frog.obj";
	public static String BULL_PATH = "bull.obj";

	public static void main(String[] args) {

		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		glcanvas.setSize(400, 400);
		// creating frame
		final JFrame frame = new JFrame("Poligoni");
		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(400, 400);
		frame.setVisible(true);

		// ucitavanje podataka
		Scanner sc = null;
		Path path = Paths.get(KOCKA_PATH);
		try {
			sc = new Scanner(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
		ArrayList<Vector3d> vrhovi = new ArrayList<Vector3d>();
		ArrayList<String[]> poligoniPozicije = new ArrayList<String[]>();
		
		
		while(sc.hasNext()) {
			String line = sc.nextLine();
			if(line.startsWith("v")) {
				String[] s = line.split(" ");
				vrhovi.add(new Vector3d(Double.parseDouble(s[1]), Double.parseDouble(s[2]), Double.parseDouble(s[3])));
			}else if (line.startsWith("f")) {
				String[] s = line.split(" ");  // nisi obriso f  s[] = ["f", 1 , 2 ,3]
				poligoniPozicije.add(s);
			}else {
				continue;
			}
		}
		sc.close();
		
		
		
		
		Vector3d glediste = new Vector3d(0.0,0.0,0.0);
		Vector3d ociste = new Vector3d(3.0,3.0,3.0);  // zasad nek je ovak fiksno, pa cu mozda kasnije promijenit da uzme neku koordinatu izvan tijela nezz
		
		System.out.println(glediste);
		
		
		//glcanvas.addGLEventListener(new Tijelo(ociste,glediste,vrhovi,poligoniPozicije));
		
		
		
		
	}
}
