package service;

import entities.Machine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.MachineService;

import java.util.List;

import static org.junit.Assert.*;

public class MachineServiceTest {

    private MachineService machineService;
    private Machine machine;

    @Before
    public void setUp() {
        machineService = new MachineService();
        machine = new Machine();
        machine.setRef("M101");
        // Créez la machine avant d'exécuter les tests
        machineService.create(machine);
    }

    @After
    public void tearDown() {
        Machine foundMachine = machineService.findById(machine.getId());
        if (foundMachine != null) {
            machineService.delete(foundMachine);
        }
    }

    @Test
    public void testCreate() {
        assertNotNull("Machine should have been created with an ID", machine.getId());
    }

    @Test
    public void testFindById() {
        Machine foundMachine = machineService.findById(machine.getId());
        assertNotNull("Machine should be found", foundMachine);
        assertEquals("Machine reference should match", machine.getRef(), foundMachine.getRef());
    }

    @Test
    public void testUpdate() {
        machine.setRef("M202"); // Modifiez la référence pour tester la mise à jour
        boolean result = machineService.update(machine);
        assertTrue("Machine should be updated successfully", result);
        Machine updatedMachine = machineService.findById(machine.getId());
        assertEquals("Updated machine reference should match", "M202", updatedMachine.getRef());
    }

    @Test
    public void testDelete() {
        boolean result = machineService.delete(machine);
        assertTrue("Machine should be deleted successfully", result);
        Machine foundMachine = machineService.findById(machine.getId());
        assertNull("Machine should not be found after deletion", foundMachine);
    }

    @Test
    public void testFindAll() {
        List<Machine> machines = machineService.findAll();
        assertNotNull("Machines list should not be null", machines);
        assertTrue("Machines list should contain at least one machine", machines.size() > 0);
    }
}

