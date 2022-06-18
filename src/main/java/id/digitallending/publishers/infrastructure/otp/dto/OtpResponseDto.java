package id.digitallending.publishers.infrastructure.otp.dto;

import lombok.Data;

@Data
public class OtpResponseDto {
    private int responseCode;
    private String message;
    private String refNum;
}
