package school.newton.sysjs2.grupp3.UAR.it;

import org.junit.Assert;
import org.junit.Test;
import school.newton.sysjs2.grupp3.UAR.it.elements.login.LoginViewElement;

public class LoginIT extends AbstractTest {

    protected LoginIT() {
        super("/login");
    }

    @Test
    public void loginAsValidUserSucceeds() {
        LoginViewElement loginView = $(LoginViewElement.class).onPage().first();
        Assert.assertTrue(loginView.login("almc", "almc1"));
    }


    @Test
    public void loginAsInvalidUserFails() {
        LoginViewElement loginView = $(LoginViewElement.class).onPage().first();
        Assert.assertFalse(loginView.login("user", "invalid"));
    }
}
