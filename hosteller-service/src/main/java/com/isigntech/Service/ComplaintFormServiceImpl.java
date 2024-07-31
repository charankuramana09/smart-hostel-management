package com.isigntech.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isigntech.Model.ComplaintForm;
import com.isigntech.Repository.ComplaintFormRepository;
import com.isigntech.ResponseDto.ComplaintFormResponseDTO;


@Service
public class ComplaintFormServiceImpl implements ComplaintFormService {
	
	@Autowired
	ComplaintFormRepository complaintFormRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public ComplaintFormResponseDTO raiseComplaint(ComplaintFormResponseDTO complaintFormResponseDTO) {
		
		ComplaintForm complaintForm = modelMapper.map(complaintFormResponseDTO, ComplaintForm.class);
		complaintForm=complaintFormRepository.save(complaintForm);
		
		ComplaintFormResponseDTO complaintFormResponseDTO2 = modelMapper.map(complaintForm,ComplaintFormResponseDTO.class);
		
		
		return complaintFormResponseDTO2;
	}

	@Override
	public ComplaintFormResponseDTO getById(long complaintId) {
	ComplaintForm complaintForm	=complaintFormRepository.findById(complaintId).get();
	ComplaintFormResponseDTO complaintFormResponseDTO = modelMapper.map(complaintForm, ComplaintFormResponseDTO.class);
		return complaintFormResponseDTO;
	}

	@Override
	 public ComplaintFormResponseDTO updateUserComplaint(ComplaintFormResponseDTO complaintFormResponseDTO, long complaintId) {
        ComplaintForm updateComplaintForm = complaintFormRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("ComplaintForm not found"));

      

        // Update the fields
//        updateComplaintForm.setFirstName(complaintFormResponseDTO.getFirstName());
//        updateComplaintForm.setLastName(complaintFormResponseDTO.getLastName());
//        updateComplaintForm.setContactNumber(complaintFormResponseDTO.getContactNumber());
//        updateComplaintForm.setEmail(complaintFormResponseDTO.getEmail());
//        updateComplaintForm.setDate(complaintFormResponseDTO.getDate());
//        updateComplaintForm.setTime(complaintFormResponseDTO.getTime());
//        updateComplaintForm.setRoomNumber(complaintFormResponseDTO.getRoomNumber());
//        updateComplaintForm.setDescription(complaintFormResponseDTO.getDescription());
//        updateComplaintForm.setAction(complaintFormResponseDTO.getAction());
//        updateComplaintForm.setTypeComplaint(complaintFormResponseDTO.getTypeComplaint());
//        updateComplaintForm.setSupportingDocument(complaintFormResponseDTO.getSupportingDocument());
//        updateComplaintForm.setWhoInvolvedInTheIncident(complaintFormResponseDTO.getWhoInvolvedInTheIncident());
        updateComplaintForm.setStatus(complaintFormResponseDTO.getStatus());

        ComplaintForm updatedForm = complaintFormRepository.save(updateComplaintForm);
        ComplaintFormResponseDTO formResponseDTO = modelMapper.map(updatedForm, ComplaintFormResponseDTO.class);

        return formResponseDTO;
    }
	
	


}
