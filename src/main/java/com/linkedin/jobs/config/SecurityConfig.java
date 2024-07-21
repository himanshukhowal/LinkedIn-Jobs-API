package com.linkedin.jobs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
				.oauth2ResourceServer(
						oauth2 -> oauth2.jwt(data -> data.jwtAuthenticationConverter(grantedAuthoritiesExtractor())))
				.build();
	}

	private Converter<Jwt, AbstractAuthenticationToken> grantedAuthoritiesExtractor() {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new GrantedAuthoritiesExtractor());
		return jwtAuthenticationConverter;
	}

	static class GrantedAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {
		@SuppressWarnings("unchecked")
		public Collection<GrantedAuthority> convert(Jwt jwt) {
			return ((Map<String, Collection<?>>) jwt.getClaims().getOrDefault("realm_access", Collections.emptyMap()))
					.getOrDefault("roles", Collections.emptyList()).stream().map(data -> "ROLE_" + data.toString())
					.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		}
	}
}