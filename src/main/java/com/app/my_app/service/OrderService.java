package com.app.my_app.service;

import com.app.my_app.domain.Order;
import com.app.my_app.domain.OrderStatus;
import com.app.my_app.domain.User;
import com.app.my_app.model.OrderDTO;
import com.app.my_app.repos.OrderRepository;
import com.app.my_app.repos.OrderStatusRepository;
import com.app.my_app.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final UserRepository userRepository;

    public OrderService(final OrderRepository orderRepository,
            final OrderStatusRepository orderStatusRepository,
            final UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.userRepository = userRepository;
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> mapToDTO(order, new OrderDTO()))
                .collect(Collectors.toList());
    }

    public OrderDTO get(final Long id) {
        return orderRepository.findById(id)
                .map(order -> mapToDTO(order, new OrderDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final OrderDTO orderDTO) {
        final Order order = new Order();
        mapToEntity(orderDTO, order);
        return orderRepository.save(order).getId();
    }

    public void update(final Long id, final OrderDTO orderDTO) {
        final Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(orderDTO, order);
        orderRepository.save(order);
    }

    public void delete(final Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO mapToDTO(final Order order, final OrderDTO orderDTO) {
        orderDTO.setId(order.getId());
        orderDTO.setTotal(order.getTotal());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setStatus(order.getStatus() == null ? null : order.getStatus().getId());
        orderDTO.setUsers(order.getUsers() == null ? null : order.getUsers().getId());
        return orderDTO;
    }

    private Order mapToEntity(final OrderDTO orderDTO, final Order order) {
        order.setTotal(orderDTO.getTotal());
        order.setAddress(orderDTO.getAddress());
        if (orderDTO.getStatus() != null && (order.getStatus() == null || !order.getStatus().getId().equals(orderDTO.getStatus()))) {
            final OrderStatus status = orderStatusRepository.findById(orderDTO.getStatus())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "status not found"));
            order.setStatus(status);
        }
        if (orderDTO.getUsers() != null && (order.getUsers() == null || !order.getUsers().getId().equals(orderDTO.getUsers()))) {
            final User users = userRepository.findById(orderDTO.getUsers())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "users not found"));
            order.setUsers(users);
        }
        return order;
    }

}
