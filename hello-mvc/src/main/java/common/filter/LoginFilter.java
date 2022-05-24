package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dto.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({ 
			"/member/memberView", 
			"/member/memberUpdate", 
			"/member/memberDelete",
			"/member/passwordUpdate",
			"/board/boardEnroll",
			"/board/boardUpdate",
			"/board/boardDelete"
})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 로그인 여부 검사
		// 로그인 후에만 접근 가능하도록함
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		HttpSession session = httpReq.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if(loginMember == null) {
			String msg1 = (String) session.getAttribute("msg");
			if(msg1 != null) {
				httpRes.sendRedirect(httpReq.getContextPath() + "/");		
				return; // 조기리턴
			}
			else {
				String msg = "로그인 후에 확인할 수 있습니다.";
				session.setAttribute("msg", msg);
				httpRes.sendRedirect(httpReq.getContextPath() + "/");		
				return; // 조기리턴
			}
			
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
