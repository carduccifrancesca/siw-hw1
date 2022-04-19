package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Corso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	private LocalDate dataInizio;
	
	private int durataMesi;
	
	//Poiché i docenti sono consulenti potrebbe essere utile usare una politica cascade solo nel
	//caso della creazione di un corso
	@ManyToOne(cascade = {CascadeType.PERSIST})
	private Docente docente;
	
	@ManyToMany
	private List<Allievo> iscritti;
	
	public Corso() {}
	
	public Corso(String nome, LocalDate dataInizio, int mesi) {
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.durataMesi = mesi;
		this.iscritti = new ArrayList<Allievo>();
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	public LocalDate getDataInizio() {
		return this.dataInizio;
	}
	
	public void setDurataMesi(int mesi) {
		this.durataMesi = mesi;
	}
	
	public int getDurataMesi() {
		return this.durataMesi;
	}
	
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	
	public Docente getDocente() {
		return this.docente;
	}
	
	public void addIscritto(Allievo iscritto) {
		this.iscritti.add(iscritto);
	}
	
	public List<Allievo> getIscritti() {
		return this.iscritti;
	}
	
	public void setIscritti(List<Allievo> iscritti) {
		this.iscritti = iscritti;
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + this.dataInizio.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != (Corso.class))
			return false;
		Corso that = (Corso)obj;
		return this.getNome().equals(that.getNome()) && 
			   this.getDataInizio().equals(that.getDataInizio());
	}
}
