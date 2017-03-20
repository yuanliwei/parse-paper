package com.ylw.parsepaper.logic.excel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "wrd_word_info")
public class WrdWordInfo {
	@DatabaseField(id = true, unique = true, columnName = "word_id")
	int wordId;
	@DatabaseField(columnName = "wb_no")
	int wbNo;
	@DatabaseField(columnName = "word")
	String word;
	@DatabaseField(columnName = "paraphrase")
	String paraphrase;
	@DatabaseField(columnName = "phonetic")
	String phonetic;
	@DatabaseField(columnName = "example")
	String example;
	@DatabaseField(columnName = "important")
	int important;
	@DatabaseField(columnName = "frequent")
	int frequent;
	@DatabaseField(columnName = "degree")
	int degree;
	@DatabaseField(columnName = "voice_file")
	String voiceFile;
	@DatabaseField(columnName = "net_file")
	String netFile;
	@DatabaseField(columnName = "pic_file")
	String picFile;
	@DatabaseField(columnName = "video_file")
	String videoFile;
	@DatabaseField(columnName = "word_desc")
	String wordDesc;

	public void setWordId(int wordId) {
		this.wordId = wordId;
	}

	public int getWordId() {
		return this.wordId;
	}

	public void setWbNo(int wbNo) {
		this.wbNo = wbNo;
	}

	public int getWbNo() {
		return this.wbNo;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getWord() {
		return this.word;
	}

	public void setParaphrase(String paraphrase) {
		this.paraphrase = paraphrase;
	}

	public String getParaphrase() {
		return this.paraphrase;
	}

	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
	}

	public String getPhonetic() {
		return this.phonetic;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getExample() {
		return this.example;
	}

	public void setImportant(int important) {
		this.important = important;
	}

	public int getImportant() {
		return this.important;
	}

	public void setFrequent(int frequent) {
		this.frequent = frequent;
	}

	public int getFrequent() {
		return this.frequent;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getDegree() {
		return this.degree;
	}

	public void setVoiceFile(String voiceFile) {
		this.voiceFile = voiceFile;
	}

	public String getVoiceFile() {
		return this.voiceFile;
	}

	public void setNetFile(String netFile) {
		this.netFile = netFile;
	}

	public String getNetFile() {
		return this.netFile;
	}

	public void setPicFile(String picFile) {
		this.picFile = picFile;
	}

	public String getPicFile() {
		return this.picFile;
	}

	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}

	public String getVideoFile() {
		return this.videoFile;
	}

	public void setWordDesc(String wordDesc) {
		this.wordDesc = wordDesc;
	}

	public String getWordDesc() {
		return this.wordDesc;
	}

	@Override
	public String toString() {
		return "WrdWordInfo [wordId=" + wordId + ", wbNo=" + wbNo + ", word=" + word + ", paraphrase=" + paraphrase
				+ ", phonetic=" + phonetic + ", example=" + example + ", important=" + important + ", frequent="
				+ frequent + ", degree=" + degree + ", voiceFile=" + voiceFile + ", netFile=" + netFile + ", picFile="
				+ picFile + ", videoFile=" + videoFile + ", wordDesc=" + wordDesc + "]";
	}
	
	
}