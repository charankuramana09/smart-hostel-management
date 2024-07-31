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
	
	


}
