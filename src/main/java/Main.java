import model.Product;
import model.ProductsService;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.List;

public class Main {

    static ProductsService productService = new ProductsService();
    public static void main(String[] args) {
        try {
            String filePath = "resources/inventory.csv";
            FileReader fileReader = new FileReader(filePath);

            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(1)
                    .build();

            List<String[]> lines = csvReader.readAll();

            for(String[] line : lines){
                if(line.length >= 6){
                    String name = line[0];
                    String description = line[1];
                    String category = line[2];
                    String tags = line[3];

                    float price = 0.0f;
                    if (!line[4].isEmpty()) {
                        price = Float.parseFloat(line[4]);
                    }

                    String imageUrl = line[5];

                    Product product = new Product(name, description, category, tags, price, imageUrl);
                    productService.addProduct(product);
                } else{
                    System.out.println("Advertencia: Línea CSV incompleta");
                }
            }

            csvReader.close();

            for (Product product : productService.getProducts()){
                System.out.println(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
