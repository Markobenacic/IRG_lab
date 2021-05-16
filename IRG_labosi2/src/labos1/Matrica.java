package labos1;

import java.util.ArrayList;

public class Matrica {
	
	public ArrayList<Double> arr;
	
	public Matrica(ArrayList<Double> arr) {
		this.arr = arr;
	}
	
	public Matrica zbroji(Matrica druga) {
		ArrayList<Double> nova = new ArrayList<Double>();
		
		for(int i = 0; i < this.arr.size(); i++) {
			nova.add(this.arr.get(i) + druga.arr.get(i));
		}
		return new Matrica(nova);
	}
	
	public Matrica transponiraj(){
		
		ArrayList<Double> pomocna = new ArrayList<Double>();
		for(int i = 0; i < 9; i++) {
			pomocna.add(0.);
		}
		
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				pomocna.set(j * 3 + i, this.arr.get(i * 3 + j));
			}
		}
		return new Matrica(pomocna);
	}
	
	public Matrica pomnozi(Matrica druga) {
		ArrayList<Double> rezultantna = new ArrayList<Double>();
		for(int i = 0; i<9; i++) {
			rezultantna.add(0.);
		}
		
		for (int i = 0; i < 3; i++) { 
            for (int j = 0; j < 3; j++) { 
                for (int k = 0; k < 3; k++) {
                	Double pom = rezultantna.get(i * 3 + j);
                	rezultantna.set(i*3 + j, pom + this.arr.get(i * 3 + k) * druga.arr.get(k * 3 + j));
                }	
            } 
        }
		
		return new Matrica(rezultantna);
	}
	
	public Double determinanta() {
		double determinanta = 0.;
		
		for(int j = 0; j < 3; j++) {
			determinanta += this.arr.get(3 * 0 + j) * 
					(this.arr.get(3 * 1 + (j+1)%3) * this.arr.get(2*3 + (j+2)%3) - this.arr.get(1*3 + (j+2)%3) * this.arr.get(2*3 + (j+1)%3));
		}
		
		return determinanta;
	}
	
	public Matrica ajdungirana() {
		ArrayList<Double> adjungirana = new ArrayList<Double>();
		
		
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				double a = this.arr.get(((j+1)%3) * 3 + (i+1)%3) * this.arr.get(((j+2)%3) * 3 + (i+2)%3);
				double b = this.arr.get(((j+1)%3) * 3 + (i+2)%3) * this.arr.get(((j+2)%3) * 3 + (i+1)%3);
				double c = a - b;
				
				adjungirana.add(c);
			}
		}
		
		return new Matrica(adjungirana);
	}
	
	public Matrica pomnoziSkalarom(double skalar) {
		ArrayList<Double> pom = new ArrayList<Double>();
		
		for(int i = 0; i < 9; i++) {
			pom.add(this.arr.get(i) * skalar);
		}
		
		return new Matrica(pom);
	}
	
	public Matrica inverz() {
		
		return new Matrica(this.arr).ajdungirana().pomnoziSkalarom(1/this.determinanta());
	}
	
	
	@Override
	public String toString() {
		String a = "[ ";
		for(int i = 0; i < 9; i++) {
			a += this.arr.get(i).toString() + " ";
			if((i + 1) % 3 ==0 && (i != this.arr.size() - 1)) a += "\n  ";
		}
		a+= "]";
		return a;
	}
}
