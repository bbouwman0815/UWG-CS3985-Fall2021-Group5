package edu.westga.cs4985.clinicApp.client;

/**
 * An Enum of request types used in the Tutor Scheduler Application 
 * 
 * @author Jinxiang Zeng
 * @version Fall 2021
 *
 */
public enum RequestType {
	GET_USER_BY_USERNAME,
	USER_LOGIN,
	GET_AVAILABILITIES,
	GET_APPOINTMENTS,
	BOOK_APPOINTMENT,
	CANCLE_APPOINTMENT,
	UPDATE_NOTES,
	ADD_AVAILABILITY,
	REMOVE_AVAILABILITY;
}