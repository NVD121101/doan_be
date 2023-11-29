package com.doan.payload;

import com.doan.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserByIdRespon {
    private int code;
    private String message;
    private User user;
}
