package id.digitallending.publishers.core.application.port.out;

public interface SendOtpByPhonePort {
    boolean sendingOtp(String phoneNumber, String message);
}
