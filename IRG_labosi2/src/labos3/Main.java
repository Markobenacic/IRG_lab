package labos3;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import org.joml.*;

public class Main {
	
	
	public static ArrayList<Point> tocke = new ArrayList<Point>();
	
	public static void main(String[] args) {
		
		
		// ################### SETUP TOCAKA  ##################
		tocke.add(new Point(100,20));
		tocke.add(new Point(130,80));
		tocke.add(new Point(90,60));
		tocke.add(new Point(60,110));
		
		System.out.println("Zelite li unijeti točke?");
		Scanner sc = new Scanner(System.in);
		
		if(sc.nextLine().equals("y")) {
			tocke = new ArrayList<Point>();
			System.out.println("unosite tocke u formatu: x,y  , za prekid unosa unesite rijec stop");
			String unos = sc.nextLine();
			while(!unos.equals("stop")) {
				Point tocka = new Point(Integer.parseInt(unos.split(",")[0]), Integer.parseInt(unos.split(",")[1]));
				tocke.add(tocka);
				unos = sc.nextLine();
			}
		}
		
		System.out.println("zadajte koordinate točke V, u formatu: x,y");
		String s = sc.nextLine();
		Point tockaV = new Point(Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1]));
		
		
		sc.close();
		
		
		// ###################################################
		
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		glcanvas.setSize(400, 400);
		// creating frame
		final JFrame frame = new JFrame("Linija, pravac");
		// adding canvas to frame
		frame.getContentPane().add(glcanvas);
		frame.setSize(400,400);
		frame.setVisible(true);
		
		
		
		
		
		
		for(int i = 0; i < tocke.size() - 1; i++) {
			int x1 = tocke.get(i).x;
			int y1 = tocke.get(i).y;
			int x2 = tocke.get(i + 1).x;
			int y2 = tocke.get(i + 1).y;
			Line l = new Line(x1, y1, x2, y2);
			glcanvas.addGLEventListener(l);
		}
		int x1 = tocke.get(tocke.size() - 1).x;
		int y1 = tocke.get(tocke.size() - 1).y;
		int x2 = tocke.get(0).x;
		int y2 = tocke.get(0).y;
		Line l = new Line(x1, y1, x2, y2);
		glcanvas.addGLEventListener(l);
		glcanvas.addGLEventListener(tockaV);
		
		
		
		
		
		
		ArrayList<Vector2i> tocke2 = new ArrayList<Vector2i>();
		ArrayList<Vector3i> bridovi = new ArrayList<Vector3i>();
		
		for(int i = 0; i < tocke.size(); i++) {
			tocke2.add(new Vector2i(tocke.get(i).x, tocke.get(i).y));
		}
		
		for(int i = 0; i < tocke2.size() - 1; i++) {
			int a = tocke2.get(i).y - tocke2.get(i+1).y;
			int b = -(tocke2.get(i).x - tocke2.get(i+1).x);
			int c = tocke2.get(i).x * tocke2.get(i+1).y - (tocke2.get(i).y * tocke2.get(i+1).x);
			bridovi.add(new Vector3i(a,b,c));
		}
		
		int indeks = tocke2.size() - 1;
		int a = tocke2.get(indeks).y - tocke2.get(0).y;
		int b = -(tocke2.get(indeks).x - tocke2.get(0).x);
		int c = tocke2.get(indeks).x * tocke2.get(0).y - (tocke2.get(indeks).y * tocke2.get(0).x);
		bridovi.add(new Vector3i(a,b,c));
		
		
		
		
		
		// sad bi tu trebao provjeru jesu li clockwise
		
		ispisiTocke(tocke, tockaV);
		odnosTockeIPoligona(bridovi, tockaV);
		
		// ISPUNJAVANJE POLIGONA, trebat ce mi tocke2 i bridovi.
		
		ArrayList<String> karakteriBridova = new ArrayList<String>();
		
		// lijevi ili desni
		for(int i = 0; i < tocke2.size() - 1; i++) {
			if(tocke2.get(i).y > tocke2.get(i + 1).y) karakteriBridova.add("lijevi");
			else karakteriBridova.add("desni");
		}
		if(tocke2.get(tocke2.size()-1).y > tocke2.get(0).y) karakteriBridova.add("lijevi");
		else karakteriBridova.add("desni");
		
		
		
		// algoritam bojanja
		int xmin = 400;
		int xmax = 0;
		int ymin = 400;
		int ymax = 0;
		
		for(int i = 0; i < tocke2.size(); i++) {
			if(tocke2.get(i).x < xmin) xmin = tocke2.get(i).x;
			if(tocke2.get(i).x > xmax) xmax = tocke2.get(i).x;
			if(tocke2.get(i).y < ymin) ymin = tocke2.get(i).y;
			if(tocke2.get(i).y > ymax) ymax = tocke2.get(i).y;
		}
		
		
		for(int y = ymin; y <= ymax; y++) {
			float L = xmin;
			float D = xmax;
			for(int i = 0; i < bridovi.size(); i++) {
				//	if(bridovi.get(i).x == 0) {
					//	continue;
					//}
					int sjeciste = (-1 * bridovi.get(i).y * y - bridovi.get(i).z) / bridovi.get(i).x; // mozda bude bilo
				if(karakteriBridova.get(i).equals("lijevi") && (sjeciste > L)) L = sjeciste;   // za floatom
				if(karakteriBridova.get(i).equals("desni") && (sjeciste < D)) D = sjeciste;
			}
			
			if(L <= D) {
				for(int i = (int) L; i <= D; i++) {
					glcanvas.addGLEventListener(new Point(i,y));
				}
			}
		}

	}
	
	public static void odnosTockeIPoligona(ArrayList<Vector3i> bridovi, Point tocka) {
		boolean unutar = true;
		Vector2i tockaV = new Vector2i(tocka.x, tocka.y);
		
		for(int i = 0; i < bridovi.size(); i++) {
			int zbroj = tockaV.x * bridovi.get(i).x + tockaV.y * bridovi.get(i).y + bridovi.get(i).z;
			if(zbroj <= 0) unutar = false;
		}
		
		if(unutar) System.out.println("Tocka je unutar poligona");
		else System.out.println("Tocka je izvan poligona");
		
		return;
	}
	
	public static void ispisiTocke(ArrayList<Point> tocke, Point tockaV) {
		System.out.println("Broj vrhova poligona:    " + tocke.size());
		for(int i = 0; i < tocke.size(); i++) {
			System.out.println("Koordinate vrha " + i + ":     " + tocke.get(i).x + " " + tocke.get(i).y);
		}
		System.out.println("Koordinate ispitne tocke:    " + tockaV.x + " " + tockaV.y);
	}
}
