package edu.westga.cs4985.clinicApp.test.MedicalPersonnelViewModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
import edu.westga.cs4985.clinicApp.viewmodel.MedicalPersonnelViewModel;

public class TestAddAvailability {
	
	public MedicalPersonnel medicalPersonnelDummy() {
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel("Xavier", "Jameson", Gender.SEX[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", Country.COUNTRY[0], Race.RACE[1], Ethnicity.ETHNICITY[1],
				"770-111-222", "email@email.com", "New", "New", "30118");
		return medicalPersonnel;
	}
	
	@Test
	public void testAddAvailability() throws ParseException {
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		User.setUser(medicalPersonnel);
		MedicalPersonnelViewModel viewModel = new MedicalPersonnelViewModel();
		viewModel.addAvailability("2024-09-12 12:00");
		assertAll(() -> assertEquals(1, viewModel.availabilityListProperty().size()),
				() -> assertEquals(1, viewModel.availabilityList().size()));
	}

}
