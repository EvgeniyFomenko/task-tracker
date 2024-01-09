package ru.efomenko.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @OneToOne
    @JoinColumn(name = "file_id")
    private File fileId;
    @ManyToOne
    @JoinColumn(name = "todolist_id")
    private TodoList todoList;
}
