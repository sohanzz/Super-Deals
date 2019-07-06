package com.asifahmedsohan.superoffer;

public class Offer {

    private String companyName;
    private String companyLogo;
    private String description_short;
    private String description_full;
    private String validityDate;

    public Offer() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Offer(String companyName, String companyLogo, String description_short, String description_full, String validityDate) {
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.description_short = description_short;
        this.description_full = description_full;
        this.validityDate = validityDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getDescription_short() {
        return description_short;
    }

    public void setDescription_short(String description_short) {
        this.description_short = description_short;
    }

    public String getDescription_full() {
        return description_full;
    }

    public void setDescription_full(String description_full) {
        this.description_full = description_full;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }
}
