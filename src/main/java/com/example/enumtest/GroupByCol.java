package com.example.enumtest;

/**
 * @author 吕茂陈
 * @description
 * @date 2022/12/21 9:56
 */
public enum GroupByCol {
    /**
     * actor_type
     **/
    ACTOR("actor_type"),
    /**
     * target_type
     **/
    TARGET("target_type"),
    /**
     * event_event_type
     **/
    EVENT("event_event_type");
    private final String col;

    GroupByCol(String col) {
        this.col = col;
    }

    public String getCol() {
        return col;
    }
}
