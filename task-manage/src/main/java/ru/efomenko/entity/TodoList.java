package ru.efomenko.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todolist")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany()
    @JoinColumn(name = "task_id")
    private List<Task> tasks = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    private String name;
}
