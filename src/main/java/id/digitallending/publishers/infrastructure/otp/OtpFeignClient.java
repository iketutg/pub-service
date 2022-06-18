package id.digitallending.publishers.infrastructure.otp;

import feign.Response;
import id.digitallending.publishers.infrastructure.configuration.GeneralConfiguration;
import id.digitallending.publishers.infrastructure.otp.dto.OtpRequestDto;
import id.digitallending.publishers.infrastructure.otp.dto.OtpResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(contextId = "otpClientId",name="otpClient", url="${otp.baseurl}", configuration = {GeneralConfiguration.class})
public interface OtpFeignClient {
    @RequestMapping(value = "/api/v1/otp", method = RequestMethod.POST)
    Response requestOtp(@RequestBody OtpRequestDto request);
}
