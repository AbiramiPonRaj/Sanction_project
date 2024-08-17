package com.ponsun.san.algorithm.dto;

import com.ponsun.san.dto.RecordDTO;
import com.ponsun.san.dto.SearchDTO;
import lombok.Data;

import java.util.List;

@Data
public class CustomerScoringResultMessageDTO {
    private SearchDTO searchDTO;
    private List<RecordDTO> recordDTO;
    private CustomerScoringResultMessageDTO() {

    }

    private CustomerScoringResultMessageDTO(SearchDTO searchDTO, List<RecordDTO> recordDTO) {
        this.searchDTO = searchDTO;
        this.recordDTO = recordDTO;
    }

    public static CustomerScoringResultMessageDTO getInstance(SearchDTO searchDTO, List<RecordDTO> recordDTO) {
        return new CustomerScoringResultMessageDTO(searchDTO, recordDTO);
    }
}
