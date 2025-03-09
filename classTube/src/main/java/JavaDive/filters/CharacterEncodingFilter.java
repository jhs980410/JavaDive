package JavaDive.filters;

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
		// TODO Auto-generated method stub
		this.config = filterConfig;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String encoding = this.config.getInitParameter("encoding");
		
		req.setCharacterEncoding(encoding);
		
		fc.doFilter(req, res);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
