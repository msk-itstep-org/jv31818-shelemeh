package org.itstep.msk.app.mapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.itstep.msk.app.entity.Category;
import org.itstep.msk.app.entity.model.CategoryDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class CategoryMapper {

    public final ModelMapper mapper;

    public Category fromDto(CategoryDTO categoryDTO) {
        this.mapper.getConfiguration().setSkipNullEnabled(false);
        Category category = mapper.map(categoryDTO, Category.class);
        category.setId(categoryDTO.getId());
        category.setImage(categoryDTO.getImage());
        category.setName(categoryDTO.getName());
        return category;
    }

    public CategoryDTO fromEntity(Category category) {
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.mapper.getConfiguration().setSkipNullEnabled(true);
        CategoryDTO categoryDTO = mapper.map(category,CategoryDTO.class);
        categoryDTO.setId(category.getId());
        categoryDTO.setImage(category.getImage());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

}
