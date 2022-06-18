package id.digitallending.publishers.infrastructure.publisher.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"@class"})
public class SpanDto {
    private String parentID;
    private String traceId;
    private String spanId;
}

