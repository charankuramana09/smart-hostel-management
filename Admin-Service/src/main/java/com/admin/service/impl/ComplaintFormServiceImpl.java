package com.admin.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dto.ComplaintFormDTO;
import com.admin.entity.ComplaintForm;
import com.admin.repository.ComplaintFormRepository;
import com.admin.service.ComplaintFormService;


@Service
public class ComplaintFormServiceImpl implements ComplaintFormService {
	
	@Autowired
	ComplaintFormRepository complaintFormRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public ComplaintFormDTO raiseComplaint(ComplaintFormDTO ComplaintFormDTO) {
		
		ComplaintForm complaintForm = modelMapper.map(ComplaintFormDTO, ComplaintForm.class);
		complaintForm=complaintFormRepository.save(complaintForm);
		
		ComplaintFormDTO ComplaintFormDTO2 = modelMapper.map(complaintForm,ComplaintFormDTO.class);
		
		
		return ComplaintFormDTO2;
	}

	@Override
	public ComplaintFormDTO getById(long complaintId) {
	ComplaintForm complaintForm	=complaintFormRepository.findById(complaintId).get();
	ComplaintFormDTO ComplaintFormDTO = modelMapper.map(complaintForm, ComplaintFormDTO.class);
		return ComplaintFormDTO;
	}
	
	
	 public ComplaintFormDTO updateUserComplaint(ComplaintFormDTO complaintFormDTO, long complaintId) {
       ComplaintForm updateComplaintForm = complaintFormRepository.findById(complaintId)
               .orElseThrow(() -> new RuntimeException("ComplaintForm not found"));

     

       // Update the fields
//       updateComplaintForm.setFirstName(complaintFormResponseDTO.getFirstName());
//       updateComplaintForm.setLastName(complaintFormResponseDTO.getLastName());
//       updateComplaintForm.setContactNumber(complaintFormResponseDTO.getContactNumber());
//       updateComplaintForm.setEmail(complaintFormResponseDTO.getEmail());
//       updateComplaintForm.setDate(complaintFormResponseDTO.getDate());
//       updateComplaintForm.setTime(complaintFormResponseDTO.getTime());
//       updateComplaintForm.setRoomNumber(complaintFormResponseDTO.getRoomNumber());
//       updateComplaintForm.setDescription(complaintFormResponseDTO.getDescription());
//       updateComplaintForm.setAction(complaintFormResponseDTO.getAction());
//       updateComplaintForm.setTypeComplaint(complaintFormResponseDTO.getTypeComplaint());
//       updateComplaintForm.setSupportingDocument(complaintFormResponseDTO.getSupportingDocument());
//       updateComplaintForm.setWhoInvolvedInTheIncident(complaintFormResponseDTO.getWhoInvolvedInTheIncident());
       updateComplaintForm.setStatus(complaintFormDTO.getStatus());

       ComplaintForm updatedForm = complaintFormRepository.save(updateComplaintForm);
       ComplaintFormDTO formResponseDTO = modelMapper.map(updatedForm, ComplaintFormDTO.class);

       return formResponseDTO;
   }

	
	
	


}
