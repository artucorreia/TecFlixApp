package br.com.tecflix_app.data.DTO.v1.util;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class IdDTO <T> {
    @NotNull
    private T id;
}
