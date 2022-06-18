package id.digitallending.publishers.core.application.usecase;

import id.digitallending.publishers.infrastructure.web.dto.GeneralResponseDto;
import id.digitallending.publishers.infrastructure.web.dto.RegistrasiOtpDto;

public interface RegistrasiOtpUseCase {
    GeneralResponseDto registrasiRequestOtp(RegistrasiOtpDto registrasiOtpDto);
}
