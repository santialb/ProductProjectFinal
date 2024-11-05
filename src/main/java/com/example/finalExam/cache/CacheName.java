package com.example.finalExam.cache;

import lombok.Getter;

@Getter
public enum CacheName {

    PRODUCTS("products");
    private final String value;

    CacheName(String value) {
        this.value = value;
    }
}
