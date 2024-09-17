package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.model.CategoryDTO;
import org.itstep.msk.app.mapper.CategoryMapper;
import org.itstep.msk.app.repository.CategoryRepository;
import org.itstep.msk.app.service.CategoryService;
import org.itstep.msk.app.service.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @MockBean
    private CategoryServiceImpl service;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private CategoryMapper categoryMapper;


    @Autowired
    private MockMvc mockMvc;

    private List<CategoryDTO> dtoList;
    private CategoryDTO dto1;
    private CategoryDTO dto2;

    @BeforeEach
    void setUp() {
        dto1 = new CategoryDTO();
        dto1.setImage("Pill");
        dto1.setId(3);
        dto1.setName("Marco");
        dto2 = new CategoryDTO();
        dto2.setName("Krill");
        dto2.setId(5);
        dto2.setImage("Fallen");
        this.dtoList = new ArrayList<>();
        this.dtoList.add(dto1);
        this.dtoList.add(dto2);
    }

    @Test
    void test_get_All_categoryDTO() throws Exception {
        given(service.getAllCategories()).willReturn(dtoList);
        mockMvc.perform(MockMvcRequestBuilders.get("/category/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()")
                        .value(dtoList.size()));
    }

    @Test
    void test_get_current_Category_Dto_by_item_name() throws Exception {
        final String name = dto1.getName();
        given(service.findCategoryByName(anyString())).willReturn(dto1);
        mockMvc.perform(MockMvcRequestBuilders.get("/category/{name}", name)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(dto1.getName()));
    }

    @Test
    void test_update_CategoryDto_by_item_name() throws Exception {
        final String cName = dto1.getName();
        when(service.getUpdatedCategory(anyString())).thenReturn(any());
        mockMvc.perform(MockMvcRequestBuilders.patch("/category/{name}", cName)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());

    }

}
