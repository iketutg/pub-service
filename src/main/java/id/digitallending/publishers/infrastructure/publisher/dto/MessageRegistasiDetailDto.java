package id.digitallending.publishers.infrastructure.publisher.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import id.digitallending.publishers.infrastructure.web.dto.RegistrasiEformDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"@class"})
public class MessageRegistasiDetailDto implements Serializable {
    private SpanDto spanDto;
    //private VerifyOtpDto verifyOtpDto;
    private RegistrasiEformDto registrasiEformDto;
}
