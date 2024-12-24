package br.com.tecflix_app.model;

import java.io.Serializable;

import br.com.tecflix_app.model.enums.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity(name = "bank_accounts_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class BankAccountData implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 5)
    private String agency;

    @Column(name = "account_number", nullable = false, length = 12)
    private String accountNumber;

    @Column(nullable = false)
    private Character dv;

    @Enumerated(EnumType.STRING)
    private Account account;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
