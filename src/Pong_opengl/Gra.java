package Pong_opengl;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

/**
 * Klasa zapewniajaca wyswietlanie i komunikacjê z u¿ytkownikiem,
 * zawiera metodê main programu
 * */
public class Gra implements GLEventListener{
	
	/**Statyczny atrybut klasy - element typu Gra (widziany w pozosta³ych klasach projektu)*/
	static Gra pong;
	/**Pi³ka, której atrybuty zmieniaja siê podczas rozgrywki*/
	Pilka pilka;
	/**Zielona rakieta gracza lewego wraz z jego wynikiem*/
	Rakieta lewa;
	/**Niebieska rakieta gracza prawego wraz z jego wynikiem*/
	Rakieta prawa;
	/**Bandy okalajace boisko oraz szesciany symbolizujace zdobyte punkty*/
	Boisko boisko;
	
	/**Element biblioteki GL odpowiadajacy za wiekszosc operacji na macierzy zobrazowania*/
	static GL2 gl;
	/**Element pomocniczy biblioteki GL*/
	static GLU glu;
	/**Element pomocniczy biblioteki GL Utility Tool umozliwiajacy rysowanie prymitywow przestrzennych*/
	static GLUT glut;
	/**Rotacja obrazu wzgledem osi OX*/
	float RotX = 90.0f;
	/**Flaga opisujaca akcjê gry:<p> 0 - tryb pauzy,<p> 1 - tryb aktywnej rozgrywki*/
	boolean flaga = false;			//0 - tryb pauzy, 1 - tryb rozgrywki
	int szer = 800, wys = 600;			//poczatkowe wymiary ekranu
	/**Do ilu punktów odbywa siê gra (z zastrze¿eniem istnienia przewag)*/
	public int doIluPunktow;
	//Ustawienia widoku poczatkowego:
	double przyblizenie = 0;
	/**Okno ca³ej aplikacji*/
	static Frame frame;
	/**Obiekt odpowiadajacy za animowanie ekranu*/
    static FPSAnimator animator;

    /**
     * Metoda main. Tworzy nowy egzemplarz gry oraz okno zobrazowania
     * oraz rozpoczyna wszelkie akcje zwiazane z rozgrywka
     * @param args argumenty wywo³ania
     * */
	public static void main(String[] args) {
			//inicjalizacja pol obiektu typu Gra - jej samej, pilki, boiska i rakiet
			pong = new Gra();
			pong.pilka = new Pilka();
			pong.lewa= new Rakieta(0,-50);
			pong.prawa= new Rakieta(0,50);
			pong.boisko= new Boisko();
			pong.przyblizenie=1;
			
	        //pobieranie wlasciwosci dla biblioteki OpenGL
	        GLProfile glp = GLProfile.getDefault();
	        GLCapabilities caps = new GLCapabilities(glp);
	        GLCanvas canvas = new GLCanvas(caps);
	        
	        //utworzenie okna gry
	        frame = new Frame("PONG - Szymon Muszyñski");
	        frame.setSize(pong.szer, pong.wys);
	        frame.add(canvas);
	        frame.setBackground(Color.lightGray);
	        frame.setVisible(true);
	        frame.setEnabled(true);
	        
	        //pobranie od uzytkownika wartosci doIluPunktow - w przypadku bledu ustaw ja na 5
	        try{
	    	String input =
	    			JOptionPane.showInputDialog("Do ilu punktów chcesz graæ?");
	    			pong.doIluPunktow = Integer.parseInt(input);
	        }catch (NumberFormatException e)
	        {
	        	pong.doIluPunktow = 5;
	    		JOptionPane.showMessageDialog(frame, "Nieprawid³owa liczba. Gra toczy siê do 5 punktów.");
	        }
	        finally
	        {
	    		JOptionPane.showMessageDialog(frame, "Sterowanie:\nGracz  I: W, S\nGracz II: 8, 5 (Numpad)\nP - pauza\n");
	        }
	        
	        //dodanie listenerow: zamykania okna, rolki myszy i klawiatury
	        frame.addWindowListener(new WindowAdapter() 
	        	{
		            public void windowClosing(WindowEvent e)
		            {
		                System.exit(0);
		            }
		        });
	       
	        MouseWheelListener MWlistener = new MouseWheelListener() {
				@Override
				public void mouseWheelMoved(MouseWheelEvent mwe) {
					if(mwe.getWheelRotation() < 0){ //rolka przyci¹gana "do siebie"
						if(pong.przyblizenie > 0.7) pong.przyblizenie-=0.05; //oddal
						}
					if(mwe.getWheelRotation() > 0){ //ruch rolki "od siebie"
						if(pong.przyblizenie < 1.2) pong.przyblizenie+=0.05; //przybliz
						}
					}
	        	};
			KeyListener Klistener = new KeySpy();
			frame.addKeyListener(Klistener);
	        frame.addMouseWheelListener(MWlistener);
	        canvas.addGLEventListener(pong);
	        
	        //ustawienie maksymalnej kliczby klatek na sekunde na 60 i wlaczenie gry 
	        animator = new FPSAnimator(canvas, 60);
	        animator.start();
	    }

	/**
	 * Wyswietla okno zawierajace wynik danej gry,
	 * odpowiada za wyjscie z programu
	 * @param nowy gra, dla której podejmowana jest akcja
	 * */
	static void podsumowanieGry(Gra nowy)
	{	
		animator.stop();
		JOptionPane.showMessageDialog(frame,
				"                        PONG "
				+ "\n            Szymon Muszyñski"
				+ "\n            Praca zaliczeniowa "
				+ "\nJêzyki i Techniki Programowania "
				+ "\nMecz zakoñczy³ siê wynikiem "
				+ nowy.lewa.wynik + " : " + nowy.prawa.wynik + ".");
		System.exit(0);
	}


	/**
	 * Metoda implementujaca metodê interfejsu GLEventListener,
	 * odpowiada za renderowanie klatek obrazu
	 * @param  drawable obiekt biblioteki graficznej, który odpowiada za wyswietlanie okna zobrazowania
	 * */
	@Override
	public void display(GLAutoDrawable drawable) {
	gl.glPushMatrix();
		//wyskaluj obraz
   	    gl.glScaled(pong.przyblizenie/100,pong.przyblizenie/67,pong.przyblizenie/67);
   	    gl.glRotatef(RotX, 1, 0, 0);
   	 	gl.glRotatef(RotX, 0, 1, 0);
	    
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f); 
   //     System.out.println(pilka.ball_dir_x);
        pilka.ruszPilke(pong);
        boisko.rysujObszarGry(drawable);
        prawa.rysujGraczaI(pong);
        lewa.rysujGraczaII(pong);
        boisko.wyswietlWynik(pong);
	gl.glPopMatrix();
	}

	/**
	 * Metoda implementujaca metode interfejsu GLEventListener, 
	 * w tym zadaniu nie jest wykorzystywana
	 * @param arg0 obiekt biblioteki graficznej, który odpowiada za wyswietlanie okna zobrazowania
	 * */
	@Override
	public void dispose(GLAutoDrawable arg0) {}

	/**
	 * Metoda implementujaca metodê interfejsu GLEventListener,
	 * zawiera operacje, które wykonywane sa przed wyswietleniem animacji
	 * @param drawable obiekt biblioteki graficznej, który odpowiada za wyswietlanie okna zobrazowania
	 * */
	@Override
	public void init(GLAutoDrawable drawable) {
		 gl = drawable.getGL().getGL2();
		 glu = new GLU();
		 glut = new GLUT();
	}

	/**
	 * Metoda implementujaca metodê interfejsu GLEventListener,
	 * w tym zadaniu nie jest wykorzystywana
	 * */
	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,int arg4) {}

}
