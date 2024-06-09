package com.web5b.guatemala.web5b_guatemala.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
  @Id
  private Long id;

  @Column("name")
  private String name;

  @Column("description")
  private String description;

  @Column("type_id")
  private Long typeId;

  @Column("user_id")
  private Long userId;

  @Column("enabled")
  private Boolean enabled;
}
