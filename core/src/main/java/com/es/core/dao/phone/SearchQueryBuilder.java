package com.es.core.dao.phone;

import com.es.core.services.product.SortType;
import com.es.core.services.product.SortDirection;

public class SearchQueryBuilder {
    private String searchQuery;
    private SortType sortType;
    private SortDirection sortDirection;
    private Integer offset;
    private Integer limit;

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String build() {
        String sql = """
                SELECT p.*, ARRAY_AGG(c.id), ARRAY_AGG(c.code)
                FROM phones p
                         INNER JOIN phone2color p2c on p.id = p2c.phone_id
                         INNER JOIN colors c on c.id = p2c.color_id
                         INNER JOIN stocks s on p.id = s.phone_id
                WHERE s.stock > s.reserved
                """;
        StringBuilder builder = new StringBuilder(sql);
        appendRequest(builder, searchQuery);
        builder.append(" GROUP BY p.id");
        appendSortType(builder, sortType);
        appendSortDirection(builder, sortDirection);
        appendLimit(builder, limit);
        appendOffset(builder, offset);
        return builder.toString();
    }

    private void appendRequest(StringBuilder builder, String request) {
        if (request != null) {
            String[] tokens = request.split("\\s");
            builder.append(" AND model ~* '");
            for (String token : tokens) {
                builder.append(token).append('|');
            }
            builder.setCharAt(builder.length() - 1, '\'');
        }
    }

    private void appendSortType(StringBuilder builder, SortType sortType) {
        if (sortType != null) {
            builder.append(" ORDER BY ").append(sortType.toString().toLowerCase());
        }
    }

    private void appendSortDirection(StringBuilder builder, SortDirection sortDirection) {
        if (sortDirection != null) {
            builder.append(' ').append(sortDirection);
        }
    }

    private void appendOffset(StringBuilder builder, Integer offset) {
        if (offset != null) {
            builder.append(" OFFSET ").append(offset);
        }
    }

    private void appendLimit(StringBuilder builder, Integer limit) {
        if (limit != null) {
            builder.append(" LIMIT ").append(limit);
        }
    }
}
