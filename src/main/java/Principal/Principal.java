package Principal;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

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
		PanelCopiaFicheros pcf = new PanelCopiaFicheros();
		panelTabbed.addTab("Copiado de Carpetas", pcf);
		panelTabbed.setSelectedIndex(0);
		this.getContentPane().add(panelTabbed);
	}
	
	public static void main(String[] args) {
		Principal.getInstance().setVisible(true);

	}
}
	