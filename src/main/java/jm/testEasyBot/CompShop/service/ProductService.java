package jm.testEasyBot.CompShop.service;

import java.util.List;

public interface ProductService<T> {

    T addNewProduct(T product);
    T updateProduct(String serialNumber, T product);
    List<T> getAllProducts();
    T getProduct(String serialnumber);
}
