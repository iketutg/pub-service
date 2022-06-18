package id.digitallending.publishers.infrastructure.web.controller.v1;

import id.digitallending.publishers.core.application.usecase.CekStatusPengajuanUseCase;
import id.digitallending.publishers.core.application.usecase.RegistrasiDetailUseCase;
import id.digitallending.publishers.core.application.usecase.RegistrasiOtpUseCase;
import id.digitallending.publishers.core.application.usecase.VerifyOtpUseCase;
import id.digitallending.publishers.infrastructure.web.dto.RegistrasiEformDto;
import id.digitallending.publishers.infrastructure.web.dto.RegistrasiOtpDto;
import id.digitallending.publishers.infrastructure.web.dto.VerifyOtpDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/eform")
public class EFormController {

    @Autowired
    private RegistrasiOtpUseCase registrasiOtpUseCase;

    @Autowired
    private VerifyOtpUseCase verifyOtpUseCase;

    @Autowired
    private RegistrasiDetailUseCase registrasiDetailUseCase;

    @Autowired
    private CekStatusPengajuanUseCase cekStatusPengajuanUseCase;

//    @ApiOperation(value = "Get OTP , 1st Eform Registrastion")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully Request OTP, Check your OTP on your message"),
//            @ApiResponse(code = 404, message = "Resource not found", response = ErrorDTO.class),
//            @ApiResponse(code = 400, message = "Bad request / Invalid parameters", response = ErrorDTO.class),
//            @ApiResponse(code = 500, message = "Internal server error", response = ErrorDTO.class)
//    })
    //Verifikasi NoHP
    @PostMapping("/otp/req")
    public ResponseEntity<?> requestOtp(@Valid @RequestBody RegistrasiOtpDto registrasiOtpDto){
        return ResponseEntity.ok(registrasiOtpUseCase.registrasiRequestOtp(registrasiOtpDto));
    }

    @PostMapping("/otp/verify")
    public ResponseEntity<?> verifyOtp(@Valid @RequestBody VerifyOtpDto otpDto) {
        return ResponseEntity.ok(verifyOtpUseCase.verifyOtp(otpDto));
    }

    @PostMapping("/form/registrasi")
    public ResponseEntity<?> registrasi(@Valid @RequestBody RegistrasiEformDto registrasiEformDto){
        return ResponseEntity.ok(registrasiDetailUseCase.registrasiDetail(registrasiEformDto));
    }

    @GetMapping("/form/cekstatus/{phoneNumber}")
    public ResponseEntity<?> cekStatus(@PathVariable("phoneNumber") String phoneNumber){
        return ResponseEntity.ok(cekStatusPengajuanUseCase.cekStatus(phoneNumber));
    }
}
