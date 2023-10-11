package dto;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RWFile {
    private final File file = new File("data/product.csv");

    public void writeFile(List<Product> productList) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            StringBuilder line = new StringBuilder();
            for (Product product : productList) {
                line.append(product.getData()).append("\n");
            }
            bufferedWriter.write(line.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Product> readFile() {
        List<Product> productList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Product product = new Product(
                        Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2]),
                        Integer.parseInt(data[3]),
                        data[4]
                );
                productList.add(product);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }
}
