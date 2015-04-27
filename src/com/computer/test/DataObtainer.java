/**
 * author:tanqidong
 * create Time:2015年4月26日,下午8:53:03
 * description:
 * fileName:DataObtainer.java	
 */
package com.computer.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 
import com.computer.app.CommentInfoApp;
import com.computer.entity.CommentInfo;
 
import com.computer.net.Client;
import com.google.gson.Gson;

/**
 * @author 
 *
 */
public class DataObtainer {

	/**
	 * 商品基本的url
	 */
	private static final String baseUrl="http://item.taobao.com/item.htm?id=";
	
	/**
	 * 查询评论附带的参数
	 */
	private static final String urlCondition="&callback=jsonp_reviews_list&userNumId=1753146414&auctionNumId=39232136537&siteID=1&rateType=&orderType=sort_weight&showContent=1&attribute=&ua=&currentPageNum=";

	/**
	 * 输出日志标签
	 */
	private static final String defaultTag = "DataObtainer";
	
	/*
	 * 默认抓取的评论条数
	 */
	private static final int defaultCommentCount=100;
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		
//		这里修改商品id，做测试
		String id="43684945921";
//		抓取商品评论数据
		List<CommentInfo>  list=grabData(id);
		if(list==null)
		{
			log("获取评论数据失败");
			System.exit(1);
		}
		
		if(list.size()==0)
		{
			log("获取评论数据条数为0");
			System.exit(1);
		}
	//	DBConnection db=new DBConnection();
		CommentInfoApp cia=new CommentInfoApp();
		log("抓取的条数:"+list.size());
		
//		预览商品评论数据
		for(CommentInfo ci:list)
		{
			log(ci.getDate()+":"+ci.getContent());
			ci.setTaoBaoId(id);
//			保存到数据库
		//	db.insert(ci);
			cia.addComment(ci);
		}
	}
	
	/**
	 * 根据商品数据，获取商品的评论数据
	 * @param id 商品id
	 */
	private static List<CommentInfo>   grabData(String id) {
		// TODO Auto-generated method stub
		String goodUrl=baseUrl+id;
		log("开始分析商品 "+id+"网页数据");
		String html = null;
//		打开商品所在的html页面
		try {
			html = Client.sendGet(goodUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		如果商品页面打开失败则返回
		if(html==null)
		{
			log("打开商品页面失败,"+goodUrl);
			return null;
			
		}
	 
//		分析商品页面，提取其中的评论数据的url
		String url=	analysis(html);
//		String temp="http://rate.taobao.com/feedRateList.htm?_ksTS=1430057796067_1323&callback=jsonp_reviews_list&userNumId=1753146414&auctionNumId=39232136537&siteID=1&currentPageNum=1&rateType=&orderType=sort_weight&showContent=1&attribute=&ua=";
		if(url==null)
		{
			log("提取商品评论数据url失败，终止分析");
			return null;
		}
//		获得商品的评论数据 
		List<CommentInfo> list=analysisComments(url);
		
		return list;
	}

	/**
	 * 分析获取评论数据对象
	 * @param url 评论数据的url
	 * @return  评论数据对象列表，如果获取失败则返回null
	 */
	private static List<CommentInfo> analysisComments(String url) {
		// TODO Auto-generated method stub
		/*
		 * 由于评论有多个页面，暂时无法获取评论的总分页数，所以这里采用循环，当获取的页面数据为null，即该页的评论数据条数为0，那么
		 * 那么就认为该页，是评论的最后一页，结束循环 
		 */
		String html=null;
		int page=1;//默认从第一页开始获取商品评论数据
		
//		商品评论数据对象总列表
		List<CommentInfo> data=new ArrayList<CommentInfo>();
		
		while(true){
			log("开始分析商品评论数据页面:"+page);
//			根据页号，获得商品评论数据
			html=getComments(url,page);
	//		如果商品评论页面打开失败则返回
			if(html==null)
			{
				log("获取商品评论页面失败，结束获取");
				return null;
				
			}
	//		对商品评论数据的分析，预处理获得标准的json格式
		String []term=html.split("\\(");//根据(分割字符串
 
			if(term.length>=2 && term[1].length()>0)
			{
				term[1]=term[1].trim();//去掉字符串 前后的空格
				String jsonData=term[1].substring(0, term[1].length()-1); //去掉字符串后面的)符号
 
	//			解析json 字符串，获得评论数据对象列表
				List<CommentInfo> list=analysisJson(jsonData);
//				如果对象列表为空，则结束循环
				if(list==null)
				{
					log("当前页面："+page+" 已经没有更多数据了，结束获取");
//					结束获取评论页面的循环
					break;
				}
				else
				{
					//将获得的评论数据列表添加到总数据列表
					
					if(data.size()+list.size()<=defaultCommentCount)
					data.addAll(list);
					else
					{
						
						for(int i=0;i<list.size();i++)
						{
							data.add(list.get(i));
							if(data.size()>=defaultCommentCount)
							{
								break;
							}
						}
						
					}
					
					if(data.size()>=defaultCommentCount)
					{
						break;
					}
				}
			}
			else
			{
				log("获得的商品数据格式无法解析："+html);
				
				log("结束获取");
//				数据抓取过程中出现错误，终止循环
				break;
			}
//			页号自增
			page++;
		}

		return data;
	}

	/**
	 * @param url 商品评论的url
	 * @param page 商品评论的页号
	 * @return 商品页面数据
	 */
	private static String getComments(String url, int page) {
		// TODO Auto-generated method stub
		String html=null;
		String commentUrl=url+urlCondition+page;
		try {
			html=Client.sendGet(commentUrl,"GBK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log("打开页面失败! "+commentUrl);
		}
// 		System.out.println(html);
 		return html;
	}

	/**
	 * 将评论的json字符串，解析成对象
	 * @param html json字符串
	 * @return CommentInfo对象列表
	 */
	public static List<CommentInfo> analysisJson(String html)
	{
		List<CommentInfo> list=new ArrayList<CommentInfo>();
		
	//	Gson gson=new Gson();
		
		//JsonData data=gson.fromJson(html, JsonData.class);
		JSONObject jo=JSONObject.parseObject(html);
		JSONArray ja=jo.getJSONArray("comments");
		
		if(ja==null)
		{
			return null;
		}
	for(int i=0;i<ja.size();i++)
	{
		
		JSONObject jai=ja.getJSONObject(i);
		
		String content=	jai.getString("content");
		String date  =jai.getString("date");
		CommentInfo ci=new CommentInfo(date, content);
		//System.out.println(content+"-->"+date);
		list.add(ci);
	}
		
		
		return list;
	}
	
	/**
	 * 根据商品的html获得商品评论数据的url
	 * @param html 商品的html
	 * @return 评论数据的url，如果无法获取的话，就返回null
	 */
	public static String analysis(String html)
	{
		Document doc=Jsoup.parse(html);
		
		Element e=doc.getElementById("reviews");
 
		if(e!=null)
		{
			System.out.println(e.html());
			
			String url=e.attr("data-listapi");
			System.out.println(url);
			
			return url;
		}
		
		return null;
	}
	
	private static void log(String ss)
	{
		log(defaultTag,ss);
	}
	private static void log(String tag,String ss)
	{
		System.out.println(tag+": "+ss);
	}
}
