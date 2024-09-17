package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    /**
     * Find category of product by item name
     * @param name
     * @return
     */
    @Query(value = "select c from Category c where c.name= :name")
    @EntityGraph(value = "category-entity-graph")
    Category findByName(@Param("name") String name);

    /**
     * Find and update category by item name
     * @param name
     */
    @Modifying(clearAutomatically = true)
    @Query("update Category  c set c.name = :name")
    void updateCategoryName(@Param("name") String name);

    /**
     * Find all category from db
     * @return
     */
    @EntityGraph(value = "category-entity-graph",type = EntityGraph.EntityGraphType.FETCH)
    List<Category> findAll();



}
