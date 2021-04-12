package school.newton.sysjs2.grupp3.UAR;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import school.newton.sysjs2.grupp3.UAR.controller.StaffController;
import school.newton.sysjs2.grupp3.UAR.model.Staff;

import java.util.Collection;

@Route(
        value = "staff/info",
        layout = school.newton.sysjs2.grupp3.UAR.StaffLayout.class)
@PageTitle("Staff - Info - Unicorns Are Real")
public class StaffView extends VerticalLayout {

    Grid<Staff> grid = new Grid<>(Staff.class);
    private StaffController staffController;
    private TextField filter;

    private school.newton.sysjs2.grupp3.UAR.StaffEditor staffEditor;


    public StaffView(StaffController staffController){
        this.staffController = staffController;
        addClassName("staff-view");
        this.filter = new TextField();
        setSizeFull();
        getToolbar();
        configureGrid();

        staffEditor = new school.newton.sysjs2.grupp3.UAR.StaffEditor(staffController.findAll());
        staffEditor.addListener(school.newton.sysjs2.grupp3.UAR.StaffEditor.SaveEvent.class, this::saveStaff);
        staffEditor.addListener(school.newton.sysjs2.grupp3.UAR.StaffEditor.DeleteEvent.class, this::deleteStaff);
        staffEditor.addListener(school.newton.sysjs2.grupp3.UAR.StaffEditor.CloseEvent.class, e -> closeEditor());


        Div content = new Div(grid, staffEditor);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("firstname", "lastname", "email", "username");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editStaff(event.getValue()));
    }
    public void editStaff(Staff staff) {
        if (staff == null) {
            closeEditor();
        } else {
            staffEditor.setStaff(staff);
            staffEditor.setVisible(true);
            addClassName("editing");
        }
    }

    private void saveStaff(school.newton.sysjs2.grupp3.UAR.StaffEditor.SaveEvent event) {
        staffController.save(event.getStaff());
        updateList();
        closeEditor();
    }

    private void deleteStaff(school.newton.sysjs2.grupp3.UAR.StaffEditor.DeleteEvent event) {
        staffController.delete(event.getStaff());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        staffEditor.setStaff(null);
        staffEditor.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolbar() {
        filter.setPlaceholder("Filter by name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());

        Button addNewStaffButton = new Button("Add new employee", click -> addStaff());

        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewStaffButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    void addStaff() {
        grid.asSingleSelect().clear();
        editStaff(new Staff());
    }
    private void updateList() {
        grid.setItems(staffController.findAll(filter.getValue()));
    }
}

