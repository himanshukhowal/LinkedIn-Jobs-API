package com.linkedin.jobs.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.linkedin.jobs.constants.AppConstants;
import com.linkedin.jobs.model.LinkedinJobPosting;
import com.linkedin.jobs.model.LinkedinJobPostingResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class JobService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	public List<LinkedinJobPostingResponse> getJobPostings(LinkedinJobPosting jobs) throws Exception {
		return extractJobPostings(jobs);
	}

	private List<LinkedinJobPostingResponse> extractJobPostings(LinkedinJobPosting jobs) throws Exception {
		String html = restTemplate.getForObject(getJobsUrl(env.getProperty(AppConstants.LINKEDIN_URL_KEY), jobs),
				String.class);

		Document document = Jsoup.parse(html);

		Elements liElements = document.select("li");
		List<LinkedinJobPostingResponse> jobPostings = new ArrayList<LinkedinJobPostingResponse>();

		for (Element liElement : liElements) {
			LinkedinJobPostingResponse jobPosting = new LinkedinJobPostingResponse();
			jobPosting.setTitle(liElement.select(".base-search-card__title").text());
			jobPosting.setSubtitle(liElement.select(".base-search-card__subtitle a").text());
			jobPosting.setLocation(liElement.select(".job-search-card__location").text());
			Element timeElement = liElement.selectFirst(".job-search-card__listdate");
			Element dateFiltered = liElement.selectFirst(".job-search-card__listdate--new");
			if (timeElement != null) {
				jobPosting.setListDate(timeElement.attr("datetime"));
				jobPosting.setListDateAgo(timeElement.text());
			} else if (dateFiltered != null) {
				jobPosting.setListDate(dateFiltered.attr("datetime"));
				jobPosting.setListDateAgo(dateFiltered.text());
			}
			Element linkElement = liElement.selectFirst(".base-card__full-link");
			if (linkElement != null) {
				jobPosting.setJobUrl(linkElement.attr("href"));
			}
			jobPostings.add(jobPosting);
		}

		return jobPostings;
	}

	private String getJobsUrl(String url, LinkedinJobPosting jobs) throws Exception {
		AtomicReference<String> atomicUrl = new AtomicReference<String>(url);
		Optional.ofNullable(jobs.getKeyword())
				.ifPresent(keyword -> atomicUrl.set(atomicUrl.get() + "&keywords=" + keyword));
		Optional.ofNullable(jobs.getLocation())
				.ifPresent(location -> atomicUrl.set(atomicUrl.get() + "&location=" + location));
		Optional.ofNullable(jobs.getDateSincePosted())
				.ifPresent(f_TPR -> atomicUrl.set(atomicUrl.get() + "&f_TPR=" + f_TPR.getDateRange()));
		Optional.ofNullable(jobs.getSalary())
				.ifPresent(f_SB2 -> atomicUrl.set(atomicUrl.get() + "&f_SB2=" + f_SB2.getSalary()));
		Optional.ofNullable(jobs.getExperienceLevel())
				.ifPresent(f_E -> atomicUrl.set(atomicUrl.get() + "&f_E=" + f_E.getExperianceLevel()));
		Optional.ofNullable(jobs.getRemoteFilter())
				.ifPresent(f_WT -> atomicUrl.set(atomicUrl.get() + "&f_WT=" + f_WT.getRemoteFilter()));
		Optional.ofNullable(jobs.getJobType())
				.ifPresent(f_JT -> atomicUrl.set(atomicUrl.get() + "&f_JT=" + f_JT.getJobType()));
		Optional.ofNullable(jobs.getPage())
				.ifPresent(start -> atomicUrl.set(atomicUrl.get() + "&start=" + (start * 10)));
		Optional.ofNullable(jobs.getSortMethod())
				.ifPresent(sortBy -> atomicUrl.set(atomicUrl.get() + "&sortBy=" + sortBy.getSortMethod()));
		return atomicUrl.get();
	}

}
