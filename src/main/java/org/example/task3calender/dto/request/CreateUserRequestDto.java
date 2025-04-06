package org.example.task3calender.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserRequestDto {

    @Size(min = 6, max = 30)
    @NotBlank
    private String account;

    @Size(min = 6, max = 30)
    @NotBlank
    private String password;

}
