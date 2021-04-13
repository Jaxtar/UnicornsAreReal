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
import school.newton.sysjs2.grupp3.UAR.UI.editor.StaffScheduleEditor;
import school.newton.sysjs2.grupp3.UAR.backend.controller.StaffScheduleController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Staffschedule;
import school.newton.sysjs2.grupp3.UAR.backend.repository.StaffScheduleRepository;


@Route(
        value = "staff/schedule",
        layout = StaffLayout.class)
@PageTitle("Staff - Schedule - Unicorns Are Real!")
@CssImport("./common.css")
public class StaffScheduleView extends VerticalLayout {
    private StaffScheduleController staffScheduleController;
    private StaffScheduleRepository staffScheduleRepository;

    Grid<Staffschedule> grid = new Grid<>(Staffschedule.class);
    private TextField filter = new TextField();
    private StaffScheduleEditor staffScheduleEditor;

    public StaffScheduleView(StaffScheduleController staffScheduleController, StaffScheduleRepository staffScheduleRepository){
        this.staffScheduleController = staffScheduleController;
        this.staffScheduleRepository = staffScheduleRepository;
        addClassName("staffschedule-view");
        setSizeFull();
        configureGrid();

        staffScheduleEditor = new StaffScheduleEditor(staffScheduleRepository.findAll());
        staffScheduleEditor.addListener(StaffScheduleEditor.SaveEvent.class, this::saveStaffSchedule);
        staffScheduleEditor.addListener(StaffScheduleEditor.DeleteEvent.class, this::deleteStaffSchedule);
        staffScheduleEditor.addListener(StaffScheduleEditor.CloseEvent.class, e -> closeEditor());
        closeEditor();

        Div content = new Div(grid, staffScheduleEditor);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("staffscheduleid", "date", "start_time", "end_time", "_staffid", "firstname", "lastname", "workarea", "_theatreid");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editStaffSchedule(event.getValue()));
    }

    public void editStaffSchedule(Staffschedule staffschedule) {
        if (staffschedule == null) {
            closeEditor();
        } else {
            staffScheduleEditor.setStaffSchedule(staffschedule);
            staffScheduleEditor.setVisible(true);
            addClassName("editing");
        }
    }

    private void saveStaffSchedule(StaffScheduleEditor.SaveEvent event) {
        staffScheduleController.save(event.getStaffSchedule());
        updateList();
        closeEditor();
    }

    private void deleteStaffSchedule(StaffScheduleEditor.DeleteEvent event) {
        staffScheduleController.delete(event.getStaffSchedule());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        staffScheduleEditor.setStaffSchedule(null);
        staffScheduleEditor.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(staffScheduleController.findAll(filter.getValue() ));
    }

    private HorizontalLayout getToolbar() {
        filter.setPlaceholder("Filter by name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());

        Button addNewStaffScheduleButton = new Button("Add new Shift");
        addNewStaffScheduleButton.addClickListener(click -> addStaffSchedule());

        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewStaffScheduleButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    void addStaffSchedule() {
        grid.asSingleSelect().clear();
        editStaffSchedule(new Staffschedule());
    }

}

