package utility;


public class TestUtil {


	/**
	 * Checks if is execuatable.
	 *
	 * @param testname the testname
	 * @param xls the xls
	 * @return true, if is execuatable
	 */

	static String deviceData[][]= new String[2][3];
	public static boolean isExecuatable(String testname, Xls_Reader xls) {

		for (int rowNum = 2; rowNum <= xls.getRowCount("Test Cases"); rowNum++) {
			if (xls.getCellData("Test Cases", "TCID", rowNum).equalsIgnoreCase(testname)) {
				if (xls.getCellData("Test Cases", "Runmode", rowNum)
						.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public static String[][] getDevicesData(Xls_Reader xls){

		System.out.println(xls.getRowCount("Device Data"));
		for (int rowNum = 2; rowNum <= xls.getRowCount("Device Data"); rowNum++) {
			deviceData[rowNum-2][0] = xls.getCellData("Device Data", "Port", rowNum);
			deviceData[rowNum-2][1] = xls.getCellData("Device Data", "DeviceID", rowNum);
			deviceData[rowNum-2][2] = xls.getCellData("Device Data", "OSVersion", rowNum);
		}

		return deviceData;
	}

}
