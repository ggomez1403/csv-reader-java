import model.Product;
import model.ProductsService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static ProductsService productService = new ProductsService();

    public static void main(String[] args) {
        try {
            File file = new File("resources/inventory.csv");
            Scanner fileScanner = new Scanner(file);

            fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] productInfo = line.split(",");

                float price = 0.0f;
                if (!productInfo[4].isEmpty()) {
                    price = Float.parseFloat(productInfo[4]);
                }

                Product product = new Product(productInfo[0], productInfo[1], productInfo[2],
                        productInfo[3], price, productInfo[5]);

                productService.addProduct(product);
            }

            for (Product product : productService.getProducts()) {
                System.out.println(product);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}