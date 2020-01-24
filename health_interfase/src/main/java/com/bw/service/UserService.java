package com.bw.service;

import com.bw.pojo.User;

public interface UserService {
     User findByUsername(String username);
}
