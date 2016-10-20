package com.ylw.parsepaper.ui.model;

import java.util.HashMap;
import java.util.Map;

import com.ylw.parsepaper.logic.paper.model.Part;
import com.ylw.parsepaper.logic.paper.model.PartType;

import javafx.scene.paint.Color;

public class ColorMap {
	static String[] cStr = ("#BBFFFF,#AEEEEE,#96CDCD,#668B8B,#98F5FF,#8EE5EE,"
			+ "#7AC5CD,#53868B,#00F5FF,#00E5EE,#00C5CD,#00868B,#00FFFF,"
			+ "#00EEEE,#00CDCD,#008B8B,#97FFFF,#8DEEEE,#79CDCD,#528B8B,"
			+ "#7FFFD4,#76EEC6,#66CDAA,#458B74,#C1FFC1,#B4EEB4,#9BCD9B,"
			+ "#698B69,#54FF9F,#4EEE94,#43CD80,#2E8B57,#9AFF9A,#90EE90,"
			+ "#7CCD7C,#548B54,#00FF7F,#00EE76,#00CD66,#008B45,#00FF00,"
			+ "#00EE00,#00CD00,#008B00,#7FFF00,#76EE00,#66CD00,#458B00,"
			+ "#C0FF3E,#B3EE3A,#9ACD32,#698B22,#CAFF70,#BCEE68,#A2CD5A,"
			+ "#6E8B3D,#FFF68F,#EEE685,#CDC673,#8B864E,#FFEC8B,#EEDC82,"
			+ "#CDBE70,#8B814C,#FFFFE0,#EEEED1,#CDCDB4,#8B8B7A,#FFFF00,"
			+ "#EEEE00,#CDCD00,#8B8B00,#FFD700,#EEC900,#CDAD00,#8B7500,"
			+ "#FFC125,#EEB422,#CD9B1D,#8B6914,#FFB90F,#EEAD0E,#CD950C,"
			+ "#8B658B,#FFC1C1,#EEB4B4,#CD9B9B,#8B6969,#FF6A6A,#EE6363,"
			+ "#CD5555,#8B3A3A,#FF8247,#EE7942,#CD6839,#8B4726,#FFD39B,"
			+ "#EEC591,#CDAA7D,#8B7355,#FFE7BA,#EED8AE,#CDBA96,#8B7E66,"
			+ "#FFA54F,#EE9A49,#CD853F,#8B5A2B,#FF7F24,#EE7621,#CD661D,"
			+ "#8B4513,#FF3030,#EE2C2C,#CD2626,#8B1A1A,#FF4040,#EE3B3B,"
			+ "#CD3333,#8B2323,#FF8C69,#EE8262,#CD7054,#8B4C39,#FFA07A,"
			+ "#EE9572,#CD8162,#8B5742,#FFA500,#EE9A00,#CD8500,#8B5A00,"
			+ "#FF7F00,#EE7600,#CD6600,#8B4500,#FF7256,#EE6A50,#CD5B45,"
			+ "#8B3E2F,#FF6347,#EE5C42,#CD4F39,#8B3626,#FF4500,#EE4000,"
			+ "#CD3700,#8B2500,#FF0000,#EE0000,#CD0000,#8B0000,#FF1493,"
			+ "#EE1289,#CD1076,#8B0A50,#FF6EB4,#EE6AA7,#CD6090,#8B3A62,"
			+ "#FFB5C5,#EEA9B8,#CD919E,#8B636C,#FFAEB9,#EEA2AD,#CD8C95,"
			+ "#8B5F65,#FF82AB,#EE799F,#CD6889,#8B475D,#FF34B3,#EE30A7,"
			+ "#CD2990,#8B1C62,#FF3E96,#EE3A8C,#CD3278,#8B2252,#FF00FF,"
			+ "#EE00EE,#CD00CD,#8B008B,#FF83FA,#EE7AE9,#CD69C9,#8B4789,"
			+ "#FFBBFF,#EEAEEE,#CD96CD,#8B668B,#E066FF,#D15FEE,#B452CD,"
			+ "#7A378B,#BF3EFF,#B23AEE,#9A32CD,#68228B,#9B30FF,#912CEE,"
			+ "#7D26CD,#551A8B,#AB82FF,#9F79EE,#8968CD,#5D478B,#FFE1FF,"
			+ "#EED2EE,#CDB5CD,#8B7B8B,#1C1C1C,#363636,#4F4F4F,#696969,"
			+ "#828282,#9C9C9C,#B5B5B5,#CFCFCF,#E8E8E8,#A9A9A9,#00008B," + "#008B8B,#8B008B,#8B0000,#B4CDCD,#90EE90")
					.split(",");
	public static Map<PartType, String> colorMap = new HashMap<>();
	static {
		int i = 50;
		colorMap.put(Part.T_TYPE_NONE, cStr[i++]);
		colorMap.put(Part.T_PAPER, cStr[i++]);
		colorMap.put(Part.T_PAPER_大标题, cStr[i++]);
		colorMap.put(Part.T_PAPER_说明文本, cStr[i++]);
		colorMap.put(Part.T_BIG, cStr[i++]);
		colorMap.put(Part.T_BIG_选择题, cStr[i++]);
		colorMap.put(Part.T_BIG_解答题, cStr[i++]);
		colorMap.put(Part.T_BIG_填空题, cStr[i++]);
		colorMap.put(Part.T_SMALL, cStr[i++]);
		colorMap.put(Part.T_SMALL_题干, cStr[i++]);
		colorMap.put(Part.T_SMALL_选项, cStr[i++]);
		colorMap.put(Part.T_SMALL_答案, cStr[i++]);
		colorMap.put(Part.T_SMALL_解析, cStr[i++]);
		colorMap.put(Part.T_SMALL_点评, cStr[i++]);
		colorMap.put(Part.T_SMALL_难度, cStr[i++]);
		colorMap.put(Part.T_SMALL_阅读材料, cStr[i++]);
		colorMap.put(Part.T_SMALL_问题, cStr[i++]);
	}

	public static Color c(ListItemData item) {
		String color = colorMap.get(PartType.get(item.getType()));
		if (color != null) {
			return Color.web(color);
		} else {
			return Color.web("#FF0000");
		}
	}

	public static String cString(ListItemData item) {
		String color = colorMap.get(PartType.get(item.getType()));
		if (color != null) {
			return (color);
		} else {
			return ("#FF0000");
		}
	}
}
