package Pong_opengl;

/**
 * Klasa inicjalizujaca po�o�enie rakiet
 * oraz rysujaca rakiety obu graczy.
 * Przechowuje informacje o wyniku danego gracza. 
 * */
public class Rakieta {
	/**Wsp�rz�dna x po�o�enia srodka rakiety*/
	double racket_x;
	/**Wsp�rz�dna y po�o�enia srodka rakiety*/
	double racket_y;
	/**Szerokos� rakiety*/
	int racket_width;
	/**Wysokos� rakiety*/
	int racket_height;
	/**Wynik gracza sterujacego dana rakieta*/
	byte wynik;
	
	/**
	 * Konstruktor domyslny - inicjalizacja p�l uniwersalnych
	 * (bez miejsca, w kt�rym rakieta ma si� znajdowa�)
	 * */
		Rakieta(){
			this.racket_width = 10;
			this.racket_height = 80;
			this.wynik = 0;
		}

		/**
		 * Konstruktor sparametryzowany - zawiera inicjalizacje p�l,
		 * kt�re r�nia si� dla r�nych rakiet (tj. po�o�enie rakiety)
		 * @param x po�o�enie rakiety (wsp�rz�dna x)
		 * @param y po�o�enie rakiety (wsp�rz�dna y)
		 * */	
		Rakieta(double x, double y){
			this.racket_width = 10;
			this.racket_height = 80;
			this.wynik = 0;
			this.racket_x = x;
			this.racket_y = y;
		}

		/**
		 * Metoda rysujaca niebieska rakiet�
		 * @param nowy gra, dla kt�rej rakieta jest rysowana
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
			 * Metoda rysujaca zielona rakiet�
			 * @param nowy gra, dla kt�rej rakieta jest rysowana
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
