package id.digitallending.publishers.core.application.usecase;

import id.digitallending.publishers.core.application.port.out.SendOtpByPhonePort;
import id.digitallending.publishers.core.application.port.out.SetGetRedisPort;
import id.digitallending.publishers.core.application.util.Utils;
import id.digitallending.publishers.infrastructure.web.dto.GeneralResponseDto;
import id.digitallending.publishers.infrastructure.web.dto.RegistrasiOtpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrasiOtpUseCaseImpl implements RegistrasiOtpUseCase{

    private final SendOtpByPhonePort sendOtpByPhonePort;

    private final SetGetRedisPort setGetRedisPort;



    @Override
    public GeneralResponseDto registrasiRequestOtp(RegistrasiOtpDto registrasiOtpDto) {
        GeneralResponseDto generalResponseDto = new GeneralResponseDto();
        generalResponseDto.setResCode(400);
        generalResponseDto.setMessage("Gagal OTP");
        String token = Utils.getToken();

        String message = "Token Anda adalah " + token + " Jaga Rahasia token anda";
        if (sendOtpByPhonePort.sendingOtp(registrasiOtpDto.getPhonenumber(), message)) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String key = registrasiOtpDto.getPhonenumber()+":"+uuid ;

            setGetRedisPort.setDataTtl(key,0, token,60);

            generalResponseDto.setResCode(200);
            generalResponseDto.setMessage("Success , Cek your OTP");
            generalResponseDto.setRefNum(uuid);
        }
        return generalResponseDto;
    }

}
