package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("isInsuranceAvailable")
    private Boolean isInsuranceAvailable;

    @JsonProperty("insuranceMapping")
    private UserInsuranceMapping insuranceMapping;

    @JsonProperty("providerIdList")
    private List<String> providerIdList;

    @JsonProperty("clinicIdList")
    private List<String> clinicIdList;

    @JsonProperty("serviceNameList")
    private List<String> serviceNameList;

    @JsonProperty("pricingList")
    private List<Policy> pricingList;

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", errorMessage='" + errorMessage + '\'' +
                ", isInsuranceAvailable=" + isInsuranceAvailable +
                ", insuranceMapping=" + insuranceMapping +
                ", providerIdList=" + providerIdList +
                ", clinicIdList=" + clinicIdList +
                ", serviceNameList=" + serviceNameList +
                ", pricingList=" + pricingList.toString() +
                '}';
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class UserInsuranceMapping {
        private long id;
        private String userId;
        private String policyNumber;
        private String productCode;
        private Date expiryDate;
        private Boolean isMain;
        private String identificationNum;
        private String membershipId;
        private String fullName;
        private Date dob;
        private String label;
        private Boolean tncAccepted;
        private Insurance insurance;
        private Policy insurancePolicy;
        private Date insertDateTime;
        private Date updateDateTime;
        
        @Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Insurance {
            private long insuranceId;
            private String type;
            private String name;
            private String nameOriginCountry;
            private String description;
            private String image;
            private String primaryLogo;
            private String secondaryLogo;
            private String originCountry;
            private Boolean isOnlineMemberValidation;
            private Boolean isOnlineClaim;
            private Boolean isTermsAndConditionDisplay;
            private String termsAndCondition;
            private Boolean isActive;
            private Date insertDateTime;
            private Date updateDateTime;
        }
    }


}
