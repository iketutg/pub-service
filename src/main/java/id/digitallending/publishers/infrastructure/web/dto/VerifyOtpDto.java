package id.digitallending.publishers.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties({"@class"})
public class VerifyOtpDto {

    @NotNull(message = "The page is required.")
    @NotEmpty(message = "Nik Not Empty")
    @Size(min = 4, message = "Nik should have at least 4 characters ")
    private String nik;

    @javax.validation.constraints.Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @NotNull(message = "The npwp is required.")
    @Size(min = 4, message = "npwp should have at least 4 characters ")
    private String npwp;

    @NotNull(message = "The phonenumber is required.")
    @Size(min = 8, message = "Phone should have at least 8 characters ")
    private String phonenumber;

    @NotNull(message = "The refNumber is required.")
    @Size(min = 8, message = "refNumber should have at least 8 characters ")
    private String refNumber;

    @NotNull(message = "The OTP is required.")
    @Size(min = 5, message = "OTP should have 6 characters ")
    private String otp;

}
