package com.peter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class CrawlConfig {
	private static List<String> userAgentList=null;
	static {
		InputStream inputStream = CrawlConfig.class.getClassLoader().getResourceAsStream("user-agent.txt");
		try {
			userAgentList=IOUtils.readLines(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static List<String> getUserAgent() {
		return userAgentList;
	}
}
