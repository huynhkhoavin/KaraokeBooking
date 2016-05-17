package com.example.khoavin.karaokebooking.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetHtml {

	static String CheckLine = "<div class='song'>";
	static String CHECK_SONG_ID_START = "<div class='number'> ";
	static String CHECK_SONG_VOL_START = "rel=\"nofollow\">";
	static String CHECK_SONG_NAME_START = "<div class='name'>";
	static String CHECK_SONG_LYRIC_START = "remark'> ";
	static String CHECK_SONG_AUTHOR_START = "Nhạc sĩ";

	static String CHECK_SONG_ID_END = " <span";
	static String CHECK_SONG_VOL_END = "</a></span> <a class=\"icon";
	static String CHECK_SONG_NAME_END = "</a> </div> <div class='remark'>";
	static String CHECK_SONG_LYRIC_END = "href";
	static String CHECK_SONG_AUTHOR_END = "</a> check_end";
	
	static private ArrayList<SongInfo> listSong = new ArrayList<SongInfo>();

	public static void main(String[] args) {
		getSonginformation("http://chonbaihat.com/tim-kiem?utf8=%E2%9C%93&query=nang+am+xa+dan&button=");
		for(int i = 0; i < listSong.size(); i++){
			System.out.println(listSong.get(i).getSongInfo());
		}
	}
	public static ArrayList<SongInfo> getSonginformation(String h_url) {
		String FilterString = "";
		listSong.clear();
		try {
			URL oracle = new URL(h_url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					oracle.openStream(), "UTF-8"));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains(CheckLine))
					FilterString += inputLine;
			}
			in.close();
		} catch (IOException e) {

		}

		if (!FilterString.equals("")) {
			String pattern1 = "<div class='song'>";
			String pattern2 = "</div> </div>";

			Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)"
					+ Pattern.quote(pattern2));
			Matcher m = p.matcher(FilterString);
			while (m.find()) {
				String temp = m.group(1);
				SongInfo info = new SongInfo();
				temp += "check_end";
				
				info.setSongID(temp.substring(temp.indexOf(CHECK_SONG_ID_START) + CHECK_SONG_ID_START.length(), temp.indexOf(CHECK_SONG_ID_END)));
				
				if(info.getSongID().length() == 5)
				{
					info.setVol(temp.substring(temp.indexOf(CHECK_SONG_VOL_START) + CHECK_SONG_VOL_START.length(), temp.indexOf(CHECK_SONG_VOL_END)));
				}
				else if(info.getSongID().length() == 6)
				{
					info.setVol("california 6 số");
				}
				info.setSongName(temp.substring(temp.indexOf(CHECK_SONG_NAME_START) + CHECK_SONG_NAME_START.length(), temp.indexOf(CHECK_SONG_NAME_END)));
				info.setSongLyric(temp.substring(temp.indexOf(CHECK_SONG_LYRIC_START) + CHECK_SONG_LYRIC_START.length(), temp.indexOf(CHECK_SONG_LYRIC_START) + 100));
				info.setAuthor(temp.substring(temp.indexOf(CHECK_SONG_AUTHOR_START) + CHECK_SONG_AUTHOR_START.length(), temp.indexOf(CHECK_SONG_AUTHOR_END)));
				
				//tach ten:
				String nametemp = info.getSongName();
				info.setSongName(nametemp.substring(nametemp.indexOf("\">") + 2, nametemp.length()));
				
				//tach loi
				String lyricTemp = info.getSongLyric();
				info.setSongLyric(lyricTemp.substring(0, lyricTemp.indexOf("<a href")));
				
				//tach nhac sy
				String authorTemp = info.getAuthor();
				info.setAuthor(authorTemp.substring(authorTemp.indexOf("\">") + 2, authorTemp.length()));
				
				listSong.add(info);
			}

		}
		return listSong;
	}
}