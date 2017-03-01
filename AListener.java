package right;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AListener implements ServletContextListener {
	private Thread t = null;
	private ServletContext context;

	public void contextInitialized(ServletContextEvent contextEvent){
		t = new Thread() {
			// task
			public void run() {
				while (true) {
					Date yourdate = new Date();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(yourdate);
					int minutes = calendar.get(Calendar.MINUTE);
					int seconds = calendar.get(Calendar.SECOND);
					int mseconds = calendar.get(Calendar.MILLISECOND);
					System.out.println("Checking...");
					if (seconds % 5 == 0) {
						System.out.println(seconds);
						wow();
						try {
							System.out.println("Sleeping...");
							Thread.sleep(4999);
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		};
		t.start();
		context = contextEvent.getServletContext();
		// you can set a context variable just like this
		context.setAttribute("TEST", "TEST_VALUE");
	}

	public void contextDestroyed(ServletContextEvent contextEvent) {
		// context is destroyed interrupts the thread
		t.interrupt();
	}

	public void wow() {
		System.out.println("Thread running every second");
	}
}
