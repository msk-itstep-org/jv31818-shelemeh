package org.itstep.msk.app.entitytests;

import org.itstep.msk.app.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CategoryTest {

    private Category category;

    @BeforeEach
    void setUpTest(){
        category =new Category();
        category.setId(2);
        category.setName("dog food");
    }

    @Test
    void testCategoryShouldBeEquals(){
        Category category1 = new Category();
        category1.setId(2);
        category1.setName("dog food");

        assertEquals(category.getName(),category1.getName());
        assertNotEquals(category1.getId(),category.getId()+1);
    }
}
