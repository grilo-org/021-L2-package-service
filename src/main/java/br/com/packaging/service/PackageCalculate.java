package br.com.packaging.service;

import static br.com.packaging.model.response.BoxesEnum.BOX_ONE;
import static br.com.packaging.model.response.BoxesEnum.BOX_TREE;
import static br.com.packaging.model.response.BoxesEnum.BOX_TWO;

import br.com.packaging.model.request.ProductRequest;
import br.com.packaging.model.request.ProductDimension;
import br.com.packaging.model.response.BoxResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class PackageCalculate {

    private List<ProductRequest> products;
    private Integer totalHeight = 0;
    private Integer totalWidth = 0;
    private Integer totalLength = 0;
    private List<BoxResponse> boxes = new ArrayList<>();

    public void setProducts(List<ProductRequest> products) {
        this.products = products;
    }

    public void calculate() {
        calculateDimensions();

        validateBoxes();
    }

    private void validateBoxes() {
        Optional<ProductRequest> opProduct = products.stream().max(Comparator.comparing(p -> p.getDimension().getLength()));
        if (opProduct.isPresent()) {
            totalLength = opProduct.get().getDimension().getLength();
            List<String> idsProducts = products.stream().map(ProductRequest::getId).toList();
            if (totalHeight <= BOX_ONE.getHeight()
                && totalWidth <= BOX_ONE.getWidth()
                && totalLength <= BOX_ONE.getLength()) {
                // CAIXA 1
                boxes.add(BoxResponse.builder()
                    .id(BOX_ONE.getName())
                    .products(idsProducts)
                    .build());
            } else if (totalHeight <= BOX_TWO.getHeight()
                && totalWidth <= BOX_TWO.getWidth()
                && totalLength <= BOX_TWO.getLength()) {
                // CAIXA 2
                boxes.add(BoxResponse.builder()
                    .id(BOX_TWO.getName())
                    .products(idsProducts)
                    .build());
            } else if (totalHeight <= BOX_TREE.getHeight()
                && totalWidth <= BOX_TREE.getWidth()
                && totalLength <= BOX_TREE.getLength()) {
                // CAIXA 3
                boxes.add(BoxResponse.builder()
                    .id(BOX_TREE.getName())
                    .products(idsProducts)
                    .build());
            } else {
                recalculate();
            }
        }
    }

    private void recalculate() {
        totalHeight = 0;
        totalWidth = 0;
        totalLength = 0;
        List<String> ids = products.stream().map(product -> {
            if (totalHeight <= BOX_TREE.getHeight()
                && totalWidth <= BOX_TREE.getWidth()
                && totalLength <= BOX_TREE.getLength()) {
                // CAIXA 3
                plus(product.getDimension());
                return product.getId();
            }
            return null;
        })
            .filter(Objects::nonNull).toList();

        boxes.add(BoxResponse.builder()
            .id(BOX_TREE.getName())
            .products(ids)
            .build());

        products = products.stream().filter(productRequest -> !ids.contains(productRequest.getId())).toList();
        calculate();
    }

    private void calculateDimensions() {
        totalHeight = 0;
        totalWidth = 0;
        totalLength = 0;
        products.forEach(product -> plus(product.getDimension()));
    }

    private void plus(ProductDimension dimension) {
        totalHeight += dimension.getHeight();
        totalWidth += dimension.getWidth();
        totalLength += dimension.getLength();
    }

    public List<BoxResponse> getBoxes() {
        return boxes;
    }

    public void reset() {
        products = new ArrayList<>();
        totalHeight = 0;
        totalWidth = 0;
        totalLength = 0;
        boxes = new ArrayList<>();
    }

}
