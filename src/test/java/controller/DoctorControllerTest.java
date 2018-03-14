package controller;

import exceptions.PatientException;
import model.Patient;
import org.junit.Before;
import org.junit.Test;
import repository.Repository;

import static org.junit.Assert.*;

/**
 * Created by Vali on 14.03.2018.
 */
public class DoctorControllerTest {

    private Repository repository;
    private DoctorController controller;

    @Before
    public void setUp() {
        repository = new Repository("FilePatients.txt","FileConsultations.txt");
        controller = new DoctorController(repository);
    }

    @Test
    public void addPatient() throws Exception {
        int n = controller.getPatientList().size();
        controller.addPatient(new Patient("2222222222222","John","ooo"));
        assertEquals(controller.getPatientList().size(),n+1);
    }

    @Test(expected = PatientException.class)
    public void addPatientException() throws PatientException {
        controller.addPatient(new Patient(null,null,null));
    }
}