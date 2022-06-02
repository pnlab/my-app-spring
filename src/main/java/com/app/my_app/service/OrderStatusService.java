package com.app.my_app.service;

import com.app.my_app.domain.OrderStatus;
import com.app.my_app.model.OrderStatusDTO;
import com.app.my_app.repos.OrderStatusRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusService(final OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public List<OrderStatusDTO> findAll() {
        return orderStatusRepository.findAll()
                .stream()
                .map(orderStatus -> mapToDTO(orderStatus, new OrderStatusDTO()))
                .collect(Collectors.toList());
    }

    public OrderStatusDTO get(final Long id) {
        return orderStatusRepository.findById(id)
                .map(orderStatus -> mapToDTO(orderStatus, new OrderStatusDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final OrderStatusDTO orderStatusDTO) {
        final OrderStatus orderStatus = new OrderStatus();
        mapToEntity(orderStatusDTO, orderStatus);
        return orderStatusRepository.save(orderStatus).getId();
    }

    public void update(final Long id, final OrderStatusDTO orderStatusDTO) {
        final OrderStatus orderStatus = orderStatusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(orderStatusDTO, orderStatus);
        orderStatusRepository.save(orderStatus);
    }

    public void delete(final Long id) {
        orderStatusRepository.deleteById(id);
    }

    private OrderStatusDTO mapToDTO(final OrderStatus orderStatus,
            final OrderStatusDTO orderStatusDTO) {
        orderStatusDTO.setId(orderStatus.getId());
        orderStatusDTO.setName(orderStatus.getName());
        orderStatusDTO.setDescription(orderStatus.getDescription());
        return orderStatusDTO;
    }

    private OrderStatus mapToEntity(final OrderStatusDTO orderStatusDTO,
            final OrderStatus orderStatus) {
        orderStatus.setName(orderStatusDTO.getName());
        orderStatus.setDescription(orderStatusDTO.getDescription());
        return orderStatus;
    }

}
