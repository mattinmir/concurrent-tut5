/*
 * ONEBUF = (put->get->ONEBUF).
 */


public class OneBuf 
{
	protected Object[] buf;
	
	public OneBuf()
	{
		buf = new Object[1];
	}
	
	synchronized public void put() throws InterruptedException
	{
		/*
		 * Check to see if the buffer is full before you can fill it.
		 * If it is full, wait() to put thread in non-runnable mode.
		 * 
		 * Once it has been awoken by a notify from a different thread, 
		 * it will exit the while loop and check the condition again.
		 * If the buffer is still full, it will enter the loop and wait.
		 * If it is empty, it will skip over the while loop and fill the 
		 * buffer, and notify the other threads.
		 */
		while (buf != null)
		{
			wait();
		}
		
		buf[0] = new Object();
		notifyAll();
	}
	
	synchronized public void get() throws InterruptedException
	{
		while (buf == null)
		{
			wait();
		}
		
		buf[0] = null;
		notifyAll();
	}
}
