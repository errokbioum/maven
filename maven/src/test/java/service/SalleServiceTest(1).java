package service;

import entities.Salle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.SalleService;

import java.util.List;

import static org.junit.Assert.*;

public class SalleServiceTest {

    private SalleService salleService;
    private Salle salle;

    @Before
    public void setUp() {
        salleService = new SalleService();
        salle = new Salle();
        salle.setCode("A101");
        salleService.create(salle);
    }

    @After
    public void tearDown() {
        Salle foundSalle = salleService.findById(salle.getId());
        if (foundSalle != null) {
            salleService.delete(foundSalle);
        }
    }

    @Test
    public void testCreate() {
        assertNotNull("Salle should have been created with an ID", salle.getId());
    }

    @Test
    public void testFindById() {
        Salle foundSalle = salleService.findById(salle.getId());
        assertNotNull("Salle should be found", foundSalle);
        assertEquals("Salle code should match", salle.getCode(), foundSalle.getCode());
    }

    @Test
    public void testUpdate() {
        salle.setCode("B202");
        boolean result = salleService.update(salle);
        assertTrue("Salle should be updated successfully", result);
        Salle updatedSalle = salleService.findById(salle.getId());
        assertEquals("Updated salle code should match", "B202", updatedSalle.getCode());
    }

    @Test
    public void testDelete() {
        boolean result = salleService.delete(salle);
        assertTrue("Salle should be deleted successfully", result);
        Salle foundSalle = salleService.findById(salle.getId());
        assertNull("Salle should not be found after deletion", foundSalle);
    }

    @Test
    public void testFindAll() {
        List<Salle> salles = salleService.findAll();
        assertNotNull("Salles list should not be null", salles);
        assertTrue("Salles list should contain at least one salle", salles.size() > 0);
    }
}
