package com.isigntech.Model;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ComplaintForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long complaintId;
	private String firstName;
	private String lastName;
	private long contactNumber;
	private String email;

	private Date date;  
	private Time time;
	private String description;
	private String action;
	private String typeComplaint;
	@Lob
	@Column(name = "supporting_Document", columnDefinition = "LONGBLOB")
	private byte[] supportingDocument;
	private String othersInvolved;
    private String location;
	private Status status;

}
