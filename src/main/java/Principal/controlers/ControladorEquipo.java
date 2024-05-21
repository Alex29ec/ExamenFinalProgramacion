package Principal.controlers;

import Principal.model.Equipo;
import Principal.model.Socio;


public class ControladorEquipo extends SuperControladorJPA{

private static ControladorEquipo instance = null;
	
	
	public ControladorEquipo() {
		super("equipo", Equipo.class);
	}
	
	
	/**
	 * Singleton
	 * @return
	 */
	public static ControladorEquipo getInstance() {
		if (instance == null) {
			instance = new ControladorEquipo();
		}
		return instance;
	}
}
