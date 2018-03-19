
public class RunApplication {

	public static void main(String[] args) throws Exception  {
		// TODO Auto-generated method stub
		LoadApplication load=new LoadApplication();
		for (int i = 0; i <=100; i++) {
		try {			
			Thread.sleep(60);
		
		load.setVisible(true);
		load.loading.setText("loading...." + i + "%");
		load.progressBar.setValue(i);
		if (i==100) {
			loginForm login=new loginForm();
			   login.frame.setVisible(true);
				load.setVisible(false);
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
   
   
	}

}
