package truelayer.java.payments.entities.beneficiary;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
public class ExternalAccount extends Beneficiary {
    private final String type = "external_account";

    private String name;

    private String reference;

    private SchemeIdentifier schemeIdentifier;

    @Builder
    @Getter
    @ToString
    @EqualsAndHashCode
    public static class SchemeIdentifier {
        private final String type = "sort_code_account_number";

        private String sortCode;

        private String accountNumber;
    }
}
