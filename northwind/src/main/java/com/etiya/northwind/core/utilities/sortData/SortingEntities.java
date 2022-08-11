package com.etiya.northwind.core.utilities.sortData;

import org.springframework.data.domain.Sort;

public class SortingEntities {
    public static Sort sortType(String property, String type) {
        if (type.equals("desc"))
            return Sort.by(property).descending();
        else return Sort.by(property).ascending();
    }
}
