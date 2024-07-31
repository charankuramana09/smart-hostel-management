package com.isigntech.Model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	long complaintId;
	String firstName;
	String lastName;
	long contactNumber;
	String email;

	private Date date;
	private Time time;
	int roomNumber;
	String description;
	String action;
	String typeComplaint;
	@Lob
	@Column(name = "supporting_Document", columnDefinition = "LONGBLOB")
	byte[] supportingDocument;
	String whoInvolvedInTheIncident;

	Status status;

}
