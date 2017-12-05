package pe.edu.sistemas.sismanweb.services.modelform;

public class CursoBCModelForm {

	private Integer idCursoBase;
	private Integer idCursoConjunto;
	
	public CursoBCModelForm(Integer idCursoBase, Integer idCursoConjunto) {
		this.idCursoBase = idCursoBase;
		this.idCursoConjunto = idCursoConjunto;
	}
	
	public CursoBCModelForm(){};

	public Integer getIdCursoBase() {
		return idCursoBase;
	}

	public void setIdCursoBase(Integer idCursoBase) {
		this.idCursoBase = idCursoBase;
	}

	public Integer getIdCursoConjunto() {
		return idCursoConjunto;
	}

	public void setIdCursoConjunto(Integer idCursoConjunto) {
		this.idCursoConjunto = idCursoConjunto;
	}
	
	
}
