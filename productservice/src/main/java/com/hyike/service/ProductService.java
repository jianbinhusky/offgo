package com.hyike.service;

import com.hyike.service.bean.Product;

/**
 * Created by hujianbin on 18/1/13.
 */
public class ProductService implements IProductService{
    @Override
    public Product queryById(long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("water");
        product.setPrice(1.0);

        return product;
    }
}
