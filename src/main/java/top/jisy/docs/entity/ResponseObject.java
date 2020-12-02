package top.jisy.docs.entity;


import javax.ws.rs.core.Response;

public class ResponseObject {

    private Status status;

    private String message;

    private Object data;

    public ResponseObject(Response.Status status, String message, Object data) {
        this.status = new Status(status);
        this.message = message;
        this.data = data;
    }

    public ResponseObject(Status status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseObject(Object data) {
        this.data = data;
    }

    public ResponseObject(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseObject success(Object data) {
        ResponseObject response = new ResponseObject(data);
        response.setStatus(new Status(Response.Status.OK));
        response.setData(data);
        return response;
    }

    public static ResponseObject success(String message) {
        ResponseObject response = new ResponseObject(new Status(Response.Status.OK), message);
        return response;
    }

    public static ResponseObject success(Object data, String message) {
        ResponseObject response = new ResponseObject(new Status(Response.Status.OK), message, data);
        return response;
    }

    public static ResponseObject fail(Response.Status status, String message) {
        ResponseObject response = new ResponseObject(new Status(status), message);
        return response;
    }
}
