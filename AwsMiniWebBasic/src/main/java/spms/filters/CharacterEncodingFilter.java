package spms.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{

	FilterConfig config = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;	
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		
		String encoding = this.config.getInitParameter("encoding");
		req.setCharacterEncoding(encoding);
		res.setCharacterEncoding(encoding);
		
		fc.doFilter(req, res);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
