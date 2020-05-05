package webapp.model;

public enum ContactsType {
    TELEPHONE("Номер телефона") {
        @Override
        public String toHtml0(String value) {
            return "<a href='tel:" + value + "'>" + value + "</a>";
        }
    },
    SKYPE("Скайп") {
        @Override
        public String toHtml0(String value) {
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    },
    MAIL("Почта") {
        @Override
        public String toHtml0(String value) {
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
    LINKEDIN("Профиль на LinkedIN") {
        @Override
        public String toHtml0(String value) {
            return "<a href='https://www.linkedin.com" + value + "'>" + value + "</a>";
        }
    },
    GITHUB("Профиль на GitHub") {
        @Override
        public String toHtml0(String value) {
            return "<a href='gitHub:" + value + "'>" + value + "</a>";
        }
    },
    STACKOVERFLOW("Профиль на StackOverflow") {
        @Override
        public String toHtml0(String value) {
            return "<a href='https://stackoverflow.com" + value + "'>" + value + "</a>";
        }
    };

    private final String contactName;

    ContactsType(String contactName) {
        this.contactName = contactName;
    }

    public String getContact() {
        return contactName;
    }

    protected String toHtml0(String value) {
        return contactName + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }
}
