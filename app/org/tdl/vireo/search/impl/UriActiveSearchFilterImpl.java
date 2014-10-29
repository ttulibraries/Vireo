package org.tdl.vireo.search.impl;

//import groovy.json.StringEscapeUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.tdl.vireo.model.ActionLog;
import org.tdl.vireo.model.CustomActionDefinition;
import org.tdl.vireo.model.EmbargoType;
import org.tdl.vireo.model.Person;
import org.tdl.vireo.model.PersonRepository;
import org.tdl.vireo.model.SettingsRepository;
import org.tdl.vireo.model.Submission;
import org.tdl.vireo.model.SubmissionRepository;
import org.tdl.vireo.search.ActiveSearchFilter;
import org.tdl.vireo.search.SearchFilter;
import org.tdl.vireo.search.SearchOrder;
import org.tdl.vireo.search.Semester;

import play.Logger;

/**
 * An URI based implementation of active search.
 * 
 * The encode and decode strings generated by this are escaped using URI
 * escaping rules, thus it's inherently URI safe.
 * 
 * @author <a href="http://www.scottphillips.com">Scott Phillips</a>
 * @author Micah Cooper
 */
public class UriActiveSearchFilterImpl implements ActiveSearchFilter {

	// Spring injection
	public PersonRepository personRepo = null;
	public SubmissionRepository subRepo = null;
	public SettingsRepository settingRepo = null;

	// Danamic variables
	public List<Submission> includedSubmissions = new ArrayList<Submission>();
	public List<Submission> excludedSubmissions = new ArrayList<Submission>();
	public List<ActionLog> includedActionLogs = new ArrayList<ActionLog>();
	public List<ActionLog> excludedActionLogs = new ArrayList<ActionLog>();
	public List<String> searchText = new ArrayList<String>();
	public List<String> states = new ArrayList<String>();
	public List<Person> assignees = new ArrayList<Person>();
	public List<EmbargoType> embargos = new ArrayList<EmbargoType>();
	public List<Semester> semesters = new ArrayList<Semester>();
	public List<String> degrees = new ArrayList<String>();
	public List<String> departments = new ArrayList<String>();
	public List<String> programs = new ArrayList<String>();
	public List<String> colleges = new ArrayList<String>();
	public List<String> majors = new ArrayList<String>();
	public List<String> documentTypes = new ArrayList<String>();
	public Boolean umiRelease = null;
	public Date rangeStart = null;
	public Date rangeEnd = null;
	public List<SearchOrder> columns = new ArrayList<SearchOrder>();
	public List<CustomActionDefinition> customActions = new ArrayList<CustomActionDefinition>();
	
	/**
	 * Use Spring to construct this object.
	 */
	private UriActiveSearchFilterImpl() {
		// Use Spring
	}
	
	/**
	 * @param personRepo
	 *            The person repository to use for looking up people.
	 */
	public void setPersonRepository(PersonRepository personRepo) {
		this.personRepo = personRepo;
	}

	/**
	 * @param subRepo
	 *            The submission repository to use for looking up submissions.
	 */
	public void setSubmissionRepository(SubmissionRepository subRepo) {
		this.subRepo = subRepo;
	}
	
	/**
	 * @param settingRepo
	 *            The setting repository to use for looking up embargo types.
	 */
	public void setSettingsRepository(SettingsRepository settingRepo) {
		this.settingRepo = settingRepo;
	}
	
	@Override
	public List<Submission> getIncludedSubmissions() {
		return includedSubmissions;
	}

	@Override
	public void addIncludedSubmission(Submission sub) {
		includedSubmissions.add(sub);
	}

	@Override
	public void removeIncludedSubmission(Submission sub) {
		includedSubmissions.remove(sub);
	}
	
	@Override
	public List<Submission> getExcludedSubmissions() {
		return excludedSubmissions;
	}

	@Override
	public void addExcludedSubmission(Submission sub) {
		excludedSubmissions.add(sub);
	}

	@Override
	public void removeExcludedSubmission(Submission sub) {
		excludedSubmissions.remove(sub);
	}
	
	@Override
	public List<ActionLog> getIncludedActionLogs() {
		return includedActionLogs;
	}

	@Override
	public void addIncludedActionLog(ActionLog log) {
		includedActionLogs.add(log);
	}

	@Override
	public void removeIncludedActionLog(ActionLog log) {
		includedActionLogs.remove(log);
	}
	
	@Override
	public List<ActionLog> getExcludedActionLogs() {
		return excludedActionLogs;
	}

	@Override
	public void addExcludedActionLog(ActionLog log) {
		excludedActionLogs.add(log);
	}

	@Override
	public void removeExcludedActionLog(ActionLog log) {
		excludedActionLogs.remove(log);
	}
	
	@Override
	public List<String> getSearchText() {
		return searchText;
	}

	@Override
	public void addSearchText(String text) {
		searchText.add(text);
	}

	@Override
	public void removeSearchText(String text) {
		searchText.remove(text);
	}

	@Override
	public List<String> getStates() {
		return states;
	}

	@Override
	public void addState(String state) {
		states.add(state);
	}

	@Override
	public void removeState(String state) {
		states.remove(state);
	}

	@Override
	public List<Person> getAssignees() {
		return assignees;
	}

	@Override
	public void addAssignee(Person assignee) {
		assignees.add(assignee);
	}

	@Override
	public void removeAssignee(Person assignee) {
		assignees.remove(assignee);
	}
	
	@Override
	public List<EmbargoType> getEmbargoTypes() {
		return embargos;
	}
	
	@Override
	public void addEmbargoType(EmbargoType type) {
		embargos.add(type);
	}
	
	@Override
	public void removeEmbargoType(EmbargoType type) {
		embargos.remove(type);
	}

	@Override
	public List<Semester> getGraduationSemesters() {
		return semesters;
	}

	@Override
	public void addGraduationSemester(Semester semester) {
		semesters.add(semester);
	}
	
	@Override
	public void removeGraduationSemester(Semester semester) {
		semesters.remove(semester);
	}
	
	@Override
	public void addGraduationSemester(Integer year, Integer month) {
		addGraduationSemester(new Semester(year,month));
	}
	
	@Override
	public void removeGraduationSemester(Integer year, Integer month) {
		removeGraduationSemester(new Semester(year,month));
	}
	
	@Override
	public List<String> getDegrees() {
		return degrees;
	}

	@Override
	public void addDegree(String degree) {
		degrees.add(degree);
	}

	@Override
	public void removeDegree(String degree) {
		degrees.remove(degree);
	}

	@Override
	public List<String> getDepartments() {
		return departments;
	}

	@Override
	public void addDepartment(String department) {
		departments.add(department);
	}

	@Override
	public void removeDepartment(String department) {
		departments.remove(department);
	}

	@Override
	public List<String> getPrograms() {
		return programs;
	}
	
	@Override
	public void addProgram(String program) {
		programs.add(program);
	}
	
	@Override
	public void removeProgram(String program) {
		programs.remove(program);
	}
	
	@Override
	public List<String> getColleges() {
		return colleges;
	}

	@Override
	public void addCollege(String college) {
		colleges.add(college);
	}

	@Override
	public void removeCollege(String college) {
		colleges.remove(college);
	}
	
	@Override
	public List<String> getMajors() {
		return majors;
	}

	@Override
	public void addMajor(String major) {
		majors.add(major);
	}

	@Override
	public void removeMajor(String major) {
		majors.remove(major);
	}

	@Override
	public List<String> getDocumentTypes() {
		return documentTypes;
	}

	@Override
	public void addDocumentType(String documentType) {
		documentTypes.add(documentType);
	}

	@Override
	public void removeDocumentType(String documentType) {
		documentTypes.remove(documentType);
	}

	@Override
	public Boolean getUMIRelease() {
		return umiRelease;
	}

	@Override
	public void setUMIRelease(Boolean value) {
		umiRelease = value;
	}

	@Override
	public Date getDateRangeStart() {
		return rangeStart;
	}
	
	@Override
	public void setDateRangeStart(Date start) {
		rangeStart = start;
	}

	@Override
	public Date getDateRangeEnd() {
		return rangeEnd;
	}
	
	@Override
	public void setDateRangeEnd(Date end) {
		rangeEnd = end;
	}
	
	@Override
    public List<SearchOrder> getColumns() {
        return columns;
    }

    @Override
    public void setColumns(List<SearchOrder> columns) {
        this.columns = columns;
    }
    
    @Override
    public List<CustomActionDefinition> getCustomActions() {
	    return customActions;
    }

	@Override
    public void addCustomAction(CustomActionDefinition customAction) {
	    customActions.add(customAction);
    }

	@Override
    public void removeCustomAction(CustomActionDefinition customAction) {
	    customActions.remove(customAction);
    }
	
	/**
	 * The string generated by this encoding will be of the form:
	 * 
	 * :value1,value2:value1,value2:
	 * 
	 * Each list of values such as searchText, degrees, departments, etc, are
	 * separated by ":" and inside each of those lists individual values are
	 * separated by ",". Thus the null search filter would be: "::::::::::::::"
	 * All the lists would be null in this case.
	 */
	@Override
	public String encode() {
		
		// Format: :one,two:other,bob:
		
		StringBuilder result = new StringBuilder();
		result.append(":");
		
		// Handle all the lists.
		encodeList(result,includedSubmissions);
		encodeList(result,excludedSubmissions);
		encodeList(result,includedActionLogs);
		encodeList(result,excludedActionLogs);
		encodeList(result,searchText);
		encodeList(result,states);
		encodeList(result,assignees);
		encodeList(result,embargos);
		encodeList(result,semesters);
		encodeList(result,degrees);
		encodeList(result,departments);
		encodeList(result,colleges);
		encodeList(result,programs);
		encodeList(result,majors);
		encodeList(result,documentTypes);
		encodeList(result,columns);
		encodeList(result,customActions);
		
		// Handle the single values.
		if (umiRelease != null) {
			result.append(umiRelease.toString());
		}
		result.append(":");
		
		if (rangeStart != null) {
			result.append(rangeStart.getTime());
		}
		result.append(":");
		
		if (rangeEnd != null) {
			result.append(rangeEnd.getTime());
		}
		result.append(":");
		
		return result.toString();
	}
	
	@Override
	public void decode(String encoded) {
		try {
			String[] split = encoded.split(":",-1);
			if (split.length != 22)
				throw new IllegalArgumentException("Unable to decode active search filter because it does not have the 19 expected number of components instead it has "+split.length);

			// Decode all the lists
			int i = 1;
			includedSubmissions = decodeList(split[i++],Submission.class);
			excludedSubmissions = decodeList(split[i++],Submission.class);
			includedActionLogs = decodeList(split[i++],ActionLog.class);
			excludedActionLogs = decodeList(split[i++],ActionLog.class);
			searchText = decodeList(split[i++],String.class);
			states = decodeList(split[i++],String.class);
			assignees = decodeList(split[i++],Person.class);
			embargos = decodeList(split[i++],EmbargoType.class);
			semesters = decodeList(split[i++],Semester.class);
			degrees = decodeList(split[i++],String.class);
			departments = decodeList(split[i++],String.class);
			colleges = decodeList(split[i++],String.class);
			programs = decodeList(split[i++],String.class);
			majors = decodeList(split[i++],String.class);
			documentTypes = decodeList(split[i++],String.class);
			columns = decodeList(split[i++],SearchOrder.class);
			customActions = decodeList(split[i++],CustomActionDefinition.class);

			// Handle the single values
			if ("true".equalsIgnoreCase(split[i])) {
				umiRelease = true;
			} else if ("false".equalsIgnoreCase(split[i])) {
				umiRelease = false;
			} else {
				umiRelease = null;
			}
			i++;

			if (split[i].length() != 0) {
				try {
					rangeStart = new Date(Long.valueOf(split[i]));
				} catch (RuntimeException re) {
					Logger.warn("Unable to decode value '"+split[i]+"' for rangeStart.");
				}
			} else {
				rangeStart = null;
			}
			i++;
			
			if (split[i].length() != 0) {
				try {
					rangeEnd = new Date(Long.valueOf(split[i]));
				} catch (RuntimeException re) {
					Logger.warn("Unable to decode value '"+split[i]+"' for rangeEnd.");
				}
			} else {
				rangeEnd = null;
			}
			
		} catch (RuntimeException re) {
			// If anything other than the specific cases we have already caught
			// just wrapped the exception with more information.
			throw new RuntimeException("Unable to decode the search filter: " + encoded, re);
		}
	}
	
	@Override
	public void copyTo(SearchFilter other) {
		
		// We're going to be sneaky and take advantage of the fact that the list return by all filters are mutable.
		
		other.getIncludedSubmissions().clear();
		other.getIncludedSubmissions().addAll(this.includedSubmissions);
		
		other.getExcludedSubmissions().clear();
		other.getExcludedSubmissions().addAll(this.excludedSubmissions);
		
		other.getIncludedActionLogs().clear();
		other.getIncludedActionLogs().addAll(this.includedActionLogs);
		
		other.getExcludedActionLogs().clear();
		other.getExcludedActionLogs().addAll(this.excludedActionLogs);
		
		other.getSearchText().clear();
		other.getSearchText().addAll(this.searchText);
		
		other.getStates().clear();
		other.getStates().addAll(this.states);
		
		other.getAssignees().clear();
		for(Person assignee : this.assignees) {
			other.addAssignee(assignee);
		}
		/*
		 *  For some reason this wont work.
		 *	other.getAssignees().addAll(this.assignees);
		 */
		
		other.getEmbargoTypes().clear();
		other.getEmbargoTypes().addAll(this.embargos);
		
		other.getGraduationSemesters().clear();
		other.getGraduationSemesters().addAll(this.semesters);
		
		other.getDegrees().clear();
		other.getDegrees().addAll(this.degrees);
		
		other.getDepartments().clear();
		other.getDepartments().addAll(this.departments);
		
		other.getColleges().clear();
		other.getColleges().addAll(this.colleges);
		
		other.getPrograms().clear();
		other.getPrograms().addAll(this.programs);
		
		other.getMajors().clear();
		other.getMajors().addAll(this.majors);
		
		other.getDocumentTypes().clear();
		other.getDocumentTypes().addAll(this.documentTypes);
		
		other.getColumns().clear();
		other.getColumns().addAll(this.columns);
		
		other.getCustomActions().clear();
		for(CustomActionDefinition customAction : this.customActions) {
			other.addCustomAction(customAction);
		}
		//other.getCustomActions().addAll(this.customActions);
		
		other.setUMIRelease(this.umiRelease);
		other.setDateRangeStart(this.rangeStart);
		other.setDateRangeEnd(this.rangeEnd);
	}

	@Override
	public void copyFrom(SearchFilter other) {
		
		this.includedSubmissions = new ArrayList<Submission>(other.getIncludedSubmissions());
		this.excludedSubmissions = new ArrayList<Submission>(other.getExcludedSubmissions());
		this.includedActionLogs = new ArrayList<ActionLog>(other.getIncludedActionLogs());
		this.excludedActionLogs = new ArrayList<ActionLog>(other.getExcludedActionLogs());
		this.searchText = new ArrayList<String>(other.getSearchText());
		this.states = new ArrayList<String>(other.getStates());
		this.assignees = new ArrayList<Person>(other.getAssignees());
		this.embargos = new ArrayList<EmbargoType>(other.getEmbargoTypes());
		this.semesters = new ArrayList<Semester>(other.getGraduationSemesters());
		this.degrees = new ArrayList<String>(other.getDegrees());
		this.departments = new ArrayList<String>(other.getDepartments());
		this.colleges = new ArrayList<String>(other.getColleges());
		this.programs = new ArrayList<String>(other.getPrograms());
		this.majors = new ArrayList<String>(other.getMajors());
		this.documentTypes = new ArrayList<String>(other.getDocumentTypes());
		this.columns = new ArrayList<SearchOrder>(other.getColumns());
		this.customActions = new ArrayList<CustomActionDefinition>(other.getCustomActions());
		this.umiRelease = other.getUMIRelease();
		this.rangeStart = other.getDateRangeStart();
		this.rangeEnd = other.getDateRangeEnd();
	}
	
	
	
	/**
	 * Internal method to decode an individual list of items from its serialized
	 * form.
	 * 
	 * @param encoded
	 *            The encoded string.
	 * @param type
	 *            The type of object expected.
	 * @return A list of encoded objects
	 */
	protected <T> List<T> decodeList(String encoded, Class<T> type) {
	
		String[] split = encoded.split(",");
		
		List<T> result = new ArrayList<T>();
		for (String raw : split) {
			if (raw.length() ==0)
				continue;
			
			try {
				if ( type == String.class) {
					// List type is string
					result.add( (T) unescape(raw));
				
				} else if (type == Integer.class) {
					// List type is integer, grad month or year
					Integer value = Integer.valueOf(raw);
					result.add((T) value);
					
				} else if (type == Person.class){
					// List type is person, just for assignee
					
					if ("null".equals(raw)) {
						result.add(null); // unassigned
					} else {
						Long personId = Long.valueOf(raw);
						Person person = personRepo.findPerson(personId);
						result.add((T) person);
					}
				} else if (type == Submission.class) {
					// List type is submission
					
					Long subId = Long.valueOf(raw);
					Submission sub = subRepo.findSubmission(subId);
					result.add((T) sub);
					
				} else if (type == ActionLog.class) {
					// List type is ActionLog
					
					Long logId = Long.valueOf(raw);
					ActionLog log = subRepo.findActionLog(logId);
					result.add((T) log);
					
				} else if (type == EmbargoType.class) {
					// List type is embargo types
					
					Long embargoId = Long.valueOf(raw);
					EmbargoType embargo = settingRepo.findEmbargoType(embargoId);
					result.add((T) embargo);
					
				} else if (type == Semester.class) {
					// List type is graduation semestens: year/month
					String[] semesterSplit = raw.split("/");

					Semester semester = new Semester();
					if (!"null".equals(semesterSplit[0]))
						semester.year = Integer.valueOf(semesterSplit[0]);
					if (!"null".equals(semesterSplit[1]))
						semester.month = Integer.valueOf(semesterSplit[1]);

					result.add((T) semester);
					
				} else if (type == SearchOrder.class) {
				    // List type is SearchOrder column
				    int columnId = Integer.valueOf(raw);
				    SearchOrder column = SearchOrder.find(columnId);
				    result.add((T) column);
				} else if (type == CustomActionDefinition.class) {
					// List type is CustomActionDefinition
				    Long customActionId = Long.valueOf(raw);
				    CustomActionDefinition customAction = settingRepo.findCustomActionDefinition(customActionId);
				    result.add((T) customAction);
				} else {
					throw new IllegalArgumentException("Unable to decode unexpected object type: "+ type);
				}
			} catch (RuntimeException re) {
				// Just log the error but keep on trucking. One legitimate
				// reason why this may fail is if a person has been deleted
				// since this filter was created, thus the person's id would no
				// longer be valid.
				Logger.warn(re,"Unable to decode value '"+raw+"' for type "+type.getName());
			}
		}
		
		return result;
	}
	
	/**
	 * Encode the list of provided objects. The result stringbuilder will be
	 * modified to include a comma separated list of values, and for convenience
	 * will end with a trailing ":". If the value type is string then it will be
	 * URI encoded prior to putting in the list.
	 * 
	 * Only three datatypes are supported by this method: String, Integer,
	 * Person, EmbargoType, and GraduationSemester. everything else will result
	 * in an error.
	 * 
	 * @param result
	 *            Where the encoded list will be appended.
	 * @param values
	 *            The values to encode.
	 */
	protected void encodeList(StringBuilder result, List<?> values) {

		boolean first = true;
		for (Object value: values) {
			if (first)
				first = false;
			else
				result.append(",");
			
			if (value == null) {
				result.append("null");
			} else if (value instanceof String) {
				// Plain old strings, the most common case.
				result.append(escape((String)value));
			
			} else if (value instanceof Integer){
				// Integers from grad month & year
				result.append(String.valueOf((Integer) value));
				
			} else if (value instanceof Person) {
				// Full person object from assignee
				Long personId = ((Person) value).getId();
				result.append(String.valueOf(personId));
				
			} else if (value instanceof Submission) {
				// Full submission object
				Long subId = ((Submission) value).getId();
				result.append(String.valueOf(subId));
				
			} else if (value instanceof ActionLog) {
				// Full actionlog object
				Long logId = ((ActionLog) value).getId();
				result.append(String.valueOf(logId));
				
			} else if (value instanceof EmbargoType) {
				// Full embargo object
				Long embargoId = ((EmbargoType) value).getId();
				result.append(String.valueOf(embargoId));
				
			} else if (value instanceof SearchOrder) {
                // Full SearchOrder(column) object
                int columnId = ((SearchOrder)value).getId();
                result.append(String.valueOf(columnId));
                
            } else if (value instanceof Semester) {
				// Graduation semester: year/month
				
				Semester semester = (Semester) value;
				if (semester.year == null)
					result.append("null");
				else
					result.append(String.valueOf(semester.year));
				
				result.append("/");
				
				if (semester.month == null)
					result.append("null");
				else
					result.append(String.valueOf(semester.month));
				
			} else if (value instanceof CustomActionDefinition) {
				// Full CustomActionDefinition object
                Long customActionId = ((CustomActionDefinition)value).getId();
                result.append(String.valueOf(customActionId));
			} else {
				throw new IllegalArgumentException("Unable to encode unexpected object type: "+value.getClass().getName());
			}
		}
		
		// Signify end of list
		result.append(":");
	}
	
	/**
	 * URI escape the provided value. This method will alse ensure that all ":"
	 * and "," are escaped as well even though they not nessesaraly be.
	 * Otherwise they will interfear with the encoding of other paramaters.
	 * 
	 * @param raw
	 *            The raw value to be escaped.
	 * @return The resulting escaped value.
	 */
	protected String escape(String raw) {
		String escapped = URLEncoder.encode(raw);
		escapped = escapped.replaceAll(",", "%2C");
		escapped = escapped.replaceAll(":", "%3A");
		return escapped;
	}

	/**
	 * Unescape a previously escaped value. This will just do a regular URI
	 * escapeing on the value.
	 * 
	 * @param escapped
	 *            The escaped value.
	 * @return The original raw string.
	 */
	protected String unescape(String escapped) {
		return URLDecoder.decode(escapped);
	}
}
