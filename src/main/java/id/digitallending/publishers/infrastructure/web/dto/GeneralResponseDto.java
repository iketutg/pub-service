package id.digitallending.publishers.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeneralResponseDto {
    private int resCode;
    private String message;
    private String refNum;
}
