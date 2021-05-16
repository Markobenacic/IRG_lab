package labos1;

import java.util.Scanner;

import org.la4j.Matrix;
import org.la4j.LinearAlgebra.InverterFactory;
import org.la4j.inversion.MatrixInverter;

public class Zadatk3 {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		double[][] abc = new double[3][3];
		
		double[] t = new double[3];
		
		for(int i = 0; i < 3; i++) {
			System.out.println("Unesi točku " + (i+1) );
			for(int j = 0; j < 3; j++) {
				abc[i][j] = sc.nextDouble();
			}
		}
		
		System.out.println("Unesi točku T: ");
		for(int i = 0; i < 3; i++) {
			t[i] = sc.nextDouble();
		}
		
		Matrix m1 = Matrix.from2DArray(abc);
		
		MatrixInverter inverter = m1.withInverter(InverterFactory.GAUSS_JORDAN);
		Matrix m1inv = inverter.inverse();
		
		Matrix m2 = Matrix.from1DArray(3, 1, t);
		
		Matrix tt = m1inv.multiply(m2);
		
		System.out.println("baricentrične koordinate [t1 t2 t3] = [" + tt.toColumnVector().toCSV() + "]");
		
	}

}
