/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.repository.core.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.vorto.repository.core.IUserContext;

public class UserContext implements IUserContext {
	
  
  private String username;
  private static final List<String> ANONYMOUS_USERS = Arrays.asList("anonymous","anonymousUser",getHash("anonymous"),getHash("anonymousUser"));
  
  public static UserContext user(String username) {
    return new UserContext(username);
  }

  private UserContext() {}

  private UserContext(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public String getHashedUsername() {
    return getHash(username);
  }

  private static String getHash(String username) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("SHA-256");
      return bytesToHex(digest.digest(username.getBytes(StandardCharsets.UTF_8)));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Exception in getting has of username: " + username, e);
    }
  }

  private static String bytesToHex(byte[] hash) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < hash.length; i++) {
      String hex = Integer.toHexString(0xff & hash[i]);
      if (hex.length() == 1)
        hexString.append('0');
      hexString.append(hex);
    }
    return hexString.toString();
  }

  @Override
  public boolean isAnonymous() {
	return ANONYMOUS_USERS.contains(this.username);
  }
}
