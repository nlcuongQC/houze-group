package commons;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataHelper {
    private final Faker  faker  = new Faker();
    private final Locale locale = new Locale("en");

    public static DataHelper getData() {
        return new DataHelper();
    }

    public String getFullname() {
        return faker.name().fullName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getUsername() {
        return faker.name().username().toUpperCase();
    }

    public String getEmployeeCode() {
        locale.getDisplayCountry();
        return faker.code().isbn13().toUpperCase();

    }

    public String getPhone() {
        return "0" + faker.phoneNumber().subscriberNumber(8);
    }

    public String getIDCard() {
        return faker.phoneNumber().subscriberNumber(9);
    }

    public String getTitle() {
        return faker.name().title();
    }

    public String getUrl() {
        return faker.internet().url();
    }

    public String getNumber() {
        return String.valueOf(faker.random().nextInt(999999));
    }

    public String getNumber(int count) {
        return String.valueOf(faker.random().nextInt(count));
    }

    public String getBirthday(String pattern) {
        Date date = faker.date().birthday();
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public String getMonth() {
        Date date = faker.date().birthday();
        DateFormat dateFormat = new SimpleDateFormat("MMMM");
        return dateFormat.format(date);
    }

    public String getCode() {
        return faker.code().isbn13();
    }

    public String getAddress() {
        return faker.address().fullAddress();
    }

    public String getBankAccount() {
        return faker.finance().creditCard().replace("-", "");
    }
}
