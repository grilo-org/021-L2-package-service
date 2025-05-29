package br.com.packaging.model;

import br.com.packaging.model.request.OrderRequest;
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
public class PackageRequest {

    @JsonProperty(value = "pedidos")
    private List<OrderRequest> orders;

}
