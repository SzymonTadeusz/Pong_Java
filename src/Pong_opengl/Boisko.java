package Pong_opengl;
import com.jogamp.opengl.GLAutoDrawable;

/**
 * Klasa odpowiadajaca za rysowanie band
 * i aktualnego wyniku meczu
 * */
public class Boisko {

	/**
	 * Metoda rysujaca bandy ograniczajace pole gry
	 * @param drawable obiekt biblioteki graficznej, który odpowiada za wyswietlanie okna zobrazowania
	 * */
	 void rysujObszarGry(GLAutoDrawable drawable){			//rysowanie band
	//	    Gra.gl.glColor3d(0.2, 0.3, 0.7);
		
	Gra.gl.glPushMatrix();
			
		Gra.gl.glColor3d(0, 0, 0); //bandy boczne (czarne)
		Gra.gl.glPushMatrix();
			Gra.gl.glScaled(100, 1, 1);
			Gra.gl.glTranslated(0, 0, -58);
			Gra.glut.glutSolidCube(1);
			Gra.gl.glTranslated(0, 0, 116);
			Gra.glut.glutSolidCube(1);
		Gra.gl.glPopMatrix();
		
		Gra.gl.glColor3d(0.2, 0.2, 0.2); //bandy górna i dolna (szare)
		Gra.gl.glPushMatrix();
			Gra.gl.glRotated(90, 0, 1, 0);
			Gra.gl.glScaled(120, 1, 1);
			Gra.gl.glTranslated(0, 0, 46);
			Gra.glut.glutSolidCube(1);
			Gra.gl.glTranslated(0, 0, -92);
			Gra.glut.glutSolidCube(1);
		Gra.gl.glPopMatrix();
	 Gra.gl.glPopMatrix();
}


		/**
		 * Metoda rysujaca wynik gry w postaci bia³ych szescianów poni¿ej boiska
		 * oraz koñczaca grê w przypadku wygranej. 
		 * W przypadku, gdy grana jest pi³ka meczowa, kolor szescianów zmienia siê na czerwony;
		 *  zaimplementowana jest równie¿ obs³uga gry na przewagi zgodnie z zasadami tenisa sto³owego
		 *  @param nowy gra, dla której podejmowana jest akcja
		 * */
	void wyswietlWynik(Gra nowy){
	  Gra.gl.glPushMatrix();
		Gra.gl.glTranslated(-55, 0, 0);
		Gra.gl.glPushMatrix();
			for (int i = 0; i < nowy.lewa.wynik; i++) 						//rysuj wynik lewej rakiety
			{
				Gra.gl.glColor3d(1, 1, 1);									//bialy kolor
				Gra.gl.glTranslated(0, 0, (-7 * (i % 10)) - 10); 			//rysuj wynik pod polem gry
				if (i % 10 == 0 && i != 0) Gra.gl.glTranslated(-7, 0, 0);	//jesli i=10,20,30..., zjedŸ linijkê ni¿ej
				if ((((nowy.lewa.wynik - nowy.prawa.wynik))>0) &&			//pi³ka setowa, jesli L.wynik > P.wynik...
					nowy.lewa.wynik >= nowy.doIluPunktow - 1)  Gra.gl.glColor3d(0.5, 0, 0);			//...oraz L.wynik >= doIluPunktow 	
				if (i >= nowy.doIluPunktow - 1 && (((nowy.lewa.wynik - nowy.prawa.wynik))>1))Gra.gl.glColor3d(1, 1, 0); //wygrywajaca pilka: zolta
				Gra.glut.glutSolidCube(2);									//rysuj jeden punkt gracza lewego
				Gra.gl.glTranslated(0, 0, 7 * (i % 10) + 10);				//powróæ do srodka ukladu
			}
		Gra.gl.glPopMatrix();

		Gra.gl.glPushMatrix();
			for (int i = 0; i < nowy.prawa.wynik; i++)						//rysuj wynik prawej rakiety
			{
				Gra.gl.glColor3d(1, 1, 1);									//bialy kolor
				Gra.gl.glTranslated(0, 0, (7 * (i % 10)) + 10); 			//rysuj wynik pod polem gry
				if (i % 10 == 0 && i != 0) Gra.gl.glTranslated(-7, 0, 0);	//jesli i=10,20,30..., zjedŸ linijkê ni¿ej
				if ((((nowy.prawa.wynik - nowy.lewa.wynik))>0) &&			//pi³ka setowa, jesli P.wynik > L.wynik...
					nowy.prawa.wynik >= (nowy.doIluPunktow - 1))  Gra.gl.glColor3d(0.5, 0, 0);		//...oraz P.wynik >= doIluPunktow
				if (i >= nowy.doIluPunktow - 1 && (((nowy.prawa.wynik - nowy.lewa.wynik))>1))Gra.gl.glColor3d(1, 1, 0); //wygrywajaca pilka: zolta
				Gra.glut.glutSolidCube(2);									//rysuj jeden punkt gracza prawego
				Gra.gl.glTranslated(0, 0, -7 * (i % 10) - 10);				//powróæ do srodka ukladu
			}
			if ((nowy.lewa.wynik >= nowy.doIluPunktow && ((nowy.lewa.wynik - nowy.prawa.wynik)>1)) || (nowy.prawa.wynik >= nowy.doIluPunktow && ((nowy.prawa.wynik - nowy.lewa.wynik)>1)))
				Gra.podsumowanieGry(nowy);
		Gra.gl.glPopMatrix();
	  Gra.gl.glPopMatrix();
	}
	
	/**
	 * Pusty konstruktor domyslny klasy Boisko
	 * */
	Boisko(){}
}	
