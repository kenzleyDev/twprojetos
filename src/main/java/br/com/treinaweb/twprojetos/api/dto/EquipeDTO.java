package br.com.treinaweb.twprojetos.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class EquipeDTO {

    @NotNull
    @NotEmpty
    private List<Long> equipeId;

    public List<Long> getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(List<Long> equipeId) {
        this.equipeId = equipeId;
    }
}
