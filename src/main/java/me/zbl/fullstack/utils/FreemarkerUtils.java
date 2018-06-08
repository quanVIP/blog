package me.zbl.fullstack.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreemarkerUtils { 
	
	    private static String defaultCharacter = "UTF-8";
	    private static Configuration config;
	    private  FreemarkerUtils() {
	    }
	    static {
	    	config = new Configuration(Configuration.getVersion());
	    	config.setDefaultEncoding(defaultCharacter);
	    	config.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
	    }
	 /**
	  * @param templatePath 模块文件的路径
	  * @param templateName 模板文件名称 
	  * @param root 数据模型根对象
	  * @param templateEncoding 模板文件的编码方式
	  */
	 public static String analysisTemplate(String templateName,Map<String,Object> root){
	  Writer out = null;
	  try {
	   File file=new File("./src/main/resources/templates");
	   //设置要解析的模板所在的目录，并加载模板文件
	   config.setDirectoryForTemplateLoading(file);
	   //设置包装器，并将对象包装为数据模型
//	   config.setObjectWrapper(new DefaultObjectWrapper());
	   
	   //获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
	   Template template=config.getTemplate(templateName);
	   //合并数据模型与模板
	   out = new StringWriter();
	      template.process(root, out);
	      return out.toString();
	  } catch (Exception e) {
	   e.printStackTrace();
	  }finally
	  {
	   try {
	    out.close();
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
	  return "";
	 } 
	 
}
