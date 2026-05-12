/*

 * Licensed to The Apereo Foundation under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 *
 * The Apereo Foundation licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
*/
package org.unitime.timetable.form;

import java.util.ArrayList;
import java.util.Collection;

import org.unitime.localization.impl.Localization;
import org.unitime.localization.messages.CourseMessages;
import org.unitime.timetable.action.UniTimeAction;
import org.unitime.timetable.model.Class_;
import org.unitime.timetable.model.SubjectArea;
import org.unitime.timetable.model.comparators.ClassCourseComparator;
import org.unitime.timetable.util.Constants;
import org.unitime.timetable.util.Day;

/**
 * @author Stephanie Schluttenhofer, Tomas Muller
 */
public class ClassAssignmentsReportForm implements UniTimeForm, ClassListFormInterface {
	private static final long serialVersionUID = 3257854294022959921L;	
	protected final static CourseMessages MSG = Localization.create(CourseMessages.class);
	private Collection<Class_> classes;
	private Collection<SubjectArea> subjectAreas;
	private String[] subjectAreaIds; 
	private String buttonAction;
	private String subjectAreaAbbv;
	private String ctrlInstrOfferingId;
	
	private String sortBy;
	private String filterAssignedRoom;
	private String filterManager;
	private String filterIType;
//	private boolean filterAssignedTimeMon;
//	private boolean filterAssignedTimeTue;
//	private boolean filterAssignedTimeWed;
//	private boolean filterAssignedTimeThu;
//	private boolean filterAssignedTimeFri;
//	private boolean filterAssignedTimeSat;
//	private boolean filterAssignedTimeSun;
private boolean[] filterAssignedDays = new boolean[7];
	private String filterAssignedTimeHour;
	private String filterAssignedTimeMin;
	private String filterAssignedTimeAmPm;
	private String filterAssignedTimeLength;
	private boolean sortByKeepSubparts;
	private boolean showCrossListedClasses;
    
	private boolean returnAllControlClassesForSubjects;
	private String[] userDeptIds;
	
	/**
     * @return Returns the ctrlInstrOfferingId.
     */
    public String getCtrlInstrOfferingId() {
        return ctrlInstrOfferingId;
    }
    /**
     * @param ctrlInstrOfferingId The ctrlInstrOfferingId to set.
     */
    public void setCtrlInstrOfferingId(String ctrlInstrOfferingId) {
        this.ctrlInstrOfferingId = ctrlInstrOfferingId;
    }
    /**
     * @return Returns the subjectAreaAbbv.
     */
    public String getSubjectAreaAbbv() {
        return subjectAreaAbbv;
    }
    /**
     * @param subjectAreaAbbv The subjectAreaAbbv to set.
     */
    public void setSubjectAreaAbbv(String subjectAreaAbbv) {
        this.subjectAreaAbbv = subjectAreaAbbv;
    }
    /**
     * @return Returns the buttonAction.
     */
    public String getButtonAction() {
        return buttonAction;
    }
    /**
     * @param buttonAction The buttonAction to set.
     */
    public void setButtonAction(String buttonAction) {
        this.buttonAction = buttonAction;
    }
    /**
	 * @return Returns the subjectAreaIds.
	 */
	public String[] getSubjectAreaIds() {
		return subjectAreaIds;
	}
	/**
	 * @param subjectAreaIds The subjectAreaIds to set.
	 */
	public void setSubjectAreaIds(String[] subjectAreaIds) {
		this.subjectAreaIds = subjectAreaIds;
	}

	@Override
	public void reset(){
		classes = new ArrayList<Class_>();
		subjectAreas = new ArrayList<SubjectArea>();
		subjectAreaIds = new String[0];

		sortBy = ClassCourseComparator.getName(ClassCourseComparator.SortBy.NAME);
		filterManager = "";
		filterAssignedRoom = "";
		filterIType = "";
		sortByKeepSubparts = false;
		
//		filterAssignedTimeMon = false;
//		filterAssignedTimeTue = false;
//		filterAssignedTimeWed = false;
//		filterAssignedTimeThu = false;
//		filterAssignedTimeFri = false;
//		filterAssignedTimeSat = false;
//		filterAssignedTimeSun = false;
		this.filterAssignedDays = new boolean[7];
		filterAssignedTimeHour = "";
		filterAssignedTimeMin = "";
		filterAssignedTimeAmPm = "";
		filterAssignedTimeLength = "";
		
		returnAllControlClassesForSubjects = true;
		userDeptIds = new String[0];
		showCrossListedClasses = false;
	}

    /**
     * @return Returns the classes.
     */
    public Collection<Class_> getClasses() {
        return classes;
    }
    /**
     * @param classes The classes to set.
     */
    public void setClasses(Collection<Class_> classes) {
        this.classes = classes;
    }
    /**
     * @return Returns the subjectAreas.
     */
    public Collection<SubjectArea> getSubjectAreas() {
        return subjectAreas;
    }
    /**
     * @param subjectAreas The subjectAreas to set.
     */
    public void setSubjectAreas(Collection<SubjectArea> subjectAreas) {
        this.subjectAreas = subjectAreas;
    }

    @Override
    public void validate(UniTimeAction action) {
    	if (subjectAreaIds == null || subjectAreaIds.length == 0)
    		action.addFieldError("subjectAreaIds", MSG.errorSubjectRequired());
    }
	public boolean getFilterAssignedDay(Day day) {
		return filterAssignedDays[day.ordinal()];
	}

	public void setFilterAssignedDay(Day day, boolean value) {
		filterAssignedDays[day.ordinal()] = value;
	}
	public String getSortBy() { return sortBy; }
	public void setSortBy(String sortBy) { this.sortBy = sortBy; }
	public String[] getSortByOptions() { return ClassCourseComparator.getNames(); }
	public String getFilterManager() { return filterManager; }
	public void setFilterManager(String filterManager) { this.filterManager = filterManager; }
	public String[] getFilterManagers() { return (filterManager == null ? new String[0] : filterManager.split(",")); }
	public void setFilterManagers(String[] filterManagers) {
		this.filterManager = "";
		if (filterManagers != null)
			for (String filterManager: filterManagers) {
				this.filterManager += (this.filterManager.isEmpty() ? "" : ",") + filterManager;
			}
	}
	public String getFilterAssignedRoom() { return filterAssignedRoom; }
	public void setFilterAssignedRoom(String filterAssignedRoom) { this.filterAssignedRoom = filterAssignedRoom; }
	public String getFilterInstructor() { return ""; }
	public String getFilterIType() { return filterIType; }
	public void setFilterIType(String filterIType) { this.filterIType = filterIType; }

	public boolean getFilterAssignedTimeMon() {
		return getFilterAssignedDay(Day.MON);
	}

	public void setFilterAssignedTimeMon(boolean value) {
		setFilterAssignedDay(Day.MON, value);
	}
	public boolean getFilterAssignedTimeTue() {
		return getFilterAssignedDay(Day.TUE);
	}

	public void setFilterAssignedTimeTue(boolean value) {
		setFilterAssignedDay(Day.TUE, value);
	}
	public boolean getFilterAssignedTimeWed() {
		return getFilterAssignedDay(Day.WED);
	}

	public void setFilterAssignedTimeWed(boolean value) {
		setFilterAssignedDay(Day.WED, value);
	}
	public boolean getFilterAssignedTimeThu() {
		return getFilterAssignedDay(Day.THU);
	}

	public void setFilterAssignedTimeThu(boolean value) {
		setFilterAssignedDay(Day.THU, value);
	}
	public boolean getFilterAssignedTimeFri() {
		return getFilterAssignedDay(Day.FRI);
	}

	public void setFilterAssignedTimeFri(boolean value) {
		setFilterAssignedDay(Day.FRI, value);
	}
	public boolean getFilterAssignedTimeSat() {
		return getFilterAssignedDay(Day.SAT);
	}

	public void setFilterAssignedTimeSat(boolean value) {
		setFilterAssignedDay(Day.SAT, value);
	}
	public boolean getFilterAssignedTimeSun() {
		return getFilterAssignedDay(Day.SUN);
	}

	public void setFilterAssignedTimeSun(boolean value) {
		setFilterAssignedDay(Day.SUN, value);
	}
	public String getFilterAssignedTimeHour() { return filterAssignedTimeHour; }
	public void setFilterAssignedTimeHour(String filterAssignedTimeHour) { this.filterAssignedTimeHour = filterAssignedTimeHour; }
	public String getFilterAssignedTimeMin() { return filterAssignedTimeMin; }
	public void setFilterAssignedTimeMin(String filterAssignedTimeMin) { this.filterAssignedTimeMin = filterAssignedTimeMin; }
	public String getFilterAssignedTimeAmPm() { return filterAssignedTimeAmPm; }
	public void setFilterAssignedTimeAmPm(String filterAssignedTimeAmPm) { this.filterAssignedTimeAmPm = filterAssignedTimeAmPm; }
	public String getFilterAssignedTimeLength() { return ("0".equals(filterAssignedTimeLength)?"":filterAssignedTimeLength); }
	public void setFilterAssignedTimeLength(String filterAssignedTimeLength) { this.filterAssignedTimeLength = filterAssignedTimeLength; }
	
	public String[] getFilterAssignedTimeHours() {
		return new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	}
	public String[] getFilterAssignedTimeMins() {
		return new String[] { "", "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" };
	}
	public String[] getFilterAssignedTimeAmPms() {
		return new String[] { "", MSG.timeAm(), MSG.timePm()};
	}
	public String[] getFilterAssignedTimeLengths() {
		String[] ret = new String[41];
		ret[0]="";
		for (int i=1;i<ret.length;i++)
			ret[i] = String.valueOf(5*i);
		return ret;
	}

	public int getFilterDayCode() {
		int dayCode = 0;

		for (Day day : Day.values()) {
			if (getFilterAssignedDay(day)) {
				dayCode += Constants.DAY_CODES[day.ordinal()];
			}
		}

		return dayCode;
	}
	public void setFilterDayCode(int dayCode) {
		for (Day day : Day.values()) {
			if (dayCode >= 0) {
				setFilterAssignedDay(day,
						(dayCode & Constants.DAY_CODES[day.ordinal()]) != 0);
			} else {
				setFilterAssignedDay(day, false);
			}
		}
	}
	public int getFilterStartSlot() {
		try {
			int hour = Integer.parseInt(filterAssignedTimeHour);
			int min = Integer.parseInt(filterAssignedTimeMin);
			boolean morn = !(MSG.timePm().equals(filterAssignedTimeAmPm));
			if (hour==12) hour=0;
			int startTime = ((hour+(morn?0:12))%24)*60 + min;
			return (startTime - Constants.FIRST_SLOT_TIME_MIN) / Constants.SLOT_LENGTH_MIN;			
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		}
		return -1;
	}
	public void setFilterStartSlot(int startSlot) {
		if (startSlot>=0) {
			int startMin = startSlot * Constants.SLOT_LENGTH_MIN + Constants.FIRST_SLOT_TIME_MIN;
			int min = startMin % 60;
			int startHour = startMin / 60;
			boolean morn = (startHour<12);
			int hour = startHour % 12;
			if (hour==0) hour = 12;
			filterAssignedTimeHour = String.valueOf(hour);
			filterAssignedTimeMin = (min<10?"0":"")+min;
			filterAssignedTimeAmPm = (morn?MSG.timeAm():MSG.timePm());
			
		} else {
			filterAssignedTimeHour = "";
			filterAssignedTimeMin = "";
			filterAssignedTimeAmPm = "";
		}
	}
	public int getFilterLength() {
		try {
			return Integer.parseInt(filterAssignedTimeLength);
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		}
		return 0;
	}
	public void setFilterLength(int length) {
		if (length>=0) {
			filterAssignedTimeLength = String.valueOf(length);
		} else {
			filterAssignedTimeLength = "";
		}
	}
	public String getCourseNbr() { return null; }
	public boolean getSortByKeepSubparts() {
		return sortByKeepSubparts;
	}
	public void setSortByKeepSubparts(boolean sortByKeepSubparts) {
		this.sortByKeepSubparts = sortByKeepSubparts;
	}
	public boolean isReturnAllControlClassesForSubjects() {
		return returnAllControlClassesForSubjects;
	}
	public void setReturnAllControlClassesForSubjects(
			boolean returnAllControlClassesForSubjects) {
		this.returnAllControlClassesForSubjects = returnAllControlClassesForSubjects;
	}
	public String[] getUserDeptIds() {
		return userDeptIds;
	}
	public void setUserDeptIds(String[] userDeptIds) {
		this.userDeptIds = userDeptIds;
	}
	public Boolean getDivSec() {
		return (Boolean.valueOf(true));
	}
	public Boolean getDemand() {
		return (Boolean.valueOf(false));
	}
	public Boolean getProjectedDemand() {
		return (Boolean.valueOf(false));
	}
	public Boolean getMinPerWk() {
		return (Boolean.valueOf(false));
	}
	public Boolean getLimit() {
		return (Boolean.valueOf(true));
	}
	public Boolean getSnapshotLimit() {
		return (Boolean.valueOf(false));
	}
	public Boolean getRoomLimit() {
		return (Boolean.valueOf(false));
	}
	public Boolean getManager() {
		return (Boolean.valueOf(false));
	}
	public Boolean getDatePattern() {
		return (Boolean.valueOf(true));
	}
	public Boolean getTimePattern() {
		return (Boolean.valueOf(false));
	}
	public Boolean getPreferences() {
		return (Boolean.valueOf(false));
	}
	public Boolean getInstructor() {
		return (Boolean.valueOf(false));
	}
	public Boolean getTimetable() {
		return (Boolean.valueOf(true));
	}
	public Boolean getCredit() {
		return (Boolean.valueOf(false));
	}
	public Boolean getSubpartCredit() {
		return (Boolean.valueOf(false));
	}
	public Boolean getSchedulePrintNote() {
		return (Boolean.valueOf(true));
	}
	public Boolean getNote() {
		return (Boolean.valueOf(false));
	}
    public Boolean getConsent() {
		return (Boolean.valueOf(false));
    }
    public Boolean getTitle() {
		return (Boolean.valueOf(false));
    }
    
    public Boolean getExams() {
        return Boolean.FALSE;
    }
    public Boolean getCanSeeExams() {
        return Boolean.FALSE;
    }
	public boolean getShowCrossListedClasses() {
		return showCrossListedClasses;
	}
	public void setShowCrossListedClasses(boolean showCrossListedClasses) {
		this.showCrossListedClasses = showCrossListedClasses;
	}
	@Override
	public boolean getIncludeCancelledClasses() {
		return false;
	}
	@Override
	public Boolean getFundingDepartment() {
		return false;
	}
	@Override
	public Boolean getInstructorAssignment() {
		return false;
	}
	@Override
	public Boolean getLms() {
		return false;
	}
	@Override
	public boolean getFilterNeedInstructor() {
		return false;
	}
	@Override
	public String getWaitlist() { return null; }
	@Override
	public Boolean getWaitlistMode() { return false; }

	public int getSubjectAreaListSize() {
		return Math.min(7, getSubjectAreas().size());
	}
}
