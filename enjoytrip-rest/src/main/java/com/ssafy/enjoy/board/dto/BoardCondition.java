package com.ssafy.enjoy.board.dto;

import com.ssafy.enjoy.util.StringUtils;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class BoardCondition {
    private String userId;
    private String keyword;
    private int boardId;
    private String orderBy;
    private String orderDir;
    private ArrayList<Category> categories = new ArrayList<>();

    public BoardCondition() {
    }

    public void setDefault() {
        if (StringUtils.isBlank(userId)) {
            userId = "%";
        }
        if (StringUtils.isBlank(keyword)) {
            keyword = "%";
        } else {
            keyword = "%" + keyword + "%";
        }
        if (StringUtils.isBlank(orderBy)) {
            orderBy = "register_time";
        }
        if (StringUtils.isBlank(orderDir)) {
            orderDir = "asc";
        }
        if (categories.size() == 0) {
            categories.add(Category.FREE);
            categories.add(Category.NOTICE);
            categories.add(Category.QNA);
        }
    }
}
