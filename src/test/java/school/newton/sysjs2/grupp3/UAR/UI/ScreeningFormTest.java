package school.newton.sysjs2.grupp3.UAR.UI;


import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.time.LocalDate.*;

/**
 * Test fail
 * java.lang.NullPointerException
 * Cannot invoke "com.vaadin.flow.component.UI.getLocale()"
 * because the return value of "com.vaadin.flow.component.UI.getCurrent()" is null
 */

@SpringBootTest
class ScreeningFormTest {

    private List <Screening> screenings;
    private Screening screening;

    @Before
    public void setupData(){
        screenings = new ArrayList<>();
        screening = new Screening();

        screening.setScreeningid(1);
        screening.set_salonid(1);
        screening.set_movieid(1);
        screening.setDate(Date.valueOf("2021-04-15"));
        screening.setStart_time(Time.valueOf("09:00"));
        screening.setEnd_time(Time.valueOf("10:45"));

        screenings.add(screening);
    }

    @Test
    public void formFieldsPopulated(){
        ScreeningForm screeningForm = new ScreeningForm(screenings);
        screeningForm.setScreening(screening);

        Assert.assertEquals(new Integer(1), screeningForm.screeningid.getValue());
        Assert.assertEquals(new Integer(1), screeningForm._salonid.getValue());
        Assert.assertEquals(new Integer(1), screeningForm._movieid.getValue());
        Assert.assertEquals(DateUtils.round("2021-04-15", Calendar.SECOND), DateUtils.round(screeningForm.date.getValue(),Calendar.SECOND));
        Assert.assertEquals(new Time(900), screeningForm.start_time.getValue());
        Assert.assertEquals(new Time(1045), screeningForm.end_time.getValue());
    }

    @Test
    public void savesCorrectValues(){
        ScreeningForm screeningForm = new ScreeningForm(screenings);
        screeningForm.setScreening(screening);

        screeningForm.screeningid.setValue(2);
        screeningForm._salonid.setValue(2);
        screeningForm._movieid.setValue(2);
        screeningForm.date.setValue(of(2021,4,16));
        screeningForm.start_time.setValue(LocalTime.of(10, 0));
        screeningForm.start_time.setValue(LocalTime.of(11, 45));

        AtomicReference<Screening> savedScreeningRef = new AtomicReference<>(null);

        screeningForm.addListener(ScreeningForm.SaveEvent.class, e -> {
            savedScreeningRef.set(e.getScreening());
        });
        screeningForm.save.click();

        Screening savedScreening = savedScreeningRef.get();

        Assert.assertEquals(new Integer(2), savedScreening.getScreeningid());
        Assert.assertEquals(new Integer(2), savedScreening.get_salonid());
        Assert.assertEquals(new Integer(2), savedScreening.get_movieid());
        Assert.assertEquals(new Date(2021,04,15), savedScreening.getDate());
        Assert.assertEquals("10:00", savedScreening.getStart_time());
        Assert.assertEquals("11:45", savedScreening.getEnd_time());
    }


}