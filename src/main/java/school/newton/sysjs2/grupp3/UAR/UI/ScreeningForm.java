package school.newton.sysjs2.grupp3.UAR.UI;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;
import school.newton.sysjs2.grupp3.UAR.backend.repository.MovieRepository;

public class ScreeningForm extends FormLayout {

    private Screening screening;

    IntegerField screeningid = new IntegerField("Screening");
    IntegerField _salonid = new IntegerField("Salon");
    IntegerField _movieid = new IntegerField("Movie");
    DatePicker date = new DatePicker("Date");
    TimePicker start_time = new TimePicker("StartTime");
    TimePicker end_time = new TimePicker("EndTime");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Screening> screeningBinder = new BeanValidationBinder<>(Screening.class);

    public ScreeningForm(Iterable<Screening> screenings) {
        addClassName("movie-form");

        screeningBinder.forField(date)
                .withConverter(new SqlDateToLocalDateConverter())
                .bind(Screening::getDate, Screening::setDate);

        screeningBinder.forField(start_time)
                .withConverter(new SqlTimeToLocalTimeConverter())
                .bind(Screening::getStart_time, Screening::setStart_time);

        screeningBinder.forField(end_time)
                .withConverter(new SqlTimeToLocalTimeConverter())
                .bind(Screening::getEnd_time, Screening::setEnd_time);

        screeningBinder.bindInstanceFields(this);

        screeningid.setReadOnly(true);
        //_movieid.setReadOnly(true);

        add(screeningid, _movieid, _salonid, date, start_time, end_time, createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, screeningBinder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        screeningBinder.addStatusChangeListener(evt ->
                save.setEnabled(screeningBinder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
        screeningBinder.setBean(screening);
    }

    private void validateAndSave() {
        try {
            screeningBinder.writeBean(screening);
            fireEvent(new SaveEvent(this, screening));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ScreeningFormEvent extends ComponentEvent<ScreeningForm> {
        private Screening screening;

        protected ScreeningFormEvent(ScreeningForm source, Screening screening) {
            super(source, false);
            this.screening = screening;
        }

        public Screening getScreening() {
            return screening;
        }
    }

    public static class SaveEvent extends ScreeningForm.ScreeningFormEvent {
        SaveEvent(ScreeningForm source, Screening screening) {

            super(source, screening);
        }
    }

    public static class DeleteEvent extends ScreeningForm.ScreeningFormEvent {
        DeleteEvent(ScreeningForm source, Screening screening) {
            super(source, screening);
        }

    }

    public static class CloseEvent extends ScreeningForm.ScreeningFormEvent {
        CloseEvent(ScreeningForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
