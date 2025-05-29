package br.com.packaging.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.packaging.Fixture;
import br.com.packaging.model.PackageRequest;
import br.com.packaging.model.PackageResponse;
import br.com.packaging.service.PackageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PackageControllerTest {

    @InjectMocks private PackageController controller;
    @Mock private PackageService service;

    @Test
    void givenPackageRequest_whenCallMakePackage_thenResponseBodyPackageResponse() {
        final PackageRequest packageRequest = Fixture.make(PackageRequest.builder().build());
        final PackageResponse packageResponse = Fixture.make(PackageResponse.builder().build());

        when(service.makePackage(any())).thenReturn(packageResponse);

        final ResponseEntity<PackageResponse> response = controller.makePackage(packageRequest);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(packageResponse.getOrders().size(), response.getBody().getOrders().size());

        verify(service).makePackage(packageRequest);
    }

}
