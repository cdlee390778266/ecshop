package com.cnacex.comm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cnacex.eshop.modul.Division;
import com.cnacex.eshop.modul.Division.Cy;
import com.cnacex.eshop.modul.Division.Cy.Prov;
import com.cnacex.eshop.modul.Division.Cy.Prov.City;
import com.cnacex.eshop.modul.Division.Cy.Prov.City.Dist;
import com.cnacex.eshop.modul.NodeDiv;
import com.cnacex.eshop.msg.DivisionMsg;

public class DivisionUtil {

	private static Logger logger = LoggerFactory.getLogger(DivisionUtil.class);

	private static Division division;

	static {
		loadXmlFile();
	}

	/**
	 * 装载行政区域文件
	 * 
	 * @author kereny
	 * @date 2015-6-3 下午3:03:15 void
	 * 
	 */
	private static void loadXmlFile() {

		InputStream is = Config.class.getResourceAsStream("/conf/division.xml");
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(is));   
			StringBuilder xmlbuffer = new StringBuilder();   
			String line = null;   

		    while ((line = reader.readLine()) != null) {   
		    	xmlbuffer.append(line);   
		   }   
		    if(xmlbuffer.toString().length() > 0){
		    	DivisionMsg msg = XmlUtil.fromXml(xmlbuffer.toString(), DivisionMsg.class);
		    	division = msg.getBody();
		    }else{
		    	logger.error("加载资源配置文件出错,请检查工程下[/conf/division.xml]文件为空");
		    }

		} catch (IOException ex) {
			logger.error("加载资源配置文件出错,请检查工程下[conf/division.xml]文件是否存在!!");
			ex.printStackTrace();
		}finally {   
            try {   
                is.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        }   
	}

	public static List<NodeDiv> findCountry() {

		List<Cy> cys = division.getCy();

		List<NodeDiv> nodes = new ArrayList<NodeDiv>();

		if (cys != null){
			for (Cy c : cys) {
				NodeDiv node = c;
				nodes.add(node);
			}
		}

		return nodes;
	}

	
	public static List<NodeDiv> findProvince(String country) {
		
		List<Cy> cys = division.getCy();

		List<NodeDiv> nodes = new ArrayList<NodeDiv>();

		List<Prov> provs = null;
		if (cys != null){
			for (Cy c : cys) {
				if(country.equalsIgnoreCase(c.getCode())){
					provs = c.getProv();
					break;
				}
			}
		}
		
		if (provs != null){
			for (Prov p : provs) {
				NodeDiv node = new NodeDiv();
				node.setCode(p.getCode());
				node.setName(p.getName());
				nodes.add(node);
			}
		}

		return nodes;
	}
	
	
	public static List<NodeDiv> findCity(String province) {

		List<Cy> cys = division.getCy();

		List<NodeDiv> nodes = new ArrayList<NodeDiv>();
		List<City> citys = null;
		if (cys != null){
			for (Cy c : cys) {
				if(c.getProv() != null){
					for(Prov p : c.getProv()){
						if(province.equalsIgnoreCase(p.getCode())){
							citys = p.getCity();
							break;
						}
					}
				}
				if(citys != null)
					break;
			}
		}
				
		if (citys != null){
			for (City c : citys) {
				NodeDiv node = new NodeDiv();
				node.setCode(c.getCode());
				node.setName(c.getName());
				nodes.add(node);
			}
		}

		return nodes;

	}

	public static List<NodeDiv> findCounty(String city) {

		List<Cy> cys = division.getCy();
		List<NodeDiv> nodes = new ArrayList<NodeDiv>();
		List<Dist> dists = null;
		if (cys != null){
			for (Cy c : cys) {
				if(c.getProv() != null){
					for(Prov p : c.getProv()){
						if(p.getCity() != null){
							for(City ct : p.getCity()){
								if(city.equalsIgnoreCase(ct.getCode())){
									dists = ct.getDist();
									break;
								}
							}
						}
						if(dists != null)
							break;
					}
				}
				if(dists != null)
					break;
			}
		}
				
		if (dists != null){
			for (Dist c : dists) {
				NodeDiv node = new NodeDiv();
				node.setCode(c.getCode());
				node.setName(c.getName());
				nodes.add(node);
			}
		}
		return nodes;

	}
}
