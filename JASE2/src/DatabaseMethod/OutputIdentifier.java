package DatabaseMethod;

import java.io.IOException;

public class OutputIdentifier extends OutputLinuxStorage {
	public OutputIdentifier(String datatype,String ip,String osIdent) throws IOException{
		OutputRecord a = new OutputRecord();
		OutputWindowsStorage w = null;
		OutputLinuxStorage t = null;
		if(osIdent=="linux"){
			 t = a.outputRecordFromLinuxRecordByIp(ip);
		}else{
			if(osIdent == "windows7"){
				w = a.outputRecordFromWindowsRecordByIp(ip);
			}
		}
		if(datatype.equals("excel")){
			OutputToXlsx e = new OutputToXlsx();
			e.createLinuxXlsxFile(t);
		}else{
			if(datatype.equals("text")){
				OutputToText f = new OutputToText();
				f.createLinuxTextFile(t);
			}
		}
	}
}
