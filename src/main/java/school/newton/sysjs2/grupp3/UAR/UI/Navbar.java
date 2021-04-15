package school.newton.sysjs2.grupp3.UAR.UI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

import school.newton.sysjs2.grupp3.UAR.UI.views.*;

@CssImport("/common.css")
public class Navbar extends AppLayout{
    public Navbar(){
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H2 name = new H2("Unicorns Are Real");
        name.addClassName("name");
        name.addClickListener(e -> UI.getCurrent().navigate(MainPage.class));
        
        Button movies = new Button("Movies");
        movies.addClickListener(e -> UI.getCurrent().navigate(MoviesView.class));

        Button ticket = new Button("Book A Ticket");
        ticket.addClickListener(e -> UI.getCurrent().navigate(BookingView.class));

        Button theatre = new Button("Choose A Theatre");
        theatre.addClickListener(e -> UI.getCurrent().navigate(SuccessfulView.class));
        Button login = new Button("Staff - Log In");
        login.addClickListener(e -> UI.getCurrent().navigate(StaffLoginView.class));

        HorizontalLayout header = new HorizontalLayout(name, movies, ticket, theatre, login);

        header.setClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        addToNavbar(header);
    }

    private void createDrawer() {

        RouterLink staff = new RouterLink("Staff Area", StaffArea.class);
        Anchor logout = new Anchor("logout", "SIGN OUT");
        addToDrawer(new VerticalLayout(staff, logout));
    }
}
