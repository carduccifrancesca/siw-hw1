package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Docente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	private LocalDate dataNascita;
	
	private String luogoNascita;
	
	@Column(nullable = false, unique = true)
	private String partitaiva;
	
	//Per un docente potrebbe aver senso applicare una politica di fetch eager, per sapere immediatamente
	//quali corsi tiene 
	@OneToMany(mappedBy = "docente", fetch = FetchType.EAGER)
	private List<Corso> corsi;
	
	public Docente() {}
	
	public Docente(String nome, String cognome, LocalDate dataNascita, String luogoNascita, String partitaiva) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.partitaiva = partitaiva;
		this.corsi = new ArrayList<Corso>();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public LocalDate getDataNascita() {
		return this.dataNascita;
	}
	
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public String getLuogoNascita() {
		return this.luogoNascita;
	}
	
	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}
	
	public String getPartitaiva() {
		return this.partitaiva;
	}
	
	public void setPartitaiva(String partitaiva) {
		this.partitaiva = partitaiva;
	}
	
	public List<Corso> getCorsi() {
		return this.corsi;
	}
	
	public void setCorsi (List<Corso> corsi) {
		this.corsi = corsi;
	}
	
	public void addCorso(Corso corso) {
		this.corsi.add(corso);
	}
	
	@Override
	public int hashCode() {
		return this.partitaiva.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != Docente.class)
			return false;
		Docente that = (Docente)obj;
		return this.getPartitaiva().equals(that.getPartitaiva());
	}
}
