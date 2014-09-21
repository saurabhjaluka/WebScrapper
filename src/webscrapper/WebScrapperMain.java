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
public class WebScrapperMain {

    public static void main(String[] args) {

        if (!validArguments(args)) {
            System.out.println("Error in arguments");
            System.out.println("Usage:");
            System.out.println("1. java -jar Assignment.jar <keyword> (e.g. java -jar Assignment \"baby strollers\")");
            System.out.println("2. java -jar Assignment.jar <keyword> <page number> (e.g. java -jar Assignment \"baby strollers\" 2)");
            System.out.println("Try Again.");
            return;
        }

        WebScrapper scrapper = new WebScrapper();

        String keyword = args[0];
        if (args.length == 1) {
            int totalResult = scrapper.getTotalNumberOfResult(keyword);

            if (totalResult != -1) {
                System.out.println("Total Result: " + totalResult);
            }

        } else {
            int pageno = Integer.parseInt(args[1]);
            Products productList = (Products) scrapper.getProducts(keyword, pageno);
            if (productList != null) {
                productList.displayProductList();
            }

        }
    }
    
    /*
     * Checks the validity of the arguments, returns true if passed the check else false.
     * @param String[]
     * @return boolean
     */
    private static boolean validArguments(String[] args) {

        if (args.length == 0 || args.length > 2) {
            return false;
        }
       
        return args.length <= 1 || args[1].matches("\\d+");
    }

}
