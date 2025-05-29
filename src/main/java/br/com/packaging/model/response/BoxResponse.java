package br.com.packaging.model.response;

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
public class BoxResponse {

    @JsonProperty(value = "caixa_id")
    private String id;

    @JsonProperty(value = "produtos")
    private List<String> products;

}
