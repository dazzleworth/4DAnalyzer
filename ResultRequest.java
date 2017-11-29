import java.io.*;
import java.net.*;
import java.text.*;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;

import java.util.Arrays;

import org.joda.time.*;
import org.joda.time.format.*;

import data.*;

public class ResultRequest
{
	public static void main(String[] args) throws Exception {

		DateTime dt = new DateTime();

		DateFormatSymbols dfs = new DateFormatSymbols();

		String[] months = dfs.getMonths();

		for(int i = 0; i < months.length; i++) {
			if(months[i].toLowerCase().startsWith(args[0].toLowerCase())) {
				dt = dt.withMonthOfYear(i + 1).withYear(Integer.parseInt(args[1]));
				break;
			}
		}

		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMdd");

		DateTime start = dt.dayOfMonth().withMinimumValue();
		DateTime end = dt.dayOfMonth().withMaximumValue();

		DateTime now = new DateTime();

		if(now.isBefore(dt)) end = now;

		//List<DateTime> drawDays = new ArrayList<>();

		ResultAnalyzerGateway analyser = new ResultAnalyzerGateway();

		while (start.isBefore(end)){
			StringBuffer datebuf = new StringBuffer("?date=");

			if ( start.getDayOfWeek() == DateTimeConstants.WEDNESDAY || 
			start.getDayOfWeek() == DateTimeConstants.SATURDAY || 
			start.getDayOfWeek() == DateTimeConstants.SUNDAY ) {
				System.out.println("");
				System.out.println("Draw date: " +start.toString(DateTimeFormat.fullDate()));
				System.out.println("");
				datebuf.append(dtf.print(start));
				DrawSingle draw = requestDraw(datebuf.toString());
				analyser.runAnalyseData(draw.getAllResults());
			}

			start = start.plusDays(1);
		}
		
	}

	public static DrawSingle requestDraw(String drawDate) throws Exception
	{
		StringBuffer sburl = new StringBuffer("http://www4.ownskin.com/live4d/api/4d/api_4d.asp");
		sburl.append(drawDate);

		URL obj = new URL(sburl.toString());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

		//int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + sburl.toString());
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);

		in.close();

		//print result
		return parseResults(response);

		//System.out.println(draw.getFirst());
		//System.out.println(draw.getSec());
		//System.out.println(draw.getThird());

		//System.out.println(draw.getStarters());
		//System.out.println(draw.getConsola());

		
	}

	public static DrawSingle parseResults(StringBuffer results) throws Exception
	{
		DrawSingle ds = new DrawSingle();

		JAXBContext jaxbContext = JAXBContext.newInstance(live4d.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		live4d fourd = (live4d) jaxbUnmarshaller.unmarshal(new StreamSource( new StringReader( results.toString() ) ));

		result res = fourd.getFourd().getResult();

		ds.setFirst(res.getFirst());
		ds.setSec(res.getSec());
		ds.setThird(res.getThird());

		ds.setStarters(res.getStarter().getStarterList());
		ds.setConsola(res.getConsola().getConsolaList());

		return ds;
	}

	
}