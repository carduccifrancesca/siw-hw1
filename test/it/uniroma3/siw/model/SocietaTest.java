package it.uniroma3.siw.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SocietaTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	private Societa s1;
	private Societa s2;
	private Indirizzo i1;
	private Indirizzo i2;
	
	@BeforeAll
	static void initEntityManager() {
		emf = Persistence.createEntityManagerFactory("products-unit");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	@AfterAll
	static void closeEntityManager() {
		if(em != null) em.close();
		if(emf != null) emf.close();
	}

	@BeforeEach
	void setUp() {
		i1 = new Indirizzo("Via Roma", 12, "Napoli", 123, "NA");
		i2 = new Indirizzo("Via Verdi", 4, "Roma", 234, "RM");
		
		s1 = new Societa("Società1", i1, "1234");
		s2 = new Societa("Società2", i2, "9876");
		
		Query deleteSocietaQuery = em.createQuery("DELETE FROM Societa s");
		Query deleteIndirizzoQuery = em.createQuery("DELETE FROM Indirizzo i");
		tx = em.getTransaction();
		tx.begin();
		deleteSocietaQuery.executeUpdate();
		deleteIndirizzoQuery.executeUpdate();
		tx.commit();
		
		Query tableSizeQuery = em.createQuery("SELECT s FROM Societa s", Societa.class);
		assertEquals(0, tableSizeQuery.getResultList().size());
		
	}

	@Test
	void persistCascadeTest() {	
		tx = em.getTransaction();
		tx.begin();
		em.persist(s1);
		em.persist(s2);
		tx.commit();
		
		Query societaTableSizeQuery = em.createQuery("SELECT s FROM Societa s", Societa.class);
		assertEquals(2, societaTableSizeQuery.getResultList().size());
		Query indirizzoTableSizeQuery = em.createQuery("SELECT i FROM Indirizzo i", Indirizzo.class);
		assertEquals(2, indirizzoTableSizeQuery.getResultList().size());
	}
	
	@Test
	void deleteCascadeTest() {
		tx = em.getTransaction();
		tx.begin();
		em.persist(s1);
		em.persist(s2);
		tx.commit();
		
		Query societaTableSizeQuery = em.createQuery("SELECT s FROM Societa s", Societa.class);
		assertEquals(2, societaTableSizeQuery.getResultList().size());
		Query indirizzoTableSizeQuery = em.createQuery("SELECT i FROM Indirizzo i", Indirizzo.class);
		assertEquals(2, indirizzoTableSizeQuery.getResultList().size());
		
		tx = em.getTransaction();
		tx.begin();
		em.remove(s1);
		tx.commit();
		
		assertEquals(1, societaTableSizeQuery.getResultList().size());
		assertEquals(1, indirizzoTableSizeQuery.getResultList().size());
	}
	
	@Test
	void mergeCascadeTest() {
		tx = em.getTransaction();
		tx.begin();
		em.persist(s1);
		em.persist(s2);
		tx.commit();
		
		Query societaTableSizeQuery = em.createQuery("SELECT s FROM Societa s", Societa.class);
		assertEquals(2, societaTableSizeQuery.getResultList().size());
		Query indirizzoTableSizeQuery = em.createQuery("SELECT i FROM Indirizzo i", Indirizzo.class);
		assertEquals(2, indirizzoTableSizeQuery.getResultList().size());
		
		Indirizzo i3 = new Indirizzo("Via Nuova", 1, "Roma", 456, "RM");
		s1.setSede(i3);
		
		tx = em.getTransaction();
		tx.begin();
		em.merge(s1);
		tx.commit();
		
		assertEquals(2, societaTableSizeQuery.getResultList().size());
		assertEquals(3, indirizzoTableSizeQuery.getResultList().size());
	}

}
