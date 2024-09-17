package org.itstep.msk.app.entitytests;

import org.itstep.msk.app.entity.Category;
import org.itstep.msk.app.entity.model.CategoryDTO;
import org.itstep.msk.app.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryConvertDtoTest {

    private ModelMapper modelMapper = new ModelMapper();
    private CategoryMapper categoryMapper = new CategoryMapper(modelMapper);

    @Test
    void convert_to_Dto_test() {
        Category category = new Category();
        category.setImage("Gty");
        category.setId(5);
        category.setName("Board");

        CategoryDTO dto = categoryMapper.fromEntity(category);
        assertEquals(category.getName(), dto.getName());

    }

    @Test
    void convert_From_DTO_TO_Entity_test() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Heli");
        categoryDTO.setId(3);
        categoryDTO.setImage("Frodo");

        Category category = categoryMapper.fromDto(categoryDTO);
        assertEquals(category.getId(), categoryDTO.getId());
    }
}
