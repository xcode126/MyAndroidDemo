package com.xcode126.asyncclient.net;

import java.io.Serializable;

/**
 * Created by sky on 2017/3/9.
 * 通用的请求结果返回处理类
 */

public class ExecResult<T> implements Serializable {

    private static final long serialVersionUID = -8895005939818095491L;
    public boolean success;
    public String errorMsg;
    public String errorType;
    public int code;
    public T result;

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "ExecResult{" +
                "success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                ", errorType='" + errorType + '\'' +
                ", code=" + code +
                ", result=" + result +
                '}';
    }
}
