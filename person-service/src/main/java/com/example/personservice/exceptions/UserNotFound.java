package com.example.personservice.exceptions;

public class UserNotFound extends Exception {
   public UserNotFound(String message) {
      super(message);
   }
}
