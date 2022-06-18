package id.digitallending.publishers.infrastructure.otp;

import feign.Response;
import id.digitallending.publishers.core.application.util.Utils;
import id.digitallending.publishers.infrastructure.otp.dto.OtpRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpFeignServiceImpl implements OtpFeignService {

    private final OtpFeignClient otpFeignClient;

    @Override
    public boolean sendOtp(String phoneNumber, String message) {
        OtpRequestDto otpRequestDto = new OtpRequestDto();
        otpRequestDto.setMessage(message);
        otpRequestDto.setPhoneNumber(phoneNumber);
        Response response = otpFeignClient.requestOtp(otpRequestDto);
        log.info("Response : {}",response);
        log.info("Response : {}",response.status());
        if (response.status() >= 200 && response.status() < 300 ) {
//            String data="";
//            try {
//                 data = IOUtils.toString(response.body().asInputStream());
//            }catch (IOException e){
//                log.error("Error : {}",e);
//            }
//            String msg = response.body().toString();
//
//            log.info("Msg : {} %s, {}",msg, response.body(),data);
//            return Utils.getRC(msg);
            return true;
        }
        return false;
    }
}
