package school.newton.sysjs2.grupp3.UAR.UI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

import school.newton.sysjs2.grupp3.UAR.UI.views.MainPage;
import school.newton.sysjs2.grupp3.UAR.UI.views.Test2;

@CssImport("/common.css")
public class Navbar extends AppLayout{
    public Navbar(){
        createHeader();
    }

    private void createHeader() {
        H1 name = new H1("Unicorns Are Real");
        name.addClassName("name");
        name.addClickListener(e -> UI.getCurrent().navigate(MainPage.class));
        
        Button movies = new Button("Movies");
        movies.addClickListener(e -> UI.getCurrent().navigate(MainPage.class));
        Button ticket = new Button("Book A Ticket");
        ticket.addClickListener(e -> UI.getCurrent().navigate(Test2.class));
        Button theatre = new Button("Choose A Theatre");
        theatre.addClickListener(e -> UI.getCurrent().navigate(Test2.class));

        HorizontalLayout header = new HorizontalLayout(name, movies, ticket, theatre);
        header.setClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        addToNavbar(header);
    }
}
