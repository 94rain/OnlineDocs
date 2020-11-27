package top.jisy.docs.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import top.jisy.docs.constant.ResponseParameters;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ResponseObject {

    @JsonProperty(value = ResponseParameters.HTTP_STATUS, required = true)
    private final Status status;

    @JsonProperty(value = ResponseParameters.MESSAGE, required = true)
    private final String message;

    public ResponseObject(Response.Status status, String message) {
        this.status = new Status(status);
        this.message = message;
    }

    public Response buildResponse() {
        return Response.status(status.getCode()).type(MediaType.APPLICATION_JSON_TYPE).entity(this).build();
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
