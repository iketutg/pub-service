package id.digitallending.publishers.core.application.usecase;

import id.digitallending.publishers.core.application.port.out.PublisherRedisPort;
import id.digitallending.publishers.core.application.port.out.SetGetRedisPort;
import id.digitallending.publishers.infrastructure.publisher.dto.MessageRegistasiDto;
import id.digitallending.publishers.infrastructure.publisher.dto.SpanDto;
import id.digitallending.publishers.infrastructure.web.dto.GeneralResponseDto;
import id.digitallending.publishers.infrastructure.web.dto.VerifyOtpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class VerifyOtpUseCaseImpl implements VerifyOtpUseCase{

    private final SetGetRedisPort setGetRedisPort;

    private final PublisherRedisPort publisherRedisPort;

    private final Tracer tracer;

    @Override
    public GeneralResponseDto verifyOtp(VerifyOtpDto verifyOtpDto) {
        GeneralResponseDto generalResponseDto = new GeneralResponseDto();
        generalResponseDto.setResCode(400);
        generalResponseDto.setMessage("Gagal OTP");

       if (validateOtp(verifyOtpDto)){
           publishMessage(verifyOtpDto);
           generalResponseDto.setResCode(200);
           generalResponseDto.setMessage("Success, Lanjutkan tahapberikutnya");
           String uuid = UUID.randomUUID().toString().replace("-", "");
           generalResponseDto.setRefNum(uuid);
           //Todo Save Data to DB by Ref / uuid
           //Todo Record Status (Blob / Json )   ===> { "PunyaPinjam":false , "Adatunggakan": True }
       }
       return generalResponseDto ;
    }

    private boolean validateOtp(VerifyOtpDto verifyOtpDto){
        String key = verifyOtpDto.getPhonenumber()+":"+verifyOtpDto.getRefNumber() ;
        String data = (String) setGetRedisPort.getData(key,0);
        if (! data.isEmpty()  ){
            if (data.equals(verifyOtpDto.getOtp())) {
                return true;
            }
        }
        return false;
    }

    private void publishMessage(VerifyOtpDto verifyOtpDto){
        Span span = tracer.currentSpan();
        SpanDto spanDto = new SpanDto();
        spanDto.setSpanId( span.context().spanId());
        spanDto.setParentID(span.context().parentId());
        spanDto.setTraceId(span.context().traceId());
        MessageRegistasiDto messageRegistasiDto = MessageRegistasiDto.builder()
                .verifyOtpDto(verifyOtpDto)
                .spanDto(spanDto)
                .build();
        publisherRedisPort.sendMessage("ikg:info1",messageRegistasiDto);
        //todo save Db
    }
}
