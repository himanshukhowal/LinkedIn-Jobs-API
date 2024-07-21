package com.linkedin.jobs.enums;

public interface CommonEnums {

	public static enum ExperianceLevel {
		internship(1), entrylevel(2), associate(3), senior(4), director(5), executive(6);

		private final Integer experianceLevel;

		ExperianceLevel(Integer experianceLevel) {
			this.experianceLevel = experianceLevel;
		}

		public Integer getExperianceLevel() {
			return experianceLevel;
		}
	}

	public static enum JobType {
		fulltime("F"), parttime("P"), contract("C"), temporary("T"), volunteer("V"), internship("I");

		private final String jobType;

		JobType(String jobType) {
			this.jobType = jobType;
		}

		public String getJobType() {
			return jobType;
		}
	}
	
	public static enum RemoteFilter {
		onsite(1), remote(2), hybrid(3);

		private final Integer remoteFilter;

		RemoteFilter(Integer remoteFilter) {
			this.remoteFilter = remoteFilter;
		}

		public Integer getRemoteFilter() {
			return remoteFilter;
		}
	}
	
	public static enum Salary {
		S_40000(1), S_60000(2), S_80000(3), S_100000(4), S_120000(5);

		private final Integer salary;

		Salary(Integer salary) {
			this.salary = salary;
		}

		public Integer getSalary() {
			return salary;
		}
	}
	
	public static enum DateRange {
		pastmonth("r2592000"), pastweek("r604800"), pastday("r86400");

		private final String dateRange;

		DateRange(String dateRange) {
			this.dateRange = dateRange;
		}

		public String getDateRange() {
			return dateRange;
		}
	}
	
	public static enum SortMethod {
		recent("DD"), relevant("R");

		private final String sortMethod;

		SortMethod(String sortMethod) {
			this.sortMethod = sortMethod;
		}

		public String getSortMethod() {
			return sortMethod;
		}
	}

}
