package ScheamLauncher;

import Presentacion.Controlador.Controlador;
import Presentacion.Controlador.Eventos;

public class Scheam {

	public static void main(String[] args) {
		Controlador.getInstance().update(Eventos.MainWindow, null);
	}

}
