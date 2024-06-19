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
@Table(name = "tasks")
public class Task {

  @Schema(title = "Identificador de la tarea", nullable = false)
  @Id
  private Long id;

  //varchar 50 not null
  @Schema(description = "Nombre de la tarea", maxLength = 50, minLength = 4, nullable = false)
  @Column("name")
  private String name;

  //text
  @Schema(description = "Descripción de la tarea", nullable = true, maxLength = 1000)
  @Column("description")
  private String description;

  //bigint
  @Schema(description = "Identificador del tipo de tarea", nullable = true)
  @Column("type_id")
  private Long typeId;

  //bigint not null
  @Schema(description = "Identificador del usuario", nullable = false)
  @Column("user_id")
  private Long userId;

  //boolean not null default true
  @Schema(description = "Habilitado", nullable = false, defaultValue = "true")
  @Column("enabled")
  private Boolean enabled;
}
