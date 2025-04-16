package com.portfolio.contact_service.enums;

public enum ActionTypes {
    VIEW_PORTFOLIO("view_portfolio"),
    DOWNLOAD_RESUME("download_resume"),
    EMAIL_CONTACT("email_contact");
    private String value;

    private ActionTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
