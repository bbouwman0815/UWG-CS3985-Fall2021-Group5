package edu.westga.cs4985.clinicApp.server;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The server class
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public class Server extends Thread {

	/**
	 * Write booked appointment to json file
	 * 
	 * @param jsonString the appointment's json string
	 * @return "Added" if appointment's json string added to json file
	 * @throws IOException    the IO exception
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings("unchecked")
	public String bookAppointment(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/appointments.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/appointments.json");

		JSONObject data = (JSONObject) parser.parse(jsonString);

		jsonObject.add(data);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "ADDED";
	}
	
	/**
	 * Write updateed appointment to json file
	 * 
	 * @param jsonString the appointment's json string
	 * @return "Added" if appointment's json string updateed to json file
	 * @throws IOException    the IO exception
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings("unchecked")
	public String updateAppointment(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/appointments.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/appointments.json");

		JSONObject data = (JSONObject) parser.parse(jsonString);
		JSONObject result = null;
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("medicalPersonnel").equals(data.get("medicalPersonnel"))
					&& parseData.get("patient").equals(data.get("patient"))
					&& parseData.get("date").equals(data.get("date"))) {
				result = parseData;
			}
		}
		jsonObject.remove(result);
		jsonObject.add(data);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "Updated";
	}

	/**
	 * Remove booked appointment from json file
	 * 
	 * @param jsonString the appointment's json string
	 * @return "Removed" if appointment's json string removed from json file
	 * @throws IOException    the IO exception
	 * @throws ParseException the parse exception
	 */
	public String cancleAppointment(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/appointments.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/appointments.json");

		JSONObject data = (JSONObject) parser.parse(jsonString);
		JSONObject result = null;
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("medicalPersonnel").equals(data.get("medicalPersonnel"))
					&& parseData.get("patient").equals(data.get("patient"))
					&& parseData.get("date").equals(data.get("date"))) {
				result = parseData;
			}
		}
		jsonObject.remove(result);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "Removed";
	}

	/**
	 * Update user's general information to json file
	 * 
	 * @param jsonString user's general information json string
	 * @return "Added" if user's general information json string added to json file
	 * @throws IOException    the IO exception
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings("unchecked")
	public String updatePatientGeneralInfo(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/users.json");

		JSONObject data = (JSONObject) parser.parse(jsonString);
		JSONObject result = null;
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("userName").equals(data.get("userName"))
					&& parseData.get("password").equals(data.get("password"))) {
				result = parseData;
			}
		}
		if(result != null) {
			jsonObject.remove(result);
			jsonObject.add(data);
		}
		
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "Updated";
	}
	
	/**
	 * Update user's avaiabilities to json file
	 * 
	 * @param jsonString user's avaiabilities json string
	 * @return "Updated" if user's avaiabilities json string ppdated to json file
	 * @throws IOException    the IO exception
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings("unchecked")
	public String updateMedicalPersonnelAvaiabilities(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/availabilities.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/availabilities.json");
		JSONObject data = (JSONObject) parser.parse(jsonString);
		JSONObject result = null;
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("medicalPersonnel").equals(data.get("medicalPersonnel"))) {
				result = parseData;
			}
		}
		if(result != null) {
			jsonObject.remove(result);
			jsonObject.add(data);
		} else {
			jsonObject.add(data);
		}
		
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "Updated";
	}

	/**
	 * Get user's json string when user login
	 * 
	 * @param jsonString the user's login json string
	 * @return the user's json string if login information is valid; otherwise,
	 *         "EROOR"
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException           the IO exception
	 * @throws ParseException 
	 */
	public String userLogin(String jsonString) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		JSONObject data = (JSONObject) parser.parse(jsonString);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("userName").equals(data.get("userName"))
					&& parseData.get("password").equals(data.get("password"))) {
				return parseData.toJSONString();
			}
		}
		return "ERROR";
	}

	/**
	 * Get user json string by given json string as user name
	 * 
	 * @param jsonString the json string that contains the user name
	 * @return the user json string associated with given user name json string
	 * @throws IOException the IO exception
	 * @throws ParseException 
	 */
	public String getUserByUserName(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();

		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		JSONObject data = (JSONObject) parser.parse(jsonString);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("userName").equals(data.get("patient"))) {
				return parseData.toJSONString();
			}
		}
		return "ERROR";
	}
	

	/**
	 * Get user json string by given json string as user name
	 * 
	 * @param jsonString the json string that contains the user name
	 * @return the user json string associated with given user name json string
	 * @throws IOException the IO exception
	 * @throws ParseException 
	 */
	public String getCaregiverByUserName(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();

		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		JSONObject data = (JSONObject) parser.parse(jsonString);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("userName").equals(data.get("caregiver"))) {
				return parseData.toJSONString();
			}
		}
		return "ERROR";
	}
	
	/**
	 * Get user json string by given json string as user name
	 * 
	 * @param jsonString the json string that contains the user name
	 * @return the user json string associated with given user name json string
	 * @throws IOException the IO exception
	 * @throws ParseException 
	 */
	public String getUserByMedicalPersonnelUserName(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();

		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		JSONObject data = (JSONObject) parser.parse(jsonString);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("userName").equals(data.get("medicalPersonnel"))) {
				return parseData.toJSONString();
			}
		}
		return "ERROR";
	}

	/**
	 * Get appointment list by given user name json string
	 * 
	 * @param jsonString the json string of user name
	 * @return the appointment list json string associated with the json stirng of
	 *         user name
	 * @throws IOException the IO exception
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public String getAppointments(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONArray appointments = new JSONArray();
		FileReader reader = new FileReader("./jsonFiles/appointments.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		JSONObject data = (JSONObject) parser.parse(jsonString);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("patient").equals(data.get("patient"))) {
				appointments.add(parseData);
			}
		}

		return appointments.toJSONString();
	}
	
	/**
	 * Get appointment list by given user name json string
	 * 
	 * @param jsonString the json string of user name
	 * @return the appointment list json string associated with the json stirng of
	 *         user name
	 * @throws IOException the IO exception
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public String getAppointmentsForMedicalPersonnel(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONArray appointments = new JSONArray();
		FileReader reader = new FileReader("./jsonFiles/appointments.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		JSONObject data = (JSONObject) parser.parse(jsonString);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("medicalPersonnel").equals(data.get("medicalPersonnel"))) {
				appointments.add(parseData);
			}
		}

		return appointments.toJSONString();
	}
	
	/**
	 * Get appointment list by given user name json string
	 * 
	 * @param jsonString the json string of user name
	 * @return the appointment list json string associated with the json stirng of
	 *         user name
	 * @throws IOException the IO exception
	 * @throws ParseException 
	 */
	public String getAvailabilities(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/availabilities.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		JSONObject data = (JSONObject) parser.parse(jsonString);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("medicalPersonnel").equals(data.get("medicalPersonnel"))) {
				return parseData.get("availabilityList").toString();
			}
		}

		return "ERROR";
	}

	/**
	 * Adds the patient user.
	 *
	 * @param jsonString the json string
	 * @return the string
	 * @throws IOException    Signals that an I/O exception has occurred.
	 * @throws ParseException
	 */
	@SuppressWarnings({ "unchecked" })
	public String addPatientUser(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/users.json");

		JSONObject data = (JSONObject) parser.parse(jsonString);

		jsonObject.add(data);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "ADDED";
	}

	/**
	 * Adds the medical condition.
	 *
	 * @param jsonString the json string
	 * @return the string
	 * @throws IOException    Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings({ "unchecked" })
	public String addMedicalCondition(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/medicalconditions.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/medicalconditions.json");

		JSONObject data = (JSONObject) parser.parse(jsonString);

		jsonObject.add(data);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "ADDED";
	}

	/**
	 * Removes the medical condition.
	 *
	 * @param jsonString the json string
	 * @return the string
	 * @throws IOException    Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	public String removeMedicalCondition(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/medicalconditions.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/medicalconditions.json");

		JSONObject data = (JSONObject) parser.parse(jsonString);
		JSONObject result = null;
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("name").equals(data.get("name"))
					&& parseData.get("patient").equals(data.get("patient"))) {
				result = parseData;
			}
		}
		jsonObject.remove(result);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "Removed";
	}
	
	/**
	 * Gets the medical conditions.
	 *
	 * @param jsonString the json string
	 * @return the medical conditions
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings("unchecked")
	public String getMedicalConditions(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONArray medicalConditions = new JSONArray();
		FileReader reader = new FileReader("./jsonFiles/medicalconditions.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		JSONObject data = (JSONObject) parser.parse(jsonString);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("patient").equals(data.get("patient"))) {
				medicalConditions.add(parseData);
			}
		}

		return medicalConditions.toJSONString();
	}
	

	/**
	 * Gets the all patients.
	 *
	 * @param jsonString the json string
	 * @return the all patients
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings("unchecked")
	private String getAllPatients() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONArray patients = new JSONArray();
		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("type").equals("PATIENT")) {
				patients.add(parseData);
			}
		}

		return patients.toJSONString();
	}
	
	/**
	 * Gets the all Caregivers.
	 *
	 * @param jsonString the json string
	 * @return the all Caregivers
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings("unchecked")
	private String getAllCaregivers() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONArray caregivers = new JSONArray();
		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("type").equals("Caregiver")) {
				caregivers.add(parseData);
			}
		}

		return caregivers.toJSONString();
	}
	
	/**
	 * Gets the all MedicalPersonnels.
	 *
	 * @param jsonString the json string
	 * @return the all MedicalPersonnels
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings("unchecked")
	public String getMedicalPersonnels(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONArray medicalPersonnel = new JSONArray();
		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		JSONObject data = (JSONObject) parser.parse(jsonString);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("zipcode") != null && parseData.get("zipcode").equals(data.get("zipcode"))) {
				medicalPersonnel.add(parseData);
			}
		}

		return medicalPersonnel.toJSONString();
	}
	
	/**
	 * Gets the all patients.
	 *
	 * @param jsonString the json string
	 * @return the all patients
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	private String getMedicalPersonnelsPatients(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONArray patients = new JSONArray();
		FileReader reader = new FileReader("./jsonFiles/medicalpersonnelpatients.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		JSONObject data = (JSONObject) parser.parse(jsonString);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("medicalPersonnel").equals(data.get("medicalPersonnel"))) {
				return parseData.get("patients").toString();
			}
		}

		return patients.toJSONString();
	}
	
	/**
	 * Gets the all patients.
	 *
	 * @param jsonString the json string
	 * @return the all patients
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	private String getCaregiverPatients(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONArray patients = new JSONArray();
		FileReader reader = new FileReader("./jsonFiles/caregiverpatients.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		JSONObject data = (JSONObject) parser.parse(jsonString);
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("caregiver").equals(data.get("caregiver"))) {
				return parseData.get("patients").toString();
			}
		}

		return patients.toJSONString();
	}
	
	/**
	 * Update medical personnels patients.
	 *
	 * @param jsonString the json string
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings("unchecked")
	public String updateMedicalPersonnelsPatients(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/medicalpersonnelpatients.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/medicalpersonnelpatients.json");
		JSONObject data = (JSONObject) parser.parse(jsonString);
		JSONObject result = null;
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("medicalPersonnel").equals(data.get("medicalPersonnel"))) {
				result = parseData;
			}
		}
		if(result != null) {
			jsonObject.remove(result);
			jsonObject.add(data);
		} else {
			jsonObject.add(data);
		}
		
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "Updated";
	}
	
	/**
	 * Update caregiver patients.
	 *
	 * @param jsonString the json string
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings("unchecked")
	public String updateCaregiverPatients(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/caregiverpatients.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);

		FileWriter writer = new FileWriter("./jsonFiles/caregiverpatients.json");
		JSONObject data = (JSONObject) parser.parse(jsonString);
		JSONObject result = null;
		for (Object aData : jsonObject) {
			JSONObject parseData = (JSONObject) aData;
			if (parseData.get("caregiver").equals(data.get("caregiver"))) {
				result = parseData;
			}
		}
		if(result != null) {
			jsonObject.remove(result);
			jsonObject.add(data);
		} else {
			jsonObject.add(data);
		}
		
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "Updated";
	}
	
	/**
	 * Adds the new medical personnel.
	 *
	 * @param jsonString the json string
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings({ "unchecked" })
	public String addMedicalPersonnelUser(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		FileWriter writer = new FileWriter("./jsonFiles/users.json");
		JSONObject data = (JSONObject) parser.parse(jsonString);
		jsonObject.add(data);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "ADDED";
	}
	
	/**
	 * Adds the new caregiver
	 *
	 * @param jsonString the json string
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@SuppressWarnings({ "unchecked" })
	public String addCaregiverUser(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./jsonFiles/users.json");
		JSONArray jsonObject = (JSONArray) parser.parse(reader);
		FileWriter writer = new FileWriter("./jsonFiles/users.json");
		JSONObject data = (JSONObject) parser.parse(jsonString);
		jsonObject.add(data);
		writer.write(jsonObject.toJSONString());
		writer.flush();
		writer.close();
		return "ADDED";
	}

	/*
	 * Run the server
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.REP);
		socket.bind("tcp://127.0.0.1:5579");

		while (!Thread.currentThread().isInterrupted()) {

			String reply = socket.recvStr(0);
			String[] message = reply.split(",", 2);

			String request = message[0];

			System.out.println("Server is running for " + request);
			String data = message[1];
			String result = "";
			if (request.equals("BOOK_APPOINTMENT")) {
				try {
					result = this.bookAppointment(data);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("CANCLE_APPOINTMENT")) {
				try {
					result = this.cancleAppointment(data);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("USER_LOGIN")) {
				try {
					result = this.userLogin(data);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
			if (request.equals("GET_APPOINTMENTS")) {
				try {
					result = this.getAppointments(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("GET_USER_BY_USERNAME")) {
				try {
					result = this.getUserByUserName(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("GET_CAREGIVER_BY_USER_NAME")) {
				try {
					result = this.getCaregiverByUserName(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("ADD_PATIENT")) {
				try {
					result = this.addPatientUser(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("ADD_CAREGIVER")) {
				try {
					result = this.addCaregiverUser(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("ADD_MEDICAL_PERSONNEL")) {
				try {
					result = this.addMedicalPersonnelUser(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (request.equals("ADD_MEDICAL_CONDITION")) {
				try {
					result = this.addMedicalCondition(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (request.equals("REMOVE_MEDICAL_CONDITION")) {
				try {
					result = this.removeMedicalCondition(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			if (request.equals("GET_MEDICAL_CONDITIONS")) {
				try {
					result = this.getMedicalConditions(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			if (request.equals("UPDATE_GENERAL_INFORMATION")) {
				try {
					result = this.updatePatientGeneralInfo(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("GET_USER_BY_MEDICAL_PERSONNEL_USERNAME")) {
				try {
					result = this.getUserByMedicalPersonnelUserName(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("GET_MEDICAL_PERSONNELS_PATIENTS")) {
				try {
					result = this.getMedicalPersonnelsPatients(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("GET_CAREGIVER_PATIENTS")) {
				try {
					result = this.getCaregiverPatients(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("UPDATE_MEDICAL_PERSONNELS_PATIENTS")) {
				try {
					result = this.updateMedicalPersonnelsPatients(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("UPDATE_CAREGIVER_PATIENTS")) {
				try {
					result = this.updateCaregiverPatients(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("GET_ALL_PATIENTS")) {
				try {
					result = this.getAllPatients();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("GET_APPOINTMENTS_FOR_MEDICAL_PEROSNNEL")) {
				try {
					result = this.getAppointmentsForMedicalPersonnel(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("GET_ALL_CAREGIVERS")) {
				try {
					result = this.getAllCaregivers();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("GET_PATIENTS")) {
				try {
					result = this.getAllPatients();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("GET_AVAILABILITIES")) {
				try {
					result = this.getAvailabilities(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("UPDATE_AVAILABILITY")) {
				try {
					result = this.updateMedicalPersonnelAvaiabilities(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			if (request.equals("GET_ALL_MEDICAL_PERSONNELS")) {
				try {
					result = this.getMedicalPersonnels(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (request.equals("UPDATE_APPOINTMENT")) {
				try {
					result = this.updateAppointment(data);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			if (request.equals("CLOSE_SERVER")) {
				socket.close();
				result = "Closed";
			}


			socket.send(result);
		}

		socket.close();
		context.term();

	}
}
