package com.admin.service;

import com.admin.dto.ComplaintFormDTO;

public interface ComplaintFormService {

	public ComplaintFormDTO raiseComplaint(ComplaintFormDTO complaintFormResponseDTO);

	public ComplaintFormDTO getById(long complaintId);

	public ComplaintFormDTO updateUserComplaint(ComplaintFormDTO complaintFormDTO, long complaintId);

}
