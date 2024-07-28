package com.linkedin.jobs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linkedin.jobs.constants.AppConstants;
import com.linkedin.jobs.enums.SuccessResponseEnum;
import com.linkedin.jobs.model.LinkedinJobPosting;
import com.linkedin.jobs.responsehandler.ResponseHandler;
import com.linkedin.jobs.service.JobService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/linkedin")
public class LinkedInController {

	@Autowired
	private JobService linkedInJobService;

	@PostMapping("/jobs")
	@RolesAllowed({ AppConstants.ROLE_ADMIN })
	public ResponseEntity<?> jobsEndpoint(HttpServletRequest request, @AuthenticationPrincipal Jwt jwt, @RequestBody LinkedinJobPosting jobs)
			throws Exception {
		try {
			log.info("Started Execution in LinkedInController : jobsEndpoint");
			return ResponseHandler.generateResponse(SuccessResponseEnum.SUCCESS_GET.getResponseMessage(), request,
					SuccessResponseEnum.SUCCESS_GET.getResponseCode(), linkedInJobService.getJobPostings(jobs));
		} finally {
			log.info("Started Completed in LinkedInController : jobsEndpoint");
		}
	}
}
