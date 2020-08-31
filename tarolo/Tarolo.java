package tarolo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import kerdes.Kerdes;

/**
 *  A kerdeseket mint objektumokat tartalmazo osztaly. 
 *  Az elkeszult kerdes tipusu objektumok tarolasan kivul azok beolvasasarol is gondolkozik
 *  a txt alapu adatfilebol. (loim2.txt)
 */
public class Tarolo {
	

	ArrayList<ArrayList<Kerdes>> tarolo = new ArrayList<>();
	
	/** Letrehoz es inicializal Tarolo tipusu objektumot a paramterul kapott filebol.
	 *  <p>
	 *  A konstruktor maga csak a kulso tomb deklarasat oldja meg maga, annak inicializalasat,
	 *  adatokkal valo feltolteset a meghivott InitializeQuestiosn fuggveny hajtja vegre.
	 *  <p>
	 *  @param 	location	Az adatbazisfile eleresi utvonalat es cimet tartalmazo string.
	*/
	public Tarolo(String location) throws FileNotFoundException, IOException {
		//kerdesszam = 0;
		for(int i=0;i<15;i++){
			tarolo.add(new ArrayList<Kerdes>());
		}
		InitializeQuestions(location);
	}
	
	/** A ket egymasba agyazott ArrayList (tarolo) - ket dimenzios tomb paramterkent
	 *  megadott poziciojahoz hozzaad egy Kerdes objektumot.
	 *  <p>
	 *  @param 	i		A tarolo kulso ArrayList-jenek indexe.
	 *  @param  k		A Kerdes objektum, amit hozzaad a tarolo megfelelo poziciojahoz.
	*/
	public void addTo(int i, Kerdes k){
		tarolo.get(i).add(k);
	}
	
	/** A ket egymasba agyazott ArrayList (tarolo) - ket dimenzios tomb paramterkent
	 *  megadott poziciojaban talalhato Kerdes objektummal ter vissza.
	 *  <p>
	 *  @param 	i		A tarolo kulso ArrayList-jenek indexe.
	 *  @param  j		A Kerdes belso ArrayList-jenek indexe.
	 *  @return 		Az adott indexeken talalhato keresett Kerdes objektum.
	*/
	public Kerdes getFrom(int i, int j){
		return tarolo.get(i).get(j);
	}
	
	/** A kerdesek tarolo tombjenek inicializalasa, feltoltese a Kerdesekkel.
	 *  <p>
	 *  15 nehezsegi szint van, az adatbazisfileban a sor elejen talalhato integert
	 *  kiolvasva hatarozza meg a program, hogy a tarolo hanyadik belso ArrayList-jebe
	 *  irja bele az eppen aktualis kerdest.
	 *  <p>
	 *  @param 	location	A kulso adatbazis file neve, amiben a kerdesek tarolodnak.
	 *  @throws IOException
	 *  @throws FileNotFoundException
	 */
	public void InitializeQuestions(String location) throws IOException, FileNotFoundException{
		FileReader fr = new FileReader(location);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null){
			Scanner sc = new Scanner(line);
			sc.useDelimiter("\t");
			Kerdes k = new Kerdes();
			int index = Integer.parseInt(sc.next())-1;
			k.setKerdes(sc.next());
			for(int i=0;i<4;i++){
				k.add(sc.next());
			}
			k.setHelyes(sc.next().charAt(0));
			//System.out.println(k);
			tarolo.get(index).add(k);
			sc.close();
		}
		//this.getTaroloInnerArraySizes();
		br.close();
	}
	/** A mar inicializalt ket dimenzios ArrayList-bol lekerdezi es egy tombben
	 *  letarolja a belso tombok mereteit integerkent.
	 *  Ezt az ertekcsoportot a Game2 control feladatokat ellato osztaly fogja hasznalni.
	 *  <p>
	 *  @return			A tarolo belso tombjeinek mereteibol alkotott tomb.
	 */
	public ArrayList<Integer> getTaroloInnerArraySizes(){
		ArrayList<Integer> TaroloInnerArraySizes = new ArrayList<Integer>();
		for(ArrayList<Kerdes> i: tarolo){
			TaroloInnerArraySizes.add(i.size());
		}
		return TaroloInnerArraySizes;
	}
	
	/** Tesztelesi celzattal kiirja az osszes eltarolt kerdest.
	 *  Ehhez felhasznalja a Kerdes osztaly toString metodusat.
	 */
	public void listAll(){
		int k = 0;
		for(ArrayList<Kerdes> i: tarolo){
			for(Kerdes j: i){
				System.out.println(k + ". " + j);
			}
			k++;
		}	
	}
}
