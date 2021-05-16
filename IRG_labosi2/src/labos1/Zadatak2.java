package labos1;
import java.util.Scanner;

import org.la4j.Matrix;
import org.la4j.LinearAlgebra.InverterFactory;
import org.la4j.inversion.MatrixInverter;

public class Zadatak2 {

	public static void main(String[] args) {
		
		
		double[] aaa =new double[3];
		aaa[0] = 1;
		aaa[1] = 2;
		aaa[2] = 3;
 		
		Matrix aaaM = Matrix.from1DArray(1, 3, aaa);
		
		double[][] bbb =new double[3][3];
		
		bbb[0][0] = 1;
		bbb[0][1] = 0;
		bbb[0][2] = 2;
		bbb[1][0] = 2;
		bbb[1][1] = 1;
		bbb[1][2] = 1;
		bbb[2][0] = 1;
		bbb[2][1] = 1;
		bbb[2][2] = 2;
		
		Matrix bbbM = Matrix.from2DArray(bbb);
		
		System.out.println(aaaM.multiply(bbbM));
		
		System.out.println("unesi podatke: ");
		Scanner sc = new Scanner(System.in);
		
		double[][] a = new double[3][3];
		double[] b = new double[3];
		
		
		double num = 235;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				num = sc.nextDouble();
				if(j != 0 && j%3 == 0) {
					b[i] = num;
				}else {
					a[i][j] = num;
				}
			}
		}
		sc.close();
		
		
		Matrix M1 = Matrix.from2DArray(a);
		Matrix M2 = Matrix.from1DArray(3, 1, b);
		
		MatrixInverter inverter = M1.withInverter(InverterFactory.GAUSS_JORDAN);
		Matrix M1inv = inverter.inverse();
		
		Matrix X = M1inv.multiply(M2);
		System.out.println("[x y z] = [" + X.toColumnVector().toCSV() + "]");
		
		
	}

}
