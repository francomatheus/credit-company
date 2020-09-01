package br.com.loan.creditcompany.model.form;

import br.com.loan.creditcompany.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreditForm {

    private BigDecimal price;

    private UserEntity userEntity;

}
