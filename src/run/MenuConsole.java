package run;

import model.*;
import service.MenuManagement;
import service.OrderManagement;
import service.SearchService;

import java.util.HashMap;
import java.util.Scanner;

public class MenuConsole {
    static MenuManagement menuManagement = new MenuManagement();
    static SearchService searchService = new SearchService();
    static OrderManagement orderManagement = new OrderManagement();

    // Menu Item
    public static void MenuItemConsole(Scanner sc) {
        sc.nextLine();
        int choice;
        do {
            System.out.println("===== MENU MANAGEMENT =====");
            System.out.println("1. Thêm đồ ăn");
            System.out.println("2. Cập nhập thông tin đồ ăn");
            System.out.println("3. Xoá đồ ăn");
            System.out.println("4. Tìm kiếm món ăn theo tên hoặc theo giá");
            System.out.println("5. Hiển thị tất cả các món ăn");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    createMenuItem(sc);
                    break;
                case 2:
                    updateMenuItem(sc);
                    break;
                case 3:
                    deleteMenuItem(sc);
                    break;
                case 4:
                    int subChoice;
                    do {
                        System.out.println("""
                                1. Tìm kiếm theo giá
                                2. Tìm kiếm theo tên
                                0. Thoát
                                """);
                        System.out.print("Lựa chọn của bạn: ");
                        subChoice = Integer.parseInt(sc.nextLine());
                        switch (subChoice){
                            case 1:
                                searchPriceService(sc);
                                break;
                            case 2:
                                searchNameService(sc);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ");
                        }
                    }while(subChoice != 0);
                    break;
                case 5:
                    System.out.println("case 5");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }while(choice != 0);
    }

    // Menu order
    public static void OrderConsole(Scanner sc) {
        sc.nextLine();
        int choice;
        do {
            System.out.println("===== ORDER MANAGEMENT =====");
            System.out.println("1. Tạo đơn hàng mới cho khách hàng");
            System.out.println("2. Thêm món ăn vào giỏ hàng");
            System.out.println("3. Áp dụng mã giảm giá");
            System.out.println("4. Cập nhật trạng thái đơn hàng");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Case 1");
                    break;
                case 2:
                    System.out.println("case 2");
                    break;
                case 3:
                    System.out.println("case 3");
                    break;
                case 4:
                    System.out.println("case 4");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }while(choice != 0);

    }

    // Menu service
    public static void StatisticAndSearchConsole(Scanner sc) {
        sc.nextLine();
        int choice;
        do {
            System.out.println("===== STATISTIC & SEARCH =====");
            System.out.println("1. Tìm kiếm món ăn theo giá hoặc theo tên");
            System.out.println("2. Thống kê tổng doanh thu");
            System.out.println("3. Các món ăn bán chạy nhất");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    int subChoice;
                    do {
                        System.out.println("""
                                1. Tìm kiếm theo giá
                                2. Tìm kiếm theo tên
                                0. Thoát
                                """);
                        System.out.print("Lựa chọn của bạn: ");
                        subChoice = Integer.parseInt(sc.nextLine());
                        switch (subChoice){
                            case 1:
                                System.out.println("Tìm kiếm theo giá");
                                break;
                            case 2:
                                System.out.println("Tìm kiếm theo tên");
                                break;
                            case 0:
                                break;
                        }
                    }while(subChoice != 0);
                    break;
                case 2:
                    System.out.println("Thống kê tổng doanh thu");
                    break;
                case 3:
                    System.out.println("Danh sách các món ăn bán chạy nhất");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 0);
    }

    // Thêm mới món ăn
    public static void createMenuItem(Scanner sc) {
        System.out.println("Chọn loại món");
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.println("3. Dessert");
        System.out.print("Lựa chọn: ");

        int type = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập ID: ");
        String id = sc.nextLine();
        System.out.print("Nhập tên: ");
        String name = sc.nextLine();
        System.out.print("Nhập giá cơ bản: ");
        double basePrice = Double.parseDouble(sc.nextLine());
        MenuItem item = null;

        switch (type) {
            case 1:
                System.out.print("Nhập mô tả món ăn: ");
                String description = sc.nextLine();
                item = new Food(id, name, basePrice, description);
                break;
            case 2:
                System.out.print("Nhập size (S/M/L): ");
                String size = sc.nextLine();
                item = new Drink(id, name, basePrice, size);
                break;
            case 3:
                System.out.print("Có đường không? (true/false): ");
                boolean isSweet = Boolean.parseBoolean(sc.nextLine());
                item = new Dessert(id, name, basePrice, isSweet);
                break;
            default:
                System.out.println("Loại món không hợp lệ!");
                return;
        }
        menuManagement.create(item);
    }

    // Cập nhật món ăn
    public static void updateMenuItem(Scanner sc) {
        System.out.print("Nhập ID cần sửa: ");
        String id = sc.nextLine();

        System.out.println("Chọn loại món mới:");
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.println("3. Dessert");
        int type = Integer.parseInt(sc.nextLine());

        System.out.print("Tên mới: ");
        String name = sc.nextLine();

        System.out.print("Giá mới: ");
        double basePrice = Double.parseDouble(sc.nextLine());
        MenuItem item = null;
        switch (type) {
            case 1:
                System.out.print("Mô tả: ");
                String description = sc.nextLine();
                item = new Food(id, name, basePrice, description);
                break;
            case 2:
                System.out.print("Size (S/M/L): ");
                String size = sc.nextLine();
                item = new Drink(id, name, basePrice, size);
                break;
            case 3:
                System.out.print("Có đường? (true/false): ");
                boolean isSweet = Boolean.parseBoolean(sc.nextLine());
                item = new Dessert(id, name, basePrice, isSweet);
                break;
            default:
                System.out.println("Loại không hợp lệ");
                return;
        }
        menuManagement.update(id, item);
    }

    // Xoá món ăn
    public static void deleteMenuItem(Scanner sc) {
        System.out.print("Nhập ID cần xóa: ");
        String id = sc.nextLine();
        menuManagement.delete(id);
    }

    // Tìm kiếm món ăn theo tên
    public static void searchNameService(Scanner sc){
        System.out.print("Nhập tên món ăn cần tìm kiếm");
        String nameItem = sc.nextLine();
        searchService.findByName(nameItem);
    }

    // Tìm kiếm món ăn theo giá
    public static void searchPriceService(Scanner sc){
        System.out.print("Nhập vào giá nhỏ nhất: ");
        double minPrice = Double.parseDouble(sc.nextLine());

        System.out.print("Nhập vào giá lớn nhất: ");
        double maxPrice = Double.parseDouble(sc.nextLine());

        searchService.findByPriceRange(minPrice, maxPrice);
    }

    // Tạo order
    public static void createOrder(Scanner sc){
        System.out.print("Nhập ID đơn hàng: ");
        String id = sc.nextLine();
        Order order = new Order(id,new HashMap<>(),0,OrderStatus.PENDING);
        orderManagement.create(order);
    }

    // Thêm món vào order
    public static void addItemToOrder(Scanner sc){
        System.out.print("Nhập ID đơn hàng: ");
        String orderId = sc.nextLine();

        System.out.print("Nhập ID món ăn: ");
        String itemId = sc.nextLine();

        System.out.print("Nhập số lượng: ");
        int quantity = Integer.parseInt(sc.nextLine());

        MenuItem item = menuManagement.findById(itemId);

        if(item == null){
            System.out.println("Không tìm thấy món ăn!");
            return;
        }

        orderManagement.addItem(orderId,item,quantity);

    }
}
