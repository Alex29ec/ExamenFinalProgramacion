package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;

import Principal.controlers.ControladorEquipo;
import Principal.model.Equipo;

import java.awt.Insets;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelClasificacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<Equipo> jList;
	private JScrollPane scrollPane;
	DefaultListModel<Equipo> dlm;

	/**
	 * Create the panel.
	 */
	public PanelClasificacion() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 280, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 61, 0, 43, 27, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblClasificacion = new JLabel("Clasificacion");
		GridBagConstraints gbc_lblClasificacion = new GridBagConstraints();
		gbc_lblClasificacion.gridwidth = 3;
		gbc_lblClasificacion.insets = new Insets(0, 0, 5, 0);
		gbc_lblClasificacion.gridx = 0;
		gbc_lblClasificacion.gridy = 0;
		panel.add(lblClasificacion, gbc_lblClasificacion);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findEquipos();
			}
		});
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 5, 0);
		gbc_btnReset.gridx = 2;
		gbc_btnReset.gridy = 1;
		panel.add(btnReset, gbc_btnReset);
		
		JButton Arriba = new JButton("Arriba");
		Arriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arriba();
			}
		});
		GridBagConstraints gbc_Arriba = new GridBagConstraints();
		gbc_Arriba.insets = new Insets(0, 0, 5, 0);
		gbc_Arriba.gridx = 2;
		gbc_Arriba.gridy = 2;
		panel.add(Arriba, gbc_Arriba);
		
		JButton btnReset_1_2 = new JButton("Abajo");
		GridBagConstraints gbc_btnReset_1_2 = new GridBagConstraints();
		gbc_btnReset_1_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnReset_1_2.gridx = 2;
		gbc_btnReset_1_2.gridy = 3;
		panel.add(btnReset_1_2, gbc_btnReset_1_2);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 4;
		panel.add(btnEliminar, gbc_btnEliminar);
		findEquipos();

	}
	private void arriba() {
		
	}
	
	private void findEquipos() {
		List<Equipo> equipos = (List<Equipo>) ControladorEquipo.getInstance().findAll();
		
		// Con los usuarios listados, construyo un JList, junto a su DefaultListModel
		dlm = new DefaultListModel<Equipo>();
		dlm.addAll(equipos);
		
		jList = new JList<Equipo>(dlm);
		
		// Tamaño mínimo de la JList
		jList.setMinimumSize(new Dimension(0, 200));
		jList.setPreferredSize(new Dimension(0, 200));
		
		this.scrollPane.setViewportView(jList);
	}
	
	private void eliminar() {
		for (int i = this.jList.getSelectedIndices().length - 1; i >= 0; i--) {
			this.dlm.removeElementAt(this.jList.getSelectedIndices()[i]);
		}
	}
}
