package repository;

import model.Order;
import model.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    public static List<Order> orders = new ArrayList<Order>();

    static {
        // Đơn hàng 1: 2 Burger
        Order o1 = new Order("ORD01");
        o1.addItem(MenuRepository.findItemById("F01"), 2);
        o1.setStatus(OrderStatus.PAID);
        orders.add(o1);

        // Đơn hàng 2: 3 Trà sữa (D01)
        Order o2 = new Order("ORD02");
        o2.addItem(MenuRepository.findItemById("D01"), 3);
        o2.setStatus(OrderStatus.PAID);
        orders.add(o2);

        // Đơn hàng 3: 1 Kem (S01) - Đang chờ (Không nên tính vào Top Seller nếu bạn filter PAID)
        Order o3 = new Order("ORD03");
        o3.addItem(MenuRepository.findItemById("S01"), 1);
        o3.setStatus(OrderStatus.PENDING);
        orders.add(o3);

        // --- BỔ SUNG ĐỂ TEST TOP SELLER ---

        // Đơn hàng 4: 5 Coca Cola (D02) -> Đẩy Coca lên Top 1
        Order o4 = new Order("ORD04");
        o4.addItem(MenuRepository.findItemById("D02"), 5);
        o4.setStatus(OrderStatus.PAID);
        orders.add(o4);

        // Đơn hàng 5: Thêm 2 Burger (F01) -> Tổng Burger = 4 (Top 2)
        Order o5 = new Order("ORD05");
        o5.addItem(MenuRepository.findItemById("F01"), 2);
        o5.setStatus(OrderStatus.PAID);
        orders.add(o5);

        // Đơn hàng 6: Thêm 1 Coca (D02) và 1 Trà sữa (D01)
        Order o6 = new Order("ORD06");
        o6.addItem(MenuRepository.findItemById("D02"), 1);
        o6.addItem(MenuRepository.findItemById("D01"), 1);
        o6.setStatus(OrderStatus.PAID);
        orders.add(o6);
    }

    public static Optional<Order> findOrderById(String id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }
}
