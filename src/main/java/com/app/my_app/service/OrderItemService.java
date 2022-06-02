package com.app.my_app.service;

import com.app.my_app.domain.Order;
import com.app.my_app.domain.OrderItem;
import com.app.my_app.model.OrderItemDTO;
import com.app.my_app.repos.OrderItemRepository;
import com.app.my_app.repos.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public OrderItemService(final OrderItemRepository orderItemRepository,
            final OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    public List<OrderItemDTO> findAll() {
        return orderItemRepository.findAll()
                .stream()
                .map(orderItem -> mapToDTO(orderItem, new OrderItemDTO()))
                .collect(Collectors.toList());
    }

    public OrderItemDTO get(final Long id) {
        return orderItemRepository.findById(id)
                .map(orderItem -> mapToDTO(orderItem, new OrderItemDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final OrderItemDTO orderItemDTO) {
        final OrderItem orderItem = new OrderItem();
        mapToEntity(orderItemDTO, orderItem);
        return orderItemRepository.save(orderItem).getId();
    }

    public void update(final Long id, final OrderItemDTO orderItemDTO) {
        final OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(orderItemDTO, orderItem);
        orderItemRepository.save(orderItem);
    }

    public void delete(final Long id) {
        orderItemRepository.deleteById(id);
    }

    private OrderItemDTO mapToDTO(final OrderItem orderItem, final OrderItemDTO orderItemDTO) {
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setName(orderItem.getName());
        orderItemDTO.setPrice(orderItem.getPrice());
        orderItemDTO.setOrder(orderItem.getOrder() == null ? null : orderItem.getOrder().getId());
        return orderItemDTO;
    }

    private OrderItem mapToEntity(final OrderItemDTO orderItemDTO, final OrderItem orderItem) {
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setName(orderItemDTO.getName());
        orderItem.setPrice(orderItemDTO.getPrice());
        if (orderItemDTO.getOrder() != null && (orderItem.getOrder() == null || !orderItem.getOrder().getId().equals(orderItemDTO.getOrder()))) {
            final Order order = orderRepository.findById(orderItemDTO.getOrder())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "order not found"));
            orderItem.setOrder(order);
        }
        return orderItem;
    }

}
