package Pong_opengl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Klasa implementujaca s³uchacza klawiatury
 * i podejmujaca ró¿ne akcje dla danej rozgrywki
 *  w zale¿nosci od przypisanej funkcji
 * */
public class KeySpy implements KeyListener{
	
	/**
	 * Metoda podejmujaca akcje przypisane do konkretnych klawiszy
	 * w przypadku zaistnienia akcji przycisniêcia klawisza
	 * @param event zdarzenie przechwycone przez Listener
	 */
	@Override
	public void keyPressed(KeyEvent event) {
	   switch(event.getKeyCode()){

 		case KeyEvent.VK_NUMPAD8:
 			if (Gra.pong.flaga)		//ruszaj rakietk¹ tylko, jesli gra nie jest w trybie pauzy
 			Gra.pong.prawa.racket_x = (Gra.pong.prawa.racket_x < 39) ? Gra.pong.prawa.racket_x += 1.5 : Gra.pong.prawa.racket_x;
 			//modyfikuj racket_x^ rakiety prawej: ^jezeli nie wychodzi poza bande gorna, to zwieksz^ o 1,5, else pozostaw^ obecna wartosc 	
 			break;

 		case KeyEvent.VK_NUMPAD5:
			if (Gra.pong.flaga)		//ruszaj rakietk¹ tylko, jesli gra nie jest w trybie pauzy
 			Gra.pong.prawa.racket_x = (Gra.pong.prawa.racket_x > -39) ? Gra.pong.prawa.racket_x -= 1.5 : Gra.pong.prawa.racket_x;
			//modyfikuj racket_x^ rakiety prawej: ^jezeli nie wychodzi poza bande dolna, to zmniejsz^ o 1,5, else pozostaw^ obecna wartosc 	
 			break;

 		case KeyEvent.VK_W:
 			if (Gra.pong.flaga)		//ruszaj rakietk¹ tylko, jesli gra nie jest w trybie pauzy
 			Gra.pong.lewa.racket_x =   (Gra.pong.lewa.racket_x <39) ? Gra.pong.lewa.racket_x += 1.5 : Gra.pong.lewa.racket_x;
 			//modyfikuj racket_x^ rakiety lewej: ^jezeli nie wychodzi poza bande gorna, to zwieksz^ o 1,5, else pozostaw^ obecna wartosc 	
 			break;

 		case KeyEvent.VK_S:
 			if (Gra.pong.flaga)		//ruszaj rakietk¹ tylko, jesli gra nie jest w trybie pauzy
 			Gra.pong.lewa.racket_x =   (Gra.pong.lewa.racket_x > -39) ? Gra.pong.lewa.racket_x -= 1.5 : Gra.pong.lewa.racket_x;
 			//modyfikuj racket_x^ rakiety prawej: ^jezeli nie wychodzi poza bande dolna, to zmniejsz^ o 1,5, else pozostaw^ obecna wartosc 	
 			break;

 		case KeyEvent.VK_P:
 			Gra.pong.flaga = !Gra.pong.flaga; //zamien wartosc flagi pauzy na przeciwna
 			break;
 			
 		case KeyEvent.VK_ESCAPE:
 			Gra.podsumowanieGry(Gra.pong); //zakoncz gre, uprzednio ja podsumowujac
 			break;
	   }
	}
	
	/**
	 * Metoda podejmujaca akcje po zwolnieniu przycisku;
	 * tu - nieu¿ywana, stanowi pusta implementacjê metody interfejsu
	 * @param event zdarzenie przechwycone przez Listener
	 */
	@Override
	public void keyReleased(KeyEvent event) {}
	
	/**
	 * Metoda podejmujaca akcje po nacisniêciu przycisku;
	 * tu - nieu¿ywana, stanowi pusta implementacjê metody interfejsu
	 * @param event zdarzenie przechwycone przez Listener
	 */
	@Override
	public void keyTyped(KeyEvent event) {}
};