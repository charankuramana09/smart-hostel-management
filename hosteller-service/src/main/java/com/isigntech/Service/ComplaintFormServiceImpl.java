package com.isigntech.Service;

import java.lang.reflect.Field;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
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
    public ComplaintFormResponseDTO patchComplaintFormResponseDTO(long complaintId, Map<String, Object> updates) {
        ComplaintForm complaintForm = complaintFormRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        updates.forEach((key, value) -> {
            try {
                Field field = ComplaintForm.class.getDeclaredField(key);
                field.setAccessible(true);
                // Special handling for enums
                if (field.getType().isEnum() && value instanceof String) {
                    value = Enum.valueOf((Class<Enum>) field.getType(), (String) value);
                }
                ReflectionUtils.setField(field, complaintForm, value);
            } catch (NoSuchFieldException e) {
                System.err.println("Failed to update field: " + key + " with value: " + value);
            } catch (IllegalArgumentException e) {
                System.err.println("Failed to set field value: " + key + " with value: " + value);
            }
        });

        ComplaintForm updatedComplaintForm = complaintFormRepository.save(complaintForm);
        return mapToDto(updatedComplaintForm);
    }

    private ComplaintFormResponseDTO mapToDto(ComplaintForm complaint) {
        ComplaintFormResponseDTO dto = new ComplaintFormResponseDTO();
        dto.setComplaintId(complaint.getComplaintId());
        dto.setFirstName(complaint.getFirstName());
        dto.setLastName(complaint.getLastName());
        dto.setContactNumber(complaint.getContactNumber());
        dto.setEmail(complaint.getEmail());
        dto.setDate(complaint.getDate());
        dto.setTime(complaint.getTime());
        dto.setDescription(complaint.getDescription());
        dto.setAction(complaint.getAction());
        dto.setTypeComplaint(complaint.getTypeComplaint());
        dto.setSupportingDocument(complaint.getSupportingDocument());
        dto.setOthersInvolved(complaint.getOthersInvolved());
        dto.setLocation(complaint.getLocation());
        dto.setStatus(complaint.getStatus());
        return dto;
    }
	


}
