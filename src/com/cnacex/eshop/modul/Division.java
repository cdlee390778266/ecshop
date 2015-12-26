package com.cnacex.eshop.modul;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class Division {

	@XStreamImplicit
	private List<Cy> cy;

	@XStreamAlias("cy")
	public static class Cy extends NodeDiv{

		@XStreamImplicit
		private List<Prov> prov;

		@XStreamAlias("prov")
		public static class Prov extends NodeDiv {

			@XStreamImplicit
			private List<City> city;

			@XStreamAlias("city")
			public static class City extends NodeDiv {


				@XStreamImplicit
				private List<Dist> dist;

				@XStreamAlias("dist")
				public static class Dist extends NodeDiv {

				}
				public List<Dist> getDist() {
					return dist;
				}

				public void setDist(List<Dist> dist) {
					this.dist = dist;
				}
			}
			public List<City> getCity() {
				return city;
			}

			public void setCity(List<City> city) {
				this.city = city;
			}
		}
		public List<Prov> getProv() {
			return prov;
		}

		public void setProv(List<Prov> prov) {
			this.prov = prov;
		}
	}

	public List<Cy> getCy() {
		return cy;
	}

	public void setCy(List<Cy> cy) {
		this.cy = cy;
	}
}


