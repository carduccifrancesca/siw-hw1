package it.uniroma3.siw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"via", "numeroCivico", "comune"}))
public class Indirizzo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String via;
	
	@Column(nullable = false)
	private int numeroCivico;
	
	@Column(nullable = false)
	private String comune;
	
	private int cap;
	
	private String provincia;
	
	public Indirizzo() {}
	
	public Indirizzo(String via, int numeroCivico, String comune, 
					 int cap, String provincia) {
		this.via = via;
		this.numeroCivico = numeroCivico;
		this.comune = comune;
		this.cap = cap;
		this.provincia = provincia;
	}
	
	public String getVia() {
		return this.via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	public int getNumeroCivico() {
		return this.numeroCivico;
	}
	
	public void setNumeroCivico(int numeroCivico) {
		this.numeroCivico = numeroCivico;
	}
	
	public String getComune() {
		return this.comune;
	}
	
	public void setComune(String comune) {
		this.comune = comune;
	}
	
	public int getCap() {
		return this.cap;
	}
	
	public void setCap(int cap) {
		this.cap = cap;
	}
	
	public String getProvincia() {
		return this.provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@Override
	public int hashCode() {
		return this.via.hashCode() + 
			   this.numeroCivico + 
			   this.comune.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != Indirizzo.class)
			return false;
		Indirizzo that = (Indirizzo)obj;
		return this.getVia().equals(that.getVia()) &&
			   this.getNumeroCivico() == that.getNumeroCivico() &&
			   this.getComune().equals(that.getComune());
	}
	
}
