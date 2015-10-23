package com.microentropy.admin.utils;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-04-05
 */
public final class SQLUtil {
    public static StringBuilder wrapCount(StringBuilder query) {
        StringBuilder countQuery = new StringBuilder();
        countQuery.append("SELECT COUNT(*) FROM (");
        countQuery.append(query);
        countQuery.append(") V");
        return countQuery;
    }
}
