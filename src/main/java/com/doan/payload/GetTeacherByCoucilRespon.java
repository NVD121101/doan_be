package com.doan.payload;

import com.doan.dto.TeacherDTO;
import com.doan.entity.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class GetTeacherByCoucilRespon {
    private int code;
    private String message;
    private String coucilName;
    private int totalRecords;
    private List<TeacherDTO> teachers;
}
