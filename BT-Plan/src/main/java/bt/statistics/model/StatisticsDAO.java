package bt.statistics.model;

import java.util.*;

public interface StatisticsDAO {

	public List wholeconnection();
	public List countMember();
	public List bancount();
	public List ageCalculate();
	public int textCount();
	public int imageCount();
	public int age_10();
	public int age_20();
	public int age_30();
	public int age_40();
	public int age_50();
	public int age_60();
	public int age_etc();
	public int gender_male();
	public int gender_female();
}
