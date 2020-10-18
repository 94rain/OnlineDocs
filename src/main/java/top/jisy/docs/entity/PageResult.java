package top.jisy.docs.entity;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

    private Long total;//总记录数

    private List<T> rows;//每页列表内容

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

