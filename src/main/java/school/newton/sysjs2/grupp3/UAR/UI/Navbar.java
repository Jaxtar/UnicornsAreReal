package school.newton.sysjs2.grupp3.UAR.UI;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

@CssImport("common.css")
public class Navbar extends AppLayout{
    public Navbar(){
        createHeader();
    }

    private void createHeader() {
        /*
        Image logo = new Image("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.uCr4Ev0mpBw61aCObFx1ygHaHa%26pid%3DApi&f=1", "Logo");
        logo.setHeight(5, Unit.EM);
        logo.addClassName("logo");
        */

        H1 name = new H1("Unicorns Are Real");
        name.addClassName("name");
        
        

        HorizontalLayout header = new HorizontalLayout(name);
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        addToNavbar(header);
    }
}
