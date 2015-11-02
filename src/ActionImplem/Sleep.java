package ActionImplem;

public class Sleep extends Action{

	@Override
	public void Do() throws InterruptedException {
//		try {
			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			
//			e.printStackTrace();
//		}
	}

}
