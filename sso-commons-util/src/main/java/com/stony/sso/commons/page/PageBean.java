package com.stony.sso.commons.page;


import java.io.Serializable;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/13 </p>
 * <p>Time: 14:31 </p>
 * <p>Version: 1.0 </p>
 */
public class PageBean implements Serializable {

    /**
     * 增加pageSizeZero属性，
     * 默认值为false，
     * 使用默认值时不需要增加该配置，
     * 需要设为true时，需要配置该参数。
     * 当该参数设置为true时，
     * 如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是Page类型）。
     */

    private int start;
    private int pageSize = 20;
    private int pageNum = 1;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getStart() {
        return start;
    }
    public void setStart(int pageNum,int pageSize) {
        this.start = pageSize * (pageNum - 1);
    }
}
