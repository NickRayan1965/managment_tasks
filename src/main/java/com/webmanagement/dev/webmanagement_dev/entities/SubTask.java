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
@Table(name = "subtasks")
public class SubTask {

  @Schema(title = "Identificador de la subtarea", nullable = false)
  @Id
  private Long id;

  @Schema(description = "Identificador de la tarea", nullable = true)
  @Column("task_id")
  private Long taskId;

  @Schema(description = "Nombre de la subtarea", maxLength = 50, minLength = 4, nullable = false)
  @Column("name")
  private String name;

  @Schema(description = "Orden de la subtearea", nullable = false)
  @Column("ordernumber")
  private Integer order;

  @Schema(description = "¿Esta completa?", nullable = false, defaultValue = "true")
  @Column("is_completed")
  private Boolean isCompleted;
}
