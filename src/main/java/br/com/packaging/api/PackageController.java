package br.com.packaging.api;

import br.com.packaging.model.PackageRequest;
import br.com.packaging.model.PackageResponse;
import br.com.packaging.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/package")
public class PackageController {

    private final PackageService service;

    @PostMapping
    public ResponseEntity<PackageResponse> makePackage(@RequestBody PackageRequest request) {
        final PackageResponse orderDTO = service.makePackage(request);
        return ResponseEntity.ok().body(orderDTO);
    }
}
