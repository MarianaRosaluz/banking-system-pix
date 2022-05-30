package br.rosaluz.banking.system.pix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PixDTO {

    public String accountOrigin;
    public String pixKey;
    public Double value;

}
