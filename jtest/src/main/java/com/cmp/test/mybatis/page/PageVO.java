package com.cmp.test.mybatis.page;

/**
 * Created by YANLL on 2016/06/06.
 */
public class PageVO {
    private Integer page;
    private Integer limit;
    private Integer pages;
    private Integer total;

    public PageVO() {
    }

    public PageVO(Integer page, Integer limit) {
        this.page = page == null || page <= 0 ? 1 : page;
        this.limit = limit == null || limit <= 0 ? 20 : limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
