package br.rosaluz.banking.system.pix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pix {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @NotNull
    private String accountOrigin;
    @NotNull
    private String pixKey;
    @NotNull
    private Double value;
}
