package br.com.loan.creditcompany.model.domain;

import br.com.loan.creditcompany.model.entity.UserEntity;
import br.com.loan.creditcompany.model.enums.StatusLoanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreditDomain {

    private Long id;

    private LocalDateTime dateOfRequest;

    private LocalDateTime dateOfAvailable;

    private StatusLoanEnum Status;

    private BigDecimal price;

    private UserEntity userEntity;

}
