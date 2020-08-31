package kerdes;

import java.util.ArrayList;

/**
 * A kerdeseket jelkepezo osztaly. Az adott kerdes, es a hozza
 * tartozo lehetseges valaszlehetosegeket, tovabba a helyes valasz sorszamat tartalmazza.
 */
public class Kerdes {
	
	String kerdes;
	
	ArrayList<String> valaszok = new ArrayList<String>();
	
	int helyes;
	
	/** Letrehoz egy Kerdes nevu elemet, amit majd a Tarolo osztalyban talalhato tombben
	 *  fogunk eltarolni.
	 *  <p>
	 *  @param 	k 		A kerdest tartalmazo string.
	 *  @param 	v1 		Valaszopcio 1.
	 *  @param 	v2 		Valaszopcio 2.
	 *  @param 	v3 		Valaszopcio 3.
	 *  @param 	v4 		Valaszopcio 4.
	 *  @param 	h 		A helyes valasz sorszama
	*/
	public Kerdes(String k, String v1, String v2, String v3, String v4, char h){
		this.kerdes=k;
		valaszok.add(v1);
		valaszok.add(v2);
		valaszok.add(v3);
		valaszok.add(v4);
		this.helyes=(int)h-65;
	}
	/** Letrehoz egy inicializalatlan, kerdes nelkuli elemet.
	*/
	public Kerdes(){}
	/** Hozzaadja a parameterkent kapott kerdest a valaszokat tartalmazo ArrayList-hez.
	 *  <p>
	 *  @param 	valasz 	A valaszlehetoseget tartalmazo string.
	*/
	public void add(String valasz){
		valaszok.add(valasz);
	}
	/** Lekerdezi a valaszokat tartalmazo tomb parameterkent megkapott szamu elemet es visszater vele.
	 *  <p>
	 *  @param 	i 		A valaszlehetoseget tartalmazo string egyik indexe.
	 *  @return			A valaszok tomb megfelelo indexu eleme.			
	*/
	public String get(int i){
		return valaszok.get(i);
	}
	/** Lekerdezi az eppen aktualis kerdest.
	 * <p>
	 * @return			Az eppen aktualis kerdes stringje.		
	*/
	public String getKerdes() {
		return kerdes;
	}
	/** Beallitja az objektumban a kerdes string erteket a parameterkent megadottra.
	 *  <p>
	 *  @param	kerdes	A parameterkent kapott beallitando kerdest tartalamzo string.	
	*/
	public void setKerdes(String kerdes) {
		this.kerdes = kerdes;
	}
	/** Lekerdezi az eppen aktualis kerdes helyes valaszanak a sorszamat.
	 *  <p>
	 *  @return			Az eppen aktualis kerdes helyes valaszanak a sorszama.	
	*/
	public int getHelyes() {
		return helyes;
	}
	/** Beallitja az objektumban a kerdes helyes valaszanak a sorszamat.
	 *  <p>
	 *  A forrasfileban A-D karakterekkel vannak jelolve, ezert konverziot hajt vegre,
	 *  levon az ASCII tablaban szereplo ertekukbol 65-ot, igy az A-D karaktereket
	 *  0-3 szamokkal tudjuk reprezentalni.
	 *  <p>
	 *  @param	helyes	A helyes valasz sorszamat jelzo karakter.
	*/
	public void setHelyes(char helyes) {
		this.helyes = (int)helyes-65;
	}
	@Override
	/** A java.object osztaly toString metodusanak feluldefinialasa tesztelesi celokra.
	 *  Kiirja az adott objektum osszes elemet.
	 *  @return			Egy stringben a kerdes, a negy lehetseges valasz es a helyes valasz sorszama.
	*/
	public String toString(){
		return kerdes + " " + valaszok.toString() + " " + helyes;
	}
	
	
}
