package com.isigntech.Service;

import java.util.List;
import java.util.Map;

import com.isigntech.ResponseDto.ComplaintFormResponseDTO;

public interface ComplaintFormService {
	
	public ComplaintFormResponseDTO raiseComplaint(ComplaintFormResponseDTO complaintFormResponseDTO);
	
	public ComplaintFormResponseDTO getById(long complaintId);

	ComplaintFormResponseDTO patchComplaintFormResponseDTO(long complaintId, Map<String, Object> updates);
	public List<ComplaintFormResponseDTO> getAll();
	
}
