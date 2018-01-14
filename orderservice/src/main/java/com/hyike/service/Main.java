package com.hyike.service;

import com.hyike.service.bean.Product;

/**
 * Created by hujianbin on 18/1/13.
 */
public class Main {
    public static void main(String[] args) {
        IProductService productService = (IProductService) RPCUtils.rpc(IProductService.class);
        Product product = productService.queryById(1);
        System.out.println(product);
    }
}
