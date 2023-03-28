package com.mostafa.Task.repository;

import com.mostafa.Task.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public List<Category> findAllByIsParent(boolean isParent);


}
