package com.bw.service;


import java.util.Map;

import com.bw.entity.Result;

public interface OrderService {
    public Result order(Map map) throws Exception;
    public Map findById(Integer id) throws Exception;
}
