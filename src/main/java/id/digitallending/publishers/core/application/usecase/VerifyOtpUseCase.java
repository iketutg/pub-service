package id.digitallending.publishers.core.application.usecase;

import id.digitallending.publishers.infrastructure.web.dto.GeneralResponseDto;
import id.digitallending.publishers.infrastructure.web.dto.VerifyOtpDto;

public interface VerifyOtpUseCase {
    GeneralResponseDto verifyOtp(VerifyOtpDto verifyOtpDto);
}
