package Principal.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the equipo database table.
 * 
 */
@Entity
@Table(name="equipo")
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo extends Entidad implements Serializable {
	@Override
	public String toString() {
		return descripcion;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String descripcion;

	public Equipo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}