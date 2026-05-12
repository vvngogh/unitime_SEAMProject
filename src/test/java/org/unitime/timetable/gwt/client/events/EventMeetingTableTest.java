package org.unitime.timetable.gwt.client.events;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.unitime.timetable.gwt.client.events.EventAdd.EventPropertiesProvider;
import org.unitime.timetable.gwt.client.events.EventMeetingTable.EventMeetingRow;
import org.unitime.timetable.gwt.client.events.EventMeetingTable.Mode;
import org.unitime.timetable.gwt.shared.EventInterface;
import org.unitime.timetable.gwt.shared.EventInterface.MeetingInterface;
import org.unitime.timetable.gwt.shared.EventInterface.SessionMonth;

import java.util.Date;
import java.util.List;

class EventMeetingTableTest {

    /** Minimal no-op stub — only override what the table actually calls. */
    private static final EventPropertiesProvider STUB_PROPERTIES =
            new EventPropertiesProvider() {
                @Override public Long getSessionId()         { return null; }
                @Override public boolean isTooEarly(int s, int e) { return false; }
                @Override public int getFirstDayOfWeek()    { return 0; }

                @Override
                public boolean isCanEmailStudents() {
                    return false;
                }

                @Override public SessionMonth.Flag getDateFlag(
                        EventInterface.EventType type, java.util.Date date) { return null; }
                @Override public EventInterface.EventPropertiesRpcResponse getProperties() { return null; }

                @Override
                public List<EventInterface.SelectionInterface> getSelection() {
                    return List.of();
                }

                @Override
                public String getRoomFilter() {
                    return "";
                }

                @Override
                public EventInterface.ContactInterface getMainContact() {
                    return null;
                }

                @Override
                public List<Date> getSelectedDates() {
                    return List.of();
                }

                @Override
                public StartEndTimeSelector.StartEndTime getSelectedTime() {
                    return null;
                }
            };

    /** Minimal concrete MeetingInterface — all methods return safe defaults. */
    private static MeetingInterface stubMeeting() {
        return new MeetingInterface() {
            // MeetingInterface is a plain class/interface in the shared package;
            // override only the methods the table reads during add().
            @Override public boolean isPast()               { return false; }
            @Override public boolean isArrangeHours()       { return false; }
            @Override public java.util.Date getMeetingDate(){ return new java.util.Date(); }
            @Override public Long getId()                   { return 1L; }
            // leave every other method at its default (returns null / false / 0)
        };
    }

    // -----------------------------------------------------------------------
    // EventMeetingRow — pure-Java inner class, no widget involved
    // -----------------------------------------------------------------------

    @Test
    void constructor_withoutParent_setsFieldsCorrectly() {
        EventMeetingRow row = new EventMeetingRow(null, null);

        assertFalse(row.hasEvent());
        assertFalse(row.hasMeeting());
        assertFalse(row.hasParent());
    }

    @Test
    void constructor_withParent_setsParentCorrectly() {
        EventMeetingRow parent = new EventMeetingRow(null, null);
        EventMeetingRow child  = new EventMeetingRow(null, null, parent);

        assertTrue(child.hasParent());
        assertSame(parent, child.getParent());
    }

}