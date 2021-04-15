package school.newton.sysjs2.grupp3.UAR.UI.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import school.newton.sysjs2.grupp3.UAR.UI.Navbar;

@Route(value="", layout=Navbar.class)
@PageTitle("Unicorns Are Real!")
public class MainPage extends VerticalLayout{
    public MainPage(){
        H1 test = new H1("MainPage");
        add(test);
    }
}
