package br.com.tecflix_app.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.Objects;
import java.util.UUID;

@Table
@Entity(name = "credit_card")
public class CreditCard {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "card_number", nullable = false, length = 20, unique = true)
    private String card_number;

    @Column(name = "cvv", nullable = false, length = 3)
    private String cvv;

    @Column(name = "month_expiration", nullable = false, length = 2)
    private Integer month_expiration;

    @Column(name = "year_expiration", nullable = false, length = 4)
    private Year year_expiration;

    @ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public CreditCard() {}

    public CreditCard(UUID id, String card_number, String cvv, Integer month_expiration, Year year_expiration, User user) {
        this.id = id;
        this.card_number = card_number;
        this.cvv = cvv;
        this.month_expiration = month_expiration;
        this.year_expiration = year_expiration;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public String getCard_number() {
        return card_number;
    }

    public String getCvv() {
        return cvv;
    }

    public Integer getMonth_expiration() {
        return month_expiration;
    }

    public Year getYear_expiration() {
        return year_expiration;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(id, that.id)
                && Objects.equals(card_number, that.card_number)
                && Objects.equals(cvv, that.cvv)
                && Objects.equals(month_expiration, that.month_expiration)
                && Objects.equals(year_expiration, that.year_expiration)
                && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                card_number,
                cvv,
                month_expiration,
                year_expiration,
                user
        );
    }
}
