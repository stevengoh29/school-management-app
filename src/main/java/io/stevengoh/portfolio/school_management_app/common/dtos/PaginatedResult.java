package io.stevengoh.portfolio.school_management_app.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResult<T> {
    private List<T> content;
    private long total;
    private int page;
    private int size;
}
