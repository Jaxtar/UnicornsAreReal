package school.newton.sysjs2.grupp3.UAR;


import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDateConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import school.newton.sysjs2.grupp3.UAR.model.StaffSchedule;
//import school.newton.sysjs2.grupp3.UAR.repository.StaffScheduleRepository;


@SpringComponent
@UIScope
public class StaffScheduleEditor extends VerticalLayout implements KeyNotifier {
/**
    private final StaffScheduleRepository repository;
    private StaffSchedule staffSchedule;

   public TextField firstname = new TextField("First name");
    public TextField lastname = new TextField("Last name");
    public TextField workarea = new TextField("Work Area");
    public TextField theaterId = new TextField("Theater ID");

    public Button save;
    public Button cancel;
    public Button delete;
    HorizontalLayout actions;
    Binder<StaffSchedule> binder;
    school.newton.sysjs2.grupp3.UAR.StaffScheduleEditor.ChangeHandler changeHandler;

    @Autowired
    public StaffScheduleEditor(StaffScheduleRepository repository) {
        this.repository = repository;
        this.save = new Button("Save", VaadinIcon.CHECK.create());
        this.cancel = new Button("Cancel");
        this.delete = new Button("Delete", VaadinIcon.TRASH.create());
        this.actions = new HorizontalLayout(save, cancel, delete);
        this.binder = new Binder(StaffSchedule.class);

        binder.bindInstanceFields(this);
        this.add(firstname, lastname, workarea, theaterId, actions);
        this.setSpacing(true);
        this.save.getElement().getThemeList().add("primary");
        this.delete.getElement().getThemeList().add("error");

        this.addKeyPressListener(Key.ENTER, (e) -> {
            this.save();
        }, new KeyModifier[0]);
        this.save.addClickListener((e) -> {
            this.save();
        });
        this.delete.addClickListener((e) -> {
            this.delete();
        });
        this.cancel.addClickListener((e) -> {
            this.editStaffSchedule(this.staffSchedule);
        });
        this.setVisible(false);
    }

    void delete() {
        repository.delete(staffSchedule);
        changeHandler.onChange();
    }

    void save() {
        repository.save(staffSchedule);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editStaffSchedule(StaffSchedule s) {
        if (s == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = s.getLastname() != null;
        if (persisted) {
            staffSchedule = (StaffSchedule) repository.findByLastnameStartsWithIgnoreCase(s.getLastname());
        }
        else {
            staffSchedule = s;
        }
        cancel.setVisible(persisted);
        binder.setBean(this.staffSchedule);
        setVisible(true);
        firstname.focus();
    }

    public void setChangeHandler(school.newton.sysjs2.grupp3.UAR.StaffScheduleEditor.ChangeHandler h){
        changeHandler = h;
    }*/
}


