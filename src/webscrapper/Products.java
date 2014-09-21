/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webscrapper;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author saurabh
 */
public class Products {
    /* This stores all the products */
    private final List<Product> productList;
    
    /**
     *  No argument constructor
     */
    public Products()
    {
        productList = new ArrayList<>();
    }

    /**
     * Adds a passed product into the productList
     * @param product
     */
    public void add(Product product)
    {
        productList.add(product);
    }

    /**
     *  Displays all the products in the productList.
     */
    public void displayProductList() {
        int index = 1;
        for (Product product : productList) {
            System.out.println("Product No. "+index++);
            product.displayDetail();
        }
    }
}
