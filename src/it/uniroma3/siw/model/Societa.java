package it.uniroma3.siw.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Societa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String ragioneSociale;
	
	//Una società è sempre associata ad un indirizzo, pertanto può essere utile una politica
	//cascade quando si crea, aggiorna ed elimina una sua istanza dal database
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private Indirizzo sede;
	
	private String telefono;
	
	public Societa() {}
	
	public Societa(String ragioneSociale, Indirizzo sede, String telefono) {
		this.ragioneSociale = ragioneSociale;
		this.sede = sede;
		this.telefono = telefono;
	}
	
	public String getRagioneSociale() {
		return this.ragioneSociale;
	}
	
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Indirizzo getSede() {
		return this.sede;
	}
	
	public void setSede(Indirizzo sede) {
		this.sede = sede;
	}
	
	public String getTelefono() {
		return this.telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return this.ragioneSociale.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != Societa.class)
			return false;
		Societa that = (Societa)obj;
		return this.getRagioneSociale().equals(that.getRagioneSociale());
	}
	
	
}
