package id.digitallending.publishers.infrastructure.web.dto;

import lombok.Builder;
import lombok.Data;

/**
 * The type Error dto.
 */
@Data
@Builder
public class ErrorDTO {

    /**
     * The Error code.
     */
    private String resCode;

    /**
     * The Message.
     */
    private String message;

}
