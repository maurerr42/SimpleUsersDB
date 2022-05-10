package com.indraInterview.queue;

import com.indraInterview.commandclasses.Command;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CommandQueueWithLocks {

    private static Queue<Command> queue = new LinkedList<>();
    private static int max = 20;

    private static ReentrantLock lock = new ReentrantLock(true);
    private static Condition notEmpty = lock.newCondition();
    private static Condition notFull = lock.newCondition();

    public static void addCommand(Command command) {
        lock.lock();
        try {
            if (queue.size() == max) {
                notFull.await();
            }
            queue.add(command);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static Command readCommand() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            Command command = queue.remove();
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
