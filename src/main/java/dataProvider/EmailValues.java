package dataProvider;

import org.testng.annotations.DataProvider;
import resources.ConfProperties;

public class EmailValues {

    @DataProvider(name="valid-emails")
    public static Object[] validMails() throws Exception{
        return new String[]{
                ConfProperties.getProperty("TEXT_63_CHARACTERS"),
                ConfProperties.getProperty("TEXT_64_CHARACTERS"),
                "prostfanat2001@gmail.co",
                "prostfanat2001@gmail.com",
                "prostfanat2001@gmail.comcomcom",
                "prostfanat2001@gmail.comcomcomc"
        };
    }

    @DataProvider(name="invalid-emails")
    public static Object[] invalidMails() throws Exception{
        return new String[]{
                ConfProperties.getProperty("TEXT_65_CHARACTERS"),
                "prostfanat2001gmail.com",
                "@prostfanat2001gmail.com",
                "prostfanat2001@gmailcom",
                "prostfanat2001@gmail.c",
                "prostfanat2001@gmail.comcomcomco"
        };
    }

}
