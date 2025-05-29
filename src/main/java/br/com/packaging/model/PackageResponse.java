package br.com.packaging.model;

import br.com.packaging.model.response.OrderResponse;
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
public class PackageResponse {

    @JsonProperty(value = "pedidos")
    private List<OrderResponse> orders;

}
