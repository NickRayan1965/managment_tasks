package com.webmanagement.dev.webmanagement_dev.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import io.swagger.v3.oas.annotations.media.Schema;
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

  @Schema(description = "Identificador del tipo de tarea", nullable = false)
  @Id
  private Long id; 

  //varchar 50 not null unique
  @Schema(description = "Nombre del tipo de tarea (único)", maxLength = 50, minLength = 4, nullable = false)
  @Column("name")
  private String name;

  //boolean not null default true
  @Schema(description = "Habilitado", nullable = false, defaultValue = "true")
  @Column("enabled")
  private Boolean enabled;
}
