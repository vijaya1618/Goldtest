package  com.pennant.customer;
import org.zkoss.zhtml.Div;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

public class aboutControl extends Div{
	/*public void onCreate() {

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
	}*/
	public void aboutus() {
		Window win = (Window)Executions.createComponents("AboutUs.zul", null, null);
		win.doModal();
		win.setTitle("aboutus");
		win.setClosable(true);
	}

}
