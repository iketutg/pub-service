package id.digitallending.publishers.infrastructure.otp;

import id.digitallending.publishers.core.application.port.out.SendOtpByPhonePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpAdapter implements SendOtpByPhonePort {

    private final OtpFeignService otpFeignService;
    @Override
    public boolean sendingOtp(String phoneNumber, String message) {
        return otpFeignService.sendOtp(phoneNumber, message);
    }
}
