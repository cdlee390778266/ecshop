package com.cnacex.comm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;

/**
 * @author kereny
 * 
 */

public class NIOUtil {

	public final static int MACLEN = 28;
	
	
	public final static int BODYLEN = 10;
		/**
	     *  从内存文件中取指定偏移的定长数据
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午2:55:29
		 * @param fileName
		 * @param offset
		 * @param size
		 * @return
		 * byte[]
	     *
		 */
	public static byte[] readData(String fileName, int offset, int size) {

		RandomAccessFile randomAccessFile = null;
		FileChannel fileChannel = null;

		byte[] bytes = new byte[size];
		try {
			
			File file = new File(fileName);
			
			if(file.exists() == false)
				return null;
			
			randomAccessFile = new RandomAccessFile(fileName, "r");
			fileChannel = randomAccessFile.getChannel();

			MappedByteBuffer mbb = fileChannel.map(MapMode.READ_ONLY, offset,
					size);
			mbb.get(bytes, 0, size);
			fileChannel.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (fileChannel != null)
				try {
					fileChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if (randomAccessFile != null)
				try {
					randomAccessFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return bytes;

	}

	

		/**
	     *  取内存文件中数据内容长度
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午2:53:53
		 * @param fileName
		 * @return
		 * int
	     *
		 */
	public static int getBodyLenth(String fileName) {
		byte[] bytes = readData(fileName, MACLEN, BODYLEN);
		
		if(bytes == null)
			return 0;
		else
			return Integer.parseInt(new String(bytes));

	}

		/**
	     *  取内存文件的前缀标识
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午2:54:09
		 * @param fileName
		 * @return
		 * long
	     *
		 */
	public static String getHead(String fileName) {
		byte[] bytes = readData(fileName, 0, MACLEN);
		
		if(bytes == null)
			return null;
		else
			return new String(bytes);
	}


		/**
	     *  设置内存文件的内容，程序会自动加入长度标识和前缀标识
	     *  
		 * @author kereny
		 * @date 2015-6-3 下午2:51:43
		 * @param fileName
		 * @param headFlag
		 * @param data
		 * @return
		 * boolean
	     *
		 */
	public static synchronized boolean setData(String fileName, String headFlag,
			String data) {

		RandomAccessFile randomAccessFile = null;
		FileChannel fileChannel = null;

		boolean flag = false;

		int length = 0;

		StringBuffer strbuf = new StringBuffer("");
		strbuf.append(String.format("%28s", headFlag));
		length += MACLEN;
		strbuf.append(String.format("%010d", data.getBytes().length));
		length += BODYLEN;
		strbuf.append(data);
		length += data.getBytes().length;

		try {
			randomAccessFile = new RandomAccessFile(fileName, "rws");
			fileChannel = randomAccessFile.getChannel();
			MappedByteBuffer mbb = fileChannel.map(MapMode.READ_WRITE, 0,
					length);
			mbb.put(strbuf.toString().getBytes(), 0, length);
			mbb.force();
			flag = true;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (fileChannel != null)
				try {
					fileChannel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if (randomAccessFile != null)
				try {
					randomAccessFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return flag;
	}
}
