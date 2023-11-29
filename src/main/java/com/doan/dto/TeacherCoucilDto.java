package com.doan.dto;

import com.doan.entity.Coucil;
import com.doan.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCoucilDto implements Serializable {
    private Long teacherCoucliId;
    private Coucil coucil;
    private Teacher teacher;
}
