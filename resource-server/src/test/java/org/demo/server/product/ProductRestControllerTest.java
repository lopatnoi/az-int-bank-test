package org.demo.server.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductRestControllerTest {

    @Mock
    private ProductRepository productRepository;

    private ProductRestController productRestController;

    @BeforeEach
    void beforeEach() {
        productRestController = new ProductRestController(productRepository);
    }

    @Test
    @DisplayName("successfully return list of products from repository")
    void productsWhenRepositoryThenReturnAll() {
        when(productRepository.findAll()).thenReturn(productList());
        Collection<Product> actual = productRestController.products();

        assertThat(actual).containsOnly(productList().toArray(new Product[0]));
    }

    @Test
    @DisplayName("successfully return empty if repository not found any products")
    void productsWhenRepositoryEmptyThenReturnEmpty() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        Collection<Product> actual = productRestController.products();

        assertThat(actual).isEmpty();
    }

    private List<Product> productList() {
        return LongStream.range(0, 5)
                .mapToObj(id -> {
                    Product p = new Product();
                    p.setId(id);
                    p.setName("product " + id);
                    return p;
                }).collect(Collectors.toList());
    }
}