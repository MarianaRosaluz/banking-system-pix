package br.rosaluz.banking.system.pix.service;

import br.rosaluz.banking.system.pix.model.Pix;
import br.rosaluz.banking.system.pix.producer.PixProducer;
import br.rosaluz.banking.system.pix.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PixServiceImpl implements PixService {


    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private PixProducer pixProducer;


    public PixServiceImpl(PixRepository pixRepository) {
        this.pixRepository = pixRepository;
    }

    @Override
    public  boolean pix(Pix pix){
        pixProducer.send(pix);
        save(pix);
        return  true;
    }

    @Override
    public Pix save(Pix pix){
        return  pixRepository.save(pix);
    }

    @Override
    public Optional<Pix> findById(long pixId){
        return pixRepository.findById(pixId);
    }

}
