package com.examauthentication.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks", schema = "base")
@SequenceGenerator(name = "task_seq", schema = "base", allocationSize = 1)
public class Task {
    @Id
    @GeneratedValue(generator = "task_seq" , strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

}
