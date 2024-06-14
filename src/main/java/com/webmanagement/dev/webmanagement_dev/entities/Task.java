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
@Table(name = "tasks")
public class Task {
  @Id
  private Long id;

  //varchar 50 not null
  @Column("name")
  private String name;

  //text
  @Column("description")
  private String description;

  //bigint
  @Column("type_id")
  private Long typeId;

  //bigint not null
  @Column("user_id")
  private Long userId;

  //boolean not null default true
  @Column("enabled")
  private Boolean enabled;
}
