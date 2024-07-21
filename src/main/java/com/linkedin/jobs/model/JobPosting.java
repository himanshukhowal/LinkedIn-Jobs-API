package com.linkedin.jobs.model;

import com.linkedin.jobs.enums.CommonEnums.DateRange;
import com.linkedin.jobs.enums.CommonEnums.ExperianceLevel;
import com.linkedin.jobs.enums.CommonEnums.JobType;
import com.linkedin.jobs.enums.CommonEnums.RemoteFilter;
import com.linkedin.jobs.enums.CommonEnums.Salary;
import com.linkedin.jobs.enums.CommonEnums.SortMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JobPosting {
	private String keyword;
	private String location;
	private DateRange dateSincePosted;
	private JobType jobType;
	private RemoteFilter remoteFilter;
	private Salary salary;
	private ExperianceLevel experienceLevel;
	private Integer page;
	private SortMethod sortMethod;
}
