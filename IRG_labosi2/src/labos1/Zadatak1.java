package labos1;

import java.util.ArrayList;

public class Zadatak1 {

	public static void main(String[] args) {
		Vektor3D v1 = new Vektor3D(2,3,-4).zbroji(new Vektor3D(-1,4,-1));
		System.out.println("vektor v1 = " + v1);
		
		
		Vektor3D v5 = new Vektor3D(1,2,3);
		Vektor3D v6 = new Vektor3D(3,2,1);
		
		System.out.println(v5.skalarniProdukt(v6));
		
		System.out.println(v5.vektorskiProdukt(v6));
		
		
		double s = v1.skalarniProdukt(new Vektor3D(-1,4,-1));
		System.out.println("skalarni produkt = " + s);
		
		Vektor3D v2 = v1.vektorskiProdukt(new Vektor3D(2,2,4));
		System.out.println("vektor v2 = " + v2);
		
		Vektor3D v3 = v2.normiraj();
		System.out.println("normirani v2 = " + v3);
		
		Vektor3D v4 = v2.suprotniVektor();
		System.out.println("suprotni od v2 = " + v4);
		
		
		ArrayList<Double> a = new ArrayList<Double>();
		a.add(1.);
		a.add(2.);
		a.add(3.);
		a.add(2.);
		a.add(1.);
		a.add(3.);
		a.add(4.);
		a.add(5.);
		a.add(1.);
		
		ArrayList<Double> b = new ArrayList<Double>();
		b.add(-1.);
		b.add(2.);
		b.add(-3.);
		b.add(5.);
		b.add(-2.);
		b.add(7.);
		b.add(-4.);
		b.add(-1.);
		b.add(3.);
		
		Matrica m1 = new Matrica(a).zbroji(new Matrica(b));
		
		System.out.println("matrica M1: \n" + m1);
		
		
		Matrica m2 = new Matrica(a).pomnozi(new Matrica(b).transponiraj());
		
		System.out.println("matrica M2: \n" + m2);
		
		Matrica m3 = new Matrica(a).pomnozi(new Matrica(b).inverz());
		
		System.out.println("matrica m3: \n" + m3);
	}

}
