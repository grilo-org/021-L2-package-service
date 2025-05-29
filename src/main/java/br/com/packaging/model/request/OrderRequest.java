package br.com.packaging.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @JsonProperty(value = "pedido_id")
    private Long id;

    @JsonProperty(value = "produtos")
    private List<ProductRequest> products;

}
