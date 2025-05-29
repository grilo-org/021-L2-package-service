package br.com.packaging.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.packaging.Fixture;
import br.com.packaging.model.PackageRequest;
import br.com.packaging.model.PackageResponse;
import br.com.packaging.model.response.BoxResponse;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PackageServiceTest {

    @InjectMocks private PackageService packageService;
    @Mock private PackageCalculate packageCalculate;

    @Test
    void givenPackageRequest_whenCallMakePackage_shouldReturnPackageResponse() {
        final PackageRequest packageRequest = Fixture.make(PackageRequest.builder().build());
        final BoxResponse boxResponse = Fixture.make(BoxResponse.builder().build());
        final int count = packageRequest.getOrders().size();

        doNothing().when(packageCalculate).setProducts(any());
        doNothing().when(packageCalculate).calculate();
        when(packageCalculate.getBoxes()).thenReturn(Collections.singletonList(boxResponse));
        doNothing().when(packageCalculate).reset();

        final PackageResponse packageResponse = packageService.makePackage(packageRequest);
        assertNotNull(packageResponse);

        verify(packageCalculate, times(count)).setProducts(any());
        verify(packageCalculate, times(count)).calculate();
        verify(packageCalculate, times(count)).getBoxes();
        verify(packageCalculate, times(count)).reset();
    }
}
