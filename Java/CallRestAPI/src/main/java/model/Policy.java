package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Policy {
    private long id;
    private InsurancePolicy insurancePolicy;
    private ConsultType consultType;
    private String programme;
    private BigDecimal basePrice;
    private BigDecimal price;
    private BigDecimal medDiscountPercent;
    private BigDecimal discountLimit;
    private String discountLimitDuration;
    private Boolean isDailyLimit;
    private String discountType;
    private Date insertDateTime;
    private Date updateDateTime;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class InsurancePolicy {
        private long insurancePolicyId;
        private long insuranceId;
        private String policyNumber;
        private String policyName;
        private String companyName;
        private Date expiryDate;
        private Date insertDateTime;
        private Date updateDateTime;
    }
}
