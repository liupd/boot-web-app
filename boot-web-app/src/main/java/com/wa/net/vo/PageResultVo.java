package com.wa.net.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupd on 16-3-28.
 **/
public class PageResultVo<T> implements Serializable {

    /**
     * 每页显示条数
     **/
    private int rowNum;
    /**
     * 当前页
     **/
    private int currentPage;
    /**
     * 存放结果集
     **/
    private List result=new ArrayList();
    /**
     * 总数
     **/
    private Integer total;
    /**
     * 总页数
     **/
    private int pages;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
