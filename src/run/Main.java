package run;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== FAST FOOD MANAGEMENT =====");
            System.out.println("1. Quản lý thực đơn");
            System.out.println("2. Quản lý đơn hàng");
            System.out.println("3. Thống kê và tìm kiếm");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    MenuConsole.MenuItemConsole(sc);
                    break;
                case 2:
                    MenuConsole.OrderConsole(sc);
                    break;
                case 3:
                    MenuConsole.StatisticAndSearchConsole(sc);
                    break;
                case 0:
                    System.out.println("Thoát chương trình");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }
}
