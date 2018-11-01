package com.proxy.service;

import com.proxy.annotation.Log;

public interface UserService
{
    @Log
    void addUser(String name);

    void remove(String name);
}