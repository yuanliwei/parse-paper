package com.ylw.parsepaper.logic.excel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.ylw.excelorm.annotation.Cell;
import com.ylw.excelorm.annotation.ErrorHandle;
import com.ylw.excelorm.annotation.Excel;

@Excel(sheet = 0)
@DatabaseTable(tableName = "wrd_word_info")
public class WrdWordInfoV2 {
	@Cell(col = 1)
	@DatabaseField(id = true, unique = true, columnName = "word_id")
	private int wordId;// '单词id',
	@Cell(col = 0)
	@DatabaseField(columnName = "wb_no")
	private int wb_no;// '批次号',
	@Cell(col = 2)
	@DatabaseField(columnName = "word")
	private String word;// '英文单词',
	@Cell(col = 5)
	@DatabaseField(columnName = "paraphrase")
	private String paraphrase;// '中文释义',
	@Cell(col = 4)
	@DatabaseField(columnName = "phonetic")
	private String phonetic;// '音标',
	// @Cell(col = 0)
	@DatabaseField(columnName = "example")
	private String example;// '例句',
	@Cell(col = 10)
	@DatabaseField(columnName = "important")
	private int important;// '0-普通词汇；1-核心词汇',
	@Cell(col = 8)
	@DatabaseField(columnName = "frequent")
	private int frequent;// '0-低频；1-高频',
	@Cell(col = 6)
	@DatabaseField(columnName = "degree")
	private int degree;// T ' 3-三会（听说读）；4-四会（听说读写）',
	// @Cell(col = 0)
	@DatabaseField(columnName = "voice_file")
	private String voice_file;// '音频文件',
	// @Cell(col = 0)
	@DatabaseField(columnName = "net_file")
	private String net_file;// '发音文件',
	// @Cell(col = 0)
	@DatabaseField(columnName = "pic_file")
	private String pic_file;// '图片文件',
	// @Cell(col = 0)
	@DatabaseField(columnName = "video_file")
	private String video_file;// '视频文件',
	// @Cell(col = 0)
	@DatabaseField(columnName = "word_desc")
	private String word_desc;// '备注',

	@ErrorHandle(col = 8)
	public int errorHandle_8(Object obj) {
		if (obj == null)
			return 0;
		if ("是".equals(obj))
			return 1;

		throw new IllegalStateException("unhandle value : " + obj);
	}

	public int getWordId() {
		return wordId;
	}

	public void setWordId(int wordId) {
		this.wordId = wordId;
	}

	public int getWb_no() {
		return wb_no;
	}

	public void setWb_no(int wb_no) {
		this.wb_no = wb_no;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getParaphrase() {
		return paraphrase;
	}

	public void setParaphrase(String paraphrase) {
		this.paraphrase = paraphrase;
	}

	public String getPhonetic() {
		return phonetic;
	}

	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public int getImportant() {
		return important;
	}

	public void setImportant(int important) {
		this.important = important;
	}

	public int getFrequent() {
		return frequent;
	}

	public void setFrequent(int frequent) {
		this.frequent = frequent;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public String getVoice_file() {
		return voice_file;
	}

	public void setVoice_file(String voice_file) {
		this.voice_file = voice_file;
	}

	public String getNet_file() {
		return net_file;
	}

	public void setNet_file(String net_file) {
		this.net_file = net_file;
	}

	public String getPic_file() {
		return pic_file;
	}

	public void setPic_file(String pic_file) {
		this.pic_file = pic_file;
	}

	public String getVideo_file() {
		return video_file;
	}

	public void setVideo_file(String video_file) {
		this.video_file = video_file;
	}

	public String getWord_desc() {
		return word_desc;
	}

	public void setWord_desc(String word_desc) {
		this.word_desc = word_desc;
	}

	@Override
	public String toString() {
		return "WrdWordInfoV2 [wordId=" + wordId + ", wb_no=" + wb_no + ", word=" + word + ", paraphrase=" + paraphrase
				+ ", phonetic=" + phonetic + ", example=" + example + ", important=" + important + ", frequent="
				+ frequent + ", degree=" + degree + ", voice_file=" + voice_file + ", net_file=" + net_file
				+ ", pic_file=" + pic_file + ", video_file=" + video_file + ", word_desc=" + word_desc + "]";
	}

}
