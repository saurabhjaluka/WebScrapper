/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webscrapper;

/**
 *
 * @author saurabh
 */
public class Product {
    private String title;
    private String price;
    private String shippingInfo;
    private String vendor;
    
    /**
     * Constructor
     * @param title
     * @param price
     * @param shippingInfo
     * @param vendor
     */
    public Product(String title, String price, String shippingInfo, String vendor)
    {
        this.title = title;
        this.price = price;
        this.shippingInfo = shippingInfo;
        this.vendor = vendor;
    }

    /**
     * No argument constructor
     */
    public Product() { }
    
    /**
     *  This function display the details of the product.
     */
    public void displayDetail(){
        System.out.println("Title: "+title);
        System.out.println("Price: $"+price);
        System.out.println("Shipping Price: "+shippingInfo);
        System.out.println("Vendor: "+vendor);
        System.out.println("");
    }
    
}
