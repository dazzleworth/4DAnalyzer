import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.joda.time.DateTimeConstants;
import org.joda.time.format.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;

import java.awt.Point;
import java.text.*;
import java.util.*;

public class SearchRequest {
    
	private String file;
    
	private int rowCount = 0;
	private int cellCount = 0;
    
	private Point cursor;

	private boolean offset = false;
    
	private final int sn_col = 0;
	private final int searchres_col = 1;
	private final int drawhit_col = 2;

	private static final String pattern = "E MM/dd/yyyy HH:mm:ss.SSS";

	private HSSFWorkbook wb;

	private HSSFSheet sheet;

	private String curMonth;

	private DataRowControl rowControl;

	public SearchRequest(String file) throws Exception {
        
        cursor = new Point();
        
        this.file = file;
        
		Display display = new Display();
		Shell shell = new Shell(display);
		rowControl = new DataRowControl();

		SearchDialog search = new SearchDialog(shell, "Search Number(s)");

		search.show();

		populateData(search.getDateFrom(), search.getDateTo(), search.getSearchStr());

		//curMonth = "";

	}

	public static void main(String[] args) throws Exception {
		SearchRequest sr = new SearchRequest("result.xls");
        
	}

	/*public HSFSheet getCurrentSheet() {
		return sheet;
	}*/

	public void moveToNextRow() {
		rowCount++;
	}

	public int getrowCOunt() {
		return rowCount;
	}

	private void populateData(org.joda.time.DateTime from, org.joda.time.DateTime to, String text) throws Exception {

	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMdd");

	DecimalFormat df = new DecimalFormat("00");

	from.toString(pattern);

	to.toString(pattern);

        
        wb = new HSSFWorkbook();
        
        sheet = wb.createSheet(from.toString(DateTimeFormat.mediumDate())+" - "+to.toString(DateTimeFormat.mediumDate()));

	HSSFFont font = (HSSFFont) wb.createFont();
	font.setFontName("Arial");
	font.setFontHeightInPoints((short)12);
	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

	HSSFRow row = sheet.createRow(rowCount);

	HSSFCellStyle style = wb.createCellStyle();
	style.setFont(font);

	sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,rowCount, 2));

	rowCount++;

	row.createCell(cellCount).setCellValue("Number Search: ");
	row.getCell(cellCount).setCellStyle(style);

	sheet.setColumnWidth(cellCount, PixelUtil.pixel2WidthUnits(25));

	cellCount++;

	row = sheet.createRow(rowCount);

	curMonth = from.toLocalDate().monthOfYear().getAsText();

	HSSFRow reshead = sheet.createRow(rowCount);

	printTableHead(text, reshead);

	int i = 1;

	ResultAnalyzerGateway analyser = new ResultAnalyzerGateway(this);

	while (from.isBefore(to)){
			StringBuffer datebuf = new StringBuffer("?date=");

        			if(isDrawDate(from)) {
					if(!curMonth.equals(from.toLocalDate().monthOfYear().getAsText())) {
						curMonth = from.toLocalDate().monthOfYear().getAsText();
						cellCount += 3;
						i = 1;
						rowCount = 1;
						offset = true;
						printTableHead(text, reshead);
					}

				datebuf.append(dtf.print(from));

				DrawSingle draw = ResultRequest.requestDraw(datebuf.toString());

				i = analyser.createResultSet(draw, text, buildSearchRes(df.format(from.getDayOfMonth()), String.valueOf(from.getYear()), from.dayOfWeek().getAsText().substring(0, 3)).toString(), i);
			}

			from = from.plusDays(1);
	}

	//printTableHead(text);

	FileOutputStream outStream = new FileOutputStream(this.file);
	wb.write(outStream);
	outStream.close();

	}

	private StringBuffer buildSearchRes(String dayOfMonth, String year, String day) {
		return new StringBuffer().append(dayOfMonth).append(' ').append(curMonth).append(' ').append(year).append(' ').append('(').append(day).append(')').append('\n');

	}

	public void createEntity(String drawRes, String ptype, int item, StringBuffer drawDetail) {
		HSSFRow rr;
		HSSFCellStyle scs = wb.createCellStyle();
		HSSFCellStyle midstyle = wb.createCellStyle();
		scs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		scs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		scs.setWrapText(true);
		scs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		scs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		scs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		scs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		midstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                midstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		midstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		midstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		midstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		midstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		if(offset){
			//sheet.setColumnWidth(cellCount + drawhit_col - 1, PixelUtil.pixel2WidthUnits(drawDetail.length()));
			rr = rowControl.removeFromQueue();
			rr.createCell((cellCount + sn_col) - 2).setCellValue(item);
			rr.getCell((cellCount + sn_col) - 2).setCellStyle(midstyle);
			rr.createCell((cellCount + searchres_col) - 2).setCellValue(drawRes);
			rr.getCell((cellCount + searchres_col) - 2).setCellStyle(midstyle);
			rr.createCell((cellCount + drawhit_col) - 2).setCellValue(drawDetail.append(ptype).toString());
			rr.getCell((cellCount + drawhit_col) - 2).setCellStyle(scs);
		}
		else {
			//sheet.setColumnWidth(cellCount + drawhit_col, PixelUtil.pixel2WidthUnits(drawDetail.length()));
			rr = sheet.createRow(rowCount);
			rr.createCell(sn_col).setCellValue(item);
			rr.getCell(sn_col).setCellStyle(midstyle);
			rr.createCell(searchres_col).setCellValue(drawRes);
			rr.getCell(searchres_col).setCellStyle(midstyle);
			rr.createCell(drawhit_col).setCellValue(drawDetail.append(ptype).toString());
			rr.getCell(drawhit_col).setCellStyle(scs);
			rowControl.addToRowQueue(rr);
		}
	}

	private boolean isDrawDate(org.joda.time.DateTime dd) {
		return ( (dd.getDayOfWeek() == DateTimeConstants.WEDNESDAY) || 
			(dd.getDayOfWeek() == DateTimeConstants.SATURDAY) || 
			(dd.getDayOfWeek() == DateTimeConstants.SUNDAY) )? true: false;
	}

	private void printTableHead(String search, HSSFRow row) {
		HSSFFont font = (HSSFFont) wb.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setFontHeightInPoints((short)12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);


		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		row.createCell(cellCount).setCellValue(search);
		row.getCell(cellCount).setCellStyle(style);

		cellCount++;

		String s = curMonth.toUpperCase();

		row.createCell(cellCount).setCellValue(s);
		row.getCell(cellCount).setCellStyle(style);

		//sheet.setColumnWidth(cellCount, PixelUtil.pixel2WidthUnits(s.length()));

		
		rowCount++;
	}

	class DataRowControl {
		private Queue<HSSFRow> createdRows;

		public DataRowControl() {
			createdRows = new LinkedList<>();
		}

		public void addToRowQueue(HSSFRow row) {
			createdRows.add(row);
		}

		public void setRowData(Queue<HSSFRow> rows) {
			createdRows = rows;
		}

		public HSSFRow removeFromQueue() {
			HSSFRow row;

			if(((LinkedList)createdRows).peekFirst() != null)
				row = (HSSFRow) createdRows.remove();
			else
				row = sheet.createRow(rowCount);

			return row;
		}
	}
}