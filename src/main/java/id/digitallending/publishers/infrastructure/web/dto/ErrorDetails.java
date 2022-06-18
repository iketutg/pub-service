package id.digitallending.publishers.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ErrorDetails {
//    private Date timestamp;
    private int responseCode;
    private String message;
    //private String details;
    private String refNum;
}
