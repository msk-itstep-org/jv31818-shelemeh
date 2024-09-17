package org.itstep.msk.app.service;

import org.itstep.msk.app.entity.model.CategoryDTO;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO findCategoryByName(@NotNull String cName);

    CategoryDTO getUpdatedCategory(@NotNull String cName);
}
