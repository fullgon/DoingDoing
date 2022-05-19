package xyz.parkh.campus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String token;
    private String userId;
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private String roll;
}
