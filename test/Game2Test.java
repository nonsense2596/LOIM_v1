package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import game2.Game2;
import gui.Gui;
import kerdes.Kerdes;
import tarolo.Tarolo;

public class Game2Test {
	Tarolo t;
	Game2 g;
	Kerdes k;
	Gui gu;
	/** Inicializalja es letrehozza a teszteleshez hasznalatos objektumokat
	 *  <p>
	 *  @throws 		FileNotFoundException
	 *  @throws 		IOException
	 */
	@Before
	public void setUp() throws FileNotFoundException, IOException{
		t = new Tarolo("loim2.txt");
		g = new Game2(t.getTaroloInnerArraySizes());
		k = new Kerdes("Mi Oroszorszag fovarosa?","Norilszk","Moszkva","Kurszk","Szentpetervar",'D');
		gu = new Gui("loim2.txt");
	}
	/** A Game2 osztaly Incr fuggvenyet teszteli */
	@Test
	public void testIncr(){
			assertEquals(g.getNumOfQ(),0);
		g.Incr();
			assertEquals(g.getNumOfQ(),1);
	}
	/** A Game2 osztaly SetHelperFalse fuggvenyet teszteli */
	@Test
	public void testSetHelperFalse(){
			assertEquals(true,g.getHelper(0));
			assertEquals(true,g.getHelper(1));
			assertEquals(true,g.getHelper(2));
			assertEquals(true,g.getHelper(3));
		g.setHelperFalse(0);
			assertEquals(false,g.getHelper(0));
		g.setHelperFalse(1);
			assertEquals(false,g.getHelper(1));
		g.setHelperFalse(2);
			assertEquals(false,g.getHelper(2));
		g.setHelperFalse(3);
			assertEquals(false,g.getHelper(3));
	}
	/** A Game2 osztaléy SetHelperFalse osztalyat teszteli tulindexelessel */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testSetHelperFalseOutOfBoundsException(){
		try{
			g.setHelperFalse(88);
		} catch (IndexOutOfBoundsException e){
			throw e;
		}
	}
	/** A Game2 osztaly InitItems inicializalo fuggvenyet teszteli */
	@Test
	public void testInitItems(){
		g.Incr();
		g.setHelperFalse(0);
			assertEquals(1,g.getNumOfQ());
			assertEquals(false,g.getHelper(0));
		g.initItems();
			assertEquals(0,g.getNumOfQ());
			assertEquals(true,g.getHelper(0));
		Integer i = g.getCurrentRandom();
			assertEquals(Integer.class,i.getClass()); // verodjunk vissza
	}
	/** A Game2 osztaly veletlenszeru szameloszlast generalo RandomDistrib fuggvenyet teszteli */
	@Test
	public void testRandomDistrib(){
		int darab = 10; int sum=1000;
		Vector<Integer> v = new Vector<Integer>();
		v.addAll(Game2.randomDistrib(darab, sum));
		for(int i=0;i<darab;i++){
			assertTrue(v.get(i) >= 0 && v.get(i) <=sum);
			assertEquals(Integer.class,v.get(i).getClass());
		}
		darab = 4; sum=100;
		v.clear();
		v.addAll(Game2.randomDistrib(darab, sum));
		for(int i=0;i<darab;i++){
			assertTrue(v.get(i) >= 0 && v.get(i) <=sum);
			assertEquals(Integer.class,v.get(i).getClass());
		}
	}
	/** A Tarolo osztaly TaroloInnerArraySizes fuggvenyet teszteli */
	@Test
	public void testTaroloInnerArraySizes(){
		Vector<Integer> v1 = new Vector<Integer>();
		@SuppressWarnings("serial")
		Vector<Integer> v2 = new Vector<Integer>(){{
			add(591);add(593);add(587);add(579);add(557);add(534);add(476);
			add(415);add(304);add(189);add(96);add(50);add(17);add(6);add(2);}};
		v1.addAll(t.getTaroloInnerArraySizes());
		for(int i=0;i<v1.size();i++)
			assertEquals(v1.get(i),v2.get(i));
	}
	/** A Tarolo osztaly InitializeQuestions fuggvenyet teszteli
	 *  <p>
	 *  @throws FileNotFoundException
	 *  @throws IOException
	 */
	@Test
	public void testInitializeQuestions() throws FileNotFoundException, IOException{
		t.InitializeQuestions("loim2.txt");
			assertEquals(t.getFrom(0, 0).getKerdes(),"Hol talalhato a csarnokviz?");
			assertEquals(t.getFrom(4, 5).get(2), "Maurice Ravel");
	}
	/** A Kerdes osztaly SetHelyes fuggvenyet teszteli (karakterkonverzio) */
	@Test
	public void testSetHelyes(){
			assertEquals(k.getHelyes(),3);
		k.setHelyes('B');
			assertEquals(k.getHelyes(),1);
	}
	/** A Gui osztaly jatekot indito StartGame fuggvenyet teszteli */
	@Test
	public void testStartGame(){
		gu.startGame();
			assertEquals(gu.getModel().getElementAt(0),"Helpers");
			assertEquals(gu.getModel().getElementAt(1),"Phone");
			assertEquals(gu.getModel().getElementAt(2),"50:50");
			assertEquals(gu.getModel().getElementAt(3),"Audience");
	}
	/** A Game2 objektumot letrehozo konstruktort teszteli */
	@Test
	public void testInitGame2(){
		Game2 g2 = new Game2(t.getTaroloInnerArraySizes());
			assertEquals(g2.getCurrentArraySize(),591);
		g2.Incr();
			assertEquals(g2.getCurrentArraySize(),593);
		@SuppressWarnings("serial")
		ArrayList<Integer> uj = new ArrayList<Integer>(){{
				add(144);add(888);add(666);add(74);}};
		g2 = new Game2(uj);
		g2.Incr();
			assertEquals(g2.getCurrentArraySize(),888);
	}
	/** Egy uj kerdest letrehozo konstruktort tesztel */
	@Test
	public void testNewKerdes(){
		Kerdes k2 = new Kerdes("A µ's ellenfele a LoveLive-ban","A-RISE","Aqours","Saint Snow","Poppin' Party",'A');
			assertEquals(k2.get(2),"Saint Snow");
			assertEquals(k2.getHelyes(),0);
	}
}
