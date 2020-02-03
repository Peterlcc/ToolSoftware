package com.peter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class App {
	public static void main(String[] args) throws Exception {
/*		String inputPath="/home/peter/文档/lunar-2019/SOA辅导书/北美SOA考试Financial Mathematics辅导书-英文版.txt";
		String output="/home/peter/文档/lunar-2019/SOA辅导书/北美SOA考试Financial Mathematics辅导书-translate.txt";*/
		String inputPath="input.txt";
		String output="output.txt";
		FileOutputStream outputStream = new FileOutputStream(output, true);
		// 普通方式初始化
		GoogleApi googleApi = new GoogleApi();
		// 通过代理
		// GoogleApi googleApi = new GoogleApi("122.224.227.202", 3128);
		List<String> lines = new ArrayList<String>();
		List<String> res=new ArrayList<String>();
		
		long lineCount = Files.lines(Paths.get(new File(inputPath).getPath())).count();
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(inputPath), "utf-8"));
		String line=null;
		int i=0;
		while((line=reader.readLine())!=null) {
			lines.add(line);
			i++;
			if (lines.size()>=80) {
				StringBuilder sBuilder=new StringBuilder();
				for (String string : lines) {
					sBuilder.append(string+System.lineSeparator());
				}
				String result = googleApi.translate(sBuilder.toString(), "zh");
				res.add(result);
				lines.clear();
				System.out.println("第"+(i-80)+" - "+i+"行 结果长度："+sBuilder.length()+"==="+result.length()+" 共"+lineCount+"行");
			}
			if (res.size()>=5) {
				IOUtils.writeLines(res, System.lineSeparator(),outputStream );
				res.clear();
			}
		}
		if (res.size()>0) {
			IOUtils.writeLines(res, System.lineSeparator(), outputStream);
		}
		reader.close();
		System.out.println("translate completed");
	}
}
