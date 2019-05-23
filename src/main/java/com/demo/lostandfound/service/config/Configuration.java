package com.demo.lostandfound.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("agent-service")
public class Configuration {

	private Double commission;

	public void setCommission(Double commission) {this.commission = commission;}
	public Double getCommission() {return commission;}
}
