package com.swd.ccp.DTO.request_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationRequest {
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private Sort.Direction sort = Sort.Direction.ASC;
    private String sortByColumn = "id";
    private String keyword;
    private String searchType;

    public PaginationRequest(int page, int size, String sort, String sortByColumn) {
    }

}