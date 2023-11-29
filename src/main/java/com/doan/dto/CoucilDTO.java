package com.doan.dto;

import com.doan.entity.TeacherCoucil;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class CoucilDTO implements Serializable {
    private Long coucilId;
    private String coucilName;
    private String masterName;
    private String subjectName;
    private List<TeacherCoucil> teacherCoucils;
    private List<MasterDetailDTO> masterDetailDTOS;
}
