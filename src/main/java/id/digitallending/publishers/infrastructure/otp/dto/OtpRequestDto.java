package id.digitallending.publishers.infrastructure.otp.dto;

import lombok.Data;

@Data
public class OtpRequestDto {
    private String phoneNumber;
    private String message;
}
