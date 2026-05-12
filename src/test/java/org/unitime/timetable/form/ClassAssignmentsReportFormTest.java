package org.unitime.timetable.form;


import static org.junit.jupiter.api.Assertions.*;
import static org.wildfly.common.Assert.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.unitime.timetable.form.ClassAssignmentsReportForm;
import org.unitime.timetable.util.Constants;
import org.unitime.timetable.util.Day;

public class ClassAssignmentsReportFormTest {

    private ClassAssignmentsReportForm form;

    @BeforeEach
    void setup() {
        form = new ClassAssignmentsReportForm();
        form.reset();
    }

    @Test
    void testReset() {

        form.setFilterManager("CS");
        form.setFilterAssignedTimeHour("10");
        form.setFilterAssignedTimeMin("30");

        form.reset();

        assertEquals("", form.getFilterManager());
        assertEquals("", form.getFilterAssignedTimeHour());
        assertEquals("", form.getFilterAssignedTimeMin());

        assertFalse(form.getFilterAssignedTimeMon());
    }

    @Test
    void testGetFilterDayCode() {

        form.setFilterAssignedTimeMon(true);
        form.setFilterAssignedTimeWed(true);

        int expected =
                Constants.DAY_CODES[Day.MON.ordinal()] +
                        Constants.DAY_CODES[Day.WED.ordinal()];

        assertEquals(expected, form.getFilterDayCode());
    }

    @Test
    void testSetFilterDayCode() {

        int code =
                Constants.DAY_CODES[Day.MON.ordinal()] +
                        Constants.DAY_CODES[Day.FRI.ordinal()];

        form.setFilterDayCode(code);

        assertTrue(form.getFilterAssignedTimeMon());
        assertTrue(form.getFilterAssignedTimeFri());

        assertFalse(form.getFilterAssignedTimeTue());
    }

    @Test
    void testGetFilterStartSlot() {

        form.setFilterAssignedTimeHour("10");
        form.setFilterAssignedTimeMin("30");
        form.setFilterAssignedTimeAmPm("AM");

        int expected =
                ((10 * 60 + 30) - Constants.FIRST_SLOT_TIME_MIN)
                        / Constants.SLOT_LENGTH_MIN;

        assertEquals(expected, form.getFilterStartSlot());
    }

    @Test
    void testGetFilterStartSlotInvalidInput() {

        form.setFilterAssignedTimeHour("abc");

        assertEquals(-1, form.getFilterStartSlot());
    }

    @Test
    void testSetFilterStartSlot() {

        form.setFilterStartSlot(10);

        assertNotEquals("", form.getFilterAssignedTimeHour());
        assertNotEquals("", form.getFilterAssignedTimeMin());
    }

    @Test
    void testGetFilterLength() {

        form.setFilterAssignedTimeLength("45");

        assertEquals(45, form.getFilterLength());
    }

    @Test
    void testSetFilterLength() {

        form.setFilterLength(60);

        assertEquals("60", form.getFilterAssignedTimeLength());
    }
}