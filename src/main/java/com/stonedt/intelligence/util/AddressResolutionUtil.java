package com.stonedt.intelligence.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0.0
 * @Description 获取字符串里的省市
 * @author wzq
 * @date 2020年4月8日下午7:12:05
 */
public class AddressResolutionUtil {

	private static String[] citylist = { "上海", "北京", "天津", "重庆", "郑州", "洛阳", "焦作", "商丘", "信阳", "周口", "鹤壁", "安阳", "濮阳",
			"驻马店", "南阳", "开封", "漯河", "许昌", "新乡", "济源", "灵宝", "偃师", "邓州", "登封", "三门峡", "新郑", "禹州", "巩义", "永城", "长葛",
			"义马", "林州", "项城", "汝州", "荥阳", "平顶山", "卫辉", "辉县", "舞钢", "新密", "孟州", "沁阳", "郏县", "合肥", "亳州", "芜湖", "马鞍山",
			"池州", "黄山", "滁州", "安庆", "淮南", "淮北", "蚌埠", "宿州", "宣城", "六安", "阜阳", "铜陵", "明光", "天长", "宁国", "界首", "桐城", "潜山",
			"福州", "厦门", "泉州", "漳州", "南平", "三明", "龙岩", "莆田", "宁德", "龙海", "建瓯", "武夷山", "长乐", "福清", "晋江", "南安", "福安", "邵武",
			"石狮", "福鼎", "建阳", "漳平", "永安", "兰州", "白银", "武威", "金昌", "平凉", "张掖", "嘉峪关", "酒泉", "庆阳", "定西", "陇南", "天水", "玉门",
			"临夏", "合作", "敦煌", "甘南州贵阳", "安顺", "遵义", "六盘水", "兴义", "都匀", "凯里", "毕节", "清镇", "铜仁", "赤水", "仁怀", "福泉", "海口",
			"三亚", "万宁", "文昌", "儋州", "琼海", "东方", "五指山", "石家庄", "保定", "唐山", "邯郸", "邢台", "沧州", "衡水", "廊坊", "承德", "迁安",
			"鹿泉", "秦皇岛", "南宫", "任丘", "叶城", "辛集", "涿州", "定州", "晋州", "霸州", "黄骅", "遵化", "张家口", "沙河", "三河", "冀州", "武安",
			"河间", "深州", "新乐", "泊头", "安国", "双滦区", "高碑店", "哈尔滨", "伊春", "牡丹江", "大庆", "鸡西", "鹤岗", "绥化", "齐齐哈尔", "黑河", "富锦",
			"虎林", "密山", "佳木斯", "双鸭山", "海林", "铁力", "北安", "五大连池", "阿城", "尚志", "五常", "安达", "七台河", "绥芬河", "双城", "海伦", "宁安",
			"讷河", "穆棱", "同江", "肇东", "武汉", "荆门", "咸宁", "襄阳", "荆州", "黄石", "宜昌", "随州", "鄂州", "孝感", "黄冈", "十堰", "枣阳", "老河口",
			"恩施", "仙桃", "天门", "钟祥", "潜江", "麻城", "洪湖", "汉川", "赤壁", "松滋", "丹江口", "武穴", "广水", "石首", "大冶", "枝江", "应城", "宜城",
			"当阳", "安陆", "宜都", "利川", "长沙", "郴州", "益阳", "娄底", "株洲", "衡阳", "湘潭", "岳阳", "常德", "邵阳", "永州", "张家界", "怀化", "浏阳",
			"醴陵", "湘乡", "耒阳", "沅江", "涟源", "常宁", "吉首", "冷水江", "临湘", "汨罗", "武冈", "韶山", "湘西", "长春", "吉林", "通化", "白城", "四平",
			"辽源", "松原", "白山", "集安", "梅河口", "双辽", "延吉", "九台", "桦甸", "榆树", "蛟河", "磐石", "大安", "德惠", "洮南", "龙井", "珲春",
			"公主岭", "图们", "舒兰", "和龙", "临江", "敦化", "南京", "无锡", "常州", "扬州", "徐州", "苏州", "连云港", "盐城", "淮安", "宿迁", "镇江",
			"南通", "泰州", "兴化", "东台", "常熟", "江阴", "张家港", "通州", "宜兴", "邳州", "海门", "溧阳", "泰兴", "如皋", "昆山", "启东", "江都", "丹阳",
			"吴江", "靖江", "扬中", "新沂", "仪征", "太仓", "姜堰", "高邮", "金坛", "句容", "南昌", "赣州", "上饶", "宜春", "景德镇", "新余", "九江", "萍乡",
			"抚州", "鹰潭", "吉安", "丰城", "樟树", "德兴", "瑞金", "井冈山", "高安", "乐平", "南康", "贵溪", "瑞昌", "沈阳", "葫芦岛", "大连", "盘锦",
			"鞍山", "铁岭", "本溪", "丹东", "抚顺", "锦州", "辽阳", "阜新", "调兵山", "朝阳", "海城", "北票", "盖州", "凤城", "庄河", "凌源", "开原", "兴城",
			"新民", "大石桥", "东港", "北宁", "瓦房店", "普兰店", "凌海", "灯塔", "营口", "西宁", "格尔木", "德令哈", "济南", "青岛", "威海", "潍坊", "菏泽",
			"济宁", "东营", "津市市", "烟台", "淄博", "枣庄", "泰安", "临沂", "日照", "德州", "聊城", "滨州", "乐陵", "兖州", "诸城", "邹城", "滕州", "肥城",
			"新泰", "胶州", "胶南", "即墨", "龙口", "平度", "莱西", "太原", "大同", "阳泉", "长治", "临汾", "晋中", "运城", "忻州", "朔州", "吕梁", "古交",
			"高平", "永济", "孝义", "侯马", "霍州", "介休", "河津", "汾阳", "原平", "晋城", "潞城", "西安", "咸阳", "榆林", "宝鸡", "铜川", "渭南", "汉中",
			"安康", "商洛", "延安", "韩城", "兴平", "华阴", "成都", "广安", "德阳", "乐山", "巴中", "内江", "宜宾", "南充", "都江堰", "自贡", "泸州", "广元",
			"达州", "资阳", "绵阳", "眉山", "遂宁", "雅安", "阆中", "攀枝花", "广汉", "绵竹", "万源", "华蓥", "江油", "西昌", "彭州", "简阳", "崇州", "什邡",
			"峨眉山", "邛崃", "昆明", "玉溪", "大理", "曲靖", "昭通", "保山", "丽江", "临沧", "楚雄", "开远", "个旧", "景洪", "安宁", "宣威", "杭州", "宁波",
			"绍兴", "温州", "台州", "湖州", "嘉兴", "金华", "舟山", "衢州", "丽水", "余姚", "乐清", "临海", "温岭", "永康", "瑞安", "慈溪", "义乌", "上虞",
			"诸暨", "海宁", "桐乡", "兰溪", "龙泉", "建德", "富德", "富阳", "平湖", "东阳", "嵊州", "奉化", "临安", "江山市", "台北", "台南", "台中", "高雄",
			"桃源", "广州", "深圳", "珠海", "汕头", "佛山", "韶关", "湛江", "肇庆", "江门", "茂名", "惠州", "梅州", "汕尾", "河源", "阳江", "清远", "东莞",
			"中山", "潮州", "揭阳", "云浮", "南宁", "贺州", "玉林", "桂林", "柳州", "梧州", "北海", "钦州", "百色", "防城港", "贵港", "河池", "崇左",
			"来宾市", "东兴", "桂平", "北流", "岑溪", "合山", "凭祥", "宜州", "呼和浩特", "呼伦贝尔", "赤峰", "扎兰屯", "鄂尔多斯", "乌兰察布", "巴彦淖尔",
			"二连浩特", "霍林郭勒", "包头", "乌海", "阿尔山", "乌兰浩特", "锡林浩特", "根河", "满洲里", "额尔古纳", "牙克石", "临河", "丰镇", "通辽", "银川", "固原",
			"石嘴山", "青铜峡", "中卫", "吴忠", "灵武", "拉萨", "那曲", "山南", "林芝", "昌都", "日喀则", "乌鲁木齐", "石河子", "喀什", "阿勒泰", "阜康",
			"库尔勒", "阿克苏", "阿拉尔", "哈密", "克拉玛依", "昌吉", "奎屯", "米泉", "和田" };
	private static String reg = "[^\u4e00-\u9fa5]";

	/**
	 * 解析地址
	 * 
	 * @author wangziqiu
	 * @param address
	 * @return
	 */
	public static List<Map<String, String>> addressResolution(String address) {
//		long start = System.currentTimeMillis();
		String regex = "((?<province>[^省]+省|.+自治区|上海|北京|天津|重庆|河北|山西|辽宁|吉林|黑龙江|江苏|浙江|安徽|福建|江西|山东|河南|湖北|湖南|广东|海南|四川|贵州|云南|陕西|甘肃|青海|台湾|内蒙古|广西壮族|西藏|宁夏回族|新疆维吾尔|香港|澳门|内蒙))(?<city>[^市]+市|.+自治州)(?<county>[^县]+县|.+区|.+镇|.+局)?(?<town>[^区]+区|.+镇)";
//        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";

		Matcher m = Pattern.compile(regex).matcher(address);
		String province = null, city = null, county = null, town = null;
		List<Map<String, String>> table = new ArrayList<Map<String, String>>();
		Map<String, String> row = null;
		while (m.find()) {
			row = new LinkedHashMap<String, String>();
			province = m.group("province");
			if (province == null) {
				List<String> provinces = getProvince(address);
				if (provinces.size() > 0) {
					province = provinces.get(0).replaceAll(reg, "").replace("自治区", "").replaceFirst("省", "");
				}
			} else if (province != null && province.replaceAll(reg, "").length() > 5) {
				List<String> provinces = getProvince(address);
				if (provinces.size() > 0) {
					province = provinces.get(0).replaceAll(reg, "").replace("自治区", "").replaceFirst("省", "");
				} else {
					province = null;
				}
			} else if (province != null) {
				List<String> provinces = getProvince(province);
				if (provinces.size() > 0) {
					province = provinces.get(0).replaceAll(reg, "").replace("自治区", "").replaceFirst("省", "");
				} else {
					List<String> provinces1 = getProvince(address);
					if (provinces1.size() > 0) {
						province = provinces1.get(0).replaceAll(reg, "").replace("自治区", "").replaceFirst("省", "");
					} else {
						province = null;
					}
				}
			}
			row.put("province", province == null ? "" : province.trim());
			city = m.group("city");
			if (city == null) {
				List<String> citys = getCity(address);
				if (citys.size() > 0) {
					city = citys.get(0).replaceAll(reg, "").replace("自治州", "").replaceFirst("市", "");
				}
			} else if (city != null && city.replaceAll(reg, "").length() > 5) {
				List<String> citys = getCity(address);
				if (citys.size() > 0) {
					city = citys.get(0).replaceAll(reg, "").replace("自治州", "").replaceFirst("市", "");
				} else {
					city = null;
				}
			} else if (city != null) {
				List<String> citys = getCity(city);
				if (citys.size() > 0) {
					city = citys.get(0).replaceAll(reg, "").replace("自治州", "").replaceFirst("市", "");
				} else {
					List<String> citys1 = getCity(address);
					if (citys1.size() > 0) {
						city = citys1.get(0).replaceAll(reg, "").replace("自治州", "").replaceFirst("市", "");
					} else {
						city = null;
					}
				}
			}
			row.put("city", city == null ? "" : city.trim());
			county = m.group("county");
			row.put("county", county == null ? "" : county.trim());
			town = m.group("town");
			row.put("town", town == null ? "" : town.trim());
			table.add(row);
		}
		if (table.size() == 0) {
			Map<String, String> row1 = null;

			List<String> provinces = getProvince(address);
			List<String> citys = getCity(address);
			if (provinces.size() > 0) {
				for (int i = 0; i < provinces.size(); i++) {
					row1 = new LinkedHashMap<String, String>();
					String tprovince = null;
					String tcity = null;
					tprovince = provinces.get(i);
					if (i < citys.size()) {
						tcity = citys.get(i);
					}
					row1.put("province", null == tprovince ? "" : tprovince.trim());
					row1.put("city", null == tcity ? "" : tcity.trim());
					table.add(row1);
				}
			} else if (citys.size() > 0) {
				for (int i = 0; i < citys.size(); i++) {
					row1 = new LinkedHashMap<String, String>();
					String tcity = null;
					tcity = citys.get(i);
					row1.put("city", tcity == null ? "" : tcity.trim());
					table.add(row1);
				}
			}

		}

		if (table.size() > 0) {
			for (int i = 0; i < table.size(); i++) {
				Map<String, String> map = table.get(i);
				String mapcity = map.get("city");
				String mapprovince = map.get("province");
				if (mapcity != null && !"".equals(mapcity.trim())) {
					String getProvince = CityToProvinceUtil
							.cityToProvince(mapcity.replace("自治州", "").replaceFirst("市", ""));
					if (getProvince != null) {
						if (mapcity != null && !"".equals(mapcity.trim())) {
							if (mapprovince == null || ""
									.equals(mapprovince.replaceAll(reg, "").replace("自治区", "").replaceFirst("省", ""))) {
								map.put("province",
										getProvince.replaceAll(reg, "").replace("自治区", "").replaceFirst("省", ""));
							} else if (getProvince.indexOf(
									mapprovince.replaceAll(reg, "").replace("自治区", "").replaceFirst("省", "")) == -1) {
								map.remove("city");
								Map<String, String> newMap = new HashMap<String, String>();
								newMap.put("city",
										mapcity.replaceAll(reg, "").replace("自治州", "").replaceFirst("市", ""));
								newMap.put("province",
										getProvince.replaceAll(reg, "").replace("自治区", "").replaceFirst("省", ""));
								table.add(newMap);
							}
						} else {
							map.put("province", getProvince);
						}
					}
				}
			}
		}
//		long end = System.currentTimeMillis();
//	    long usedTime = (end-start);
//	    System.err.println(address);
		return table;
	}

	private static List<String> getCity(String address) {
		List<String> city = new ArrayList<>();
		for (String c : citylist) {
			if (address.indexOf(c) != -1) {
				address = address.substring(c.indexOf(c));
				city.add(c.replaceAll(reg, "").replace("自治州", "").replaceFirst("市", ""));
			}
		}
		return city;
	}

	/**
	 * 获取省
	 * 
	 * @param address
	 * @return
	 */
	public static List<String> getProvince(String address) {
		String regexprovince = "上海|北京|天津|重庆|河北|山西|辽宁|吉林|黑龙江|江苏|浙江|安徽|福建|江西|山东|河南|湖北|湖南|广东|海南|四川|贵州|云南|陕西|甘肃|青海|台湾|内蒙古|广西壮族|西藏|宁夏回族|新疆维吾尔|香港|澳门|内蒙";
		Matcher mprovince = Pattern.compile(regexprovince).matcher(address);
		List<String> province1 = new ArrayList<>();
		while (mprovince.find()) {
			province1.add(mprovince.group().replaceAll(reg, "").replace("自治区", "").replaceFirst("省", ""));
		}
		return province1;
	}

}