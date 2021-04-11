package school.newton.sysjs2.grupp3.UAR;


import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import school.newton.sysjs2.grupp3.UAR.model.Staffschedule;

import java.util.List;

@SpringComponent
@UIScope
public class StaffScheduleEditor extends FormLayout {

    private Staffschedule staffschedule;


    /**
     * DatePicker date = new DatePicker("Date");
     * TimePicker startTime = new TimePicker("Start Time");
     * TimePicker endTime = new TimePicker("End Time");
     */


    //TODO date- start time - end time e capire perchè non vedo nulla nella tabella


    TextField firstname = new TextField("First name");
    TextField lastname = new TextField("Last name");
    ComboBox<Staffschedule.Workarea> workarea = new ComboBox<>("Work Area");
    TextField theaterid = new TextField("Theater ID");

    Binder<Staffschedule> binder = new BeanValidationBinder<>(Staffschedule.class);

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");


    public StaffScheduleEditor(List<Staffschedule> staffschedule) {
        addClassName("Staffschedule-editor");
        binder.bindInstanceFields(this);

        /** binder.bindInstanceFields(date.getLocale());
         binder.bindInstanceFields(startTime.getLocale());
         binder.bindInstanceFields(endTime.getLocale());*/

        workarea.setItems(Staffschedule.Workarea.values());
        add(/**date, startTime, endTime, */firstname, lastname, workarea, theaterid, createButtonsLayout());

    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new StaffScheduleEditor.DeleteEvent(this, staffschedule)));
        close.addClickListener(click -> fireEvent(new StaffScheduleEditor.CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public void setStaffSchedule(Staffschedule staffschedule) {
        this.staffschedule = staffschedule;
        binder.readBean(staffschedule);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(staffschedule);
            fireEvent(new SaveEvent(this, staffschedule));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


    public static abstract class StaffScheduleEditorEvent extends ComponentEvent<StaffScheduleEditor> {
        private Staffschedule staffschedule;

        protected StaffScheduleEditorEvent(StaffScheduleEditor source, Staffschedule staffschedule) {
            super(source, false);
            this.staffschedule = staffschedule;
        }

        public Staffschedule getStaffSchedule() {
            return staffschedule;
        }
    }

    public static class SaveEvent extends StaffScheduleEditorEvent {
        SaveEvent(StaffScheduleEditor source, Staffschedule staffschedule) {

            super(source, staffschedule);
        }
    }

    public static class DeleteEvent extends StaffScheduleEditorEvent {
        DeleteEvent(StaffScheduleEditor source, Staffschedule staffschedule) {
            super(source, staffschedule);
        }

    }

    public static class CloseEvent extends StaffScheduleEditorEvent {
        CloseEvent(StaffScheduleEditor source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}