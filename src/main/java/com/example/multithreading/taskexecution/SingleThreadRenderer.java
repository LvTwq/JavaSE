package com.example.multithreading.taskexecution;

/**
 * 串行地渲染页面元素
 *
 * @author 吕茂陈
 * @date 2022/01/19 08:57
 */
public class SingleThreadRenderer {

//    void renderPage(CharSequence source) {
//        renderText(source);
//        List<ImageData> imageData = new ArrayList<>();
//        for (ImageInfo imageInfo : scanForImageInfo(source)) {
//            imageData.add(imageInfo.downLoadImage);
//        }
//        for (ImageData data : imageData) {
//            renderImage(data);
//        }
//    }
//}
//
//class FutureRenderer {
//
//    private final ExecutorService executor = new ExecutorService() {
//        @Override
//        public void shutdown() {
//
//        }
//
//        @Override
//        public List<Runnable> shutdownNow() {
//            return null;
//        }
//
//        @Override
//        public boolean isShutdown() {
//            return false;
//        }
//
//        @Override
//        public boolean isTerminated() {
//            return false;
//        }
//
//        @Override
//        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
//            return false;
//        }
//
//        @Override
//        public <T> Future<T> submit(Callable<T> task) {
//            return null;
//        }
//
//        @Override
//        public <T> Future<T> submit(Runnable task, T result) {
//            return null;
//        }
//
//        @Override
//        public Future<?> submit(Runnable task) {
//            return null;
//        }
//
//        @Override
//        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
//            return null;
//        }
//
//        @Override
//        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
//            return null;
//        }
//
//        @Override
//        public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
//            return null;
//        }
//
//        @Override
//        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//            return null;
//        }
//
//        @Override
//        public void execute(Runnable command) {
//
//        }
//    };
//
//    void renderPage(CharSequence source) {
//        final List<ImageInfo> imageInfos = scanForImageInfo(source);
//        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
//            @Override
//            public List<ImageData> call() throws Exception {
//                List<ImageData> result = new ArrayList<>();
//                for (ImageInfo imageInfo : imageInfos) {
//                    result.add(imageInfo.downloadImage());
//                }
//                return result;
//            }
//        };
//
//        Future<List<ImageData>> future = executor.submit(task);
//        renderText(source);
//
//        try {
//            List<ImageData> imageData = future.get();
//            for (ImageData data : imageData) {
//                renderImage(data);
//            }
//        } catch (InterruptedException e) {
//            // 重新设置线程的中断状态
//            Thread.currentThread().interrupt();
//            // 由于不需要结果，取消任务
//            future.cancel(true);
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.getCause();
//        }
//    }
}
