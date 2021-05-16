package labos4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
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
		
		System.out.println("java.version = " + System.getProperty("java.version"));
		System.out.println("Locale.getDefault() = " + Locale.getDefault());
		
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		glcanvas.setSize(400, 400);
		// creating frame
		final JFrame frame = new JFrame("Poligoni");
		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(400,400);
		frame.setVisible(true);
		
		// ucitavanje podataka
		Scanner sc = null;
		Path path = Paths.get(DRAGON_PATH);
		try {
			sc = new Scanner(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
		ArrayList<Vector3d> vrhovi = new ArrayList<Vector3d>();
		ArrayList<Poligon> poligoni = new ArrayList<Poligon>();
		ArrayList<String[]> poligoniPozicije = new ArrayList<String[]>();
		
		
		
		while(sc.hasNext()) {
			String line = sc.nextLine();
			if(line.startsWith("v")) {
				String[] s = line.split(" ");
				vrhovi.add(new Vector3d(Double.parseDouble(s[1]), Double.parseDouble(s[2]), Double.parseDouble(s[3])));
			}else if (line.startsWith("f")) {
				String[] s = line.split(" ");
				poligoni.add(new Poligon(vrhovi.get(Integer.parseInt(s[1])-1), vrhovi.get(Integer.parseInt(s[2])-1) ,vrhovi.get(Integer.parseInt(s[3])-1)));
				poligoniPozicije.add(s);
			}else {
				continue;
			}
		}
		System.out.println("broj vrhova: " + vrhovi.size());
		System.out.println("broj poligona: " + poligoni.size());
		
		
		//minimalne i maksimalne kordinate:
		double xmin = 10000;
		double xmax = -10000;
		double ymin = 10000;
		double ymax = -10000;
		double zmin = 10000;
		double zmax = -10000;
		
		for(int i = 0; i < vrhovi.size(); i++) {
			Vector3d vrh = vrhovi.get(i);
			if(xmin > vrh.x) xmin = vrh.x;
			if(xmax < vrh.x) xmax = vrh.x;
			if(ymin > vrh.y) ymin = vrh.y;
			if(ymax < vrh.y) ymax = vrh.y;
			if(zmin > vrh.z) zmin = vrh.z;
			if(zmax < vrh.z) zmax = vrh.z;
		}
		
		// odrediti središte tijela i postaviti u ishodište, skalirati tijelo na raspon [-1, 1]
		
		double xSrednje = (xmin + xmax) / 2;
		double ySrednje = (ymin + ymax) / 2;
		double zSrednje = (zmin + zmax) / 2;
		
		System.out.println("Srediste tijela: (" + xSrednje + "," + ySrednje + "," + zSrednje + ")");
		
		double M = Math.max(Math.max(xmax - xmin, ymax - ymin), zmax - zmin);
		
		// to su vrhovi translatirani na raspon [-1,1]
		ArrayList<Vector3d> vrhoviTranslatirano = new ArrayList<Vector3d>();
		
		for(int i = 0; i < vrhovi.size(); i++) {
			Vector3d vrh = vrhovi.get(i);
			vrhoviTranslatirano.add(new Vector3d((vrh.x - xSrednje) * (2/M), 
					-(vrh.y - ySrednje) * (2/M), 
					(vrh.z - zSrednje) * (2/M)));
		}
		sc.close();
		
		sc = new Scanner(System.in);
		
		System.out.println("Upisi tocku V: format x,y,z    ");
		String[] s = sc.nextLine().split(",");
		sc.close();
		
		Vector3d tockaV = new Vector3d(Double.parseDouble(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]));
		
		
		
		//vrhovi translatirani sa [-1,1] na [0,400], jer je toiliki prozor koji smo napravili
		ArrayList<Vector3d> vrhoviZaCrtanje = new ArrayList<Vector3d>();
		for(int i = 0; i < vrhoviTranslatirano.size(); i++) {
			Vector3d vrh = vrhoviTranslatirano.get(i);
			double xC = (double)400/2 * Math.abs(1 + vrh.x);
			double yC = (double)400/2 * Math.abs(1 + vrh.y);
			double zC = (double)400/2 * Math.abs(1 + vrh.z);
			
			
			vrhoviZaCrtanje.add(new Vector3d(xC,yC,zC));
		}
		
		
		
		
		ArrayList<Poligon> poligoniZaCrtanje = new ArrayList<Poligon>();
		for(int i = 0; i < poligoniPozicije.size(); i++) {
			String[] ss = poligoniPozicije.get(i);
			poligoniZaCrtanje.add(new Poligon(vrhoviZaCrtanje.get(Integer.parseInt(ss[1])-1), vrhoviZaCrtanje.get(Integer.parseInt(ss[2])-1), vrhoviZaCrtanje.get(Integer.parseInt(ss[3])-1)));
		}
		
		
		for(int i = 0; i < poligoniZaCrtanje.size(); i++) {
			glcanvas.addGLEventListener(poligoniZaCrtanje.get(i));
		}
		
		
		
		tockaIzvanIliUnutar(tockaV, poligoni);
		
	}
	
	public static void tockaIzvanIliUnutar(Vector3d tocka, ArrayList<Poligon> poligoni) {
		boolean izvan = false;
		for(int i = 0; i < poligoni.size(); i++) {
			Poligon poligon = poligoni.get(i);
			double A = (poligon.v2.y - poligon.v1.y) * (poligon.v3.z - poligon.v1.z) - (poligon.v2.z - poligon.v1.z) * (poligon.v3.y - poligon.v1.y);
			double B = -(poligon.v2.x - poligon.v1.x)*(poligon.v3.z - poligon.v1.z) + (poligon.v2.z - poligon.v1.z)*(poligon.v3.x - poligon.v1.x);
			double C = (poligon.v2.x - poligon.v1.x)*(poligon.v3.y - poligon.v1.y) - (poligon.v2.y - poligon.v1.y)*(poligon.v3.x - poligon.v1.x);
			double D = -1 * poligon.v1.x * A -(poligon.v1.y * B) - (poligon.v1.z * C);
			
			double broj = tocka.x * A + tocka.y * B + tocka.z * C + D;
			
			if(broj > 0) {
				izvan = true;
			}
		}
		
		if(izvan) System.out.println("Tocka je izvan objekta");
		else System.out.println("Tocka je unutar objekta");
	}
}
