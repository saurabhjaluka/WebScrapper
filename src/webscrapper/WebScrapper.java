/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webscrapper;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author saurabh
 */
public class WebScrapper {
    
    /**
     * This function takes 1 argument as keyword and return the total number of results found on www.shopping.com
     * @param keyword
     * @return Total number of result 
     */
    public int getTotalNumberOfResult(String keyword) {
        try {
            Document doc = Jsoup.connect("http://www.shopping.com/xFS").data("KW", keyword).get();
            Elements totalNumberOfProducts = doc.getElementsByClass("numTotalResults");
            int totalNumber = parseNumberFromResultSpan(totalNumberOfProducts.text());
            return totalNumber;
        } catch (IOException ex) {
            System.err.println("Error in loading document. Please try again.");
            return -1;
        }
    }

    private static int parseNumberFromResultSpan(String number) {
        return Integer.parseInt(number.substring(number.lastIndexOf(' ') + 1).replace("+", ""));
    }

    /**
     *  This function takes 2 arguments, keyword and page no., returns the Products.
     * @param keyword
     * @param pageno
     * @return Products
     */
    public Products getProducts(String keyword, int pageno) {
        
        try {
            if (pageno <= 0) {
                System.out.println("Invalid PageNo.");
                return null;
            }
            Document doc = null;
            if (pageno > 1) {
                Document document = Jsoup.connect("http://www.shopping.com/xFS").data("KW", keyword).data("CLT", "SCH").get();
                Element link = document.select("a[name=PL" + pageno + "]").first();
                if (link == null) {
                    int noOfItemsPerPage = 40;
                    System.out.println("Invalid PageNo. \nLast page no. is: "+(int)(Math.floor(getTotalNumberOfResult(keyword)/(double)noOfItemsPerPage)+1));
                    System.out.println("Please try again");
                    return null;
                }
                String absLink = link.attr("abs:href");
                doc = Jsoup.connect(absLink).get();
            } else {
                doc = Jsoup.connect("http://www.shopping.com/xFS").data("KW", keyword).data("CLT", "SCH").get();
            }

            Products productsList = new Products();

            Elements products = doc.getElementsByClass("gridItemBtm");
       
            int index = 1;
            
            for (Element product : products) {

                Element nameSpan = product.getElementById("nameQA" + index);
                String title = nameSpan.attr("title");

                Elements priceSpan = product.getElementsByClass("productPrice").not("toSalePrice");
                String price = priceSpan.text().trim().replace("$", "");

                Elements merchantSpan = product.getElementsByClass("newMerchantName");
                String merchant = "No merchent info";

                if (merchantSpan != null && merchantSpan.hasText() != false) {
                    merchant = merchantSpan.text();
                }
                
                Element numStoreSpan = product.getElementById("numStoresQA" + index);
                String numStore = "";
                
                if (numStoreSpan != null && numStoreSpan.hasText() != false) {
                    numStore = ", " + numStoreSpan.text();
                }

                StringBuilder vendor = new StringBuilder();
                vendor.append(merchant);
                vendor.append(numStore);

                Elements shippingSpan = product.getElementsByClass("freeShip");
                String shippingInfo = "No shipping info";

                if (shippingSpan != null && shippingSpan.hasText() != false) {
                    shippingInfo = shippingSpan.text();
                }

                Elements calcSpan = product.getElementsByClass("calc");

                if (calcSpan != null && calcSpan.hasText() != false) {
                    shippingInfo = calcSpan.text().replace("+ ", "").replace("shipping","");
                }

                Product productObj = new Product(title, price, shippingInfo, vendor.toString());
                productsList.add(productObj);
                
                index++;
            }
            return productsList;
                    

        } catch (IOException ex) {
            System.err.println("Error in loading document. Please try again.");
            return null;
        }
    }
}
