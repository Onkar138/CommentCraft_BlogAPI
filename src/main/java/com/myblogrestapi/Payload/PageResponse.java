package com.myblogrestapi.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse {

    private List<PostDTO> content;
    private int PageNumber;
    private int PageSize;
    private long TotalElements;
    private int TotalPage;
    private boolean LastPage;

}
