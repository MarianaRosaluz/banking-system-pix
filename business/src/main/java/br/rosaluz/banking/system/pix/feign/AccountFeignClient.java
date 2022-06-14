package br.rosaluz.banking.system.pix.feign;


import br.rosaluz.banking.system.pix.feign.dto.AccountDTO;
import br.rosaluz.banking.system.pix.feign.dto.BalanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@FeignClient(name = "account", url = "${banking-system-account-url}")
public interface AccountFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/banking/system/account/findByAccount/{accountNumber}")
    @ResponseBody
    ResponseEntity<AccountDTO> getAccount(@PathVariable final String accountNumber);

    @RequestMapping(method = RequestMethod.POST, value = "/api/banking/system/account/balance/increase")
    @ResponseBody
    ResponseEntity<?> increaseBalance(@RequestBody final BalanceDTO balanceDTO);

    @RequestMapping(method = RequestMethod.POST, value = "/api/banking/system/account/balance/decrease")
    @ResponseBody
    ResponseEntity<?> decreaseBalance(@RequestBody  final BalanceDTO balanceDTO);

}
