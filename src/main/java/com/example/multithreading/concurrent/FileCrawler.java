package com.example.multithreading.concurrent;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * @author 吕茂陈
 * @date 2022/02/06 17:30
 */
public class FileCrawler implements Runnable {

    private final BlockingQueue<File> fileQueue;

    private final FileFilter fileFilter;

    private final File root;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        crawl(root);
    }

    private void crawl(File root) {
        File[] entries = root.listFiles(fileFilter);
        if (Objects.nonNull(entries)) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else if (!alreadyIndexed(entry)) {
                    try {
                        fileQueue.put(entry);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    private boolean alreadyIndexed(File entry) {
        return false;
    }

}

class Indexer implements Runnable {

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                indexFile(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void indexFile(File take) {
        
    }
}