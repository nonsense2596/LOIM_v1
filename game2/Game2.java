package game2;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Vector;



/**
 *  A jatekmenethez szukseges segedvaltozok es fuggvenyek kollekcioja.
 *  Kerdesek szama, segitsegek szama es elerhetosege, logikaja, minden korben 
 *  aktualis veletlen kerdes sorszam  meghatarozasaert felel. Tovabba a 
 *  mentes es mentesbol valo betoltes funkciokat is ellatja.
 */
@SuppressWarnings("serial")
public class Game2 implements Serializable {
	
	int numOfQ; 							// Kerdes-sama (1-15)
	ArrayList<Integer> TaroloArraySizes; 	// kerdesek szama tipusra lebontva
	ArrayList<Boolean> helpsRemaining; 		// segitsegek szama es elerhetosege
	int rnum; 								// random veletlenszam generalva melyik kerdes?
	
	/** Konstruktor. Inicializalja az objektum altal hasznalt tomboket,
	 *  aktivva teszi jatek kezdetekor a segitsegeket,
	 *  es eltarolja a kerdestombok elemeinek a szamat. Meghivja az  initItems fuggvenyt
	 *  ami inicializalja a nem csak jatek elejen valtoztatando valtozokat.
	 *  <p>
	 *  @param 	a 		A mereteket tartalmazo tombot kapja meg a Tarolo osztalybol.
	*/
	public Game2(ArrayList<Integer> a){
		TaroloArraySizes = new ArrayList<Integer>();
		helpsRemaining = new ArrayList<Boolean>(4);
		for(Integer i: a){
			TaroloArraySizes.add(i);
		}
		for(int i=0;i<4;i++){
			helpsRemaining.add(true);
		}
		initItems();
	}
	/** Ha egy segitseget elhasznaltunk a jatekban, akkor meghivodik a fuggveny, es
	 *  eltarolja, hogy az adott segitseget mar elhasznaltuk. A menteshez szukseges ezt lejegyezni
	 *  is, nem eleg csak eltuntetni a segitseg gombjat.
	 *  <p>
	 *  @param 	i 		A letiltando segitseg indexe: 0-fenntartott, 1-telefon, 2-felezo, 3-kozonseg.
	*/
	public void setHelperFalse(int i){
		helpsRemaining.set(i, false); 
	}
	/** Egy segitseg allapotanak a lekerdezese, hogy elerheto-e meg a felhasznalo szamara.
	 *  <p>
	 *  @param 	i 		A lekerdezendo segitseg indexe: 0-fenntartott, 1-telefon, 2-felezo, 3-kozonseg. 
	 *  @return			Elerheto (true) a valasztott segitseg vagy sem (false).
	*/
	public Boolean getHelper(int i){
		return helpsRemaining.get(i); 
	}
	
	/** Konstruktor kozeli szerepe van, de a program inditasa mellett uj jatek inditasakor is meghivodik,
	 *  mivel ez a fuggveny inicializalja az aktualis kerdes sorszamat 0-ra, es teszi elerhetove (ujra)
	 *  az osszes segitseget. Vegezetul a soron kovetkezo kerdeshez tartozo veletlenszamot is legeneralja
	 *  a rollNewRandom fuggveny meghivasaval.
	*/
	public void initItems(){ 
		numOfQ=0;
		helpsRemaining.clear();
		for(int i=0;i<4;i++){
			helpsRemaining.add(true);
		}
		rollNewRandom();
	}
	/** Az adott kerdes helyes megvalaszolasakor noveli egyel a kerdes szamat tartozo (numOfQ) 
	 *  valtozo erteket.
	 */
	public void Incr(){
		numOfQ++;
	}
	/** Az aktualis kerdes sorszamat kerdezi le. Az ezt feltunteto Gui elemnek kell tudnia.
	 */
	public int getNumOfQ(){ 
		return numOfQ;
	}
	/** Az eppen aktualis kerdes tombjenek a merete, az adott nehezsegu kerdesek tombjebol valo
	 *  veletlen sorsolashoz szukseges tudni a max erteket amit felvehet a random.
	 *  <p>
	 *  @return			A visszaadott veletlen szam az aktualis nehezsegu kerdesek tombjebol.
	 */
	public int getCurrentArraySize(){
		return TaroloArraySizes.get(numOfQ); 
	}
	/** A rollNewRandom fuggveny altal generalt veletlenszam lekerdezese 
	 *  <p>
	 *  @return			Az eppen aktualis veletlenszam.
	 */
	public int getCurrentRandom(){ 
		return rnum;
	}
	/** Az aktualis nehezsegu kerdesnek megfelelo tomb indexintervallumabol
	 *  egy pseudo-veletlenszám generalasa.
	 */
	public void rollNewRandom(){ 
		rnum = (int)(Math.random() * getCurrentArraySize()); 
	}
	/** Kisegito fuggveny. Veletlenszeru eloszlast general. A count valtozoban
	 *  parameterul megkapott darabot, amiknek az osszege a finalSum valtozoban
	 *  van meghatarozva. Jelen programban ezek rendre fixen 4 es 100 ertekeket vesznek fel.
	 *  <p>
	 *  @param	count		Hany darab veletlenszamot generaljon.
	 *  @param	finalSum	A generalt veletlenszamoknak mennyi legyen az osszege.
	 *  @return				A negy veletlenszamot tartalmazo tomb.
	 */
	public static Collection<Integer> randomDistrib(int count, int finalSum){ 
		Vector<Integer> ints = new Vector<Integer>();
	    Random r = new Random();
	    int sum = 0;
	    for (int i = 0; i < count - 1; i++){
	       // numbers[i] = r.nextInt((finalSum - sum) / 2) + 1;
	        int temp = r.nextInt((finalSum - sum) / 2) + 1;
	        sum += temp;
	        ints.add(temp);
	    }
	    ints.add(finalSum - sum);
		return ints;
	}
	/** A szerializacioert, a jatek mentesi allapotat tartalmazo file letrehozasaert
	 *  felelos.
	 *  <p>
	 *  A kiirt valtozok a kovetkezoek: TaroloArraySizes, helpsRemaining, nuimOfQ, rnum
	 */
	public void write(){
		try {
			//System.out.println(numOfQ);
			FileOutputStream fos = new FileOutputStream("savefile.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(TaroloArraySizes);
			oos.writeObject(helpsRemaining);
			oos.writeObject(numOfQ);
			oos.writeObject(rnum);
			oos.close();
			//System.out.println(numOfQ);
		}
		catch(FileNotFoundException ex){}
		catch(IOException ex){}
	}
	/** A szerializacioert, a jatek menteset tartalmazo file beolvasasaert felelos.
	 *  <p>
	 *  A beolvasott valtozok a kovetkezoek: TaroloArraySizes, helpsRemaining, nuimOfQ, rnum
	 */
	@SuppressWarnings("unchecked")
	public void read(){
		try {
			//System.out.println(numOfQ);
			FileInputStream fis = new FileInputStream("savefile.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			TaroloArraySizes = (ArrayList<Integer>)ois.readObject();
			helpsRemaining = (ArrayList<Boolean>)ois.readObject();
			/*for(Boolean i: helpsRemaining){
				System.out.println(i);
			}*/
			numOfQ = (int)ois.readObject();
			rnum = (int)ois.readObject();
			ois.close();
			//System.out.println(numOfQ);
		}
		catch(FileNotFoundException ex){}
		catch(IOException ex){}
		catch(ClassNotFoundException ex){}	
	}

}

//	>describe your sex life with java keywords
// 	>protected static void
//  >mfw.jpg
//  >protected, so it's only available for me and my children (^^,)
