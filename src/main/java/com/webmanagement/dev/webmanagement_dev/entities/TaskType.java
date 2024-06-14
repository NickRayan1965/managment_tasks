package com.webmanagement.dev.webmanagement_dev.entities;

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
@Table(name = "task_types")
public class TaskType {
  @Id
  private Long id; 

  //varchar 50 not null unique
  @Column("name")
  private String name;

  //boolean not null default true
  @Column("enabled")
  private Boolean enabled;
}
