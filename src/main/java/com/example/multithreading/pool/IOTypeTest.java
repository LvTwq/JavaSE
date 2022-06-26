package com.example.multithreading.pool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/22 09:07
 */
@Slf4j
public class IOTypeTest implements Runnable {


    /**
     * 整体执行时间，包括在队列中等待的时间
     */
    List<Long> wholeTimeList;
    /**
     * 真正执行时间
     */
    List<Long> runTimeList;

    private long initStartTime = 0;

    /**
     * 构造函数
     *
     * @param runTimeList
     * @param wholeTimeList
     */
    public IOTypeTest(List<Long> runTimeList, List<Long> wholeTimeList) {
        initStartTime = System.currentTimeMillis();
        this.runTimeList = runTimeList;
        this.wholeTimeList = wholeTimeList;
    }

    /**
     * IO操作
     *
     * @return
     * @throws IOException
     */
    public void readAndWrite() throws IOException {
        File sourceFile = new File("spFilePath/demo.txt");
        //创建输入流
        try (BufferedReader input = new BufferedReader(new FileReader(sourceFile))) {
            //读取源文件,写入到新的文件
            String line;
            while ((line = input.readLine()) != null) {
                log.info("{}", line);
            }
        }
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            readAndWrite();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        long wholeTime = end - initStartTime;
        long runTime = end - start;
        wholeTimeList.add(wholeTime);
        runTimeList.add(runTime);
        log.info("单个线程花费时间：{}", (end - start));
    }


}
