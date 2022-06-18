package id.digitallending.publishers.infrastructure.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import id.digitallending.publishers.infrastructure.exception.FeignExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FeignCustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();


    @Override
    public Exception decode(String methodKey, Response response) {
        String erroMessage = null;
        Reader reader = null;

        Exception exception = defaultErrorDecoder.decode(methodKey, response);

        if(exception instanceof RetryableException){
            log.error("[RetryableException]");
            return exception;
        }

        if(response.status() == 504){
            //log.info("[RetryableException] ",new RetryableException("504 error", response.request().httpMethod(), null ));
            return new RetryableException(504,"504 error", response.request().httpMethod(), null,response.request() );
        }

        try {

            reader = response.body().asReader(StandardCharsets.UTF_8);
            String result = IOUtils.toString(reader);
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            FeignExceptionMessage exceptionMessage = mapper.readValue(result,
                    FeignExceptionMessage.class);

            erroMessage = exceptionMessage.getMessage();
        }catch (RetryableException re){
            log.error("Retry Excpetion "+ re);
        } catch (IOException e) {
            log.error("IO Exception on reading exception message feign client" + e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                log.error("IO Exception on reading exception message feign client" + e);
            }
        }

        //END DECODING ORIGINAL ERROR MESSAGE

        switch (response.status()) {
            case 400:
                log.error("Error in request went through feign client {} ", erroMessage);
                //handle exception
                return new Exception("Bad Request Through Feign");
            case 401:
                log.error("Error in request went through feign client {} ", erroMessage);
                //handle exception
                return new Exception("Unauthorized Request Through Feign");
            case 404:
                log.error("Error in request went through feign client {} ", erroMessage);
                //handle exception
                return new Exception("Unidentified Request Through Feign");
            default:
                log.error("Error in request went through feign client {} ", erroMessage);
                //handle exception
                return new Exception("Common Feign Exception");
        }
    }
}
