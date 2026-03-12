package service;

import exception.InvalidOrderIdException;
import model.MenuItem;
import model.Order;
import model.OrderStatus;
import repository.MenuRepository;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static repository.MenuRepository.menuItems;

public class OrderManagement implements IOrderService{



    @Override
    public void create(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Dữ liệu đơn hàng không hợp lệ");
        }
        if(OrderRepository.findOrderById(order.getId()).isPresent()) {
            System.out.println("Id đơn hàng đã tồn tại!");
            return;
        }
        OrderRepository.orders.add(order);
        System.out.println("Tạo đơn hàng thành công!");
    }

    @Override
    public void updateStatus(String id, OrderStatus orderStatus) throws InvalidOrderIdException {
        Optional<Order> order = OrderRepository.findOrderById(id);
        if (order.isPresent()) {
            order.get().setStatus(orderStatus);
            System.out.println("Cập nhật trạng thái thành công!");
        }else{
            throw new InvalidOrderIdException("Không tìm thấy đơn hàng ID: " + id);
        }

    }

    @Override
    public void addItem(String orderId, String itemId, int quantity) throws InvalidOrderIdException {
        if(quantity <= 0){
            System.out.println("Số lượng không phù hợp!");
            return;
        }
        Optional<Order> order = OrderRepository.findOrderById(orderId);

        if (order.isPresent()) {
            MenuItem item = MenuRepository.findItemById(itemId);
            if (item == null) {
                System.out.println("Không tìm thấy sản phẩm");
                return;
            }
            order.get().addItem(item, quantity);
            System.out.println("Thêm món thành công!");
        }else{
            throw new InvalidOrderIdException("Không tìm thấy đơn hàng ID: " + orderId);
        }

    }

    @Override
    public void displayOrder(String orderId) throws InvalidOrderIdException {
        Optional<Order> order = OrderRepository.findOrderById(orderId);

        if(order.isPresent()) {
            System.out.println("Đơn hàng : ");
            System.out.println(order.get());
            System.out.println("Danh sách sản phẩm trong đơn hàng : ");
            Map<MenuItem, Integer> items = order.get().getItems();
            for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
                MenuItem item = entry.getKey();
                System.out.println(item + " Số lượng : " + entry.getValue());
            }
        }else{
            throw new InvalidOrderIdException("Không tìm thấy đơn hàng ID: " + orderId);
        }

    }
}
