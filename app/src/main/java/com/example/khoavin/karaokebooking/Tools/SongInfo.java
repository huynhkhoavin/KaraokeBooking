package com.example.khoavin.karaokebooking.Tools;

public class SongInfo {
	private String songID;
	private String songName;
	private String SongLyric;
	private String author;
	private String vol;
	
	
	public String getVol() {
		return vol;
	}
	public void setVol(String vol) {
		this.vol = vol;
	}

	
	
	public String getSongID() {
		return songID;
	}
	public void setSongID(String songID) {
		this.songID = songID;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSongLyric() {
		return SongLyric;
	}
	public void setSongLyric(String songLyric) {
		SongLyric = songLyric;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSongInfo(){
		return "\nMã số: " + songID + "\nVOL: " + vol +  "\nTên: " + songName + "\nLời: " + SongLyric + "\nNhạc sỹ: " + author;
	}
}
