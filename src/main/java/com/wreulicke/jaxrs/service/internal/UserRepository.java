package com.wreulicke.jaxrs.service.internal;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.wreulicke.jaxrs.model.User;


@Service
public class UserRepository implements com.wreulicke.jaxrs.service.UserRepository{

  @Inject
  ExecutorService service;
  
  Function<String, User> supplier=User::new;
  
  @Override
  public CompletionStage<User> find(String id) {
    return CompletableFuture.supplyAsync(()->supplier.apply(id), service);
  }

}
