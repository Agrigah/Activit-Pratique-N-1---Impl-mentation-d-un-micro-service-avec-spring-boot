package org.sid.bank_account_service.entities;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Date createAt;
    private double balance;
    private String currency;
    private AccountType type;

}
