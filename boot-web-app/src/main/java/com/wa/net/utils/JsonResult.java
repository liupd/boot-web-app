package com.wa.net.utils;

/**
 * Created by liupd on 16-2-22.
 **/
public class JsonResult {

    AjaxStatus status;

    String resultstr;

    Object bean;

    public JsonResult(AjaxStatus status){
        this.status=status;
    }

    public AjaxStatus getStatus() {
        return status;
    }

    public void setStatus(AjaxStatus status) {
        this.status = status;
    }

    public String getResultstr() {
        return resultstr;
    }

    public void setResultstr(String resultstr) {
        this.resultstr = resultstr;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
