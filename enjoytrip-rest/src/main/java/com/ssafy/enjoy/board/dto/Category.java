package com.ssafy.enjoy.board.dto;

import lombok.Getter;

@Getter
public enum Category {
    NOTICE("notice"),
    QNA("qna"),
    FREE("free");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public static Category get(String code) {
        for (Category element : values()) {
            if (element.value.equalsIgnoreCase(code)) {
                return element;
            }
        }
        return null;
    }

    public static Category lookup(String code) {
        for (Category element : Category.values()) {
            if (element.value.equalsIgnoreCase(code)) {
                return element;
            }
        }
        return null;
    }
}
