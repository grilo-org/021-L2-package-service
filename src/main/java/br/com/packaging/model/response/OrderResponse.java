package br.com.packaging.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    @JsonProperty(value = "pedido_id")
    private Long id;

    @JsonProperty(value = "caixas")
    private List<BoxResponse> boxes = new ArrayList<>();

}
