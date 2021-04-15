package school.newton.sysjs2.grupp3.UAR.UI.editor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import school.newton.sysjs2.grupp3.UAR.backend.model.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class StaffEditorTest {

    private List<Staff> staffList;
    private Staff paulShaffer;


    @Before
    public void setupData() {
        staffList = new ArrayList<>();

        paulShaffer = new Staff();
        paulShaffer.setFirstname("Alex");
        paulShaffer.setLastname("Shaffer");
        paulShaffer.setEmail("AshBand@Shaffer.com");
        paulShaffer.setUsername("alsh");
        paulShaffer.setPassword("alsh1");
        staffList.add(paulShaffer);
    }

    @Test
    public void formFieldsPopulated() {
        StaffEditor form = new StaffEditor(staffList);
        form.setStaff(paulShaffer);
        Assert.assertEquals("Alex", form.firstname.getValue());
        Assert.assertEquals("Shaffer", form.lastname.getValue());
        Assert.assertEquals("AshBand@Shaffer.com", form.email.getValue());
        Assert.assertEquals("alsh", form.username.getValue());
        Assert.assertEquals("alsh1", form.password.getValue());
    }

    @Test
    public void saveEventHasCorrectValues() {
        StaffEditor form = new StaffEditor(staffList);
        Staff staff = new Staff();
        form.setStaff(staff);

        form.firstname.setValue("John");
        form.lastname.setValue("Doe");
        form.email.setValue("john@doe.com");
        form.username.setValue("jodo");
        form.password.setValue("jodo1");

        AtomicReference<Staff> savedStaffRef = new AtomicReference<>(null);
        form.addListener(StaffEditor.SaveEvent.class, e -> {
            savedStaffRef.set(e.getStaff());
        });
        form.save.click();
        Staff savedStaff = savedStaffRef.get();

        Assert.assertEquals("John", savedStaff.getFirstname());
        Assert.assertEquals("Doe", savedStaff.getLastname());
        Assert.assertEquals("john@doe.com", savedStaff.getEmail());
        Assert.assertEquals("jodo", savedStaff.getUsername());
        Assert.assertEquals("jodo1", savedStaff.getPassword());
    }

}
