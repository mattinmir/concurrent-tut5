import java.util.concurrent.Semaphore;


public class OneBufSema 
{
	protected Object[] buf;
	protected Semaphore s;
	
	public OneBufSema()
	{
		buf = new Object[1];
		s = new Semaphore(1);
	}
	
	synchronized public void put() throws InterruptedException
	{
		s.acquire();
		buf[0] = new Object();
		s.release();
	}
	
	synchronized public void get() throws InterruptedException
	{
		s.acquire();
		buf[0] = null;
		s.release();
	}
}
