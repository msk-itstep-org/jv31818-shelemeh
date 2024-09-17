package org.itstep.msk.app.servicetests;

import org.itstep.msk.app.entity.Category;
import org.itstep.msk.app.entity.model.CategoryDTO;
import org.itstep.msk.app.mapper.CategoryMapper;
import org.itstep.msk.app.repository.CategoryRepository;
import org.itstep.msk.app.service.CategoryService;
import org.itstep.msk.app.service.CategoryServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryMapper mapper;


    @Mock
    private CategoryService service;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category1;
    private Category category2;
    private CategoryDTO categoryDTO;

    @Before
    public void setUp() {
        category1 = new Category();
        category1.setName("Sword");
        category1.setImage("Toy");
        category1.setId(2);
        category2 = new Category();
        category2.setId(3);
        category2.setImage("Plane");
        category2.setName("Buzlater");
        repository.save(category1);
        repository.save(category2);
        categoryDTO = new CategoryDTO();
        MockitoAnnotations.initMocks(this);


    }

    @Test
    void shouldReturnAllCategories() throws Exception {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        var list = categoryService.getAllCategories();
        verify(repository, times(1)).findAll();
        assertThat(list).isNotNull();
    }
}
