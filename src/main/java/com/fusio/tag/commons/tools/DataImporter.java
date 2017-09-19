package com.fusio.tag.commons.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fusio.tag.commons.busi.PrimaryKeyGenerator;
import com.fusio.tag.commons.utils.DateUtil;
import com.fusio.tag.commons.utils.JsonUtil;
import com.fusio.tag.commons.utils.UuidUtil;
import com.fusio.tag.model.autogen.Catg;
import com.fusio.tag.model.autogen.Tags;

/**
 * 
 * 将读取的
 */
public class DataImporter {
	public static void main(String[] args) throws IOException {
		// 从目标文件生成合格的文件
		String sourceFilePath = "d:/！dmp_tag_system_table v0.4 (20170206）.txt";
		String destFilePath = sourceFilePath + "_modify";
		DataImporter.wellData(sourceFilePath, destFilePath);

		// 从格式良好的文件读取
		ReadResult r = DataImporter.readData(destFilePath);
		
		
		
		
		System.out.println(JsonUtil.toJSONString(r.getCatgList()));
		System.out.println(JsonUtil.toJSONString(r.getTagList().size()));

		System.out.println(JsonUtil.toJSONString(r.getTagList().subList(0, 10)));
	}
	
	
	public static final int HOW_MANY_CATG = 1;// 多深的目录算是catg,这里1级就算,也就是/分类 算是catg,但是2级及以上算tag,如/分类/foo,这里foo是标签
	
	public static ReadResult readData(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
		String line = "";
		List<Catg> catgList = new ArrayList<>();
		List<Tags> tagList = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			// trim
			line = line.trim();
			if (line.length() == 0) {
				continue;
			}

			String fullName = line.trim();
			if (isCatg(line)) {
				Catg catg = getCatg(fullName);
				catgList.add(catg);
			}
			//
			else {
				Tags tag = getTag(fullName);
				tagList.add(tag);
			}
		}
		reader.close();

		return new ReadResult(catgList, tagList);
	}
	
	
	public static void wellData(String sourceFilePath, String destFilePath) throws IOException {
		Map<String, Object> result= fillLack(sourceFilePath);
		List<String> ori = (List<String>) result.get("ori");
		List<String> noParent = (List<String>) result.get("noParent");
		
		List<String> all = new ArrayList<>(noParent.size() +  ori.size() + 1);
		all.addAll(noParent);
		all.add("\r\n\r\n");
		all.addAll(ori);
		
		StringBuilder sb = new StringBuilder();
		for (String string : all) {
			sb.append(string).append("\r\n");
		}
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(destFilePath)), "UTF-8"));
		writer.write(sb.toString());
		writer.close();
	}
	
	public static Map<String, Object> fillLack(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
		String line = "";
		List<String> list = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			// trim
			line = line.trim();
			if (line.length() == 0) {
				continue;
			}

			line = line.startsWith("/") ? line : "/" + line;// 数据不是那么规范,规范为都是/开头
			
			// 提供的数据可能会有重复
			if (!list.contains(line)) {
				list.add(line);
			}
		}
		
		
		// 现在list里全装着/xxx的格式了
		// 检查父节点是否存在
		List<String> noParent = new ArrayList<>();
		for (String fullName : list) {
			String parentFullName = getParentPath(fullName);
			
			// 返回/说明是根,无需检查此部分path
			while (!parentFullName.equals("/")) {
				boolean bothNotContain = !list.contains(parentFullName) && !noParent.contains(parentFullName);
				if (bothNotContain) {
					noParent.add(parentFullName);
				}
				parentFullName = getParentPath(parentFullName);
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("ori", list);
		result.put("noParent", noParent);
		
		reader.close();
		return result;
	}

	
	
	
	
	
	
	public static class ReadResult {
		private List<Catg> catgList;
		private List<Tags> tagList;

		public ReadResult() {
			super();
		}

		public ReadResult(List<Catg> catgList, List<Tags> tagList) {
			super();
			this.catgList = catgList;
			this.tagList = tagList;
		}

		public List<Catg> getCatgList() {
			return catgList;
		}

		public void setCatgList(List<Catg> catgList) {
			this.catgList = catgList;
		}

		public List<Tags> getTagList() {
			return tagList;
		}

		public void setTagList(List<Tags> tagList) {
			this.tagList = tagList;
		}
	}

	public static final String placeholder = "to_be_maintained";
	public static Tags getTag(String fullName) {

		Tags tag = new Tags();
		tag.setCatgId(placeholder);
		tag.setCode("code待定:" + UuidUtil.getUUID32Lowercase());
		tag.setCreatedAt(DateUtil.now());
		tag.setCreatedBy("default");
		tag.setFullId(placeholder);
		tag.setFullName(fullName);
		tag.setLevel(getTagLevel(fullName));
		tag.setName(getLastName(fullName));
		tag.setPid(placeholder);
		tag.setStatus((short) 0);
		tag.setTagId(PrimaryKeyGenerator.getTagId());
		// tag.setUpdatedAt(updatedAt);
		tag.setUpdatedBy("default");
		//

		return tag;
	}

	// 入参fullName,如:消费/餐饮/口味偏好/西餐
	public static Catg getCatg(String fullName) {
		Catg catg = new Catg();
		catg.setCatgId(PrimaryKeyGenerator.getCatgId());
		catg.setCode("code待定:" + UuidUtil.getUUID32Lowercase());
		catg.setCreatedAt(DateUtil.now());
		catg.setCreatedBy("default");
		catg.setFullId(placeholder);
		catg.setFullName(fullName);
		catg.setLevel(getCatgLevel(fullName));
		catg.setName(getLastName(fullName));
		catg.setPid(placeholder);
		catg.setStatus((short) 0);// 0
		// catg.setUpdatedAt(updatedAt);// timestamp自动赋值
		catg.setUpdatedBy("default");
		return catg;
	}

	// 入参如 【/消费/餐饮/口味偏好/西餐】,返回最后一个中文,空串也返回空串
	public static String getLastName(String fullName) {
		String[] arr = fullName.split("/");
		return arr[arr.length - 1];
	}

	// 如入参 /消费/餐饮/口味偏好/西餐 返回/消费/餐饮/口味偏好,如果/消费 返回 /
	public static String getParentPath(String fullName) {
		File file = new File(fullName);
		return file.getParent().toString().replaceAll("\\\\", "/");
	}

	public static boolean isTagRoot(String fullName) {
		return count(fullName, "/") - HOW_MANY_CATG == 1 ? true : false;
	}

	public static String getCatgNameByTagFullName(String fullName) {
		int slashCount = count(fullName, "/");
		if (slashCount <= HOW_MANY_CATG) {
			throw new RuntimeException("full_name不是标签的");
		}

		// 一直获取,直到/的数量达到catg的标准
		String tmp = fullName;
		while (count(tmp, "/") > HOW_MANY_CATG) {
			tmp = tmp.substring(0, tmp.lastIndexOf("/"));
		}

		return tmp;
	}

	
	
	
	
	
	private static int getTagLevel(String fullName) {
		int slashCount = count(fullName, "/");
		return slashCount - HOW_MANY_CATG;// 级别从1开始,tag的级别要扣掉
	}

	private static int getCatgLevel(String fullName) {
		int slashCount = count(fullName, "/");
		return slashCount;// 级别从1开始
	}

	// 一级目录是catg
	private static boolean isCatg(String fullName) {
		int slashCount = count(fullName, "/");
		if (slashCount <= HOW_MANY_CATG) {
			return true;
		}
		return false;
	}

	private static int count(String str, String key) {
		int count = 0;
		int index = 0;
		while ((index = str.indexOf(key, index)) != -1) {
			index = index + key.length();
			count++;
		}
		return count;
	}

}
