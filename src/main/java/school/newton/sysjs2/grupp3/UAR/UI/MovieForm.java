package school.newton.sysjs2.grupp3.UAR.UI;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;

import java.util.List;

public class MovieForm extends FormLayout {

    TextField title = new TextField("Title");
    TextField agerating = new TextField("Agerating");
    TextField description = new TextField("Description");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Movie> binder = new BeanValidationBinder<>(Movie.class);

    public MovieForm(List<Movie> movies) {
        addClassName("movie-form");

        binder.bindInstanceFields(this);

        add(title, agerating, description,
        createButtonsLayout());
    }

    public void setMovie (Movie movie){
        binder.setBean(movie);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if(binder.isValid()){
            fireEvent(new SaveEvent(this, binder.getBean()));
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