package com.example.serializable;

import java.io.*;

/**
 * @author ��ï��
 */
public class InsertContent {
	public static void main(String[] args) throws IOException {
		insert("out.txt", 10, "123456\r\n");
	}

	private static void insert(String fileName, long pos, String insertContent) throws IOException {
		File tmp = File.createTempFile("tmp", null);
		tmp.deleteOnExit();

		try (
				RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
				FileOutputStream tmpOut = new FileOutputStream(tmp);
				FileInputStream tmpIn = new FileInputStream(tmp)) {
			raf.seek(pos);
			byte[] bbuf = new byte[64];
			int hasRead = 0;
			while ((hasRead = raf.read(bbuf)) > 0) {
				tmpOut.write(bbuf, 0, hasRead);
			}
			raf.seek(pos);
			raf.write(insertContent.getBytes());
			while ((hasRead = tmpIn.read(bbuf)) > 0) {
				raf.write(bbuf, 0, hasRead);
			}
		}

	}

}
