import java.util.*;

public class DrawSingle
{
	public static final int MAX_NUM = 23;
	public static final int START_BEG = 3;
	public static final int CONS_BEG = 13;

	private String[] drawNums;

	public DrawSingle()
	{
		drawNums = new String[MAX_NUM];
	}

	public void setFirst(String first)
	{
		drawNums[0] = first;
	}

	public void setSec(String second)
	{
		drawNums[1] = second;
	}

	public void setThird(String third)
	{
		drawNums[2] = third;
	}

	public void setStarters(List<String> sts)
	{
		System.arraycopy(sts.toArray(), 0, drawNums, START_BEG, 10);
	}

	public void setConsola(List<String> cons)
	{
		System.arraycopy(cons.toArray(), 0, drawNums, CONS_BEG, 10);
	}

	public String getAllResults()
	{
		return Arrays.toString(drawNums);
	}

	public String getFirst()
	{
		return drawNums[0];
	}

	public String getSec()
	{
		return drawNums[1];
	}

	public String getThird()
	{
		return drawNums[2];
	}

	public String getStarters()
	{
		String[] str = new String[10];

		System.arraycopy(drawNums, START_BEG, str, 0, 10);

		return Arrays.toString(str);
	}

	public String getConsola()
	{

		String[] str = new String[10];

		System.arraycopy(drawNums,  CONS_BEG, str, 0, 10);

		return Arrays.toString(str);
	}


}