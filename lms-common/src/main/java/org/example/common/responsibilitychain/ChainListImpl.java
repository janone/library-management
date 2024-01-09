package org.example.common.responsibilitychain;

import java.util.ArrayList;
import java.util.List;

public class ChainListImpl implements Chain{


    private List<Chain> chainList = new ArrayList<>();

    public ChainListImpl(List<Chain> oldChainList){
        chainList.addAll(oldChainList);
    }

    public ChainListImpl(){
    }
    private int num = 0;

    public ChainListImpl replica(){
        return new ChainListImpl(chainList);
    }

    @Override
    public Object doFilter(Chain chain, Object... args) throws Exception {
    	//遍历完结束返回
        if(num == chainList.size()) return null;
        // 遍历chainList里的Chain。
        return chainList.get(num++).doFilter(this,args);
    }

    public void addChain(Chain chain){
        chainList.add(chain);
    }

}

