package com.warehouseservice.Service;

import java.util.Comparator;

public class FilesComperator implements Comparator<FileSort>{

	@Override
	public int compare(FileSort o1, FileSort o2) {
		return o1.id-o2.id;
	}

}
