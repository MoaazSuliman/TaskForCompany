package com.mostafa.Task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name Must Not Be Null")
    @NotEmpty(message = "Name Must Not Be Empty")
    private String name;
    @NotNull(message = "Desc Must Not Be Null")
    @NotEmpty(message = "Desc Must Not Be Empty")
    private String description;
    @JsonIgnore
    private boolean isParent;
    @OneToMany(mappedBy = "category")
    // @JoinColumn(name = "category_id")
    private List<Item> items;

    @ManyToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    private Category parentCategory;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Category> subCategory;


    public void addSubCategory(Category category) {
        this.subCategory.add(category);
    }
}