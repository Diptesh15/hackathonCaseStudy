
/*
 * ExcelUtils.java
 * Apache POI
 * This utility file is necessary to do Data Driven Test
 * Contains static methods which will extract data from the HackathonInputData.xlsx file
 * 
 */

package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle style;   
    
//-----------------------------------------------------------------------------------------------------
    
    // Following method will get number of rows

    public static int getRowCount(String xlfile,String xlsheet) throws IOException 
    {
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        int rowcount=ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowcount;        
    }
    
//-------------------------------------------------------------------------------------------------------
    
    //Following method will get the number of cells present ion a particular row

    public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
    {
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        row=ws.getRow(rownum);
        int cellcount=row.getLastCellNum();
        wb.close();
        fi.close();
        return cellcount;
    }
    
//------------------------------------------------------------------------------------------------------
    
    // Following method will get data from excel file

    public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
    {
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        row=ws.getRow(rownum);
        cell=row.getCell(colnum);

        String data;
        try 
        {
            //data=cell.toString();

            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
            return data;
        }
        catch (Exception e) 
        {
            data="";
        }

        wb.close();
        fi.close();
        return data;
    }
}

//#######################################################################################################
