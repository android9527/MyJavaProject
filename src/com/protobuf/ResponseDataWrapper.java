package com.protobuf;

/**
 * 服务器响应的数据包装
 * <p>
 */
public class ResponseDataWrapper<T> {
    /**
     * {"message":"操作成功","error":0, data: {}}
     */
    private T data;
    private String message;
    private int error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDataWrapper [data=" + data + ", message=" + message + ", error=" + error + "]";
    }

}
