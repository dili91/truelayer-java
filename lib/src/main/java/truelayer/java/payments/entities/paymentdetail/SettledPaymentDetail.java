package truelayer.java.payments.entities.paymentdetail;

import static truelayer.java.payments.entities.paymentdetail.Status.SETTLED;

import java.util.Date;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class SettledPaymentDetail extends PaymentDetail {

    Status status = SETTLED;

    SourceOfFunds sourceOfFunds;

    Date succeededAt;

    Date settledAt;

    Date executedAt;

    Optional<AuthorizationFlowWithConfiguration> authorizationFlow;
}
