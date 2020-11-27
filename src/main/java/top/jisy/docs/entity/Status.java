package top.jisy.docs.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import top.jisy.docs.constant.ResponseParameters;

import javax.ws.rs.core.Response;

public class Status {

    @JsonProperty(value = ResponseParameters.STATUS_CODE, required = true)
    private final int code;

    @JsonProperty(value = ResponseParameters.STATUS_DESCRIPTION, required = true)
    private final String description;

    public Status(Response.Status status) {
        this.code = status.getStatusCode();
        this.description = status.getReasonPhrase();
    }

    @JsonCreator
    private Status(@JsonProperty(ResponseParameters.STATUS_CODE) int code,
                   @JsonProperty(ResponseParameters.STATUS_DESCRIPTION) String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
