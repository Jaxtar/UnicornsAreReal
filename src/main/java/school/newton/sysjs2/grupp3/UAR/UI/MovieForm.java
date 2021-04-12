package school.newton.sysjs2.grupp3.UAR.UI;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import school.newton.sysjs2.grupp3.UAR.backend.controller.ScreeningController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MovieForm extends FormLayout {

    TextField title = new TextField("Title");
    TextField agerating = new TextField("Agerating");
    TextArea description = new TextArea("Description");
    DateTimePicker screening = new DateTimePicker("Screening");

    LocalDate today = LocalDate.now();
    LocalDateTime min = LocalDateTime.of(today, LocalTime.parse("10:00"));
    LocalDateTime max = LocalDateTime.of(today.plusWeeks(2), LocalTime.parse("18:00"));

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Movie> movieBinder = new BeanValidationBinder<>(Movie.class);

    public MovieForm(List<Movie> movies) {
        addClassName("movie-form");

        movieBinder.bindInstanceFields(this);
        screening.setStep(Duration.ofMinutes(120));
        screening.setMax(max);
        screening.setMin(min);

        add(title, agerating, screening, description,

        createButtonsLayout());
    }

    public void setMovie (Movie movie){
        movieBinder.setBean(movie);
    }
    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, movieBinder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        movieBinder.addStatusChangeListener(evt -> save.setEnabled(movieBinder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if(movieBinder.isValid()){
            fireEvent(new SaveEvent(this, movieBinder.getBean()));
        }
    }

    // Events
    public static abstract class MovieFormEvent extends ComponentEvent<MovieForm> {
        private Movie movie;

        protected MovieFormEvent(MovieForm source, Movie movie) {
            super(source, false);
            this.movie = movie;
        }

        public Movie getMovie() {
            return movie;
        }
    }

    public static class SaveEvent extends MovieFormEvent {
        SaveEvent(MovieForm source, Movie movie) {

            super(source, movie);
        }
    }

    public static class DeleteEvent extends MovieFormEvent {
        DeleteEvent(MovieForm source, Movie movie) {
            super(source, movie);
        }

    }

    public static class CloseEvent extends MovieFormEvent {
        CloseEvent(MovieForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}