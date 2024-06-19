package com.webmanagement.dev.webmanagement_dev.dtos.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
  @NotNull
  @Size(min = 4, max = 50)
  private String username;

  @NotNull
  @Size(min = 8, max = 100)
  private String password;
}
