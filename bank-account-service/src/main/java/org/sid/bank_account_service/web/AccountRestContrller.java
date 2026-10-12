package org.sid.bank_account_service.web;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountRestContrller {
    private BankAccountRepository bankAccountRepository;
    public AccountRestContrller(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount>bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts( @PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account   %s not found",id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody  BankAccount bankAccount){
       if(bankAccount.getId()==null) bankAccount.setId(UUID.randomUUID().toString());
       return bankAccountRepository.save(bankAccount);

    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,  BankAccount bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreateAt()!=null) account.setCreateAt(new Date());
        if (bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if (bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);

    }
    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount( @PathVariable String id){
         bankAccountRepository.deleteById(id);

    }


}
