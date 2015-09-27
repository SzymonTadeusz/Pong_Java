package Pong_opengl;


/**
 * Klasa rysujaca pi�k� oraz sprawdzajaca, czy pi�ka nie odbi�a si� ju� 
 * od kt�regos z obiekt�w (rakiet, band). 
 * W przypadku odbicia, podejmowana jest akcja zgodnie z logika gry
 * (zwi�kszenie wyniku i podanie pi�ki drugiemu graczowi/ odbicie pi�ki)
 * */
public class Pilka {
	/**Pozycja pilki w osi X*/
	double ball_pos_x;
	/**Pozycja pilki w osi Y*/
	double ball_pos_y;
	/**Kierunek loty pi�ki w osi X*/
	double ball_dir_x;
	/**Kierunek lotu pi�ki w osi Y*/
	double ball_dir_y;
	/**Rozmiar pi�ki*/
	int ball_size;
	/**Pr�dkos� pi�ki*/
	double ball_speed;
	
	/**
	 * Pusty konstruktor domyslny klasy Pilka, 
	 * zawiera inicjalizacje podstawowych atrybutow obiektu klasy Pilka
	 * */
	Pilka(){
		this.ball_pos_x=0;
		this.ball_pos_y=0;
		this.ball_dir_x=-0.1;
		this.ball_dir_y=2.0;
		this.ball_size=2;
		this.ball_speed=3;
	}

	/**
	 * Metoda rysujaca pi�k�, niezale�nie od tego, gdzie si� ona znajduje
	 * (za translacje odpowiedzialna jest metoda ruszPilke)
	 *  @param pilka pi�ka, dla kt�rej podejmowana jest akcja
	 * */
	 void rysujPilke(Pilka pilka){				//rysowanie (statycznej) pi�ki
		Gra.gl.glPushMatrix();
		Gra.gl.glScaled(0.5, 0.5, 0.5);
		Gra.gl.glColor3d(0.5, 0, 0);
		Gra.glut.glutSolidSphere(pilka.ball_size,10,10);
		Gra.gl.glPopMatrix();
	}

		/**
		 * Metoda rysujaca pi�k� w miejscu jej aktualnego po�o�enia
		 * opisywanego przez atrybuty pilki: pos_x i pos_y
		 *  @param nowy gra, dla kt�rej podejmowana jest akcja
		 * */
	 void ruszPilke(Gra nowy){
		 Gra.gl.glPushMatrix();
		 Gra.gl.glTranslated(Gra.pong.pilka.ball_pos_x, 0, Gra.pong.pilka.ball_pos_y);
		if(nowy.flaga)Gra.pong.pilka.wykrywaczkolizji(nowy);
		rysujPilke(Gra.pong.pilka);
		Gra.gl.glPopMatrix();
	}
	
		/**
		 * Metoda wykrywajaca zderzenia z pozosta�ymi obiektami:
		 * rakietami oraz bandami
		 * oraz wp�ywajaca na wynik meczu, gdy pi�ka dotyka band bocznych
		 * (kt�rys z graczy nie odbi� pi�ki)
		 *  @param nowy gra, dla kt�rej podejmowana jest akcja
		 * */
	void wykrywaczkolizji(Gra nowy){			//collider
		// rusz pi�k� dalej w kierunku jej lotu
		this.ball_pos_x += this.ball_dir_x * this.ball_speed / 8;
		this.ball_pos_y += this.ball_dir_y * this.ball_speed / 8;
		
		// Sprawd�, czy nie uderzy�a lewa rakietka:
		if (this.ball_pos_y > nowy.lewa.racket_y - nowy.lewa.racket_width / 5 &&
			this.ball_pos_y < nowy.lewa.racket_y+2 &&
			Math.abs(this.ball_pos_x - nowy.lewa.racket_x) < nowy.lewa.racket_height / 7)
			{
				// ustawia kierunek gora-do� w zale�nosci od tego, ktor� cz�sci� rakietki uderzymy pi�k�:
				double t = (((this.ball_pos_x - nowy.lewa.racket_x) / (nowy.lewa.racket_height / 4)) + 0.1) * 3;
				if (t>1 || t<-1) t=-t;
				this.ball_dir_y = Math.abs(this.ball_dir_y); // odbij w prawo
				this.ball_dir_x = t;
			}
		
		// Sprawd�, czy nie uderzy�a prawa rakietka:
		if (this.ball_pos_y < nowy.prawa.racket_y + nowy.prawa.racket_width / 5 &&
			this.ball_pos_y > nowy.prawa.racket_y-2 &&
			Math.abs(this.ball_pos_x - nowy.prawa.racket_x) < nowy.prawa.racket_height / 7) 
			{
				// ustaw kierunek gora-do�
				double t = (((this.ball_pos_x - nowy.prawa.racket_x) / (nowy.prawa.racket_height / 4)) + 0.1) * 3;
				if (t>1 || t<-1) t=-t;
				this.ball_dir_y = -Math.abs(this.ball_dir_y); // odbij w lewo
				this.ball_dir_x = t;
			}
		
		// Sprawd�, czy lewy nie przegra�:
		if (this.ball_pos_y < -58) {
			++nowy.prawa.wynik;
			this.ball_pos_x = 0;
			this.ball_pos_y = 0;
			nowy.lewa.racket_x = 0;
			nowy.prawa.racket_x = 0;
			this.ball_dir_y = Math.abs(this.ball_dir_y); // podaj pi�k� zwyci�zcy
			this.ball_dir_x = 0;
		}
		
		// Sprawd�, czy prawy nie przegra�:
		if (this.ball_pos_y > 58) {
			++nowy.lewa.wynik;
			this.ball_pos_x = 0;
			this.ball_pos_y = 0;
			nowy.lewa.racket_x = 0;
			nowy.prawa.racket_x = 0;
			this.ball_dir_y = -Math.abs(this.ball_dir_y); // podaj pi�k� zwyci�zcy
			this.ball_dir_x = 0;
		}
		
		// Sprawd�, czy nie powinna si� odbi� od g�rnej bandy:
		if (this.ball_pos_x > 46) {
			this.ball_dir_x = -Math.abs(this.ball_dir_x); // odbij lustrzanie w do�
		}
		
		// Sprawd�, czy nie powinna si� odbi� od dolnej bandy:
		if (this.ball_pos_x < -46) {
			this.ball_dir_x = Math.abs(this.ball_dir_x); // odbij lustrzanie w gor�
		}
	}
	
	
}	
