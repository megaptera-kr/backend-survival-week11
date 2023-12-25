package com.example.demo.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class LineItemId extends EntityId {
    public LineItemId() {
        super();
    }

    public LineItemId(String value) {
        super(value);
    }

    public static LineItemId generate() {
        return new LineItemId(newTsid());
    }
}
