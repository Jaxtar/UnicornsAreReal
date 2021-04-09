package school.newton.sysjs2.grupp3.UAR;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import school.newton.sysjs2.grupp3.UAR.model.Staffschedule;
import school.newton.sysjs2.grupp3.UAR.repository.StaffScheduleRepository;

import java.util.Collection;

@Route(
        value = "staff/schedule",
        layout = school.newton.sysjs2.grupp3.UAR.StaffLayout.class)
@PageTitle("Staff - Schedule - Unicorns Are Real")
public class StaffScheduleView extends VerticalLayout {

    private final StaffScheduleRepository repo;
    // private final StaffScheduleEditor editor;
    final Grid<Staffschedule> grid;
    final TextField filter;
    private final Button addNewButton;


    public StaffScheduleView(StaffScheduleRepository repo){//, StaffScheduleEditor editor){
        this.repo = repo;
        //this.editor = editor;
        this.grid = new Grid<>(Staffschedule.class);
        this.filter = new TextField();
        this.addNewButton = new Button("New Work Shift", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewButton);
        add(actions, grid);//, editor);

        grid.setHeight("700px");
        grid.setColumns("staffscheduleid", "date", "start_time", "end_time", "_staffid", "firstname", "lastname", "workarea", "_theatreid");
        grid.getColumnByKey("staffscheduleid").setWidth("50px").setFlexGrow(0);
        filter.setPlaceholder("Filter by last name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listShift(e.getValue()));

        grid.asSingleSelect().addValueChangeListener((e) -> {
            //editor.editStaffSchedule(e.getValue());
        });
        this.addNewButton.addClickListener((e) -> {
            //editor.editStaffSchedule(new Staffschedule());
        });
        //editor.setChangeHandler(() -> {
        //editor.setVisible(false);
        //this.listShift(this.filter.getValue());
        //});
        this.listShift(null);
    }
    void listShift(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            this.grid.setItems((Collection<Staffschedule>) repo.findAll());
        }
        else {
            grid.setItems(repo.findByStaffscheduleidStartsWithIgnoreCase(filter.getTabIndex()));
        }
    }
}



