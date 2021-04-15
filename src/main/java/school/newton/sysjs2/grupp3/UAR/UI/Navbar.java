package school.newton.sysjs2.grupp3.UAR.UI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import school.newton.sysjs2.grupp3.UAR.UI.views.BookingView;
import school.newton.sysjs2.grupp3.UAR.UI.views.MainPage;
import school.newton.sysjs2.grupp3.UAR.UI.views.MoviesView;
import school.newton.sysjs2.grupp3.UAR.UI.views.StaffLoginView;

@CssImport("/common.css")
public class Navbar extends AppLayout{
    public Navbar(){
        createHeader();
    }

    private void createHeader() {
        H2 name = new H2("Unicorns Are Real");
        name.addClassName("name");
        name.addClickListener(e -> UI.getCurrent().navigate(MainPage.class));
        
        Button movies = new Button("Movies");
        movies.addClickListener(e -> UI.getCurrent().navigate(MainPage.class));

        Button ticket = new Button("Book A Ticket");
        ticket.addClickListener(e -> UI.getCurrent().navigate(BookingView.class));

        Button login = new Button("Staff - Log In");
        login.addClickListener(e -> UI.getCurrent().navigate(StaffLoginView.class));

        HorizontalLayout header = new HorizontalLayout(name, movies, ticket, login);

        header.setClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        addToNavbar(header);
    }
}
