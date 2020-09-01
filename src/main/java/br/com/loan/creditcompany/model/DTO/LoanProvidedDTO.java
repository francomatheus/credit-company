package br.com.loan.creditcompany.model.DTO;

import br.com.loan.creditcompany.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanProvidedDTO {

    private Long id;
    private LocalDateTime dayOfLoanProvided;
    private BigDecimal price;

    private UserDTO userDTO;
}
