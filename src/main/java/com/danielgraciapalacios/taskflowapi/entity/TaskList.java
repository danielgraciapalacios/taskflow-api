package com.danielgraciapalacios.taskflowapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasklist")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public TaskList(){}
}
