package com.InvGenius.InvGenius.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class changePasswordRequest {
    
    private String newPassword;
    private String confirmPassword;
}
