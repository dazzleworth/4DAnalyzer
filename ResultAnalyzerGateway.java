import java.util.StringTokenizer;


public class ResultAnalyzerGateway
{
	static {
		System.loadLibrary("permute");
	}

	private SearchRequest sr;

	public ResultAnalyzerGateway() {
		this.sr = null;
	}

	public ResultAnalyzerGateway(SearchRequest sr) {
		this.sr = sr;
	}

	public synchronized native void runAnalyseData(String data);

	public native boolean checkContainsPermutation(String target, String source);

	public int createResultSet(DrawSingle draw, String text, String drawDetails, int reSN) {
		String s = "";

		if(sr != null) {
			s = draw.getFirst();

			if(checkContainsPermutation(s, text + 'X')) {
				StringBuffer dd = new StringBuffer(drawDetails);
				sr.createEntity(s, "1st Prize", reSN, dd);
				sr.moveToNextRow();
				reSN++;
			}

			s = draw.getSec();

			if(checkContainsPermutation(s, text + 'X')) {
				StringBuffer dd = new StringBuffer(drawDetails);
				sr.createEntity(s, "2nd Prize", reSN, dd);
				sr.moveToNextRow();		
				reSN++;	
			}

			s = draw.getThird();

			if(checkContainsPermutation(s, text + 'X')) {
				StringBuffer dd = new StringBuffer(drawDetails);
				sr.createEntity(s, "3rd Prize", reSN, dd);
				sr.moveToNextRow();
				reSN++;
			}
			
			StringTokenizer token = new StringTokenizer(draw.getStarters(), " ,[]");

			while (token.hasMoreElements()) {
				s = token.nextToken();

				if(checkContainsPermutation(s, text + 'X')) {
					StringBuffer dd = new StringBuffer(drawDetails);
					sr.createEntity(s, "Starter", reSN, dd);
					sr.moveToNextRow();
					reSN++;
				}
			}

			token = new StringTokenizer(draw.getConsola(), " ,[]");

			while (token.hasMoreElements()) {
				s = token.nextToken();

				if(checkContainsPermutation(s, text + 'X')) {
					StringBuffer dd = new StringBuffer(drawDetails);
					sr.createEntity(s, "Consolation", reSN, dd);
					sr.moveToNextRow();
					reSN++;
				}
			}

			return reSN;
		}

		return -1;
	}
	
}
