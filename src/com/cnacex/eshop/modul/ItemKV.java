package com.cnacex.eshop.modul;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class ItemKV {
	
	@XStreamImplicit
	private List<Item> item;

	@XStreamAlias("item")
	public static class Item{
		
		@XStreamAlias("code")
		private String code;
		
		@XStreamAlias("name")
		private String name;
		
		@XStreamImplicit
		private List<Row> Row;

		@XStreamAlias("row")
		public static class Row{
			
			private String key;
			
			private String value;

			public String getKey() {
				return key;
			}

			public void setKey(String key) {
				this.key = key;
			}

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}
			
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Row> getRow() {
			return Row;
		}

		public void setRow(List<Row> row) {
			Row = row;
		}


		
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	

}
