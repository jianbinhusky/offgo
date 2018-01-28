package com.hyike.service;

import com.hyike.service.bean.Product;

/**
 * Created by hujianbin on 18/1/13.
 */
public class Main {
    public static void main(String[] args) {
        //生成一个动态代理对象
        IProductService productService = (IProductService) RPCUtils.rpc(IProductService.class);
        //代理对象调用Handler的方法
        Product product = productService.queryById(1);
        
        System.out.println(product);
    }
}
