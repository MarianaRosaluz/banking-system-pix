package br.rosaluz.banking.system.pix.service;

import br.rosaluz.banking.system.pix.exception.AccountNotFoundException;
import br.rosaluz.banking.system.pix.exception.TransferNotCompletedExeption;
import br.rosaluz.banking.system.pix.feign.AccountFeignClient;
import br.rosaluz.banking.system.pix.feign.dto.AccountDTO;
import br.rosaluz.banking.system.pix.feign.dto.BalanceDTO;
import br.rosaluz.banking.system.pix.model.Pix;
import br.rosaluz.banking.system.pix.producer.PixProducer;
import br.rosaluz.banking.system.pix.repository.PixRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PixServiceImpl implements PixService {


    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private PixProducer pixProducer;

    @Autowired
    private AccountFeignClient accountFeignClient;


    public PixServiceImpl(PixRepository pixRepository) {
        this.pixRepository = pixRepository;
    }

    @Override
    public void pix(Pix pix){
        makePix(pix);
        pixProducer.send(pix);
        save(pix);
    }
    private void makePix(Pix pix) {

        var accountOrigin = getAccount(pix.getAccountOrigin());

        makeDecreaseBalance(
                BalanceDTO.builder()
                        .accountNumber(pix.getAccountOrigin())
                        .amount(pix.getValue()).build());
    }

    private void  makeDecreaseBalance(BalanceDTO balanceDTO){

        ResponseEntity<?> responseEntity = accountFeignClient.decreaseBalance(balanceDTO);

        if (responseEntity.getStatusCodeValue() != 200)
        {
            throw new TransferNotCompletedExeption("Transfer cannot be  completed", "transfer");
        }
    }
    private AccountDTO getAccount(String accountNumber){

        ResponseEntity<AccountDTO> accountResponseEntity = accountFeignClient.getAccount(accountNumber);
        if (accountResponseEntity.getStatusCodeValue() != 200)
        {
            throw new AccountNotFoundException("Account not Found", "accountNumber");
        }
        return accountResponseEntity.getBody();
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
