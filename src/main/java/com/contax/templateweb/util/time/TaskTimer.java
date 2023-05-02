package com.contax.templateweb.util.time;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The <code>TaskTimer</code> Class provides a set of tools for loggin timers and execution timers of custom created tasks
 *
 * @author Daniel do Valle
 * @version 1.02, 14/06/12
 * @since NGR-02.00.00
 */
public class TaskTimer {

    private static final Logger logger = LogManager.getLogger();
    private static ArrayList<String> taskIdArray = null;
    private static ArrayList<TaskTimer> taskTimerArray = null;
    private final String taskId;
    private String taskName;
    private final GregorianCalendar timerStartTime;
    private GregorianCalendar timerStopTime;
    private double timerTotalExecutionTime;

    private TaskTimer() {
        this.taskId = "";
        this.timerStartTime = null;
        this.timerStopTime = null;
        this.timerTotalExecutionTime = -1;
    }

    private TaskTimer(String taskName, String timerId) {
        this.taskName = taskName;
        this.taskId = timerId;
        this.timerStartTime = new GregorianCalendar();
        this.timerStopTime = null;
        this.timerTotalExecutionTime = -1;
    }

    /**
     * Shutdown the TaskTimer
     */
    public static synchronized void shutdown() {
        taskIdArray = new ArrayList<>(100);
        taskIdArray = null;
        taskTimerArray = new ArrayList<>(100);
        taskTimerArray = null;
        logger.info("==== TaskTime Shutdown Completed. ====");
    }

    /**
     * Starts the TaskTimer
     */
    public static synchronized void init() {
        if (taskIdArray != null || taskTimerArray != null) {
            TaskTimer.shutdown();
        }
        taskIdArray = new ArrayList<>(100);
        taskTimerArray = new ArrayList<>(100);
        logger.info("==== TaskTime Init Completed. ====");
    }

    /**
     * Creates and Logs a new Task with the given taskId
     *
     * @param taskName The formated Task Name
     * @param taskId An Unique task Id
     */
    public static synchronized void startTimer(String taskName, String taskId) {
        if (!taskIdArray.contains(taskId)) {
            TaskTimer taskTimer = new TaskTimer(taskName, taskId);
            taskTimerArray.add(taskTimer);
            taskIdArray.add(taskId);
            logger.info("==== Task: {} ====", taskTimer.getTaskId());
        } else {
            logger.warn("====Task {} Already Defined ====", taskId);
        }
    }

    /**
     * Logs a custom message on a Task, without stopping it, informing the Total Execution timer until this point
     *
     * @param taskId
     * @param customMessage
     */
    public static synchronized void checkPointTimer(String taskId, String customMessage) {
        if (taskIdArray.contains(taskId)) {
            int taskIndex = taskIdArray.indexOf(taskId);
            TaskTimer taskTimer = taskTimerArray.get(taskIndex);
            taskTimer.stopTimerTime();
            taskTimer.calculateTimerTotalExecutionTime();
            logger.info("==== Task: {} ||{}||Time: {} Secs ====", taskTimer.getTaskId(), customMessage, taskTimer.getTimerCompletionTime());
        } else {
            logger.warn("====Task: {} Not Found ====", taskId);
        }
    }

    /**
     * Logs a custom message on a Task, without stopping it, informing the Total Execution timer until this point
     *
     * @param taskId
     * @param customMessage
     * @param level
     */
    public static synchronized void checkPointTimer(String taskId, String customMessage, Level level) {
        if (taskIdArray.contains(taskId)) {
            int taskIndex = taskIdArray.indexOf(taskId);
            TaskTimer taskTimer = taskTimerArray.get(taskIndex);
            taskTimer.stopTimerTime();
            taskTimer.calculateTimerTotalExecutionTime();
            logger.log(level, "==== Task: {} ||{}||Time: {} Secs ====", taskTimer.getTaskId(), customMessage, taskTimer.getTimerCompletionTime());
        } else {
            logger.warn("====Task: {} Not Found ====", taskId);
        }
    }

    /**
     * Stops, summarize the total time and logs the Task identified with the given taskId
     *
     * @param taskId
     */
    public static synchronized void stopTimer(String taskId) {
        if (taskIdArray.contains(taskId)) {
            int taskIndex = taskIdArray.indexOf(taskId);
            TaskTimer taskTimer = taskTimerArray.get(taskIndex);
            taskTimer.stopTimerTime();
            taskTimer.calculateTimerTotalExecutionTime();
            taskTimerArray.remove(taskIndex);
            taskIdArray.remove(taskIndex);
            logger.info("==== Task: {}||Time: {} Seconds ====", taskTimer.getTaskId(), taskTimer.getTimerCompletionTime());
        } else {
            logger.warn("====Task {} Not Found ====", taskId);
        }
    }

    /**
     * @return the timerId
     */
    private String getTaskId() {
        return taskId;
    }

    /**
     * @return the timerStartTime
     */
    private GregorianCalendar getTimerStartTime() {
        return (GregorianCalendar) timerStartTime.clone();
    }

    /**
     * @return the timerStopTime
     */
    private GregorianCalendar getTimerStopTime() {
        return (GregorianCalendar) timerStopTime.clone();
    }

    /**
     * @return the timerCompletionTime
     */
    private String getTimerCompletionTime() {
        DecimalFormat fmt = new DecimalFormat("0.000");
        String formatedTimerTotalExecutionTime = fmt.format(timerTotalExecutionTime);
        return formatedTimerTotalExecutionTime;
    }

    /**
     */
    private void stopTimerTime() {
        this.timerStopTime = new GregorianCalendar();
    }

    /**
     */
    private void calculateTimerTotalExecutionTime() {
        this.timerTotalExecutionTime = this.getTimerStopTime().getTimeInMillis() - this.getTimerStartTime().getTimeInMillis();
        this.timerTotalExecutionTime /= 1000;
    }

    public String getTaskName() {
        return this.taskName;
    }
}
