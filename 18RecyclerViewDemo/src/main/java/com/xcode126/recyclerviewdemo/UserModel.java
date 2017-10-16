package com.xcode126.recyclerviewdemo;

/**
 * Author：sky on 2017/9/6 18:47
 * Email：xcode126@126.com
 */

public class UserModel {


    /**
     * code : 1
     * msg : success
     * data : {"c_id":"1","c_title":"标题"}
     * num :
     */

    private int code;
    private String msg;
    private DataBean data;
    private String num;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public static class DataBean {
        /**
         * c_id : 1
         * c_title : 标题
         */

        private String c_id;
        private String c_title;

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
            this.c_id = c_id;
        }

        public String getC_title() {
            return c_title;
        }

        public void setC_title(String c_title) {
            this.c_title = c_title;
        }
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", num='" + num + '\'' +
                '}';
    }
}
