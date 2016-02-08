package testing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import graphics.Window;

public class Testing {

	public static void main(String[] args) {

		/*
		 * docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
		 * Initial Threads
		 * 
		 * Every program has a set of threads where the application logic
		 * begins. In standard programs, there's only one such thread: the
		 * thread that invokes the main method of the program class.
		 * 
		 * In this lesson, we call these threads the initial threads. In Swing
		 * programs, the initial threads don't have a lot to do. Their most
		 * essential job is to create a Runnable object that initializes the GUI
		 * and schedule that object for execution on the event dispatch thread.
		 * Once the GUI is created, the program is primarily driven by GUI
		 * events, each of which causes the execution of a short task on the
		 * event dispatch thread.
		 * 
		 * An initial thread schedules the GUI creation task by invoking
		 * javax.swing.SwingUtilities.invokeLater or
		 * javax.swing.SwingUtilities.invokeAndWait . Both of these methods take
		 * a single argument: the Runnable that defines the new task. Their only
		 * difference is indicated by their names: invokeLater simply schedules
		 * the task and returns; invokeAndWait waits for the task to finish
		 * before returning.
		 * 
		 * In an applet, the GUI-creation task must be launched from the init
		 * method using invokeAndWait; otherwise, init may return before the GUI
		 * is created, which may cause problems for a web browser launching an
		 * applet. In any other kind of program, scheduling the GUI-creation
		 * task is usually the last thing the initial thread does, so it doesn't
		 * matter whether it uses invokeLater or invokeAndWait.
		 * 
		 * Why does not the initial thread simply create the GUI itself? Because
		 * almost all code that creates or interacts with Swing components must
		 * run on the event dispatch thread. This restriction is discussed
		 * further in the next section.
		 * 
		 * The Event Dispatch Thread
		 * 
		 * Swing event handling code runs on a special thread known as the event
		 * dispatch thread. Most code that invokes Swing methods also runs on
		 * this thread. This is necessary because most Swing object methods are
		 * not "thread safe": invoking them from multiple threads risks thread
		 * interference or memory consistency errors. Some Swing component
		 * methods are labelled "thread safe" in the API specification; these
		 * can be safely invoked from any thread. All other Swing component
		 * methods must be invoked from the event dispatch thread. Programs that
		 * ignore this rule may function correctly most of the time, but are
		 * subject to unpredictable errors that are difficult to reproduce.
		 * 
		 * A note on thread safety: It may seem strange that such an important
		 * part of the Java platform is not thread safe. It turns out that any
		 * attempt to create a thread-safe GUI library faces some fundamental
		 * problems.
		 * 
		 * It's useful to think of the code running on the event dispatch thread
		 * as a series of short tasks. Most tasks are invocations of
		 * event-handling methods, such as ActionListener.actionPerformed. Other
		 * tasks can be scheduled by application code, using invokeLater or
		 * invokeAndWait. Tasks on the event dispatch thread must finish
		 * quickly; if they don't, unhandled events back up and the user
		 * interface becomes unresponsive.
		 * 
		 * If you need to determine whether your code is running on the event
		 * dispatch thread, invoke
		 * javax.swing.SwingUtilities.isEventDispatchThread.
		 */

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				new Window("Struttura Sportiva");
			}
		});
	}
}