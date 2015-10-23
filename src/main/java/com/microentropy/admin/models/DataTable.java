package com.microentropy.admin.models;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-04-05
 */
public class DataTable<T> {
    Integer draw;
    Integer recordsTotal;
    Integer recordsFiltered;
    T data;

    public DataTable() {
    }

    public DataTable(Integer draw, Integer recordsTotal, Integer recordsFiltered, T data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public Integer getDraw() {
        return draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public T getData() {
        return data;
    }
}
