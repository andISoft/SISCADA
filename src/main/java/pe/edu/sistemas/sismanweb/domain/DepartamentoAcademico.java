package pe.edu.sistemas.sismanweb.domain;
// Generated 26/09/2017 04:31:47 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * DepartamentoAcademico generated by hbm2java
 */
@Entity
@Table(name = "DEPARTAMENTO_ACADEMICO", catalog = "modeloGeneralFisi", uniqueConstraints = @UniqueConstraint(columnNames = "DEPARTAMENTO_ACADEMICO_NOMBRE"))
public class DepartamentoAcademico implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int iddepartamentoAcademico;
	private String departamentoAcademicoNombre;
	private Set<Docente> docentes = new HashSet<Docente>(0);

	public DepartamentoAcademico() {
	}

	public DepartamentoAcademico(int iddepartamentoAcademico, String departamentoAcademicoNombre) {
		this.iddepartamentoAcademico = iddepartamentoAcademico;
		this.departamentoAcademicoNombre = departamentoAcademicoNombre;
	}

	public DepartamentoAcademico(int iddepartamentoAcademico, String departamentoAcademicoNombre,
			Set<Docente> docentes) {
		this.iddepartamentoAcademico = iddepartamentoAcademico;
		this.departamentoAcademicoNombre = departamentoAcademicoNombre;
		this.docentes = docentes;
	}

	@Id

	@Column(name = "IDDEPARTAMENTO_ACADEMICO", unique = true, nullable = false)
	public int getIddepartamentoAcademico() {
		return this.iddepartamentoAcademico;
	}

	public void setIddepartamentoAcademico(int iddepartamentoAcademico) {
		this.iddepartamentoAcademico = iddepartamentoAcademico;
	}

	@Column(name = "DEPARTAMENTO_ACADEMICO_NOMBRE", unique = true, nullable = false, length = 50)
	public String getDepartamentoAcademicoNombre() {
		return this.departamentoAcademicoNombre;
	}

	public void setDepartamentoAcademicoNombre(String departamentoAcademicoNombre) {
		this.departamentoAcademicoNombre = departamentoAcademicoNombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departamentoAcademico")
	public Set<Docente> getDocentes() {
		return this.docentes;
	}

	public void setDocentes(Set<Docente> docentes) {
		this.docentes = docentes;
	}

}