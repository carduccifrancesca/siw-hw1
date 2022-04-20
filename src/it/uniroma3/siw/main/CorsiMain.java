package it.uniroma3.siw.main;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import it.uniroma3.siw.model.*;

public class CorsiMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("products-unit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Corso c1 = new Corso("Excel", LocalDate.of(2022, 5, 5) , 3);
		Corso c2 = new Corso("Word", LocalDate.of(2022, 7, 1), 1);
		
		Docente d1 = new Docente("Mario", "Bianchi", LocalDate.of(1968, 5, 12), "Milano", "121232");
		Indirizzo i1 = new Indirizzo("Via Roma", 3, "Napoli", 123, "NA");
		Societa s1 = new Societa("Societa1", i1, "12345");
		
		Allievo a1 = new Allievo("Franco", "Rossi", LocalDate.of(1999, 3, 1), "Roma", "123", "franco@email.com");
		Allievo a2 = new Allievo("Carla", "Verdi", LocalDate.of(1998, 12, 30), "Roma", "234", "carla@email.com");
		
		c1.setDocente(d1);
		c2.setDocente(d1);
		
		d1.addCorso(c1);
		d1.addCorso(c2);
		
		c1.addIscritto(a1);
		c1.addIscritto(a2);
		
		c2.addIscritto(a2);
		
		a1.addCorso(c1);
		a1.setSocieta(s1);
		
		a2.addCorso(c1);
		a2.addCorso(c2);
		a2.setSocieta(s1);
		
		tx.begin();
		em.persist(d1);
		em.persist(a1);
		em.persist(a2);
		em.persist(c1);
		em.persist(c2);
		em.persist(s1);
		tx.commit();
		
		em.close();
		emf.close();
	}

}
