package com.example.khoavin.karaokebooking.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetHtml {
	/*------------------------------------------------------*/
	//các ký tự trong tiếng việt
	/*------------------------------------------------------*/
	private static char[] SOURCE_CHARACTERS = { 'À', 'Á', 'Â', 'Ã', 'È', 'É',
			'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
			'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
			'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
			'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
			'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
			'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
			'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
			'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
			'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
			'ữ', 'Ự', 'ự', };

	/*------------------------------------------------------*/
	//các ký tự tương ứng đã loại bỏ dấu
	/*------------------------------------------------------*/
	private static char[] DESTINATION_CHARACTERS = { 'A', 'A', 'A', 'A', 'E',
			'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
			'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
			'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
			'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
			'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
			'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
			'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
			'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
			'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
			'U', 'u', 'U', 'u', };


	/*------------------------------------------------------*/
	//Các dấu hiệu nhận biết thông tin của bài hát
	/*------------------------------------------------------*/
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


	/*------------------------------------------------------*/
	//lưu trữ thông tin của các bài hát
	/*------------------------------------------------------*/
	static private ArrayList<SongInfo> listSong = new ArrayList<SongInfo>();

	public static void main(String[] args) {
		getSonginformation(changeNameToUrl("quà cho anh"));
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

				info.setSongID(temp.substring(temp.indexOf(CHECK_SONG_ID_START)
								+ CHECK_SONG_ID_START.length(),
						temp.indexOf(CHECK_SONG_ID_END)));

				if (info.getSongID().length() == 5) {
					info.setVol(temp.substring(
							temp.indexOf(CHECK_SONG_VOL_START)
									+ CHECK_SONG_VOL_START.length(),
							temp.indexOf(CHECK_SONG_VOL_END)));
				} else if (info.getSongID().length() == 6) {
					info.setVol("california 6 số");
				}
				info.setSongName(temp.substring(
						temp.indexOf(CHECK_SONG_NAME_START)
								+ CHECK_SONG_NAME_START.length(),
						temp.indexOf(CHECK_SONG_NAME_END)));
				info.setSongLyric(temp.substring(
						temp.indexOf(CHECK_SONG_LYRIC_START)
								+ CHECK_SONG_LYRIC_START.length(),
						temp.indexOf(CHECK_SONG_LYRIC_START) + 100));
				info.setAuthor(temp.substring(
						temp.indexOf(CHECK_SONG_AUTHOR_START)
								+ CHECK_SONG_AUTHOR_START.length(),
						temp.indexOf(CHECK_SONG_AUTHOR_END)));

				// tach ten:
				String nametemp = info.getSongName();
				info.setSongName(nametemp.substring(
						nametemp.indexOf("\">") + 2, nametemp.length()));

				// tach loi
				if(info.getSongLyric().contains("<a href")){
					String lyricTemp = info.getSongLyric();
					info.setSongLyric(lyricTemp.substring(0,
							lyricTemp.indexOf("<a href")));
				}else if(info.getSongLyric().contains("</div>")){
					String lyricTemp = info.getSongLyric();
					info.setSongLyric(lyricTemp.substring(0,
							lyricTemp.indexOf("</div>")));
				}
				// tach nhac sy
				String authorTemp = info.getAuthor();
				info.setAuthor(authorTemp.substring(
						authorTemp.indexOf("\">") + 2, authorTemp.length()));

				listSong.add(info);
			}

		}
		return listSong;
	}

	public static char removeAccent(char ch) {
		int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
		if (index >= 0) {
			ch = DESTINATION_CHARACTERS[index];
		}
		return ch;
	}

	private static String removeAccent(String s) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < sb.length(); i++) {
			sb.setCharAt(i, removeAccent(sb.charAt(i)));
		}
		return sb.toString();
	}

	public static String changeNameToUrl(String h_Name){
		String url = "";
		url = removeAccent(h_Name).trim().toLowerCase().replace(' ', '+');
		return "http://chonbaihat.com/tim-kiem?utf8=%E2%9C%93&query=" + url + "&button=";
	}
}