package Pong_opengl;


/**
 * Klasa rysujaca pi³kê oraz sprawdzajaca, czy pi³ka nie odbi³a siê ju¿ 
 * od któregos z obiektów (rakiet, band). 
 * W przypadku odbicia, podejmowana jest akcja zgodnie z logika gry
 * (zwiêkszenie wyniku i podanie pi³ki drugiemu graczowi/ odbicie pi³ki)
 * */
public class Pilka {
	/**Pozycja pilki w osi X*/
	double ball_pos_x;
	/**Pozycja pilki w osi Y*/
	double ball_pos_y;
	/**Kierunek loty pi³ki w osi X*/
	double ball_dir_x;
	/**Kierunek lotu pi³ki w osi Y*/
	double ball_dir_y;
	/**Rozmiar pi³ki*/
	int ball_size;
	/**Prêdkosæ pi³ki*/
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
	 * Metoda rysujaca pi³kê, niezale¿nie od tego, gdzie siê ona znajduje
	 * (za translacje odpowiedzialna jest metoda ruszPilke)
	 *  @param pilka pi³ka, dla której podejmowana jest akcja
	 * */
	 void rysujPilke(Pilka pilka){				//rysowanie (statycznej) pi³ki
		Gra.gl.glPushMatrix();
		Gra.gl.glScaled(0.5, 0.5, 0.5);
		Gra.gl.glColor3d(0.5, 0, 0);
		Gra.glut.glutSolidSphere(pilka.ball_size,10,10);
		Gra.gl.glPopMatrix();
	}

		/**
		 * Metoda rysujaca pi³kê w miejscu jej aktualnego po³o¿enia
		 * opisywanego przez atrybuty pilki: pos_x i pos_y
		 *  @param nowy gra, dla której podejmowana jest akcja
		 * */
	 void ruszPilke(Gra nowy){
		 Gra.gl.glPushMatrix();
		 Gra.gl.glTranslated(Gra.pong.pilka.ball_pos_x, 0, Gra.pong.pilka.ball_pos_y);
		if(nowy.flaga)Gra.pong.pilka.wykrywaczkolizji(nowy);
		rysujPilke(Gra.pong.pilka);
		Gra.gl.glPopMatrix();
	}
	
		/**
		 * Metoda wykrywajaca zderzenia z pozosta³ymi obiektami:
		 * rakietami oraz bandami
		 * oraz wp³ywajaca na wynik meczu, gdy pi³ka dotyka band bocznych
		 * (którys z graczy nie odbi³ pi³ki)
		 *  @param nowy gra, dla której podejmowana jest akcja
		 * */
	void wykrywaczkolizji(Gra nowy){			//collider
		// rusz pi³kê dalej w kierunku jej lotu
		this.ball_pos_x += this.ball_dir_x * this.ball_speed / 8;
		this.ball_pos_y += this.ball_dir_y * this.ball_speed / 8;
		
		// SprawdŸ, czy nie uderzy³a lewa rakietka:
		if (this.ball_pos_y > nowy.lewa.racket_y - nowy.lewa.racket_width / 5 &&
			this.ball_pos_y < nowy.lewa.racket_y+2 &&
			Math.abs(this.ball_pos_x - nowy.lewa.racket_x) < nowy.lewa.racket_height / 7)
			{
				// ustawia kierunek gora-do³ w zale¿nosci od tego, ktor¹ czêsci¹ rakietki uderzymy pi³kê:
				double t = (((this.ball_pos_x - nowy.lewa.racket_x) / (nowy.lewa.racket_height / 4)) + 0.1) * 3;
				if (t>1 || t<-1) t=-t;
				this.ball_dir_y = Math.abs(this.ball_dir_y); // odbij w prawo
				this.ball_dir_x = t;
			}
		
		// SprawdŸ, czy nie uderzy³a prawa rakietka:
		if (this.ball_pos_y < nowy.prawa.racket_y + nowy.prawa.racket_width / 5 &&
			this.ball_pos_y > nowy.prawa.racket_y-2 &&
			Math.abs(this.ball_pos_x - nowy.prawa.racket_x) < nowy.prawa.racket_height / 7) 
			{
				// ustaw kierunek gora-do³
				double t = (((this.ball_pos_x - nowy.prawa.racket_x) / (nowy.prawa.racket_height / 4)) + 0.1) * 3;
				if (t>1 || t<-1) t=-t;
				this.ball_dir_y = -Math.abs(this.ball_dir_y); // odbij w lewo
				this.ball_dir_x = t;
			}
		
		// SprawdŸ, czy lewy nie przegra³:
		if (this.ball_pos_y < -58) {
			++nowy.prawa.wynik;
			this.ball_pos_x = 0;
			this.ball_pos_y = 0;
			nowy.lewa.racket_x = 0;
			nowy.prawa.racket_x = 0;
			this.ball_dir_y = Math.abs(this.ball_dir_y); // podaj pi³kê zwyciêzcy
			this.ball_dir_x = 0;
		}
		
		// SprawdŸ, czy prawy nie przegra³:
		if (this.ball_pos_y > 58) {
			++nowy.lewa.wynik;
			this.ball_pos_x = 0;
			this.ball_pos_y = 0;
			nowy.lewa.racket_x = 0;
			nowy.prawa.racket_x = 0;
			this.ball_dir_y = -Math.abs(this.ball_dir_y); // podaj pi³kê zwyciêzcy
			this.ball_dir_x = 0;
		}
		
		// SprawdŸ, czy nie powinna siê odbiæ od górnej bandy:
		if (this.ball_pos_x > 46) {
			this.ball_dir_x = -Math.abs(this.ball_dir_x); // odbij lustrzanie w do³
		}
		
		// SprawdŸ, czy nie powinna siê odbiæ od dolnej bandy:
		if (this.ball_pos_x < -46) {
			this.ball_dir_x = Math.abs(this.ball_dir_x); // odbij lustrzanie w gorê
		}
	}
	
	
}	
