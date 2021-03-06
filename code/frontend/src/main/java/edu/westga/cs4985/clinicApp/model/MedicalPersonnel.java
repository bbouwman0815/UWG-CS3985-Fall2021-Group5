package edu.westga.cs4985.clinicApp.model;

import edu.westga.cs4985.clinicApp.resources.UI;

/**
 * The MedicalePersonnel class.
 * 
 * @author JinxiangZeng
 * @version Fall 2021
 *
 */
public class MedicalPersonnel extends Person {

	private static final String ADDRESS_SEPERATOR = ", ";

	private String phoneNumber;

	private String email;

	private String zipcode;

	/**
	 * Instantiates a new patient.
	 *
	 * @param firstName   the first name
	 * @param lastName    the last name
	 * @param gender      the gender
	 * @param dateOfBirth the date of birth
	 * @param address1    the address 1
	 * @param address2    the address 2
	 * @param city        the city
	 * @param state       the state
	 * @param country     the country
	 * @param race        the race
	 * @param ethnicity   the ethnicity
	 * @param phoneNumber the phone number
	 * @param email       the email
	 * @param username    the username
	 * @param password    the password
	 * @param zipcode     the zipcode
	 * 
	 * @precondition insurance != null && !insurance.isEmpty() && phoneNumber !=
	 *               null && !phoneNumber.isEmpty() && email != null &&
	 *               !email.isEmpty()
	 *
	 * @postcondition insurance == getInsurance() && phoneNumber == getPhoneNumber()
	 *                && email == getEmail()
	 */
	public MedicalPersonnel(String firstName, String lastName, String gender, String dateOfBirth, String address1,
			String address2, String city, String state, String country, String race, String ethnicity,
			String phoneNumber, String email, String username, String password, String zipcode) {
		super(firstName, lastName, gender, dateOfBirth, address1, address2, city, state, country, race, ethnicity,
				username, password);

		if (phoneNumber == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_LASTNAME);
		}
		if (phoneNumber.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_LASTNAME);
		}
		if (email == null) {
			throw new IllegalArgumentException(UI.ExceptionMessages.NULL_DATEOFBIRTH);
		}
		if (email.isEmpty()) {
			throw new IllegalArgumentException(UI.ExceptionMessages.EMPTY_DATEOFBIRTH);
		}
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.zipcode = zipcode;
	}

	/**
	 * Gets the zipcode
	 *
	 * @return the zipcode
	 */
	public String getZipCode() {
		return this.zipcode;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return this.getFirstName() + " " + this.getLastName();
	}

	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}

	/**
	 * Gets the full address
	 *
	 * @return the full address
	 */
	public String getFullAddress() {
		return this.getAddress1() + ADDRESS_SEPERATOR + this.getCity() + ADDRESS_SEPERATOR + this.getState()
				+ ADDRESS_SEPERATOR + this.getCountry();
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
