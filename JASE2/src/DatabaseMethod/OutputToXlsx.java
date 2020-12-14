package DatabaseMethod;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.ChartLegend;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.apache.poi.ss.usermodel.charts.LegendPosition;
import org.apache.poi.ss.usermodel.charts.ScatterChartData;
import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
public class OutputToXlsx extends OutputLinuxStorage {
	boolean check = false;
	public boolean createLinuxXlsxFile(OutputLinuxStorage p) throws IOException{
		//System day
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		// explode the object to each record array
		OutputLinuxStorage ols = p;
		String[] recordID = ols.outputRecordId();
		String[] ipaddress = ols.outputIpaddress();
		String[] devName = ols.outputDevName();
		String[] systemUptime = ols.outputSysTime();
		String[] total_ram = ols.outputTotalRam();
		String[] used_ram = ols.outputUsedRam();
		String[] ram_usage = ols.outputRamUsage();
		String[] cpu_usage = ols.outputCpuUsage();
		String[] total_hdd_space = ols.outputTotalHddSpace();
		String[] avail_hdd_space = ols.outputAvailHdd();
		String[] network_in_traffic = ols.outputNetwork_income_traffic();
		String[] network_out_traffic = ols.outputNetwork_outcome_traffic();
		String[] recordtime = ols.outputRecordTime();
				
		String excelFileName = timeStamp+"_linux.xlsx";//name of excel file
		String sheetName = "Sheet1";//name of sheet
		String [] fieldname ={"RecordID","IP address","DeviceName","SystemUptime","Total Ram (GB)",
				 "Used Ram (GB)","Ram Usage (%)","CPU Usage (%)","TotalHarddiskSpace (GB)","Avail Harddisk Space(GB)","Network income traffic (KB)","Network outcome traffic (KB)","Record Time"};
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		int i = 0;
		int col = 0;
		for (int counter=0;counter<fieldname.length;counter++){
			cell = row.createCell(counter);
			cell.setCellValue(fieldname[counter]);
		}
		//iterating r number of rows
		for (int r=1;r < recordID.length; r++ ){
			 row = sheet.createRow(r);
 
			//iterating c number of columns		
			 cell = row.createCell(col);
			 cell.setCellValue(recordID[i]);
			 cell = row.createCell(col+1);
			 cell.setCellValue(ipaddress[i]);
			 cell = row.createCell(col+2);
			 cell.setCellValue(devName[i]);
			 cell = row.createCell(col+3);
			 cell.setCellValue(systemUptime[i]);
			 cell = row.createCell(col+4);
			 cell.setCellValue(total_ram[i]);
			 cell = row.createCell(col+5);
			 cell.setCellValue(used_ram[i]);
			 cell = row.createCell(col+6);
			 cell.setCellValue(ram_usage[i]);
			 cell = row.createCell(col+7);
			 cell.setCellValue(cpu_usage[i]);
			 cell = row.createCell(col+8);
			 cell.setCellValue(total_hdd_space[i]);
			 cell = row.createCell(col+9);
			 cell.setCellValue(avail_hdd_space[i]);
			 cell = row.createCell(col+10);
			 cell.setCellValue(network_in_traffic[i]);
			 cell = row.createCell(col+11);
			 cell.setCellValue(network_out_traffic[i]);
			 cell = row.createCell(col+12);
			 cell.setCellValue(recordtime[i]);
			 cell = row .createCell(col+13);
			 cell.setCellValue(i);
			 i++;		
		}
			
		FileOutputStream fileOut = new FileOutputStream(excelFileName);
 
		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		wb.close();
		
		return true;
	}

	public boolean createWindowsXlsxFile(OutputWindowsStorage p) throws IOException{
		
			String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			// explode the object to each record array
			OutputWindowsStorage ows = p;
			String[] recordID = ows.outputRecordId();
			String[] ipaddress = ows.outputIpaddress();
			String[] devName = ows.outputDevName();
			String[] systemUptime = ows.outputSysTime();
			String[] total_ram = ows.outputTotalRam();
			String[] used_ram = ows.outputUsedRam();
			String[] ram_usage = ows.outputRamUsage();
			String[] cpu_usage = ows.outputCpuUsage();
			String[] total_hdd_space = ows.outputTotalHddSpace();
			String[] used_hdd_space = ows.outputUsedHdd();
			String[] recordtime = ows.outputRecordTime();
			
			String excelFileName = timeStamp+"_windows.xlsx";//name of excel file
			String sheetName = "Sheet1";//name of sheet
			String [] fieldname ={"RecordID","IP address","DeviceName","SystemUptime","Total Ram (GB)",
					"Used Ram (GB)","Ram Usage (%)","CPU Usage (%)","TotalHarddiskSpace (GB)","Avail Harddisk Space(GB)","Record Time"};
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(sheetName) ;
			XSSFRow row = sheet.createRow(0);
			XSSFCell cell = row.createCell(0);
			int i = 0;
			int col = 0;
			for (int counter=0;counter<fieldname.length;counter++){
				cell = row.createCell(counter);
				cell.setCellValue(fieldname[counter]);
			}
			//iterating r number of rows
			for (int r=1;r < recordID.length; r++ ){
				 row = sheet.createRow(r);
		 
				//iterating c number of columns		
				 cell = row.createCell(col);
				 cell.setCellValue(recordID[i]);
				 cell = row.createCell(col+1);
				 cell.setCellValue(ipaddress[i]);
				 cell = row.createCell(col+2);
				 cell.setCellValue(devName[i]);
				 cell = row.createCell(col+3);
				 cell.setCellValue(systemUptime[i]);
				 cell = row.createCell(col+4);
				 cell.setCellValue(total_ram[i]);
				 cell = row.createCell(col+5);
				 cell.setCellValue(used_ram[i]);
				 cell = row.createCell(col+6);
				 cell.setCellValue(ram_usage[i]);
				 cell = row.createCell(col+7);
				 cell.setCellValue(cpu_usage[i]);
				 cell = row.createCell(col+8);
				 cell.setCellValue(total_hdd_space[i]);
				 cell = row.createCell(col+9);
				 cell.setCellValue(used_hdd_space[i]);
				 cell = row.createCell(col+10);
				 cell.setCellValue(recordtime[i]);
				 i++;
					
				}
		 
			FileOutputStream fileOut = new FileOutputStream(excelFileName);
		 
			//write this workbook to an Outputstream.
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();				
			wb.close();
				
			return true;

	}
	public boolean createRouterXlsxFile(OutputRouterStorage p) throws IOException{
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());//System day
		// explode the object to each record array
		OutputRouterStorage ors = p;
		String[] recordID = ors.outputRecordId();
		String[] ipaddress = ors.outputIpaddress();
		String[] devName = ors.outputDevName();
		String[] total_interface = ors.outputTotal_interface();
		String[] income_traffic_mbps = ors.outputIncome_traffic_mbps();
		String[] outcome_traffic_mbps = ors.outputOutcome_traffic_mbps();
		String[] income_traffic_pps = ors.outputIncome_traffic_pps();
		String[] outcome_traffic_pps = ors.outputOutcome_traffic_pps();
		String[] cpu_usage = ors.outputCpuUsage();
		String[] recordtime = ors.outputRecordTime();
				
		String excelFileName = timeStamp+"_router.xlsx";//name of excel file
		String sheetName = "Sheet1";//name of sheet
		String [] fieldname ={"RecordID","IP address","DeviceName","Total Interface","Income Traffic (Mbps)",
				 "Outcome traffic (Mbps)","Income Traffic (Packet Per Second)","Outcome Traffic (Packet Per Second)","Cpu Usage (%)","Record Time"};
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		int i = 0;
		int col = 0;
		for (int counter=0;counter<fieldname.length;counter++){
			cell = row.createCell(counter);
			cell.setCellValue(fieldname[counter]);
		}
		//iterating r number of rows
		for (int r=1;r < recordID.length; r++ ){
			 row = sheet.createRow(r);
 
			//iterating c number of columns		
			 cell = row.createCell(col);
			 cell.setCellValue(recordID[i]);
			 cell = row.createCell(col+1);
			 cell.setCellValue(ipaddress[i]);
			 cell = row.createCell(col+2);
			 cell.setCellValue(devName[i]);
			 cell = row.createCell(col+3);
			 cell.setCellValue(total_interface[i]);
			 cell = row.createCell(col+4);
			 cell.setCellValue(income_traffic_mbps[i]);
			 cell = row.createCell(col+5);
			 cell.setCellValue(outcome_traffic_mbps[i]);
			 cell = row.createCell(col+6);
			 cell.setCellValue(income_traffic_pps[i]);
			 cell = row.createCell(col+7);
			 cell.setCellValue(outcome_traffic_pps[i]);
			 cell = row.createCell(col+8);
			 cell.setCellValue(cpu_usage[i]);
			 cell = row.createCell(col+9);
			 cell.setCellValue(recordtime[i]);		
			 i++;
			
		}
 
		FileOutputStream fileOut = new FileOutputStream(excelFileName);
 
		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		wb.close();
		
		return true;

	}

	public boolean createAlertLogXlsxFile(OutputAlertStorage p)throws IOException{
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		// explode the object to each record array
		OutputAlertStorage oas = p;
		String[] recordID = oas.outputRecordId();
		String[] ipaddress = oas.outputIpaddress();
		String[] devName = oas.outputDevName();	
		String[] alert_type = oas.outputAlertType();
		String[] recordtime = oas.outputRecordTime();
				
		String excelFileName = timeStamp+"_alert_log.xlsx";//name of excel file
		String sheetName = "Sheet1";//name of sheet
		String [] fieldname ={"RecordID","IP address","DeviceName","Alert Type","Record Time"};
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		int i = 0;
		int col = 0;
		for (int counter=0;counter<fieldname.length;counter++){
			cell = row.createCell(counter);
			cell.setCellValue(fieldname[counter]);
		}
		//iterating r number of rows
		for (int r=1;r < recordID.length; r++ ){
			 row = sheet.createRow(r);
 
			//iterating c number of columns		
			 cell = row.createCell(col);
			 cell.setCellValue(recordID[i]);
			 cell = row.createCell(col+1);
			 cell.setCellValue(ipaddress[i]);
			 cell = row.createCell(col+2);
			 cell.setCellValue(devName[i]);
			 cell = row.createCell(col+3);
			 cell.setCellValue(alert_type[i]);
			 cell = row.createCell(col+4);
			 cell.setCellValue(recordtime[i]);
			 i++;
			
		}
 
		FileOutputStream fileOut = new FileOutputStream(excelFileName);
 
		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		wb.close();
		
		return true;

	}
}