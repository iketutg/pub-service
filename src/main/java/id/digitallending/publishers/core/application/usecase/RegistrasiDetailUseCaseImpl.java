package id.digitallending.publishers.core.application.usecase;

import id.digitallending.publishers.core.application.port.out.PublisherRedisPort;
import id.digitallending.publishers.infrastructure.publisher.dto.MessageRegistasiDetailDto;
import id.digitallending.publishers.infrastructure.publisher.dto.SpanDto;
import id.digitallending.publishers.infrastructure.web.dto.GeneralResponseDto;
import id.digitallending.publishers.infrastructure.web.dto.RegistrasiEformDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrasiDetailUseCaseImpl implements RegistrasiDetailUseCase{

    private final PublisherRedisPort publisherRedisPort;

    private final Tracer tracer;

    @Override
    public GeneralResponseDto registrasiDetail(RegistrasiEformDto registrasiEformDto) {
        GeneralResponseDto generalResponseDto = new GeneralResponseDto();
        generalResponseDto.setResCode(400);
        generalResponseDto.setMessage("No Ref , available , please try again");
        //todo get Data in db by Ref
        //registrasiEformDto.getRefNumber()
        // todo updated data in db by ref
        // todo publish
        // todo response
        boolean dataAvailable = true;
        if (dataAvailable) {
            publishMessage(registrasiEformDto);
            generalResponseDto.setResCode(200);
            generalResponseDto.setMessage("Pengajuan dalam Proses , Terimakasih");
            String uuid = UUID.randomUUID().toString().replace("-", "");
            generalResponseDto.setRefNum(uuid);
        }

        return generalResponseDto;

    }

    private void publishMessage(RegistrasiEformDto registrasiEformDto){
        Span span = tracer.currentSpan();
        SpanDto spanDto = new SpanDto();
        spanDto.setSpanId( span.context().spanId());
        spanDto.setParentID(span.context().parentId());
        spanDto.setTraceId(span.context().traceId());
        MessageRegistasiDetailDto msg = MessageRegistasiDetailDto.builder()
                .registrasiEformDto(registrasiEformDto)
                .spanDto(spanDto)
                .build();
        publisherRedisPort.sendMessage("ikg:info2",msg);
        //todo save Db
    }
}
