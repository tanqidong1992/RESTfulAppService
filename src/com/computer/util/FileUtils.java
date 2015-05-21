package com.computer.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class FileUtils {
	private static Log log=Log.getLog(FileUtils.class);
	public static final String root="d:/bysj";
	static{
		
		File file=new File(root);
		if(file.exists() || file.mkdir())
		{
			Log.i("test", "success");
		}
		
	}
	public static void main(String[] args) {
		
		URL url=FileUtils.class.getResource("");
	//	FileUtils.class.getResource("").get
		System.out.println(url.toString());
		System.out.println(url.getHost());
		System.out.println(url.getPath().substring(1));
//		url.toURI()
//		File file=new File(uri)
		
		 
	}
	
	public static String writeToFile(InputStream is,String fileType)
	{
		
		String fileName=System.currentTimeMillis()+"_"+Math.random()*100+"."+fileType;
		File file=new File(root+File.separator+fileName);
		log.i("write to file "+file.getAbsolutePath());
		try {
			if(file.exists() || file.createNewFile())
			{
				FileOutputStream fos=new FileOutputStream(file);
				
				 int temp=0;
				 
				 byte []buffer=new byte[1024];
				  
					while((temp=is.read(buffer))!=-1)
					 {
						 fos.write(buffer, 0, temp);
						 
					 }
			 
				  
					 fos.flush();
					 fos.close();
					is.close();
			 
				 
				return file.getAbsolutePath();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.i("write file fail "+file.getAbsolutePath());
		}
		finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
