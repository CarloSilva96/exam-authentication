package com.examauthentication.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles", schema = "base")
@SequenceGenerator(name = "role_seq", schema = "base", allocationSize = 1)
public class Role {
    @Id
    @GeneratedValue(generator = "task_seq" , strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "role_type", nullable = false)
    private String roleType;
}
