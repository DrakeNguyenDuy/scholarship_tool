package com.matohela.scholarshipManage.utils;

import com.matohela.scholarshipManage.common.Constant;

/**
 * 
 */
public class ServiceUtils {

	/**
	 * Get number of page from filter request
	 * 
	 * @param sPage
	 * @return int
	 * @throws NumberFormatException
	 */
	public static int getPageFromFilter(String sPage) {
		try {
			Integer page = Integer.parseInt(sPage);
			return page == null? Constant.DEFAULT_PAGE: page;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
