package org.itstep.msk.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itstep.msk.app.entity.Category;
import org.itstep.msk.app.entity.model.CategoryDTO;
import org.itstep.msk.app.mapper.CategoryMapper;
import org.itstep.msk.app.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper mapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findCategoryByName(@NotNull String cName) {
        return Optional.of(categoryRepository.findByName(cName))
                .stream()
                .filter(category -> category.getName().equals(cName))
                .map(mapper::fromEntity)
                .findAny()
                .get();
    }

    @Override
    public CategoryDTO getUpdatedCategory(@NotNull String cName) {
        Optional<Category> optionalCategory = Optional.of(categoryRepository.findByName(cName));
        Category category = optionalCategory.get();
        category.setName(category.getName());
        category.setImage(category.getImage());
        categoryRepository.save(category);
        return mapper.fromEntity(category);
    }
}
