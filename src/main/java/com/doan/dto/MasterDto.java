package com.doan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterDto implements Serializable {
    private Long masterId;
    private String masterName;
    private String startDate;
    private String endDate;
    private Long statusDepartment;
    private List<MasterDetailDTO> masterDetailDTOS;
}
