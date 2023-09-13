package com.github.dhslrl321.pagination.persistence;

import lombok.Value;

@Value(staticConstructor = "of")
public class PageRequest {
    int pageNumber;
    int pageSize;

    public int getLimit() {
        return pageSize;
    }

    public int getOffset() {
        return pageSize * (pageNumber - 1);
    }
}
