package controller;

import exceptions.ConsultationException;
import exceptions.PatientException;
import model.Patient;
import org.junit.Before;
import org.junit.Test;
import repository.Repository;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * created by bnorbert on 18,04,2018
 * bnorbertcristian@gmail.com
 */
public class DoctorControllerTopDownTest {

    private Repository repository;
    private DoctorController controller;

    @Before
    public void setUp() throws PatientException, ConsultationException {
        repository = new Repository("FilePatients.txt", "FileConsultations.txt");
        controller = new DoctorController(repository);

        controller.addPatient(new Patient("1111111111111","Hermoine","London"));

        controller.addConsultation("1","1111111111111","muggle-born", Arrays.asList("gillyweed"),"2018-03-03");
    }

    @Test
    public void reqA() throws PatientException {
        controller.addPatient(new Patient("8888888888888","Ron","Liverpool"));
    }

    @Test
    public void reqAB() throws ConsultationException, PatientException {
        controller.addPatient(new Patient("8888888888888","Ron","Liverpool"));
        controller.addConsultation("32","8888888888888","dragon-pox",Arrays.asList("dittany"),"2018-03-03");
    }

    @Test
    public void reqABC() throws PatientException, ConsultationException {
        controller.addPatient(new Patient("8888888888888","Ron","Liverpool"));
        controller.addConsultation("32","8888888888888","dragon-pox",Arrays.asList("dittany"),"2018-03-03");
        assertEquals("Ron",controller.getPatientsWithDisease("dragon-pox").get(0).getName());
    }


}
