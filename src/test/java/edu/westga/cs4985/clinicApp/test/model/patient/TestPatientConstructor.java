package edu.westga.cs4985.clinicApp.test.model.patient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.Patient;
import edu.westga.cs4985.clinicApp.model.Person;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;

/**
 * JUnit Test Case for Patient Constructor
 * 
 * @author Brian Bouwman
 * @version Fall 2021
 *
 */
class TestPatientConstructor {

	public Person patientDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		Patient patientDummy = new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street", "",
				"Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1], "770-111-222",
				"email@email.com", "United Healthcare");
		return patientDummy;
	}

	@Test
	void testEmptyPhoneNumber() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1], "",
						"email@email.com", "United Healthcare"));
	}

	@Test
	void testNullPhoneNumber() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1], null,
						"email@email.com", "United Healthcare"));
	}

	@Test
	void testEmptyEmail() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1], "770-111-222",
						"", "United Healthcare"));
	}

	@Test
	void testNullEmail() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1], "770-111-222",
						null, "United Healthcare"));
	}

	@Test
	void testEmptyInsurance() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1], "770-111-222",
						"email@email.com", ""));
	}

	@Test
	void testNullInsurance() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		assertThrows(IllegalArgumentException.class,
				() -> new Patient("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street", "",
						"Carrollton", "GA", country.country[0], race.race[2], ethnicity.ethnicity[1], "770-111-222",
						"email@email.com", null));
	}
}
