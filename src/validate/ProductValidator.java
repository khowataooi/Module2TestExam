package validate;

import input.Input;

public class ProductValidator {
    public static String inputId() {
        String regex = "^(\\d*)$";
        String id;
        do {
            System.out.println("Nhập Id sản phẩm:");
            id = Input.inputString();
        } while (!id.matches(regex));
        return id;
    }

    public static String inputName() {
        String regex = "^[a-zA-ZÀ-ỹ1-9\\s]$";
        String name;
        do {
            System.out.println("Nhập tên sản phẩm:");
            name = Input.inputString();
        } while (!name.matches(regex));
        return name;
    }
    public static String inputConfirm() {
        String regex = "^[YyNn]$";
        String choice;
        do {
            System.out.println("Bạn có chắc muốn xoá sản pẩm này không? (Y/N");
            choice = Input.inputString();
        } while (!choice.matches(regex));
        return choice;
    }
}
