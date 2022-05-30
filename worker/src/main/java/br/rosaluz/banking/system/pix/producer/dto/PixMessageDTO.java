package br.rosaluz.banking.system.pix.producer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PixMessageDTO {

    private long id;
    private String accountOrigin;
    private String pixKey;
    private Double value;

}
