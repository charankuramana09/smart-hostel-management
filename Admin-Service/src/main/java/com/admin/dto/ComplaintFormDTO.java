package com.admin.dto;
import java.sql.Time;
import java.util.Date;

import com.admin.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintFormDTO {
	
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
