package school.newton.sysjs2.grupp3.UAR.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;
import school.newton.sysjs2.grupp3.UAR.UI.views.StaffLoginView;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
        if (!StaffLoginView.class.equals(event.getNavigationTarget())
                && !SecurityUtils.isUserLoggedIn()) {
            event.rerouteTo(StaffLoginView.class);
        }
    }
}
