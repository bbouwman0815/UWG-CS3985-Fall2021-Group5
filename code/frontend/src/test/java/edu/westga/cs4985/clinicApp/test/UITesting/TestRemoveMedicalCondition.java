package edu.westga.cs4985.clinicApp.test.UITesting;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import edu.westga.cs4985.clinicApp.ClinicApp;
import edu.westga.cs4985.clinicApp.client.Communicator;
import edu.westga.cs4985.clinicApp.client.RequestType;
import edu.westga.cs4985.clinicApp.model.UserManager;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

class TestRemoveMedicalCondition extends ApplicationTest {

	@Override
	public void start(Stage stage) throws IOException {
		ClinicApp app = new ClinicApp();
		UserManager user = new UserManager(new ServerFake());
		app.setUser(user);
		app.start(stage);
	}

	private class ServerFake extends Communicator {

		@Override
		public String request(RequestType requestType, String data) {
			String request = requestType + "," + data;

			if (request.equals(
					"USER_LOGIN,{\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"userName\":\"bbouwman0815\"}")) {
				return "{\"insurance\":\"United Healthcare\",\"lastName\":\"Bouwman\",\"country\":\"United States\",\"gender\":\"Male\",\"race\":\"White\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"912 Lovvorn Road\",\"dateOfBirth\":\"2021-09-15\",\"type\":\"PATIENT\",\"userName\":\"bbouwman0815\",\"firstName\":\"Brian\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"6665554444\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"bbouwman0815@yahoo.com\"}";
			}
			if (request.equals(
					"ADD_MEDICAL_CONDITION,{\"terminationDate\":\"2016-11-01\",\"notes\":\"this disease lasts six years\",\"patient\":\"bbouwman0815\",\"diagnosisDate\":\"2010-11-01\",\"name\":\"sixyearsdiseaselmao\"}")) {
				return "ADDED";
			}
			if (request.equals(
					"GET_USER_BY_USERNAME,{\"patient\":\"bbouwman0815\"}")) {
				return "{\"insurance\":\"United Healthcare\",\"lastName\":\"Bouwman\",\"country\":\"United States\",\"gender\":\"Male\",\"race\":\"White\",\"address2\":\"\",\"city\":\"Carrollton\",\"address1\":\"912 Lovvorn Road\",\"dateOfBirth\":\"2021-09-15\",\"type\":\"PATIENT\",\"userName\":\"bbouwman0815\",\"firstName\":\"Brian\",\"password\":\"5f4dcc3b5aa765d61d8327deb882cf99\",\"phoneNumber\":\"6665554444\",\"ethnicty\":\"Not Hispanic or Latino\",\"caregiver\":\"\",\"state\":\"GA\",\"email\":\"bbouwman0815@yahoo.com\"}";
			}
			if (request.equals(
					"GET_MEDICAL_CONDITIONS,{\"patient\":\"bbouwman0815\"}")) {
				return "[{\"terminationDate\":\"2021-11-01\",\"notes\":\"this disease lasts six years lmao\",\"patient\":\"bbouwman0815\",\"diagnosisDate\":\"2010-11-01\",\"name\":\"sixyearsdiseaselmao\"}]";
			} else {
				return "ERROR";
			}
		}

	}

	@Test
	public void testRemoveMedicalCondition() throws InterruptedException {
		this.clickOn("#usernameTextField");
		this.type(KeyCode.B);
		this.type(KeyCode.B);
		this.type(KeyCode.O);
		this.type(KeyCode.U);
		this.type(KeyCode.W);
		this.type(KeyCode.M);
		this.type(KeyCode.A);
		this.type(KeyCode.N);
		this.type(KeyCode.DIGIT0);
		this.type(KeyCode.DIGIT8);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT5);
		this.clickOn("#passwordTextField");
		this.type(KeyCode.P);
		this.type(KeyCode.A);
		this.type(KeyCode.S);
		this.type(KeyCode.S);
		this.type(KeyCode.W);
		this.type(KeyCode.O);
		this.type(KeyCode.R);
		this.type(KeyCode.D);
		this.clickOn("#loginButton");
		this.clickOn("#medicalConditionsNavButton");

		this.clickOn("#medicalConditionTableView");
		this.type(KeyCode.UP);
		this.clickOn("#deleteConditionButton");
		this.type(KeyCode.ENTER);

	}
	
	@Test
	public void testRemoveMedicalConditionCancel() throws InterruptedException {
		this.clickOn("#usernameTextField");
		this.type(KeyCode.B);
		this.type(KeyCode.B);
		this.type(KeyCode.O);
		this.type(KeyCode.U);
		this.type(KeyCode.W);
		this.type(KeyCode.M);
		this.type(KeyCode.A);
		this.type(KeyCode.N);
		this.type(KeyCode.DIGIT0);
		this.type(KeyCode.DIGIT8);
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT5);
		this.clickOn("#passwordTextField");
		this.type(KeyCode.P);
		this.type(KeyCode.A);
		this.type(KeyCode.S);
		this.type(KeyCode.S);
		this.type(KeyCode.W);
		this.type(KeyCode.O);
		this.type(KeyCode.R);
		this.type(KeyCode.D);
		this.clickOn("#loginButton");
		this.clickOn("#medicalConditionsNavButton");

		this.clickOn("#medicalConditionTableView");
		this.type(KeyCode.UP);
		this.clickOn("#deleteConditionButton");
		this.type(KeyCode.ESCAPE);
	}

}
