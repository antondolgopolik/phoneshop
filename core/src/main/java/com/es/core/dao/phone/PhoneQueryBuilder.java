package com.es.core.dao.phone;

public class PhoneQueryBuilder {
    private String request;
    private PhoneSortType phoneSortType;
    private SortDirection sortDirection;
    private Integer offset;
    private Integer limit;

    public void setRequest(String request) {
        this.request = request;
    }

    public void setPhoneSortType(PhoneSortType phoneSortType) {
        this.phoneSortType = phoneSortType;
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
                         INNER JOIN phone2color p2c on p.id = p2c.phoneId
                         INNER JOIN colors c on c.id = p2c.colorId
                         INNER JOIN stocks s on p.id = s.phoneId
                WHERE s.stock > 0
                """;
        StringBuilder builder = new StringBuilder(sql);
        appendRequest(builder, request);
        builder.append(" GROUP BY p.id");
        appendPhoneSortType(builder, phoneSortType);
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

    private void appendPhoneSortType(StringBuilder builder, PhoneSortType phoneSortType) {
        if (phoneSortType != null) {
            builder.append(" ORDER BY ").append(phoneSortType.toString().toLowerCase());
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
