package com.peter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileTest {

	public static void main(String[] args) throws IOException {
		List<String> lines=FileUtils.readLines(new File("/home/peter/文档/lunar-2019/SOA辅导书/北美SOA考试Financial Mathematics辅导书.txt"), "utf-8");
		System.out.println(lines.size());
		System.out.println(lines.get(256));
	}

}
