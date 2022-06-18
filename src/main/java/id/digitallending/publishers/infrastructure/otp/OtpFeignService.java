package id.digitallending.publishers.infrastructure.otp;

public interface OtpFeignService {
    boolean sendOtp(String phoneNumber, String message);
}
