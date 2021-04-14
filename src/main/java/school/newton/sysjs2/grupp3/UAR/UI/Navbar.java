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

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import school.newton.sysjs2.grupp3.UAR.UI.views.*;

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
        movies.addClickListener(e -> UI.getCurrent().navigate(MoviesView.class));

        Button ticket = new Button("Book A Ticket");
        ticket.addClickListener(e -> UI.getCurrent().navigate(BookingView.class));


        HorizontalLayout header = new HorizontalLayout(name, movies, ticket);
        header.setClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        addToNavbar(header);
    }
}
