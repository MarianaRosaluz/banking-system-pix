package br.rosaluz.banking.system.pix.configuration;

import br.rosaluz.banking.system.pix.dto.converter.PixDTOToPix;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new PixDTOToPix());
    }
}
