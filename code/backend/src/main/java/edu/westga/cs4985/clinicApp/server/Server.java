package edu.westga.cs4985.clinicApp.server;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server extends Thread {
	
	@SuppressWarnings("unchecked")
	public String bookAppointment(String jsonString) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
//		FileReader reader = new FileReader("/Users/pipai/Downloads/appointments.json");
//    	JSONArray jsonObject = (JSONArray) parser.parse(reader);
//        
//		FileWriter writer = new FileWriter("/Users/pipai/Downloads/appointments.json");
//		
//		JSONObject data = (JSONObject) parser.parse(jsonString);
//		
//		jsonObject.add(data.toJSONString());
//		System.out.print(data.toJSONString());
//		writer.write(jsonObject.toJSONString());
//		writer.flush();
//		writer.close();
		return "ADDED";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String userLogin(String jsonString) throws FileNotFoundException, IOException {
		JSONParser parser = new JSONParser();
        try {
        	FileReader reader = new FileReader("users.json");
        	JSONArray jsonObject = (JSONArray) parser.parse(reader);
            JSONObject data = (JSONObject) parser.parse(jsonString);
            for (Object aData : jsonObject) {
            	JSONObject parseData = (JSONObject) aData;
            	if (parseData.get("userName").equals(data.get("userName")) && parseData.get("password").equals(data.get("password"))){
            		return parseData.toJSONString();
            	}
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "ERROR";
	}
	
	public String getUserByUserName(String jsonString) throws IOException {
		JSONParser parser = new JSONParser();
        try {
        	FileReader reader = new FileReader("users.json");
        	JSONArray jsonObject = (JSONArray) parser.parse(reader);
            JSONObject data = (JSONObject) parser.parse(jsonString);
            for (Object aData : jsonObject) {
            	JSONObject parseData = (JSONObject) aData;
            	if (parseData.get("userName").equals(data.get("patient"))){
            		return parseData.toJSONString();
            	}
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "ERROR";
	}
	
	@SuppressWarnings("unchecked")
	public String getAppointments(String jsonString) throws IOException {
		JSONParser parser = new JSONParser();
		JSONArray appointments = new JSONArray();
        try {
        	FileReader reader = new FileReader("appointments.json");
        	JSONArray jsonObject = (JSONArray) parser.parse(reader);
            JSONObject data = (JSONObject) parser.parse(jsonString);
            for (Object aData : jsonObject) {
            	JSONObject parseData = (JSONObject) aData;
            	if (parseData.get("patient").equals(data.get("patient"))){
            		appointments.add(parseData);
            	}
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return appointments.toJSONString();
	}

	@SuppressWarnings("deprecation")
	public void run() {
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.REP);
		socket.bind("tcp://127.0.0.1:5561");
		
        while (!Thread.currentThread().isInterrupted()) {

            String reply = socket.recvStr(0);
            String[] message = reply.split(",", 2);
            
            String reqest = message[0];
            String data = message[1];
            String result = "";
            if (reqest.equals("BOOK_APPOINTMENT")) {
            	try {
					result = this.bookAppointment(data);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if (reqest.equals("USER_LOGIN")) {
				try {
					result = this.userLogin(data);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
            }
            if (reqest.equals("GET_APPOINTMENTS")) {
            	try {
            		result = this.getAppointments(data);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            if (reqest.equals("GET_USER_BY_USERNAME")) {
            	try {
            		result = this.getUserByUserName(data);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            this.delay();
            socket.send(result);
        }

        socket.close();
        context.term();
		
	}

	private void delay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
