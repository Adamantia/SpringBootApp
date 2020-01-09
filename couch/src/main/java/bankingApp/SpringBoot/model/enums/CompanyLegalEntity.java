package bankingApp.SpringBoot.model.enums;

public enum CompanyLegalEntity {

    BESLOTEN_VENNOOTSCHAP("Private Company"),
    NAAMLOZE_VENNOOTSCHAP("Limited Company"),
    EENMANSZAAK("Proprietorship"),
    VERENIGING_OF_STICHTING("Association or Foundation"),
    VENNOOTSCHAP_ONDER_FIRMA("General Partnership");

    private final String displayEntity;

    CompanyLegalEntity(String displayEntity) {
        this.displayEntity = displayEntity;
    }

    public String getDisplayEntity() {
        return displayEntity;
    }
}
