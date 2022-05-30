package br.rosaluz.banking.system.pix.dto.converter;

import br.rosaluz.banking.system.pix.dto.PixDTO;
import br.rosaluz.banking.system.pix.model.Pix;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

public class PixDTOToPix implements Converter<PixDTO, Pix> {

    @Override
    public Pix convert(PixDTO pixDTO) {
        return Optional.ofNullable(pixDTO).map(pixDTOChecked -> Pix.builder()
                .accountOrigin(pixDTOChecked.getAccountOrigin())
                .pixKey(pixDTOChecked.getPixKey())
                .value(pixDTOChecked.getValue())
                .build()).orElse(null);
    }

}
