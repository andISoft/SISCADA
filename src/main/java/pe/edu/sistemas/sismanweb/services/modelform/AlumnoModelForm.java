package pe.edu.sistemas.sismanweb.services.modelform;

public class AlumnoModelForm {
	
	private int idAlumno;
	private int idPersona;
	private int idPlan;
	private String codigo;
	private String nombre;
	private String apPaterno;
	private String apMaterno;
	private String sexo;
	private String dni;
	private String telefono;
	private String correo;
	private String direccion;	
	
	public AlumnoModelForm(){		
		
	}

	public AlumnoModelForm(int idAlumno, int idPersona, int idPlan, String codigo, String nombre, String apPaterno, String apMaterno,
			String sexo, String dni, String telefono, String correo, String direccion) {
		super();
		this.idAlumno = idAlumno;
		this.idPersona = idPersona;
		this.idPlan = idPlan;
		this.codigo = codigo;
		this.nombre = nombre;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.sexo = sexo;
		this.dni = dni;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
	}

	

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	
	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	

}
