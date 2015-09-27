package Pong_opengl;

/**
 * Klasa inicjalizujaca po³o¿enie rakiet
 * oraz rysujaca rakiety obu graczy.
 * Przechowuje informacje o wyniku danego gracza. 
 * */
public class Rakieta {
	/**Wspó³rzêdna x po³o¿enia srodka rakiety*/
	double racket_x;
	/**Wspó³rzêdna y po³o¿enia srodka rakiety*/
	double racket_y;
	/**Szerokosæ rakiety*/
	int racket_width;
	/**Wysokosæ rakiety*/
	int racket_height;
	/**Wynik gracza sterujacego dana rakieta*/
	byte wynik;
	
	/**
	 * Konstruktor domyslny - inicjalizacja pól uniwersalnych
	 * (bez miejsca, w którym rakieta ma siê znajdowaæ)
	 * */
		Rakieta(){
			this.racket_width = 10;
			this.racket_height = 80;
			this.wynik = 0;
		}

		/**
		 * Konstruktor sparametryzowany - zawiera inicjalizacje pól,
		 * które ró¿nia siê dla ró¿nych rakiet (tj. po³o¿enie rakiety)
		 * @param x po³o¿enie rakiety (wspó³rzêdna x)
		 * @param y po³o¿enie rakiety (wspó³rzêdna y)
		 * */	
		Rakieta(double x, double y){
			this.racket_width = 10;
			this.racket_height = 80;
			this.wynik = 0;
			this.racket_x = x;
			this.racket_y = y;
		}

		/**
		 * Metoda rysujaca niebieska rakietê
		 * @param nowy gra, dla której rakieta jest rysowana
		 * */
		 void rysujGraczaI(Gra nowy){			
			Gra.gl.glPushMatrix();
			Gra.gl.glColor3d(0, 0, 0.2);
			Gra.gl.glScaled(nowy.prawa.racket_height/5, 1.5, nowy.prawa.racket_width/5);
			Gra.gl.glTranslated(nowy.prawa.racket_x/16.5, 0, nowy.prawa.racket_y/2);
			Gra.glut.glutSolidCube(1);
			
			Gra.gl.glTranslated(-0.4, 0, -0.2);
			Gra.gl.glScaled(0.5, 0.5, 0.5);
			
			Gra.gl.glRotated(-20, 0, 1, 0);
			Gra.gl.glRotated(-20, 1, 0, 0);
			Gra.glut.glutSolidCube(1);
			Gra.gl.glRotated(20, 0, 1, 0);
			Gra.gl.glRotated(20, 1, 0, 0);
			
			Gra.gl.glTranslated(1.6, 0, 0);
			Gra.gl.glRotated(20, 0, 1, 0); 
			Gra.gl.glRotated(20, 1, 0, 0);
			Gra.glut.glutSolidCube(1);
			Gra.gl.glRotated(-20, 0, 1, 0);
			Gra.gl.glRotated(-20, 1, 0, 0);
			Gra.gl.glPopMatrix();
		}

			/**
			 * Metoda rysujaca zielona rakietê
			 * @param nowy gra, dla której rakieta jest rysowana
			 * */
		 void rysujGraczaII(Gra nowy){			
			Gra.gl.glPushMatrix();
			Gra.gl.glColor3d(0, 0.2, 0);
			Gra.gl.glScaled(nowy.lewa.racket_height/5, 1.5, nowy.lewa.racket_width/5);
			Gra.gl.glTranslated(nowy.lewa.racket_x / 16.5, 0, nowy.lewa.racket_y/2);
			Gra.glut.glutSolidCube(1);
			
			Gra.gl.glTranslated(0.4,0,0.2);
			Gra.gl.glScaled(0.5, 0.5, 0.5);
			
			Gra.gl.glRotated(-20, 0, 1, 0);
			Gra.gl.glRotated(-20, 1, 0, 0);
			Gra.glut.glutSolidCube(1);
			Gra.gl.glRotated(20, 0, 1, 0);
			Gra.gl.glRotated(20, 1, 0, 0);
			
			Gra.gl.glTranslated(-1.6, 0, 0);
			Gra.gl.glRotated(20, 0, 1, 0);
			Gra.gl.glRotated(20, 1, 0, 0);
			Gra.glut.glutSolidCube(1);
			Gra.gl.glRotated(-20, 0, 1, 0);
			Gra.gl.glRotated(-20, 1, 0, 0);
			Gra.gl.glPopMatrix();
		}
	
}
