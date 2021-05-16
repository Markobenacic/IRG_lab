package labos1;

public class Vektor3D{
	
	public double x;
	public double y;
	public double z;

	public Vektor3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vektor3D zbroji(Vektor3D drugi) {	
		return(new Vektor3D(this.x + drugi.x, this.y + drugi.y, this.z + drugi.z));
	}
	
	public double skalarniProdukt(Vektor3D drugi) {
		double umnozak = this.x * drugi.x + this.y * drugi.y + this.z * drugi.z;
		return umnozak;
	}
	
	public Vektor3D vektorskiProdukt(Vektor3D drugi) {
		
		double a = this.y * drugi.z - drugi.y * this.z;
		double b = drugi.x * this.z - this.x * drugi.z;
		double c = this.x * drugi.y - drugi.x * this.y;
		
		return new Vektor3D(a,b,c);
	}
	
	public Vektor3D normiraj() {
		double norma = this.norma();
		return new Vektor3D(this.x / norma, this.y / norma, this.z / norma);
	}
	
	
	public double norma() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
	}
	
	public Vektor3D suprotniVektor() {
		return new Vektor3D(-1 * this.x, -1 * this.y, -1 * this.z);
	}
	
	@Override
	public String toString() {
		return "(" + Double.toString(x) + "i " + Double.toString(y) + "j " + Double.toString(z) + "k" + ")";
	}
	
}
