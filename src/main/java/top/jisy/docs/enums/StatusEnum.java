package top.jisy.docs.enums;


public enum StatusEnum {
    /* 成功状态码 */
    SUCCESS(0, "Request is successful"),
    FAIL(1, "Request is failed"),
    TOKEN_INVALID(40001, "Token is null or invalid"),
    ACCESS_DENIED(40003, "Access denied"),
    FAIL4DELETE(50001, "Delete failed"),
    FAIL4UPDATE(50002, "Update failed"),
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad request"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private Integer code;
    private String description;

    StatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer code() {
        return this.code;
    }

    public String description() {
        return this.description;
    }
}
