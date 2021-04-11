package school.newton.sysjs2.grupp3.UAR.UI.editor;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import school.newton.sysjs2.grupp3.UAR.model.Staff;


import java.util.List;


@SpringComponent
@UIScope
public class StaffEditor extends FormLayout {
    private Staff staff;

    TextField firstname = new TextField("First name");
    TextField lastname = new TextField("Last name");
    TextField email = new TextField("Email");
    TextField username = new TextField("Username");
    TextField password = new TextField("Password");

    Binder<Staff> binder = new BeanValidationBinder<>(Staff.class);


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");


    public StaffEditor(List<Staff> staff){
        addClassName("Staff-editor");
        binder.bindInstanceFields(this);

        add(firstname, lastname, email, username, password, createButtonsLayout());

    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, staff)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
        binder.readBean(staff);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(staff);
            fireEvent(new SaveEvent(this, staff));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


    public static abstract class StaffEditorEvent extends ComponentEvent<StaffEditor> {
        private Staff staff;

        protected StaffEditorEvent(StaffEditor source, Staff staff) {
            super(source, false);
            this.staff = staff;
        }

        public Staff getStaff() {
            return staff;
        }
    }

    public static class SaveEvent extends StaffEditorEvent {
        SaveEvent(StaffEditor source, Staff staff) {

            super(source, staff);
        }
    }

    public static class DeleteEvent extends StaffEditorEvent {
        DeleteEvent(StaffEditor source, Staff staff) {
            super(source, staff);
        }

    }

    public static class CloseEvent extends StaffEditorEvent {
        CloseEvent(StaffEditor source) {
            super(source, null);
        }
    }
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}

