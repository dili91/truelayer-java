package truelayer.java.payments.entities.paymentdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import truelayer.java.payments.entities.beneficiary.BaseBeneficiary;
import truelayer.java.payments.entities.paymentmethod.BasePaymentMethod;

import static truelayer.java.payments.entities.paymentdetail.BasePaymentDetail.Status.AUTHORIZATION_REQUIRED;
import static truelayer.java.payments.entities.paymentdetail.BasePaymentDetail.Status.AUTHORIZING;

@Getter
@Value
@EqualsAndHashCode(callSuper = true)
public class Authorizing extends BasePaymentDetail {
    @JsonProperty("id")
    private String id;

    @JsonProperty("amount_in_minor")
    private int amountInMinor;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("beneficiary")
    private BaseBeneficiary beneficiary;

    @JsonProperty("user")
    private User user;

    @JsonProperty("payment_method")
    private BasePaymentMethod paymentMethod;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("authorization_flow")
    private AuthorizationFlow authorizationFlow;

    @JsonProperty("status")
    private final Status status = AUTHORIZING;

    @Getter
    @Value
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AuthorizationFlow {

        @JsonProperty("actions")
        private Actions actions;

        @JsonProperty("configuration")
        private BasePaymentDetail.AuthorizationFlow.Configuration configuration;

        @Value
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Actions {

            @JsonProperty("next")
            private BaseAuthorizationFlowAction next;
        }
    }
}
