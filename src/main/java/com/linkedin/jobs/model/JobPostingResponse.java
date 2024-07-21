package com.linkedin.jobs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingResponse {
	private String title;
    private String subtitle;
    private String location;
    private String listDate;
    private String listDateAgo;
    private String jobUrl;
}
