package utility;


public class TestUtil {


	/**
	 * Checks if is execuatable.
	 *
	 * @param testname the testname
	 * @param xls the xls
	 * @return true, if is execuatable
	 */
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

}
