package br.com.packaging.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoxesEnum {

    BOX_ONE("Caixa 1", 30, 40, 80),
    BOX_TWO("Caixa 2", 80, 50, 40),
    BOX_TREE("Caixa 3", 50, 80, 60);

    private final String name;
    private final Integer height;
    private final Integer width;
    private final Integer length;

}
