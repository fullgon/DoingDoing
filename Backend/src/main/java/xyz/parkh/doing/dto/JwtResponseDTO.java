package xyz.parkh.doing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtResponseDTO {
    private String jwt;
    ResultDTO result;
}
