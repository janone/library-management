package org.example.common.responsibilitychain;


public interface Chain {
	// 接口
    Object doFilter(Chain chain, Object ... args) throws Exception;

}

