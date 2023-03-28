package com.mostafa.Task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name Must Not Be Null")
    @NotEmpty(message = "Name Must Not Be Empty")
    private String name;


    @NotNull(message = "Desc Must Not Be Null")
    @NotEmpty(message = "Desc Must Not Be Empty")
    private String description;

    @ManyToOne
    @JsonIgnore
    private Category category;
}
