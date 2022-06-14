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

@CrossOrigin(origins = "*")
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

            var pix = conversionService.convert(pixDTO, Pix.class);
             pixService.pix(pix);

                return ResponseEntity.ok().build();


    }
    @GetMapping("status/{pixId}")
    public ResponseEntity<?> statusPix(@PathVariable long pixId){

        Pix pix = pixService.findById(pixId).get();
        return ResponseEntity.ok(conversionService.convert(pix, PixResponseDTO.class));

    }
}
