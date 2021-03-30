package school.newton.sysjs2.grupp3.UAR.model;

import com.vaadin.flow.component.template.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer theatreID;

    public String theatreName;

    public Theatre(Integer theatreID, String theatreName) {
        this.theatreID = theatreID;
        this.theatreName = theatreName;
    }

    public Integer getTheatreID() {
        return theatreID;
    }

    public void setTheatreID(Integer theatreID) {
        this.theatreID = theatreID;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }


}
