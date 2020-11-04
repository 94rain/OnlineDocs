package top.jisy.docs.entity;

import java.io.Serializable;

/**
 * 返回信息实体类
 */
public class Result implements Serializable {

    private Integer code;//返回状态码

    private Boolean flag;//是否成功

    private String message;//返回信息

    private Object data;//后台返回业务数据

    public Result() {
    }

    public Result(Boolean flag, Integer code, String message) {
        this.code = code;
        this.flag = flag;
        this.message = message;
    }

    public Result(Boolean flag, Integer code, String message, Object data) {
        this.code = code;
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
