package edu.neu.filter;

//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpFilter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
////import org.hibernate.validator.internal.util.logging.Log_.logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;
//
//import com.neu.model.Error;
//import com.neu.model.ErrorResponse;
//import com.neu.service.JwtUserDetailsService;






//@WebFilter
//(urlPatterns = {"/profile/*","/post/*","/like/*","/unlike/*","/comment/*"})
public class RequestFilter {

//	@Autowired
//	private UserDetailsService jwtUserDetailsService;
//
//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;



//	@Override
//	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//	System.out.println("filter123");
//		HttpServletRequest req= (HttpServletRequest) request;
//		final String requestTokenHeader = req.getHeader("x-auth-token");
//		JwtTokenUtil jwt=new JwtTokenUtil();
//		String username = null;
//		String jwtToken = null;
//		// JWT Token is in the form "Bearer token". Remove Bearer word and get
//		// only the Token
//		if (requestTokenHeader != null) {
//
//			try {
//
//				username = jwt.getUsernameFromToken(requestTokenHeader);
//				chain.doFilter(request, response);
//			} catch (IllegalArgumentException e) {
//				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid token");
//
//			} catch (Exception e) {
//
//				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid token");
//
//			}
//		} else {
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"JWT Token does not begin with Bearer String");
//	//		logger.warn("JWT Token does not begin with Bearer String");
//		}

//		// Once we get the token validate it.
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//          // UserDet
//			JwtUserDetailsService jwtUserDetailsService=new JwtUserDetailsService();
//			UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
//
//			// if token is valid configure Spring Security to manually set
//			// authentication
//			if (jwt.validateToken(jwtToken, userDetails)) {
//
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//				usernamePasswordAuthenticationToken
//						.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			}
//		}
		
    //    req.setAttribute("username", username);
	//	HttpSession session = req.getSession();
		
	//	 MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);
		//request.
	//	 mutableRequest.putHeader("username", username);
		 
		//filterChain.doFilter(mutableRequest, httpRes);

		
		
//	}
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
////		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
////	            filterConfig.getServletContext());
//
//	}

}