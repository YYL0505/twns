package org.nightschool.model;

/**
 * Created by Thoughtworks on 3/9/15.
 */
public class RequestResult {

    public static final String OK = "ok";
    public static final String FAIL = "fail";
    private String status;
    private String user_name;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String errorMsg;

    public RequestResult(String status, String user_name, String type, String errorMsg) {
        this.status = status;
        this.user_name = user_name;
        this.type = type;
        this.errorMsg = errorMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
