package interceptor;

import org.apache.struts2.ServletActionContext;


import com.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		String action = arg0.getInvocationContext().getName();
		String u = (String) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(u == null) {
			ServletActionContext.getRequest().getSession().setAttribute("msg", "您还没有登录，请登录");
			return "noLogin";
		} else {
			return arg0.invoke();
		}
	}

	
}
