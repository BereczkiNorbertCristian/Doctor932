package controller;

import exceptions.ConsultationException;
import exceptions.PatientException;
import model.Consultation;
import model.Patient;
import org.junit.Before;
import org.junit.Test;
import repository.Repository;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by bnorbert on 14.03.2018.
 */
public class DoctorControllerTest {

    private Repository repository;
    private DoctorController controller;

    @Before
    public void setUp() {
        repository = new Repository("FilePatients.txt", "FileConsultations.txt");
        controller = new DoctorController(repository);
    }

    @Test
    public void addPatient() throws Exception {
        int n = controller.getPatientList().size();
        controller.addPatient(new Patient("2222222222222", "John", "ooo"));
        assertEquals(n + 1, controller.getPatientList().size());
    }

    @Test(expected = PatientException.class)
    public void addPatientNullSSN() throws PatientException {
        controller.addPatient(new Patient(null, "John", "ooo"));
    }

    @Test(expected = PatientException.class)
    public void addPatientInvalidNumberOfCharactersSSN() throws PatientException {
        controller.addPatient(new Patient("4444", "John", "ooo"));
    }

    @Test(expected = PatientException.class)
    public void addPatientInvalidNumberOfCharactersSSN2() throws PatientException {
        controller.addPatient(new Patient("222222222222", "John", "ooo"));
    }

    @Test(expected = PatientException.class)
    public void addPatientInvalidFormatSSN() throws PatientException {
        controller.addPatient(new Patient("ds22222", "John", "ooo"));
    }

    @Test(expected = PatientException.class)
    public void addPatientInvalidDigitsName() throws PatientException {
        controller.addPatient(new Patient("2222222222222", "John1", "ooo"));
    }

    @Test(expected = PatientException.class)
    public void addPatientNullName() throws PatientException {
        controller.addPatient(new Patient("2222222222222", null, "ooo"));
    }

    @Test(expected = PatientException.class)
    public void addPatientNullAddress() throws PatientException {
        controller.addPatient(new Patient("2222222222222", "John", null));
    }

    @Test
    public void addConsultation() throws ConsultationException {
        controller.addConsultation("123","2222222222222","dummyDiagnostic", Arrays.asList("medicament1"),"2017-03-03");
    }

    @Test(expected = ConsultationException.class)
    public void addConsultationNullSSN() throws ConsultationException {
        controller.addConsultation("123",null, "dummy",Arrays.asList("med1"),"2017-03-03");
    }

    @Test(expected = ConsultationException.class)
    public void addConsulationNullMeds() throws ConsultationException {
        controller.addConsultation("123","2222222222222", "dummyDiag",null,"2017-03-03");
    }

}