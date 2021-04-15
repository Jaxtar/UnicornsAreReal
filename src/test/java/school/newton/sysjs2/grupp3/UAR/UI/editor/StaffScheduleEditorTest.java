package school.newton.sysjs2.grupp3.UAR.UI.editor;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import school.newton.sysjs2.grupp3.UAR.backend.model.Staff;
import school.newton.sysjs2.grupp3.UAR.backend.model.Staffschedule;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class StaffScheduleEditorTest {

        private List<Staffschedule> staffscheduleList;
        private Staffschedule todayStaffschedule;

        @Before
        public void setupData() {
            staffscheduleList = new ArrayList<>();

            todayStaffschedule = new Staffschedule();
            todayStaffschedule.setDate(Date.valueOf(LocalDate.now()));
            todayStaffschedule.setStart_time(Time.valueOf(LocalTime.now()));
            todayStaffschedule.setEnd_time(Time.valueOf(LocalTime.now()));
            todayStaffschedule.set_staffid(20);
            todayStaffschedule.setfirstname("Will");
            todayStaffschedule.setLastname("Do");
            todayStaffschedule.setWorkarea(Staffschedule.Workarea.Cashier);
            todayStaffschedule.set_theatreid(1);

            staffscheduleList.add(todayStaffschedule);
        }

        @Test
        public void formFieldsPopulated() {
            StaffScheduleEditor form = new StaffScheduleEditor(staffscheduleList);
            form.setStaffSchedule(todayStaffschedule);
            Assert.assertEquals(Date.valueOf(LocalDate.now()),form.date.getValue());
            Assert.assertEquals(Time.valueOf(LocalTime.now()),form.startTime.getValue());
            Assert.assertEquals(Time.valueOf(LocalTime.now()),form.endTime.getValue());
            Assert.assertEquals(20,form.staffId.getValue());
            Assert.assertEquals("Will", form.firstname.getValue());
            Assert.assertEquals("Do", form.lastname.getValue());
            Assert.assertEquals(Staffschedule.Workarea.Cashier, form.workarea.getValue());
            Assert.assertEquals(1, form.theaterid.getValue());
        }

        @Test
        public void saveEventHasCorrectValues() {
            StaffScheduleEditor form = new StaffScheduleEditor(staffscheduleList);
            Staffschedule staffschedule = new Staffschedule();
            form.setStaffSchedule(staffschedule);

            form.date.setValue(LocalDate.of(2021,04,15));
            form.startTime.setValue(LocalTime.of(15,00));
            form.endTime.setValue(LocalTime.of(17,00));
            form.staffId.setValue("20");
            form.firstname.setValue("Will");
            form.lastname.setValue("Do");
            form.workarea.setValue(Staffschedule.Workarea.Cashier);
            form.theaterid.setValue("1");

            AtomicReference<Staffschedule> savedStaffScheduleRef = new AtomicReference<>(null);
            form.addListener(StaffScheduleEditor.SaveEvent.class, e -> {
                savedStaffScheduleRef.set(e.getStaffSchedule());
            });
            form.save.click();
            Staffschedule savedStaffSchedule = savedStaffScheduleRef.get();
            Assert.assertEquals("2021-04-15", savedStaffSchedule.getDate());
            Assert.assertEquals("15:00", savedStaffSchedule.getStart_time());
            Assert.assertEquals("17:00", savedStaffSchedule.getEnd_time());
            Assert.assertEquals("20", savedStaffSchedule.get_staffid());
            Assert.assertEquals("John", savedStaffSchedule.getFirstname());
            Assert.assertEquals("Doe", savedStaffSchedule.getLastname());
            Assert.assertEquals("Cashier", savedStaffSchedule.getWorkarea());
            Assert.assertEquals("1", savedStaffSchedule.get_theatreid());
        }

    }

