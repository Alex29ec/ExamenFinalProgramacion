package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Principal.controlers.ControladorEquipo;
import Principal.controlers.ControladorSocio;
import Principal.model.Equipo;
import Principal.model.Socio;

import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelSociosDeEquipo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBox<Equipo> comboBox;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rbtnNombre;
	private JRadioButton rbtnApellido1;
	private JRadioButton rbtnApellido2;
	private JRadioButton rbtnFecha;
	private JScrollPane scrollPane;
	private Socio currentTipo;

	/**
	 * Create the panel.
	 */
	public PanelSociosDeEquipo() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 168, 199, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblSociosDeEquipo = new JLabel("Socios De Equipo");
		GridBagConstraints gbc_lblSociosDeEquipo = new GridBagConstraints();
		gbc_lblSociosDeEquipo.gridwidth = 3;
		gbc_lblSociosDeEquipo.insets = new Insets(0, 0, 5, 0);
		gbc_lblSociosDeEquipo.gridx = 0;
		gbc_lblSociosDeEquipo.gridy = 0;
		panel.add(lblSociosDeEquipo, gbc_lblSociosDeEquipo);

		JLabel lblNewLabel = new JLabel("Equipo: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		comboBox = new JComboBox<Equipo>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla();
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		panel.add(comboBox, gbc_comboBox);

		rbtnNombre = new JRadioButton("Ordenar por nombre");
		rbtnNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarTabla();
			}
		});
		buttonGroup.add(rbtnNombre);
		GridBagConstraints gbc_rbtnNombre = new GridBagConstraints();
		gbc_rbtnNombre.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnNombre.gridx = 1;
		gbc_rbtnNombre.gridy = 2;
		panel.add(rbtnNombre, gbc_rbtnNombre);

		rbtnApellido1 = new JRadioButton("Ordenar por Apellido");
		rbtnApellido1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarTabla();
			}
		});
		buttonGroup.add(rbtnApellido1);
		GridBagConstraints gbc_rbtnApellido1 = new GridBagConstraints();
		gbc_rbtnApellido1.insets = new Insets(0, 0, 5, 0);
		gbc_rbtnApellido1.gridx = 2;
		gbc_rbtnApellido1.gridy = 2;
		panel.add(rbtnApellido1, gbc_rbtnApellido1);

		rbtnApellido2 = new JRadioButton("Ordenar por segundo Apellido");
		rbtnApellido2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarTabla();
			}
		});
		buttonGroup.add(rbtnApellido2);
		GridBagConstraints gbc_rbtnApellido2 = new GridBagConstraints();
		gbc_rbtnApellido2.insets = new Insets(0, 0, 5, 5);
		gbc_rbtnApellido2.gridx = 1;
		gbc_rbtnApellido2.gridy = 3;
		panel.add(rbtnApellido2, gbc_rbtnApellido2);

		rbtnFecha = new JRadioButton("Ordenar por Fecha");
		rbtnFecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarTabla();
			}
		});
		buttonGroup.add(rbtnFecha);
		GridBagConstraints gbc_rbtnFecha = new GridBagConstraints();
		gbc_rbtnFecha.insets = new Insets(0, 0, 5, 0);
		gbc_rbtnFecha.gridx = 2;
		gbc_rbtnFecha.gridy = 3;
		panel.add(rbtnFecha, gbc_rbtnFecha);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		panel.add(scrollPane, gbc_scrollPane);

		cargarTodosEquipos();
		cargarTabla();

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	private void cargarTodosEquipos() {
		List<Equipo> l = (List<Equipo>) ControladorEquipo.getInstance().findAll();
		for (Equipo o : l) {
			comboBox.addItem(o);
		}
	}

	private void cargarTabla() {
		Equipo eq = (Equipo) this.comboBox.getSelectedItem();
		List<Socio> contractTypes = (List<Socio>) ControladorSocio.getInstance().findAll();
		if (this.rbtnNombre.isSelected()) {
			contractTypes = ControladorEquipo.getInstance().findByEquipoPorNombre(eq.getId());
		} else {
			if (this.rbtnApellido1.isSelected()) {
				contractTypes = ControladorEquipo.getInstance().findByEquipoPor1Apellido(eq.getId());
			} else {
				if (this.rbtnApellido2.isSelected()) {
					contractTypes = ControladorEquipo.getInstance().findByEquipoPor2Apellido(eq.getId());
				} else {
					if (this.rbtnFecha.isSelected()) {
						contractTypes = ControladorEquipo.getInstance().findByEquipoPorFecha(eq.getId());
					}
				}
			}
		}

		Object m[][] = new Object[contractTypes.size()][4];
		for (int i = 0; i < m.length; i++) {
			m[i][0] = contractTypes.get(i).getNombre();
			m[i][1] = contractTypes.get(i).getApellido1();
			m[i][2] = contractTypes.get(i).getApellido2();
			m[i][3] = contractTypes.get(i).getFechaNacimiento();
		}
		DefaultTableModel dtm = new DefaultTableModel(m,
				new String[] { "Nombre", "Apellido1", "Apellido2", "Fecha de Nacimiento" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dtm);

		// Determino el mecanismo de doble clic de seleccion del tipo de contrato sobre
		// la tabla
		PanelSociosDeEquipo thisPanel = this; // Necesito un puntero a "this" antes de entrar en la clase anónima de
		
		final List<Socio> contractTypesFinal = contractTypes; 
		// MouseAdapter
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);

				// Compruebo el doble clic
				if (e.getClickCount() > 1) {
					if (table.getSelectedRow() > -1) {
						currentTipo = contractTypesFinal.get(table.getSelectedRow());
						JOptionPane jop = new JOptionPane();
						jop.showMessageDialog(null,
								"Has seleccionado a " + currentTipo.getNombre() + " con id: " + currentTipo.getId());
					}
				}
			}

		});

		// Muestro la JTable construida
		scrollPane.setViewportView(table);
	}

}
