package menu;

import input.Input;
import manager.ProductManager;
import model.Product;
import validate.ProductValidator;

import java.util.ArrayList;
import java.util.List;

public class ProductMenu {
    private static final ProductManager productManager = new ProductManager();

    public static void run() {
        int choice;
        do {
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM ---- \n" +
                    "Chọn chức năng theo số (để tiếp tục) \n" +
                    "1. Xem danh sách\n" +
                    "2. Thêm mới\n" +
                    "3. Cập nhật\n" +
                    "4. Xoá\n" +
                    "5. Sắp xếp sản phẩm theo giá\n" +
                    "6. Tìm sản phẩm có giá đắt nhất\n" +
                    "0.Thoát");
            System.out.println("Nhập lựa chọn:");
            choice = Input.inputNumber();
            switch (choice) {
                case 1:
                    showAll();
                    break;
                case 2:
                    addMenu();
                    break;
                case 3:
                    editMenu();
                    break;
                case 4:
                    deleteMenu();
                    break;
                case 5:
                    sortMenu();
                    break;
                case 6:
                    findMostExpensiveMenu();
                    break;
            }
        } while (choice != 0);
    }

    private static void deleteMenu() {
        System.out.println("---=Xoá sản phẩm=---");
        System.out.println("Nhập Id sản phẩm:");
        int id = Input.inputNumber();
        int index = productManager.findById(id);
        if (index == -1) {
            System.out.println("====Id sản phẩm không tồn tại====\n " +
                    "1. Nhập lại Id\n " +
                    "0. Quay về menu\n " +
                    "Nhập lựa chọn:");
            int choice = Input.inputNumber();
            switch (choice) {
                case 1:
                    deleteMenu();
                    break;
                case 0:
                    return;
            }
        }
        System.out.println(productManager.findAll().get(index));
        String choice = ProductValidator.inputConfirm().toLowerCase();
        switch (choice) {
            case "y":
                productManager.delete(id);
                System.out.println("===Xoá thành công===");
                showAll();
                break;
            case "n":
                run();
        }
    }

    private static void editMenu() {
        System.out.println("---=Cập nhật sản phẩm=---");
        System.out.println("Nhập Id sản phẩm cần sửa đổi:");
        String id = ProductValidator.inputId();
        if (id.isEmpty()) return;
        int productIndex = productManager.findById(Integer.parseInt(id));
        while (productIndex == -1) {
            System.out.println("Id sản phẩm không tồn tại, vui lòng nhập lại;");
            id = ProductValidator.inputId();
            if (id.isEmpty()) return;
            else productIndex = productManager.findById(Integer.parseInt(id));

        }
        Product product = productManager.findAll().get(productIndex);
        System.out.println(product);
        String name = ProductValidator.inputName();
        int price = Input.inputNumber();
        int quantity = Input.inputNumber();
        System.out.println("Nhập mô tả sản phẩm:");
        String description = Input.inputString();
        Product newProduct = new Product(Integer.parseInt(id), name, price, quantity, description);
        productManager.edit(Integer.parseInt(id), newProduct);
        System.out.println("Sửa đổi thành công.");
    }

    public static void addMenu() {
        System.out.println("---=Thêm sản phẩm mới=---");
        String id = ProductValidator.inputId();
        if (id.isEmpty()) return;
        while (productManager.findById(Integer.parseInt(id)) != -1) {
            System.out.println("Id đã tồn tại, mời nhập lại:");
            id = ProductValidator.inputId();
        }
        String name = ProductValidator.inputName();
        int price = Input.inputNumber();
        int quantity = Input.inputNumber();
        System.out.println("Nhập mô tả sản phẩm:");
        String description = Input.inputString();
        productManager.add(new Product(Integer.parseInt(id), name, price, quantity, description));
        System.out.println("===Thêm thành công===");
    }

    public static void showAll() {
        System.out.println("=======Danh sách sản phẩm=======");
        List<Product> list = productManager.findAll();
        for (Product product : list) {
            System.out.println(product);
        }
    }

    public static void sortMenu() {
        List<Product> temp = new ArrayList<>(productManager.findAll());
        sort(temp);
        for (Product product : temp) {
            System.out.println(product);
        }
    }

    public static void findMostExpensiveMenu() {
        System.out.println("Sản phẩm có giá đắt nhất:");
        System.out.println(productManager.findMostExpensive());
    }

    public static void sort(List<Product> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = 0; j < arr.size() - i - 1; j++) {
                if (arr.get(j).getPrice() > arr.get(j+1).getPrice()) {
                    Product temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }
}
