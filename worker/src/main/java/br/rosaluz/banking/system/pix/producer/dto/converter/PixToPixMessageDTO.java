package br.rosaluz.banking.system.pix.producer.dto.converter;

import br.rosaluz.banking.system.pix.model.Pix;
import br.rosaluz.banking.system.pix.producer.dto.PixMessageDTO;

import java.util.Optional;

public class PixToPixMessageDTO {

    public static PixMessageDTO convert(Pix pix) {
        return Optional.ofNullable(pix).map(pixDTOChecked -> PixMessageDTO.builder()
                .accountOrigin(pixDTOChecked.getAccountOrigin())
                .pixKey(pixDTOChecked.getPixKey())
                .value(pixDTOChecked.getValue())
                .build()).orElse(null);
    }
}
