package com.indraInterview.queue;

import com.indraInterview.commandclasses.Command;
import com.indraInterview.utils.Utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CommandQueueWithLocks {

    private static Queue<Command> queue = new LinkedList<>();
    private static int QUEUE_MAX_SIZE = 15;

    private static ReentrantLock lock = new ReentrantLock(true);
    private static Condition notEmpty = lock.newCondition();
    private static Condition notFull = lock.newCondition();

    public static void addCommand(Command command, int id) {
        lock.lock();
        try {
            while (queue.size() == QUEUE_MAX_SIZE) {
                notFull.await();
            }
            queue.add(command);
            Utils.printQueue(queue, id, true);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static Command readCommand(int id) {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            Command command = queue.remove();
            Utils.printQueue(queue, id, false);
            notFull.signalAll();
            return command;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return null;
    }
}
