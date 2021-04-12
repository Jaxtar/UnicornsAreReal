package school.newton.sysjs2.grupp3.UAR.UI.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import school.newton.sysjs2.grupp3.UAR.UI.Navbar;

@Route(value="/booking/successful", layout=Navbar.class)
@PageTitle("Your Booking Was A Success!")
@CssImport("/common.css")
public class SuccessfulView extends VerticalLayout{
    public SuccessfulView(){
        H1 test = new H1("Your booking was completed successfully");
        add(test);
    }
}
