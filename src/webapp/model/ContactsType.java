package webapp.model;

public enum ContactsType {
    TELEPHONE("Номер телефона"),
    SKYPE("Скайп"),
    MAIL("Почта"),
    LINKEDIN("Профиль на LinkedIN"),
    GITHUB("Профиль на GitHub"),
    STACKOVERFLOW("Профиль на StackOverflow");

    private final String contactName;

    ContactsType(String contactName) {
        this.contactName = contactName;
    }

    public String getContact() {
        return contactName;
    }
}
