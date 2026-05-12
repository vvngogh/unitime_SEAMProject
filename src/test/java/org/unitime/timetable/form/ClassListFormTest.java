package org.unitime.timetable.form;
//org.unitime.timetable.form.ClassListFormTest

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.unitime.timetable.form.ClassListForm;
import org.unitime.timetable.util.Constants;
import org.unitime.timetable.util.Day;

public class ClassListFormTest {

    private ClassListForm form;

    @BeforeEach
    void setup() {
        form = new ClassListForm();
        form.reset();
    }

    @Test
    void testReset() {

        form.setCourseNbr("CS101");
        form.setFilterInstructor("Ali");

        form.reset();

        assertEquals("", form.getCourseNbr());
        assertEquals("", form.getFilterInstructor());

        assertFalse(form.getFilterAssignedTimeMon());
    }

    @Test
    void testGetFilterDayCode() {

        form.setFilterAssignedTimeTue(true);
        form.setFilterAssignedTimeThu(true);

        int expected =
                Constants.DAY_CODES[Day.TUE.ordinal()] +
                        Constants.DAY_CODES[Day.THU.ordinal()];

        assertEquals(expected, form.getFilterDayCode());
    }

    @Test
    void testSetFilterDayCode() {

        int code =
                Constants.DAY_CODES[Day.MON.ordinal()] +
                        Constants.DAY_CODES[Day.SAT.ordinal()];

        form.setFilterDayCode(code);

        assertTrue(form.getFilterAssignedTimeMon());
        assertTrue(form.getFilterAssignedTimeSat());

        assertFalse(form.getFilterAssignedTimeTue());
    }

    @Test
    void testGetFilterStartSlot() {

        form.setFilterAssignedTimeHour("9");
        form.setFilterAssignedTimeMin("00");
        form.setFilterAssignedTimeAmPm("AM");

        int expected =
                ((9 * 60) - Constants.FIRST_SLOT_TIME_MIN)
                        / Constants.SLOT_LENGTH_MIN;

        assertEquals(expected, form.getFilterStartSlot());
    }

    @Test
    void testGetFilterStartSlotInvalidInput() {

        form.setFilterAssignedTimeHour("wrong");

        assertEquals(-1, form.getFilterStartSlot());
    }

    @Test
    void testSetFilterStartSlot() {

        form.setFilterStartSlot(15);

        assertNotEquals("", form.getFilterAssignedTimeHour());
        assertNotEquals("", form.getFilterAssignedTimeMin());
    }

    @Test
    void testGetFilterLength() {

        form.setFilterAssignedTimeLength("90");

        assertEquals(90, form.getFilterLength());
    }
}