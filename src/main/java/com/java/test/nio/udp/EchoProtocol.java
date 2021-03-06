package com.java.test.nio.udp;

import java.nio.channels.SelectionKey;
import java.io.IOException;

public interface EchoProtocol {
  void handleAccept(SelectionKey key) throws IOException;
  void handleRead(SelectionKey key) throws IOException;
  void handleWrite(SelectionKey key) throws IOException;
}
