package br.rosaluz.banking.system.pix.service;


import br.rosaluz.banking.system.pix.model.Pix;

import java.util.Optional;

public interface PixService {

     Pix save(Pix pix);
      void pix(Pix pixDTO);
     Optional<Pix> findById(long pixId);
}
