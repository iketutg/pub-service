package id.digitallending.publishers.infrastructure.configuration;

import feign.Request;
import feign.Response;
import feign.Util;
import feign.slf4j.Slf4jLogger;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static feign.Util.*;

@Slf4j
public class CustomFeignLogger extends Slf4jLogger {
    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        FeignRequest feignRequest = new FeignRequest();
        feignRequest.setMethod(request.httpMethod().toString());
        feignRequest.setUrl(request.url());

        if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
            for (String field : request.headers().keySet()) {
                for (String value : valuesOrEmpty(request.headers(), field)) {
                    feignRequest.addHeader(field, value);
                }
            }

            if (request.body() != null && logLevel.ordinal() >= Level.FULL.ordinal()) {
                feignRequest.setBody(request.charset() != null ? new String(request.body(), request.charset()) : "Binary data");
            }
        }

        log.info(String.format("[%s] == >> Request : %s ", configKey, feignRequest));
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        FeignResponse feignResponse = new FeignResponse();
        int status = response.status();
        feignResponse.setStatus(response.status());
        feignResponse.setReason(response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ? " " + response.reason() : "");
        feignResponse.setTimeTaken(elapsedTime);

        if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
            for (String field : response.headers().keySet()) {
                for (String value : valuesOrEmpty(response.headers(), field)) {
                    feignResponse.addHeader(field, value);
                }
            }

            if (response.body() != null && !(status == 204 || status == 205)) {
                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                if (logLevel.ordinal() >= Level.FULL.ordinal() && bodyData.length > 0) {
                    feignResponse.setBody(decodeOrDefault(bodyData, UTF_8, "Binary data"));
                }
                log.info(String.format("%s == >> Response : %s ", configKey, feignResponse));
                return response.toBuilder().body(bodyData).build();
            } else {
                log.info(String.format("%s == >> Response : %s ", configKey, feignResponse));
            }

            //response.body?.close();
        }
        return response;
    }

    @Setter
    private class FeignResponse {
        private int status;
        private String reason;
        private long timeTaken;
        private List<String> headers;
        private String body;

        public void addHeader(String key, String value) {
            if (headers == null) {
                headers = new ArrayList<>();
            }
            headers.add(String.format("%s: %s", key, value));
        }

        @Override
        public String toString() {
            return String.format("Status = %s, Reason = %s, TimeTaken = %s, Headers = %s Body = %s, BodyLength = %s Bytes",
                    status, reason, timeTaken, headers, body, (body != null && body.trim().length() > 0 ? body.length() : 0));
        }
    }

    @Setter
    private class FeignRequest {
        private String method;
        private String url;
        private List<String> headers;
        private String body;

        public void addHeader(String key, String value) {
            if (headers == null) {
                headers = new ArrayList<>();
            }
            headers.add(String.format("%s: %s", key, value));
        }

        @Override
        public String toString() {
            return String.format("Method = %s, url = %s, Headers = %s Body = %s, BodyLength = %s Bytes",
                    method, url, headers, body, (body != null && body.trim().length() > 0 ? body.length() : 0));
        }
    }
}