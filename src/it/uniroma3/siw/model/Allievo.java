package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Allievo {
	
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
	private String matricola;
	
	private String email;
	
	@ManyToMany(mappedBy = "iscritti")
	private List<Corso> corsi;
	
	//L'informazione relativa alla società in cui un allievo lavora potrebbe essere caricata
	//solo su richiesta poiché non indispensabile per la società che eroga corsi di formazione.
	@ManyToOne(fetch = FetchType.LAZY)
	private Societa societa;
	
	public Allievo() {}
	
	public Allievo(String nome, String cognome, LocalDate dataNascita,
				   String luogoNascita, String matricola, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.matricola = matricola;
		this.email = email;
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
	
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public LocalDate getDataNascita() {
		return this.dataNascita;
	}
	
	public String getLuogoNascita() {
		return this.luogoNascita;
	}
	
	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}
	
	public String getMatricola() {
		return this.matricola;
	}
	
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Corso> getCorsi() {
		return this.corsi;
	}
	
	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}
	
	public void addCorso(Corso corso) {
		this.corsi.add(corso);
	}
	
	public Societa getSocieta() {
		return this.societa;
	}
	
	public void setSocieta(Societa societa) {
		this.societa = societa;
	}
	
	@Override
	public int hashCode() {
		return this.matricola.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != Allievo.class)
			return false;
		Allievo that = (Allievo)obj;
		return this.getMatricola().equals(that.getMatricola());
	}
}
