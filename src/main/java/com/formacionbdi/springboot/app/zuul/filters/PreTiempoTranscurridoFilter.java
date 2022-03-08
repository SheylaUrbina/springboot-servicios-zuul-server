package com.formacionbdi.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//se registra comoo componente de spring y hereda de zuulfilter
@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);
	@Override
	public boolean shouldFilter() {
		//ejecuta o no elfiltro, si es true ejecutar el filtro en cada request, si el usuario esta autenticado mandamos true o false
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		//logica del filtro
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info(String.format("%s resquest enrutado a %s ", request.getMethod(),request.getRequestURL().toString()));
		
		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
