package com.hengyuan.hicash.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;

public class HicashAppFilter implements Filter {

	private static Logger logger = Logger.getLogger(HicashAppFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		logger.info(req.getRemoteHost() + " 请求时间" + req.getRequestURL()
				+ " on " + new Date() + ".");

		logger.info("IP:" + req.getRemoteHost());
		logger.info("请求地址：" + req.getRequestURL());
		logger.info("请求参数：" + req.getQueryString());

		// response.getWriter().write("<HTML><BODY> Check error! <br/></BODY></HTML>");
		// response.getWriter().flush();

		request.setCharacterEncoding(Consts.ENCODING);
		response.setCharacterEncoding(Consts.ENCODING);
		//response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
