package bankingApp.SpringBoot.model.enums;

public enum CompanySector {

    ICT("ICT"),
    CONSULTANCY("Consultancy"),
    CONSTRUCTION_AND_INFRASTRUCTURE("Construction and Infrastructure"),
    ENERGY("Energy"),
    FINANCIAL_SERVICES("Financial Services"),
    WATER_AND_WASTE("Water and Waste"),
    INDUSTRY("Industry"),
    RETAIL("Retail"),
    AGRICULTURAL_SECTOR("Agricultural Sector"),
    CATERING("Catering"),
    EDUCATION("Education "),
    NON_PROFIT_ORGANIZATION("Non-profit Organization"),
    EDUCATION_AND_TRAINING("Education and Training"),
    HEALTHCARE("Healthcare"),
    TRANSPORT("Transport");

    private final String displayName;

    CompanySector(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
