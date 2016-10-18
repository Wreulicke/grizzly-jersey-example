package com.wreulicke.jaxrs.service;

import java.util.concurrent.CompletionStage;

import org.jvnet.hk2.annotations.Contract;

import com.wreulicke.jaxrs.model.User;

@Contract
public interface UserRepository {
  public CompletionStage<User> find(String id);
}
