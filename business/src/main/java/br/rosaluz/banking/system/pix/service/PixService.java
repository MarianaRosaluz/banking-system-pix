package br.rosaluz.banking.system.pix.service;


import br.rosaluz.banking.system.pix.model.Pix;

import java.util.Optional;

public interface PixService {

    public Pix save(Pix pix);
    public  boolean pix(Pix pixDTO);
    public Optional<Pix> findById(long pixId);
}
