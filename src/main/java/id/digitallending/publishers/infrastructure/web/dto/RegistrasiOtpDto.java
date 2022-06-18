package id.digitallending.publishers.infrastructure.web.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegistrasiOtpDto {

//    @NotNull(message = "The page is required.")
//    @NotEmpty(message = "Nik Not Empty")
//    @Size(min = 4, message = "Nik should have at least 4 characters ")
//    private String Nik;
//
//    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
//    private String Email;
//
//    @NotNull(message = "The npwp is required.")
//    @Size(min = 4, message = "npwp should have at least 4 characters ")
//    private String npwp;

    @NotNull(message = "The phonenumber is required.")
    @Size(min = 8, message = "Phone should have at least 8 characters ")
    private String phonenumber;

//    @NotNull(message = "The requestTime is required.")
//    @Size(min = 8, message = "Phone should have at least 8 characters ")
//    private String requestTime;

}
