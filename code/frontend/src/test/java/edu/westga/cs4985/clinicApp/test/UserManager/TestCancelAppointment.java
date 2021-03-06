package edu.westga.cs4985.clinicApp.test.UserManager;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.Appointment;
import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.UserManager;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * JUnit Test Case for Cancel Appointment
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
class TestCancelAppointment {

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;
			if (request.equals(
					"CANCLE_APPOINTMENT,{\"date\":\"2024-09-01T14:00\",\"notes\":\"help\",\"patient\":\"New\",\"location\":\"TCL\",\"medicalPersonnel\":\"New\"}")) {
				return "Removed";
			} else {
				return "ERROR";
			}
		}
	}
	
	public MedicalPersonnel medicalPersonnelDummy() {
		MedicalPersonnel patientDummy = new MedicalPersonnel("Xavier", "Jameson", Gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[1], Ethnicity.ETHNICITY[1],
				"770-111-222", "email@email.com", "New", "New", "30118");
		return patientDummy;
	}


	public Patient patientDummy() {
		Patient patientDummy = new Patient("Xavier", "Jameson", Gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[1], Ethnicity.ETHNICITY[1],
				"770-111-222", "email@email.com", "United Healthcare", "New", "New");
		return patientDummy;
	}

	@Test
	void testCancelValidAppointment() {
		LocalDateTime dateTime = LocalDateTime.of(2024, 9, 01, 14, 00);
		Patient patient = this.patientDummy();
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		Appointment appointment = new Appointment(dateTime, patient, medicalPersonnel, "TCL", "help");
		UserManager userManager = new UserManager(new ServerFake());
		boolean removed = userManager.cancelAppointment(appointment);
		assertEquals(true, removed);
	}

	@Test
	void testCancelInvalidAppointment() {
		LocalDateTime dateTime = LocalDateTime.of(2021, 9, 01, 14, 00);
		Patient patient = this.patientDummy();
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		Appointment appointment = new Appointment(dateTime, patient, medicalPersonnel, "TCL", "help");
		UserManager userManager = new UserManager(new ServerFake());
		boolean removed = userManager.cancelAppointment(appointment);
		assertEquals(false, removed);
	}

}
