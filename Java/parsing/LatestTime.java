package parsing;

public class LatestTime {
	public String findLatestTime(String s) {
		String[] time = s.split(":");
		char[] hh = time[0].toCharArray();
		char[] mm = time[1].toCharArray();
		if(hh[0] == '?' && hh[1] == '?') {
			hh[0] = '1';
			hh[1] = '1';
		}
		if(hh[0] == '?') {
			if(hh[1] > '1') {
				hh[0] = '0';
			} else {
				hh[0] = '1';
			}
		}
		if(hh[1] == '?') {
			hh[1] = hh[0] == '0' ? '9' : '1';
		}

		if(mm[0] == '?') {
			mm[0] = '5';
		}
		if(mm[1] == '?') {
			mm[1] = '9';
		}
		return new String(hh) + ":" + new String(mm);
	}

}
