package bt.statistics.model;

import org.mybatis.spring.SqlSessionTemplate;
import java.util.*;

public class StatisticsDAOImple implements StatisticsDAO {
	
	private SqlSessionTemplate sqlMap;

	public StatisticsDAOImple(SqlSessionTemplate sqlMap) {
		super();
		this.sqlMap = sqlMap;
	}

	public List wholeconnection() {
		List lists=sqlMap.selectList("manageConnectionCount");
		return lists;
	}

	public List countMember() {
		List lists=sqlMap.selectList("countmember");
		return lists;
	}

	public List bancount() {
		List lists=sqlMap.selectList("bancount");
		return lists;
	}

	public List ageCalculate() {
		List lists=sqlMap.selectList("calculateAge");
		return lists;
	}
	public int textCount() {
		int count=sqlMap.selectOne("textCount");
		return count;
	}
	public int imageCount() {
		int count=sqlMap.selectOne("imageCount");
		return count;
	}

	public int age_10() {
		int count=sqlMap.selectOne("age_10");
		return count;
	}

	public int age_20() {
		int count=sqlMap.selectOne("age_20");
		return count;
	}

	public int age_30() {
		int count=sqlMap.selectOne("age_30");
		return count;
	}

	public int age_40() {
		int count=sqlMap.selectOne("age_40");
		return count;
	}

	public int age_50() {
		int count=sqlMap.selectOne("age_50");
		return count;
	}

	public int age_60() {
		int count=sqlMap.selectOne("age_60");
		return count;
	}
	public int age_etc() {
		int count=sqlMap.selectOne("age_etc");
		return count;
	}

	public int gender_male() {
		int count=sqlMap.selectOne("gender_male");
		return count;
	}

	public int gender_female() {
		int count=sqlMap.selectOne("gender_female");
		return count;
	}
}
