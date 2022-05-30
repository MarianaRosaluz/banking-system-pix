package br.rosaluz.banking.system.pix.controller;

import br.rosaluz.banking.system.pix.dto.PixDTO;
import br.rosaluz.banking.system.pix.dto.PixResponseDTO;
import br.rosaluz.banking.system.pix.model.Pix;
import br.rosaluz.banking.system.pix.service.PixService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/banking/system/pix", produces="application/json")
@Api(value="API REST Banking System")
@RequiredArgsConstructor
public class PixController {

    @Autowired
    private PixService pixService;

    private final ConversionService conversionService;


    @PostMapping()
    public ResponseEntity<?> pix(@RequestBody @Valid PixDTO pixDTO )throws Exception {
        try {

            var pix = conversionService.convert(pixDTO, Pix.class);
            Boolean completed = pixService.pix(pix);
            if (completed) {

                return ResponseEntity.ok().build();
            } else throw new Exception("O pix não foi realizada");

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("status/{pixId}")
    public ResponseEntity<?> statusPix(@PathVariable long pixId){

        Pix pix = pixService.findById(pixId).get();
        return ResponseEntity.ok(conversionService.convert(pix, PixResponseDTO.class));

    }
}
