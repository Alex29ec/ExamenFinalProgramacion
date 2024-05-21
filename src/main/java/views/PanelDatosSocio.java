package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JToolBar;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;

import Principal.controlers.ControladorEquipo;
import Principal.controlers.ControladorSocio;
import Principal.model.Entidad;
import Principal.model.Equipo;
import Principal.model.Socio;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PanelDatosSocio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfnombre;
	private JTextField jtf1apellido;
	private JTextField jtf2apellido;
	private Socio currentSocio;
	private Equipo currentEquipo;
	private JSlider jsliderantiguedad;
	private JFormattedTextField jftffecha;
	private JLabel lblantiguedad;
	private JCheckBox jchboxActivo;
	private JComboBox<Equipo> jcbEquipo;

	/**
	 * Create the panel.
	 */
	public PanelDatosSocio() {
		setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEntityOnPanel(ControladorSocio.getInstance().findFirst());
			}
		});
		
		button.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/views/res/gotostart.png")));
		toolBar.add(button);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEntityOnPanel(ControladorSocio.getInstance().findPrevious(currentSocio.getId()));
			}
		});
		button_1.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/views/res/previous.png")));
		toolBar.add(button_1);

		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEntityOnPanel(ControladorSocio.getInstance().findNext(currentSocio.getId()));

			}
		});
		button_2.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/views/res/next.png")));
		toolBar.add(button_2);

		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEntityOnPanel(ControladorSocio.getInstance().findLast());
			}
		});
		button_3.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/views/res/gotoend.png")));
		toolBar.add(button_3);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEntity();
			}
		});
		btnNewButton.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/views/res/nuevo.png")));
		toolBar.add(btnNewButton);
		

		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveEntity();
			}
		});
		button_4.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/views/res/guardar.png")));
		toolBar.add(button_4);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteEntity();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(PanelDatosSocio.class.getResource("/views/res/eliminar.png")));
		toolBar.add(btnNewButton_1);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblGestionDeSocios = new JLabel("Gestion de Socios");
		GridBagConstraints gbc_lblGestionDeSocios = new GridBagConstraints();
		gbc_lblGestionDeSocios.gridwidth = 2;
		gbc_lblGestionDeSocios.insets = new Insets(0, 0, 5, 5);
		gbc_lblGestionDeSocios.gridx = 0;
		gbc_lblGestionDeSocios.gridy = 0;
		panel.add(lblGestionDeSocios, gbc_lblGestionDeSocios);

		JLabel lblNombre = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		panel.add(lblNombre, gbc_lblNombre);

		jtfnombre = new JTextField();
		GridBagConstraints gbc_jtfnombre = new GridBagConstraints();
		gbc_jtfnombre.insets = new Insets(0, 0, 5, 5);
		gbc_jtfnombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfnombre.gridx = 1;
		gbc_jtfnombre.gridy = 1;
		panel.add(jtfnombre, gbc_jtfnombre);
		jtfnombre.setColumns(10);

		JLabel lblPrimerApellido = new JLabel("Primer Apellido:");
		GridBagConstraints gbc_lblPrimerApellido = new GridBagConstraints();
		gbc_lblPrimerApellido.anchor = GridBagConstraints.EAST;
		gbc_lblPrimerApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrimerApellido.gridx = 0;
		gbc_lblPrimerApellido.gridy = 2;
		panel.add(lblPrimerApellido, gbc_lblPrimerApellido);

		jtf1apellido = new JTextField();
		GridBagConstraints gbc_jtf1apellido = new GridBagConstraints();
		gbc_jtf1apellido.insets = new Insets(0, 0, 5, 5);
		gbc_jtf1apellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtf1apellido.gridx = 1;
		gbc_jtf1apellido.gridy = 2;
		panel.add(jtf1apellido, gbc_jtf1apellido);
		jtf1apellido.setColumns(10);

		JLabel lblSegundoApellido = new JLabel("Segundo Apellido :");
		GridBagConstraints gbc_lblSegundoApellido = new GridBagConstraints();
		gbc_lblSegundoApellido.anchor = GridBagConstraints.EAST;
		gbc_lblSegundoApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblSegundoApellido.gridx = 0;
		gbc_lblSegundoApellido.gridy = 3;
		panel.add(lblSegundoApellido, gbc_lblSegundoApellido);

		jtf2apellido = new JTextField();
		GridBagConstraints gbc_jtf2apellido = new GridBagConstraints();
		gbc_jtf2apellido.insets = new Insets(0, 0, 5, 5);
		gbc_jtf2apellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtf2apellido.gridx = 1;
		gbc_jtf2apellido.gridy = 3;
		panel.add(jtf2apellido, gbc_jtf2apellido);
		jtf2apellido.setColumns(10);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
		gbc_lblFechaDeNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeNacimiento.gridx = 0;
		gbc_lblFechaDeNacimiento.gridy = 4;
		panel.add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);

		jftffecha = new JFormattedTextField();
		GridBagConstraints gbc_jftffecha = new GridBagConstraints();
		gbc_jftffecha.insets = new Insets(0, 0, 5, 5);
		gbc_jftffecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_jftffecha.gridx = 1;
		gbc_jftffecha.gridy = 4;
		panel.add(jftffecha, gbc_jftffecha);

		JLabel lblAntiguedad = new JLabel("Antiguedad (años):");
		GridBagConstraints gbc_lblAntiguedad = new GridBagConstraints();
		gbc_lblAntiguedad.insets = new Insets(0, 0, 5, 5);
		gbc_lblAntiguedad.gridx = 0;
		gbc_lblAntiguedad.gridy = 5;
		panel.add(lblAntiguedad, gbc_lblAntiguedad);

		jsliderantiguedad = new JSlider();
		jsliderantiguedad.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				showSliderBalanceDescriptor();
			}
		});
		GridBagConstraints gbc_jsliderantiguedad = new GridBagConstraints();
		gbc_jsliderantiguedad.insets = new Insets(0, 0, 5, 5);
		gbc_jsliderantiguedad.gridx = 1;
		gbc_jsliderantiguedad.gridy = 5;
		panel.add(jsliderantiguedad, gbc_jsliderantiguedad);

		lblantiguedad = new JLabel("");
		GridBagConstraints gbc_lblantiguedad = new GridBagConstraints();
		gbc_lblantiguedad.insets = new Insets(0, 0, 5, 0);
		gbc_lblantiguedad.gridx = 2;
		gbc_lblantiguedad.gridy = 5;
		panel.add(lblantiguedad, gbc_lblantiguedad);

		jchboxActivo = new JCheckBox("Socio en Activo");
		GridBagConstraints gbc_jchboxActivo = new GridBagConstraints();
		gbc_jchboxActivo.insets = new Insets(0, 0, 5, 5);
		gbc_jchboxActivo.gridx = 1;
		gbc_jchboxActivo.gridy = 6;
		panel.add(jchboxActivo, gbc_jchboxActivo);

		JLabel lblEquipo = new JLabel("Equipo:");
		GridBagConstraints gbc_lblEquipo = new GridBagConstraints();
		gbc_lblEquipo.anchor = GridBagConstraints.EAST;
		gbc_lblEquipo.insets = new Insets(0, 0, 0, 5);
		gbc_lblEquipo.gridx = 0;
		gbc_lblEquipo.gridy = 7;
		panel.add(lblEquipo, gbc_lblEquipo);

		jcbEquipo = new JComboBox<Equipo>();
		GridBagConstraints gbc_jcbEquipo = new GridBagConstraints();
		gbc_jcbEquipo.insets = new Insets(0, 0, 0, 5);
		gbc_jcbEquipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbEquipo.gridx = 1;
		gbc_jcbEquipo.gridy = 7;
		panel.add(jcbEquipo, gbc_jcbEquipo);
		cargarTodosEquipos();
		addControlCustomizableBehaviours();
		showEntityOnPanel(ControladorSocio.getInstance().findFirst());
		
	}

	private void showEntityOnPanel(Socio socio) {

		if (socio != null) {
			this.currentSocio = socio;
			int equipoindex =this.currentSocio.getEquipo().getId()-1;
			this.jtfnombre.setText(this.currentSocio.getNombre());
			this.jtf1apellido.setText(this.currentSocio.getApellido1());
			this.jtf2apellido.setText(this.currentSocio.getApellido2());
			
			this.jftffecha.setText(GuiUtils.getFormattedStringFromDate("yyyy/MM/dd", this.currentSocio.getFechaNacimiento()));
			if(this.currentSocio.getEquipo() != null) {
			this.jcbEquipo.setSelectedIndex(equipoindex);
			}
			
			else {
			}
			if (currentSocio.getActivo() == 1) {
				this.jchboxActivo.setSelected(true);
			} else {
				this.jchboxActivo.setSelected(false);
			}
			// Asociado al slider del saldo
			this.jsliderantiguedad.setMaximum(200);
			this.jsliderantiguedad.setMinimum(0);
			this.jsliderantiguedad.setValue(currentSocio.getAntiguedadAnios());
			this.showSliderBalanceDescriptor();
		} else {
			System.out.println("El CurrentSocio es nulo");
		}
		

	}
	private void saveEntity() {
		this.currentSocio.setNombre(this.jtfnombre.getText());
		this.currentSocio.setApellido1(this.jtf1apellido.getText());
		this.currentSocio.setApellido2(this.jtf2apellido.getText());
		this.currentSocio.setEquipo((Equipo) this.jcbEquipo.getSelectedItem());
		if(jchboxActivo.isSelected()) {
			this.currentSocio.setActivo(1);
		}
		else {
			this.currentSocio.setActivo(0);
		}
		this.currentSocio.setAntiguedadAnios(this.jsliderantiguedad.getValue());
		this.currentSocio.setFechaNacimiento(GuiUtils.getDateFromFormattedString(this.jftffecha.getText(),"yyyy/MM/dd"));
		try {
			ControladorSocio.getInstance().save(currentSocio);
			JOptionPane.showMessageDialog(null, "Contrato guardado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "NO se ha guardado el contrato. ERROR");
		}
	}
private void addControlCustomizableBehaviours() {
		
		// JFormattedTextfield para la fecha de firma, si el valor no es correcto se pone fondo en rojo
		this.jftffecha.setFormatterFactory(new AbstractFormatterFactory() {
			@Override
			public AbstractFormatter getFormatter(JFormattedTextField tf) {
				return new JFormattedTextField.AbstractFormatter() {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

					@Override
					public String valueToString(Object value) throws ParseException {
						if (value != null && value instanceof Date) {
							jftffecha.setBackground(Color.WHITE);
							return sdf.format(((Date) value));
						}
						return "";
					}

					@Override
					public Object stringToValue(String text) throws ParseException {
						try {
							return sdf.parse(text);
						} catch (Exception e) {
							jftffecha.setBackground(Color.RED);
							JOptionPane.showMessageDialog(null, "Error en la fecha");
							return null;
						}
					}
				};
			}
		});
		
		
		
	}
private void newEntity () {
	this.currentSocio = new Socio();
	this.currentSocio.setId(0);
	this.currentSocio.setEquipo(currentEquipo);
	showEntityOnPanel(currentSocio);
}

	private void cargarTodosEquipos() {
		List<Equipo> l = (List<Equipo>) ControladorEquipo.getInstance().findAll();
		for (Equipo o : l) {
			this.jcbEquipo.addItem(o);
		}
	}

	private void showSliderBalanceDescriptor() {
		this.lblantiguedad.setText(this.jsliderantiguedad.getValue() + " años");
	}
	private void deleteEntity () {
		try {
			String respuestas[] = new String[] {"Sí", "No"};
			int opcionElegida = JOptionPane.showOptionDialog(
					null, 
					"¿Realmente desea eliminar el registro?", 
					"Eliminación de fabricante", 
			        JOptionPane.DEFAULT_OPTION, 
			        JOptionPane.WARNING_MESSAGE, 
			        null, respuestas, 
			        respuestas[1]);
		    
			if(opcionElegida == 0) {
				ControladorSocio.getInstance().remove(currentSocio);
		    	  
		    	  // Decido qué registro voy a mostrar en pantalla.
		    	  // Voy a comprobar si existe un anterior, si existe lo muestro
		    	  // Si no existe anterior compruebo si existe siguiente, 
		    	  // si existe lo muestro. En caso contrario, no quedan registros
		    	  // así que muestro en blanco la pantalla
		    	  this.currentSocio = ControladorSocio.getInstance().findPrevious(this.currentSocio.getId());
		    	  if (this.currentSocio != null) { // Existe un anterior, lo muestro
		    		  showEntityOnPanel(currentSocio);
		    	  }
		    	  else {
		    		  this.currentSocio = ControladorSocio.getInstance().findNext(this.currentSocio.getId());
			    	  if (this.currentSocio != null) { // Existe un anterior, lo muestro
			    		  showEntityOnPanel(currentSocio);
			    	  }
		    		  else { // No quedan registros en la tabla
		    			  newEntity();
		    		  }
		    	  }
		      }
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
