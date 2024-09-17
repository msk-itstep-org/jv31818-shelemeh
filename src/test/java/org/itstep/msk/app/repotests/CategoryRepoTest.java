package org.itstep.msk.app.repotests;

import org.itstep.msk.app.entity.Category;
import org.itstep.msk.app.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepoTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUpTest() {
        category = new Category();
        category.setName("flood");
        category.setId(2);
        category.setImage("camera");
        categoryRepository.save(category);
    }

    @AfterEach
    void tearDownTest() {
        categoryRepository.deleteAll();
    }

    @Test
    @Transactional
    void shouldUpdateCategorySuccess() {
        Category category1 = new Category();
        category1.setName("kitty food");
        category1.setImage("food");
        categoryRepository.save(category1);

        categoryRepository.updateCategoryName(category1.getName());
        var category2 = categoryRepository.findByName(category1.getName());
        assertEquals(category2.getName(), category1.getName());

    }

    @Test
    @DisplayName("test should check and return one row of category")
    void should_return_at_least_one_category() {
        var categories = categoryRepository.findAll();
        assertThat(categories).isNotNull()
                .isIn(category.getId(), category.getImage(), categories);

    }

    @Test
    @DisplayName("test for checks correct returns category by its name")
    void should_return_category_by_name() {
        var currentCategory = categoryRepository.findByName(category.getName());
        assertEquals(currentCategory.getName(), "flood");
    }


}
