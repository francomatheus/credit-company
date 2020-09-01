package br.com.loan.creditcompany.model.DTO;

import br.com.loan.creditcompany.model.enums.ErrorRequestEnum;
import br.com.loan.creditcompany.model.enums.StatusLoanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusOfRequestDTO {

    private Long id;

    private LocalDateTime dateOfRequest;

    private LocalDateTime dateOfAvailable;

    private StatusLoanEnum Status;

    private ErrorRequestEnum error;

}
