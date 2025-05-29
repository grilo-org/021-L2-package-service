package br.com.packaging.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDimension {

    @JsonProperty(value = "altura")
    private Integer height;

    @JsonProperty(value = "largura")
    private Integer width;

    @JsonProperty(value = "comprimento")
    private Integer length;
}
