package com.isigntech.Service;

import com.isigntech.ResponseDto.ComplaintFormResponseDTO;

public interface ComplaintFormService {
	
	public ComplaintFormResponseDTO raiseComplaint(ComplaintFormResponseDTO complaintFormResponseDTO);
	
	public ComplaintFormResponseDTO getById(long complaintId);
	
	 ComplaintFormResponseDTO updateUserComplaint(ComplaintFormResponseDTO complaintFormResponseDTO, long complaintId);

}
