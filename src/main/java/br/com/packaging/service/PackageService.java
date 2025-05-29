package br.com.packaging.service;

import br.com.packaging.model.PackageRequest;
import br.com.packaging.model.PackageResponse;
import br.com.packaging.model.response.OrderResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PackageService {

    private final PackageCalculate packageCalculate;

    public PackageResponse makePackage(@NonNull PackageRequest request) {
        final List<OrderResponse> orders = new ArrayList<>();
        request.getOrders().forEach(order -> {
            packageCalculate.setProducts(order.getProducts());
            packageCalculate.calculate();

            OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .boxes(packageCalculate.getBoxes())
                .build();

            orders.add(orderResponse);
            packageCalculate.reset();
        });

        return PackageResponse.builder()
            .orders(orders)
            .build();
    }
}
