package principal;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import views.PanelClasificacion;
import views.PanelDatosSocio;
import views.PanelSociosDeEquipo;

public class Principal extends JFrame{
	private static final long serialVersionUID = 1L;
	JTabbedPane panelTabbed;  
	static Principal instance = null;

	public static Principal getInstance() {
		if (instance == null) {
			instance = new Principal();
		}
		return instance;
	}
	public Principal() {
		super("Examen Final Programacion");
		this.setBounds(0, 0, 800, 600);
		panelTabbed = new JTabbedPane();
		PanelDatosSocio pcf = new PanelDatosSocio();
		PanelSociosDeEquipo ppse =new PanelSociosDeEquipo();
		PanelClasificacion pc = new PanelClasificacion();
		panelTabbed.addTab("Panel Datos de Socio", pcf);
		panelTabbed.addTab("Panel Socios de Equipo", ppse);
		panelTabbed.addTab("Panel Clasificacion", pc);
		panelTabbed.setSelectedIndex(0);
		this.getContentPane().add(panelTabbed);
	} 
	public static void main(String[] args) {
		Principal.getInstance().setVisible(true);

	}
}
	