package school.newton.sysjs2.grupp3.UAR;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import school.newton.sysjs2.grupp3.UAR.model.Staff;
import school.newton.sysjs2.grupp3.UAR.repository.StaffRepository;



@SpringComponent
@UIScope
public class StaffEditor extends VerticalLayout implements KeyNotifier {

    private final StaffRepository repository;
    private Staff staff;

    public TextField firstname = new TextField("First name");
    public TextField lastname = new TextField("Last name");
    public TextField email = new TextField("Email");
    public TextField username = new TextField("Username");
    public TextField password = new TextField("Password");

    public Button save;
    public Button cancel;
    public Button delete;
    HorizontalLayout actions;
    Binder<Staff> binder;
    private school.newton.sysjs2.grupp3.UAR.StaffEditor.ChangeHandler changeHandler;

    @Autowired
    public StaffEditor(StaffRepository repository){
        this.repository = repository;

        this.save = new Button("Save", VaadinIcon.CHECK.create());
        this.cancel = new Button("Cancel");
        this.delete = new Button("Delete", VaadinIcon.TRASH.create());
        this.actions = new HorizontalLayout(save, cancel, delete);
        this.binder = new Binder(Staff.class);
        this.binder.bindInstanceFields(this);
        this.add(firstname, lastname, email, username, password, actions);
        setSpacing(true);

        this.save.getElement().getThemeList().add("primary");
        this.delete.getElement().getThemeList().add("error");

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editStaff(staff));
        setVisible(false);
    }

    void delete() {
        repository.delete(staff);
        changeHandler.onChange();
    }

    void save() {
        repository.save(staff);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editStaff(Staff s) {
        if (s == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = s.getLastname() != null;
        if (persisted) {
            staff = (Staff) repository.findByLastnameStartsWithIgnoreCase(s.getLastname());
        }
        else {
            staff = s;
        }
        cancel.setVisible(persisted);
        binder.setBean(staff);
        setVisible(true);
        firstname.focus();
    }

    public void setChangeHandler(school.newton.sysjs2.grupp3.UAR.StaffEditor.ChangeHandler h) {
        this.changeHandler = h;
    }
}
