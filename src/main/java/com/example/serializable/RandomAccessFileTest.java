package com.example.serializable;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author ��ï��
 */
@Slf4j
public class RandomAccessFileTest {
	public static void main(String[] args) {
		try (RandomAccessFile raf = new RandomAccessFile("object.txt", "r")) {
			log.info("{}", raf.getFilePointer());
			raf.seek(300);
			byte[] bbuf = new byte[1024];
			int hasRead = 0;
			while ((hasRead = raf.read(bbuf)) > 0) {
				log.info(new String(bbuf, 0, hasRead));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void main() {
		try (RandomAccessFile raf = new RandomAccessFile("out.txt", "rw")) {
			raf.seek(raf.length());
			raf.write("123456\r\n".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
