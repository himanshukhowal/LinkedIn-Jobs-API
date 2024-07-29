package com.linkedin.jobs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NaukriJobPosting {

	private String location;
	private String keyword;
	private Integer pageNo;
	
}
