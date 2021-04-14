package school.newton.sysjs2.grupp3.UAR.UI.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import school.newton.sysjs2.grupp3.UAR.UI.Navbar;
import school.newton.sysjs2.grupp3.UAR.UI.editor.StaffEditor;
import school.newton.sysjs2.grupp3.UAR.backend.controller.StaffController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Staff;
import school.newton.sysjs2.grupp3.UAR.backend.repository.StaffRepository;


@Route(
        value = "staff/info")
@PageTitle("Staff - Info - Unicorns Are Real")
@CssImport("./common.css")
public class StaffView extends VerticalLayout {
    private StaffController staffController;
    private StaffRepository staffRepository;

    Grid<Staff> grid = new Grid<>(Staff.class);
    private TextField filter = new TextField();
    private StaffEditor staffEditor;


    public StaffView(StaffController staffController, StaffRepository staffRepository){
        this.staffController = staffController;
        this.staffRepository = staffRepository;
        addClassName("staff-view");
        setSizeFull();
        configureGrid();

        staffEditor = new StaffEditor(staffRepository.findAll());
        staffEditor.addListener(StaffEditor.SaveEvent.class, this::saveStaff);
        staffEditor.addListener(StaffEditor.DeleteEvent.class, this::deleteStaff);
        staffEditor.addListener(StaffEditor.CloseEvent.class, e -> closeEditor());
        closeEditor();

        Div content = new Div(grid, staffEditor);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("password");
        grid.setColumns("staffid", "firstname", "lastname", "email", "username");
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

    private void saveStaff(StaffEditor.SaveEvent event) {
        staffController.save(event.getStaff());
        updateList();
        closeEditor();
    }

    private void deleteStaff(StaffEditor.DeleteEvent event) {
        staffController.delete(event.getStaff());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        staffEditor.setStaff(null);
        staffEditor.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(staffController.findAll(filter.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filter.setPlaceholder("Filter by name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());

        Button addNewStaffButton = new Button("Add new employee");
        addNewStaffButton.addClickListener(click -> addStaff());

        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewStaffButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    void addStaff() {
        grid.asSingleSelect().clear();
        editStaff(new Staff());
    }

}
