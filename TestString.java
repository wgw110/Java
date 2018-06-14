package com.wangguowei.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestString {
	public static void main(String[] args) {
		List<CatalogResponse> list = new ArrayList<>();
		CatalogResponse catalogResponse1=new CatalogResponse();
		CatalogResponse catalogResponse2=new CatalogResponse();
		CatalogResponse catalogResponse3=new CatalogResponse();
		Map<String, String> map1=new HashMap<>();
		Map<String, String> map2=new HashMap<>();
		Map<String,String> map3=new HashMap<>();
		map1.put("resturl", "");
		map1.put("monitors", "10.16.23.5:8010");
		map2.put("resturl", "");
		map2.put("monitors", "C20.15.36.1");
		map3.put("resturl", "Gttp://195.23.25.2:8080");
		map3.put("monitors", "");
		catalogResponse1.setTt(map1);
		catalogResponse2.setTt(map2);
		catalogResponse3.setTt(map3);
	    list.add(catalogResponse1);
	    list.add(catalogResponse2);
	    list.add(catalogResponse3);
		StorageCompator compator=new TestString().new StorageCompator();
		Collections.sort(list,compator );
		for (int i = 0; i < 3; i++) {
			CatalogResponse response = list.get(i);
			if(response.getTt().get("resturl").equals("")) {
				System.out.println(response.getTt().get("monitors"));
			}else {
				System.out.println(response.getTt().get("resturl"));
			}
			
		}

	}

	class StorageCompator implements Comparator<CatalogResponse> {
		public StorageCompator() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public int compare(CatalogResponse o1, CatalogResponse o2) {
			String value1=null;
			String value2=null;
			if(o1.getTt().get("resturl").equals("")) {
				value1=o1.getTt().get("monitors");
			}
			if(o1.getTt().get("monitors").equals("")) {
				value1=o1.getTt().get("resturl");
			}
			if(o2.getTt().get("resturl").equals("")) {
				value2=o2.getTt().get("monitors");
			}
			if(o2.getTt().get("monitors").equals("")) {
				value2=o2.getTt().get("resturl");
			}
			return value2.compareTo(value1);
		}

	}
}
