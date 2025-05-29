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
public class ProductRequest {

    @JsonProperty(value = "produto_id")
    private String id;

    @JsonProperty(value = "dimensoes")
    private ProductDimension dimension;

}
