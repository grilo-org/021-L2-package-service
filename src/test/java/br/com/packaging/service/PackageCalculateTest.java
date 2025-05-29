package br.com.packaging.service;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.packaging.model.PackageRequest;
import br.com.packaging.model.request.OrderRequest;
import br.com.packaging.model.request.ProductDimension;
import br.com.packaging.model.request.ProductRequest;
import br.com.packaging.model.response.OrderResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PackageCalculateTest {

    @InjectMocks private PackageCalculate packageCalculate;

    @Test
    void givenPackageRequest_whenCallMethodsCalculate_shouldReturnOrdersResponse() {
        final PackageRequest packageRequest = buildPackageRequest();
        final List<OrderResponse> orders = new ArrayList<>();

        packageRequest.getOrders().forEach(order -> {
            packageCalculate.setProducts(order.getProducts());
            packageCalculate.calculate();

            OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .boxes(packageCalculate.getBoxes())
                .build();

            orders.add(orderResponse);
            packageCalculate.reset();
        });

        assertEquals(packageRequest.getOrders().size(), orders.size());
    }

    private PackageRequest buildPackageRequest() {
        return PackageRequest.builder()
            .orders(asList(OrderRequest.builder()
                .id(1L)
                .products(asList(ProductRequest.builder()
                    .id("PS5")
                    .dimension(ProductDimension.builder()
                        .height(40)
                        .width(10)
                        .length(25)
                        .build())
                    .build(),
                    ProductRequest.builder()
                        .id("Volante")
                        .dimension(ProductDimension.builder()
                            .height(40)
                            .width(30)
                            .length(30)
                            .build())
                        .build()))
                .build(),
                OrderRequest.builder()
                    .id(2L)
                    .products(asList(ProductRequest.builder()
                        .id("Webcam")
                        .dimension(ProductDimension.builder()
                            .height(7)
                            .width(10)
                            .length(5)
                            .build())
                        .build(),
                        ProductRequest.builder()
                            .id("Microfone")
                            .dimension(ProductDimension.builder()
                                .height(25)
                                .width(10)
                                .length(10)
                                .build())
                            .build(),
                        ProductRequest.builder()
                            .id("Monitor")
                            .dimension(ProductDimension.builder()
                                .height(50)
                                .width(60)
                                .length(20)
                                .build())
                            .build(),
                        ProductRequest.builder()
                            .id("Notebook")
                            .dimension(ProductDimension.builder()
                                .height(2)
                                .width(35)
                                .length(25)
                                .build())
                            .build()))
                    .build()))
            .build();
    }
}
