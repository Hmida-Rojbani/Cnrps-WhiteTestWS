package tn.cnrps.ws.soap.white.services;


import java.time.LocalDateTime;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import tn.cnrps.ws.soap.white.models.whitetest.Exam;
import tn.cnrps.ws.soap.white.models.whitetest.Student;
import tn.cnrps.ws.soap.white.models.whitetest.StudentRequest;
import tn.cnrps.ws.soap.white.models.whitetest.WhiteTestResponse;

@Service
public class WhiteService {
	
	public WhiteTestResponse register(StudentRequest request) {
		
		WhiteTestResponse response = new WhiteTestResponse();
		
		if(request.getStudentId() <= 0)
			response.getBadRequests().add("Student ID not found or not Valid");
		
		try {
			Integer.parseInt(request.getExamCode());
		} catch (NumberFormatException e) {
			response.getBadRequests().add("Exam Code not found or not valid");
		}
		
		if(response.getBadRequests().isEmpty()) {
			Student student = new Student();
			student.setId(request.getStudentId());
			student.setName("TestStudent");
			student.setAddress("TestAddress");
			
			Exam exam = new Exam();
			exam.setCode(request.getExamCode());
			exam.setName("TestExam");
			
			response.setExam(exam);
			response.setStudent(student);
			
			LocalDateTime localDateTime = LocalDateTime.of(2020, 9, 25,14,30);
			 
			XMLGregorianCalendar xmlGregorianCalendar=null;
			try {
				String iso = localDateTime.toString();
				if (localDateTime.getSecond() == 0 && localDateTime.getNano() == 0) {
				    iso += ":00"; // necessary hack because the second part is not optional in XML
				}
				xmlGregorianCalendar =  DatatypeFactory.newInstance().newXMLGregorianCalendar(iso);
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setDate(xmlGregorianCalendar);
		}
		System.out.println(response.getBadRequests());
		return response;
	}

}
