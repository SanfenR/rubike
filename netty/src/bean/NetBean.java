package bean;

import com.alibaba.fastjson.JSONObject;

public class NetBean {
    private int type;

    private String netData;

    private String requestId;

    private String errorCode;

    private String errorMsg;

    private boolean success;

    //adb通讯暂时没用到这个字段
    private String session;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNetData() {
        return netData;
    }

    public void setNetData(String netData) {
        this.netData = netData;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


}
