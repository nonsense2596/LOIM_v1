package gui;

import game2.Game2;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import tarolo.Tarolo;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JLabel;


@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
/**
 *  A jatek grafikus feluletet kezelo objektumokat hozza letre es tarolja, egyben
 *  a jatekmenetbeli kulcsfunkciokat megvalosito beagyazott ActionListener objektumok
 *  tarolasi helye is.
 */
public class Gui extends JFrame{
	
	Tarolo t;
	Game2 ctrl;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	
	JComboBox<String> comboBox;
	DefaultComboBoxModel model;
	

	
	/** Konstruktor. Inicializalja a kerdeseket tarolo es a segito
	 *  osztalyok objektumait, es meghivja a grafikus feluletet
	 *  tenylegesen elkeszito initGui fuggvenyt. Attekinthetosegi
	 *  celbol lett kulonvalasztva.
	 *  <p>
	 *  @param 	s		A kerdeseket tartalmazo adatfile neve..
	*/
	public Gui(String s) throws FileNotFoundException, IOException{
		t = new Tarolo(s);
		ctrl = new Game2(t.getTaroloInnerArraySizes());
		initGui();
	}
	/** Letrehozza a grafikus feluletet. Az ablakot, a menurendszert, 
	 *  a listakat, text fieldeket es az osszes gombot. */
	public void initGui(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New Game");
		ActionListener jmi_1 = new NewGameAL(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(jmi_1);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Load Game");
		ActionListener jmi_2 = new Loader(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(jmi_2);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setText(""); //left_text
		textField.setEditable(false);
		textField.setBounds(10, 128, 315, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		btnNewButton_1 = new JButton("");
		ActionListener al_1 = new AnswerChecker(btnNewButton_1);
		btnNewButton_1.addActionListener(al_1);
		btnNewButton_1.setBounds(10, 159, 147, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setEnabled(false);
		
		btnNewButton_2 = new JButton("");
		ActionListener al_2 = new AnswerChecker(btnNewButton_2);
		btnNewButton_2.addActionListener(al_2);
		btnNewButton_2.setBounds(178, 159, 147, 23);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.setEnabled(false);

		btnNewButton_3 = new JButton("");
		ActionListener al_3 = new AnswerChecker(btnNewButton_3);
		btnNewButton_3.addActionListener(al_3);
		btnNewButton_3.setBounds(10, 193, 147, 23);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.setEnabled(false);
		
		btnNewButton_4 = new JButton("");
		ActionListener al_4 = new AnswerChecker(btnNewButton_4);
		btnNewButton_4.addActionListener(al_4);
		btnNewButton_4.setBounds(178, 193, 147, 23);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.setEnabled(false);
		

		ImageIcon imageic = new ImageIcon("bg.png");
		Image image = imageic.getImage();
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
		imageic = new ImageIcon(newimg);
		JLabel lblNewLabel = new JLabel(imageic, JLabel.CENTER);
		lblNewLabel.setBounds(96, 0, 132, 117);
		contentPane.add(lblNewLabel);
		
		
		textField_1 = new JTextField();
		textField_1.setText("");//right_text
		textField_1.setEditable(false);
		textField_1.setBounds(332, 128, 89, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("Save");
		btnNewButton_5.setBounds(332, 63, 89, 23);
		contentPane.add(btnNewButton_5);
		ActionListener saver = new Saver(btnNewButton_5);
		btnNewButton_5.addActionListener(saver);
		
		JButton btnNewButton_6 = new JButton("Concede");
		btnNewButton_6.setBounds(332, 97, 89, 23);
		contentPane.add(btnNewButton_6);
		ActionListener concede = new Concede(btnNewButton_6);
		btnNewButton_6.addActionListener(concede);
		
		textArea = new JTextArea();
		textArea.setText(""); //bottom_right_text
		textArea.setEditable(false);
		textArea.setBounds(332, 159, 89, 57);
		contentPane.add(textArea);
		textArea.setColumns(10);
		
		this.setVisible(true);
		// teszt erejeig -- THIS IS A TEST. THIS IS ONLY A TEST.
		
		model = new DefaultComboBoxModel(new String[] {"Helpers", "Phone", "50:50", "Audience"});
		comboBox = new JComboBox(model);
		comboBox.setMaximumRowCount(4);
		comboBox.setBounds(332, 11, 92, 22);
		ActionListener hlpr = new HelperAL(comboBox);
		comboBox.addActionListener(hlpr);
		contentPane.add(comboBox);	
	}
	
	/** Elinditja a jatekot, elkezdett allapotba allitja a feluletet, kiirja
	 *  az elso kerdest es hozza tartozo valaszokat. A Game2 osztalyban levo
	 *  valtozokat hasznalja fel, igy uj jatek kezdetekor es mar meglevo jatek
	 *  mentesfilejanak betoltesekor is hasznalando.
	 */
	public void startGame(){
		String[] cbbxElements = new String[]{"Helpers","Phone","50:50","Audience"};
		for(int i=0;i<cbbxElements.length;i++){
			if(model.getIndexOf(cbbxElements[i]) == -1 && ctrl.getHelper(i)!= false) {
				model.addElement(cbbxElements[i]);
			}
		}

		btnNewButton_1.setText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(0));
		btnNewButton_1.setToolTipText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(0));
		btnNewButton_2.setText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(1));
		btnNewButton_2.setToolTipText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(1));
		btnNewButton_3.setText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(2));
		btnNewButton_3.setToolTipText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(2));
		btnNewButton_4.setText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(3));
		btnNewButton_4.setToolTipText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(3));
		btnNewButton_1.setEnabled(true);
		btnNewButton_2.setEnabled(true);
		btnNewButton_3.setEnabled(true);
		btnNewButton_4.setEnabled(true);
		
		textField.setText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).getKerdes());
		textField.setToolTipText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).getKerdes());
		
		textField_1.setText(ctrl.getNumOfQ()+ 1 + "/" + 15 );
		textArea.setText("");
	}
	/** A Save gombhoz tartozo Esemenykezelo. A jatek menti
	 *  a (Game2 osztalypeldany) allapotat.
	*/
	final public class Saver implements ActionListener{
		JButton gomb;
		public Saver(JButton gomb){
			this.gomb = gomb;
		}
		public void actionPerformed(ActionEvent ae){
			ctrl.write();
		}
	}
	/** A valaszlehetoseg gombjaihoz tartozo Esemenykezelo.
	 *  Helytelen valaszlehetoseg kivalasztasa vagy jatek megnyerese 
	 *  (15. kerdes helyes megvalaszolasa) eseten a program
	 *  a jatek vege allapotba kerul, a gombok elszurkulnek, a Jatekos
	 *  a New Game vagy Load Game gombok segitsegevel tud ujra jatszani.
	 *  Helyes valaszlehetoseg eseten inkrementalja a kerdes sorszamat
	 *  es kiirja az uj kerdeseket.
	 */
	final public class AnswerChecker implements ActionListener{
		JButton gomb;
		int i=1;
		public AnswerChecker(JButton gomb) { 
			this.gomb = gomb;
		}
		public void actionPerformed(ActionEvent ae){

			//System.out.println("TIZENOT" + ctrl.getNumOfQ()); 
			if(	ae.getActionCommand().equals(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).getHelyes()))){
				if(ctrl.getNumOfQ()>=14){
					gameEndState();
				}else{
					ctrl.Incr();
					ctrl.rollNewRandom();
					
					btnNewButton_1.setText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(0));
					btnNewButton_1.setToolTipText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(0));
					
					btnNewButton_2.setText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(1));
					btnNewButton_2.setToolTipText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(1));
					
					btnNewButton_3.setText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(2));
					btnNewButton_3.setToolTipText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(2));
					
					btnNewButton_4.setText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(3));
					btnNewButton_4.setToolTipText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(3));
					
					textField.setText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).getKerdes());
					textField.setToolTipText(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).getKerdes());
					
					textField_1.setText(ctrl.getNumOfQ()+ 1 + "/" + 15 );
					textArea.setText("");
				}
			}
			else{
				gameEndState();
			}


			
		}
	}
	/** Uj jatek inditasa a New Game jatekkezdo gomb lenyomasa altal */
	final public class NewGameAL implements ActionListener{
		JMenuItem gomb;
		public NewGameAL(JMenuItem gomb) { 
			this.gomb = gomb;
		}
		public void actionPerformed(ActionEvent ae){
			ctrl.initItems();
			startGame();
		}
	}
	/** Korabban elkezdett jatek folytatasa a Load Game gomb lenyomasa altal */
	final public class Loader implements ActionListener{
		JMenuItem gomb;
		public Loader(JMenuItem gomb) { 
			this.gomb = gomb;
		}
		public void actionPerformed(ActionEvent ae){
			ctrl.read();
			startGame();
		}
	}
	/** Jatek feladasa, rogton inaktivizalodik a grafikus felulet, es
	 *  jatek vege allapotba kerul a program.
	 */
	final public class Concede implements ActionListener{
		JButton gomb;
		public Concede (JButton gomb) { 
			this.gomb = gomb;
		}
		public void actionPerformed(ActionEvent ae){
			gameEndState();
		}
	}
	/** A 3 segitseghez tartozo Esemenykezelo. Megallapitja, hogy melyik
	 *  segitseg kerult kivalasztasra a getSelectedItem alapjan, majd eltunteti
	 *  a segitsegnek megfelelo gombokat az 50:50 es a Telefonos segitseg
	 *  eseteben, vagy feltunteti a lehetseges valaszokhoz tartozo szazalekokat
	 *  a megfelelo textboxban.
	 */
	final public class HelperAL implements ActionListener{
		JComboBox gomb;
		public HelperAL (JComboBox gomb) { 
			this.gomb = gomb;
		}
		public void actionPerformed(ActionEvent ae){
			String whichButton = gomb.getSelectedItem().toString();
			Map<String, JButton> m = new LinkedHashMap<String, JButton>();
			m.put(btnNewButton_1.getText(), btnNewButton_1);
			m.put(btnNewButton_2.getText(), btnNewButton_2);
			m.put(btnNewButton_3.getText(), btnNewButton_3);
			m.put(btnNewButton_4.getText(), btnNewButton_4);
			String searchKey = t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).get(t.getFrom(ctrl.getNumOfQ(), ctrl.getCurrentRandom()).getHelyes());
			int skIdx;
			
			switch(whichButton){
				case("Phone"):
					for(String i: m.keySet()){
						if(!(i.equals(searchKey))){
							m.get(i).setText("");
						} 
					}
					ctrl.setHelperFalse(1);
					comboBox.removeItem("Phone");
					break;
				case("50:50"):	
						skIdx = 0;
						label:
						for(String i: m.keySet()){
							if(i.equals(searchKey)){
								break label;
							}
							skIdx++;
						}
						ArrayList<Integer> list = new ArrayList<Integer>();		 
						for (int i=0; i<4; i++) {								
							Integer j =  Integer.valueOf(i);					
							list.add(j);
						}
						int t1;
						int t2;
						t1 = t2 = skIdx;
						while(t1==t2 || t1==skIdx || t2==skIdx){
							Collections.shuffle(list);
							t1=list.get(0);
							t2=list.get(1);
						}
						if(ctrl.getHelper(1)==true){							
							m.get( (m.keySet().toArray())[ t1 ] ).setText(" ");
							m.get( (m.keySet().toArray())[ t2 ] ).setText(" ");
						}
					ctrl.setHelperFalse(2);
					comboBox.removeItem("50:50");
					break;
				
				case("Audience"):	
					Vector<Integer> prcnts = new Vector<Integer>();
					prcnts.addAll(Game2.randomDistrib(4,100));
					skIdx = 0;
					label:
					for(String i: m.keySet()){
						if(i.equals(searchKey)){
							break label;
						}
						skIdx++;
					}
					//System.out.println(skIdx);
					
					int greatestindex = 0;
					for(int j=0;j<prcnts.size();j++){
						if(prcnts.get(j)>prcnts.get(greatestindex))
							greatestindex=j;
					}
					int greatestvalue = prcnts.get(greatestindex);
					greatestvalue = prcnts.remove(greatestindex);
					//System.out.println(greatestindex);
					
					for(int i=0;i<4;i++){
						if(skIdx==i){
							textArea.setText(textArea.getText() + (char)(i+65) + " " +  greatestvalue + "%  ");
						} else{
							textArea.setText(textArea.getText() + (char)(i+65) + " " + prcnts.lastElement() + "%  ");
							prcnts.remove(prcnts.lastElement());
						}
						if((i+1)%2==0){
							textArea.setText(textArea.getText() + "\n");
						}
					}
					ctrl.setHelperFalse(3);
					comboBox.removeItem("Audience");
					break;
				default:
					break;		
			}
		}
	}
	/** Visszaadja a segitsegeket tartalmazo legordulo
	 *  mezo modelljet. Abbol tovabbi Swinges alap getter
	 *  fuggvenyek segitsegevel lekerdezhetoek a parameterei es tulajdonsagai
	 *  <p>
	 *  @return			A legordulo menu alapja.
	 */
	public DefaultComboBoxModel getModel(){
		return model;
	}
	/** Visszaadja a jatek kiegeszito fuggvenykonyvtara egyben
	 *  vezerloosztaja egy peldanyat.
	 *  <p>
	 *  @return			A jatek vezerloosztaja.
	 */
	public Game2 getCtrlClass(){
		return ctrl;
	}
	/** Jatek vege allapot. A Concede gomb lenyomasaval, vagy
	 *  rossz valaszlehetoseg kivalasztasaval lehet ide kerulni.
	 *  Letiltja a gombokat es kitorli a textfieldek tartalmat.
	 *  New Game vagy Load Game lenyomasaval lehet ezutan jatekot kezdeni
	 *  vagy folytatni.
	 */
	public void gameEndState(){
		textArea.setText("jatek vege\n" + "elert eredmeny:\n" + textField_1.getText());
		btnNewButton_1.setText(""); btnNewButton_1.setEnabled(false);
		btnNewButton_2.setText(""); btnNewButton_2.setEnabled(false);
		btnNewButton_3.setText(""); btnNewButton_3.setEnabled(false);
		btnNewButton_4.setText(""); btnNewButton_4.setEnabled(false);
		textField.setText("");
	}
	/** A program belepesi pontja, letrehoz egy Gui peldanyt, ami
	 *  ezutan letrehozza az osszes a jatek altal hasznalt objektumot.
	 *  <p>
	 *  @param args		nincs hasznalatban
	 *  @throws 		FileNotFoundException
	 *  @throws 		IOException
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws FileNotFoundException, IOException{
		Gui game = new Gui("loim2.txt");
	}

}

