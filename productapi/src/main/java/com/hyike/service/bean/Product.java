package com.hyike.service.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hujianbin on 18/1/13.
 */
@Data
public class Product implements Serializable {
    private long id;
    private String name;
    private double price;
}
