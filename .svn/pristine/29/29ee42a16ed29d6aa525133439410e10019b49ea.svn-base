package place;

public class Distance {
	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * 
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double GetDistance(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根39.94607, 116.32793, 31.24063, 121.42575
		//double distance = GetDistance(116.32793, 39.94607, 121.42575, 31.24063);
		//丰业广场 34.792298,113.649876
		//华林都市家园 34.794044,113.643785
		//升龙天汇广场售楼部 34.783709,113.629946
		double distance = GetDistance(113.66971603733218,34.763262162437556,113.649644,34.75661);
		System.out.println("Distance is:" + distance);
	}
}
