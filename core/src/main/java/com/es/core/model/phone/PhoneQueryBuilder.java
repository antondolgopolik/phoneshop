package com.es.core.model.phone;

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
        StringBuilder builder = new StringBuilder("SELECT phones.* FROM phones INNER JOIN stocks ON phones.id=stocks.phoneId WHERE stocks.stock>0");
        appendRequest(builder, request);
        appendPhoneSortType(builder, phoneSortType);
        appendSortDirection(builder, sortDirection);
        appendOffset(builder, offset);
        appendLimit(builder, limit);
        return builder.toString();
    }

    private void appendRequest(StringBuilder builder, String request) {
        if (request != null) {
            String[] tokens = request.split("\\s");
            builder.append(" AND REGEXP_LIKE(model, '");
            for (String token : tokens) {
                builder.append(token).append('|');
            }
            builder.setCharAt(builder.length() - 1, '\'');
            builder.append(')');
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
