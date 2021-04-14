package school.newton.sysjs2.grupp3.UAR.UI.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class StaffLayout extends AppLayout {

    public StaffLayout(){
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Unicorn Are Real / Staff Area");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");
        addToNavbar(header);
    }

    private void createDrawer() {

        RouterLink scheduleLink = new RouterLink("Staff Schedule", StaffScheduleView.class);
        RouterLink staffLink = new RouterLink("Staff Info", StaffView.class);
        RouterLink screeningLink = new RouterLink("Screenings List", ScreeningsView.class);
        Anchor logout = new Anchor("logout", "SIGN OUT");
        addToDrawer(new VerticalLayout(scheduleLink, staffLink, screeningLink, logout));

    }
}
