package controller.validation;

public interface RegexContainer {

    String REGEX_NAME = "[A-Za-zА-Яа-яЁёІіЇїЄє']{2,20}";
    String REGEX_EMAIL = "(?:[a-z0-9!#$\\&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$\\&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    String REGEX_PASSWORD = "[\\d\\w_]{4,20}";
    String REGEX_CATEGORY = "[A-Za-zА-Яа-яЁёІіЇїЄє'0-9_]{2,20}";
    String REGEX_TITLE = "[A-Za-zА-Яа-яЁёІіЇїЄє'\\s_\\-\\.\\!]{2,50}";
    String REGEX_TAGS = "[A-Za-zА-Яа-яЁёІіЇїЄє\\s\\,]{2,50}";
    String REGEX_TAG = "[A-Za-zА-Яа-яЁёІіЇїЄє]{2,50}";
    String REGEX_MESSAGE = "[A-Za-zА-Яа-яЁёІіЇїЄє'\\s_\\-\\.\\,\\!\\?\\:\\;\\&\\#\\'\\n]{1,1000}";
    String REGEX_DATETIME = "^(\\d{4})-(\\d\\d)-(\\d\\d)T(\\d\\d):(\\d\\d)$";
}
