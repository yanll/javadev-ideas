package com.cmp.common.id;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * ID生成策略：64位ID (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加)) 每秒26W ID
 *
 * @author HongjianZ
 */
public class UUID {
    private static long twepoch = 1288834974657L;
    // 机器标识位数
    private static long workerIdBits = 5L;
    // 数据中心标识位数
    private static long datacenterIdBits = 5L;
    // 毫秒内自增位
    private static long sequenceBits = 12L;
    // 机器ID偏左移12位
    private static long workerIdShift = sequenceBits;
    // 数据中心ID左移17位
    private static long datacenterIdShift = sequenceBits + workerIdBits;
    // 时间毫秒左移22位
    private static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private static long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static long lastTimestamp = -1L;

    private long sequence = 0L;
    private long workerId;
    private long datacenterId;

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

    public synchronized long nextID() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            try {
                throw new Exception("Clock moved backwards.  Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        // ID偏移组合生成最终的ID
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    // TEST
    public static void main(String[] args) {
        // 机器ID和数据中心ID
        UUID w = new UUID();
        w.setWorkerId(2);
        w.setDatacenterId(2);
        final CyclicBarrier cdl = new CyclicBarrier(100);
        for (int i = 0; i < 1000; i++) {
            System.out.println(i + 1);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(w.nextID());
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
