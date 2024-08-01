package com.isigntech.ResponseDto;

import java.sql.Time;
import java.util.Date;

import com.isigntech.Model.Status;

import lombok.Data;

@Data
public class ComplaintFormResponseDTO {
	long complaintId;
	String firstName;
	String lastName;
	long contactNumber;
	String email;
	private Date date;
	private Time time;
	String description;
	String action;
	String typeComplaint;
	byte[] supportingDocument;
	String othersInvolved;
    String location;
	Status status;

	

}
