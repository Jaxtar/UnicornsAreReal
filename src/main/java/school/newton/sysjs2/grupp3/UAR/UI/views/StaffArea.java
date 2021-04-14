package school.newton.sysjs2.grupp3.UAR.UI.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import school.newton.sysjs2.grupp3.UAR.UI.MovieForm;
import school.newton.sysjs2.grupp3.UAR.UI.Navbar;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.repository.MovieRepository;

@Route(value="/staffarea", layout= Navbar.class)
@CssImport("./common.css")
public class StaffArea extends VerticalLayout {

    public StaffArea(){
        addClassName("list-view");
        setSizeFull();
        getContent();

        add(getContent());
    }

    private VerticalLayout getContent(){

        Button staffSchedule = new Button("Staff Schedule");
        staffSchedule.addClickListener(e -> UI.getCurrent().navigate(StaffScheduleView.class));
        Button staffInfo = new Button("Staff Info");
        staffInfo.addClickListener(e -> UI.getCurrent().navigate(StaffView.class));
        Button screeningsList = new Button("Screenings List");
        screeningsList.addClickListener(e -> UI.getCurrent().navigate(ScreeningsView.class));


        VerticalLayout buttons = new VerticalLayout(screeningsList, staffSchedule, staffInfo);

        return buttons;
    }


}
