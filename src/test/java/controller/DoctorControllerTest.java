package controller;

import exceptions.ConsultationException;
import exceptions.PatientException;
import model.Consultation;
import model.Patient;
import org.junit.Before;
import org.junit.Test;
import repository.Repository;

import java.util.ArrayList;
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
    public void setUp() throws PatientException, ConsultationException {
        repository = new Repository("FilePatients.txt", "FileConsultations.txt");
        controller = new DoctorController(repository);

        controller.addPatient(new Patient("1111111111111","Hermoine","London"));
        controller.addPatient(new Patient("2222333333333","Ron","Liverpool"));

        controller.addConsultation("1","1111111111111","muggle-born",Arrays.asList("gillyweed"),"2018-03-03");
        controller.addConsultation("2","2222333333333","dragon-pox",Arrays.asList("dragonweed"),"2018-03-03");
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

//    START OF CONSULTATIONS TEST CASES

    @Test
    public void addConsultation() throws ConsultationException {
        controller.addConsultation("5","1111111111111","scrofungulus", Arrays.asList("dittany"),"2017-03-03");
    }

    @Test(expected = ConsultationException.class)
    public void addConsultationNullSSN() throws ConsultationException {
        controller.addConsultation("4",null, "scrofungulus",Arrays.asList("dittany"),"2017-03-03");
    }

    @Test(expected = ConsultationException.class)
    public void addConsultationNullMeds() throws ConsultationException {
        controller.addConsultation("4","1111111111111", "scrofungulus",null,"2017-03-03");
    }

    @Test(expected = ConsultationException.class)
    public void addConsultationNullDiag() throws ConsultationException {
        controller.addConsultation("4","1111111111111", null, Arrays.asList("dittany"),"2017-03-03");
    }

    @Test(expected = ConsultationException.class)
    public void addConsultationNullConsID() throws ConsultationException {
        controller.addConsultation(null, "1111111111", "scrofungulus",Arrays.asList("dittany"),"2017-03-03");
    }

    @Test(expected = ConsultationException.class)
    public void addConsultationMedsSizeZero() throws ConsultationException {
        controller.addConsultation("4","1111111111111", "scrofungulus", Collections.EMPTY_LIST,"2017-03-03");
    }

    @Test(expected = ConsultationException.class)
    public void addConsultationConsIDExists() throws ConsultationException {
        controller.addConsultation("1","1111111111111","scrofungulus",Arrays.asList("dittany"),"2017-03-03");
    }

    @Test(expected = ConsultationException.class)
    public void addConsultationPatientSSNDoesntExist() throws ConsultationException {
        controller.addConsultation("4","9999999999999","scrofungulus",Arrays.asList("dittany"),"2017-03-03");
    }

    @Test
    public void getPatientWithADisease() throws ConsultationException, PatientException {
        assertEquals("Hermoine",controller.getPatientsWithDisease("muggle-born").get(0).getName());
    }

    @Test
    public void integrateABC() throws PatientException, ConsultationException {
        controller.addPatient(new Patient("1111222222222","Harry","Cambridge"));
        controller.addConsultation("12","1111222222222","squibs-palsy",Arrays.asList("mandrake"),"2017-03-03");
        assertEquals("Harry",controller.getPatientsWithDisease("squibs-palsy").get(0).getName());
    }
}