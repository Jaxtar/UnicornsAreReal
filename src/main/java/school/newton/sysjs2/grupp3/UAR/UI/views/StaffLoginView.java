package school.newton.sysjs2.grupp3.UAR.UI.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("staff/login")
@PageTitle("Login - Unicorns Are Real Staff")
public class StaffLoginView extends VerticalLayout implements BeforeEnterObserver {
    private LoginForm loginForm = new LoginForm();

    public StaffLoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        loginForm.setAction("login");
        add(new H1("Unicorns Are Real"), loginForm);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent){
        if (beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")){
            loginForm.setError(true);
        }
    }
}