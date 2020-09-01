package br.com.loan.creditcompany.model.entity;

import br.com.loan.creditcompany.model.enums.ErrorRequestEnum;
import br.com.loan.creditcompany.model.enums.StatusLoanEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "request_credit")
public class RequestCreditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOfRequest;

    private LocalDateTime dateOfAvailable;

    @Enumerated(EnumType.STRING)
    private StatusLoanEnum Status;

    @Enumerated(EnumType.STRING)
    private ErrorRequestEnum error;

    @NotNull
    private BigDecimal price;

    @ManyToOne
    private UserEntity userEntity;
}
