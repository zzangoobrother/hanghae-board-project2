package com.sparta.hanghaeboardproject2.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.hanghaeboardproject2.chat.ChatMessage;
import com.sparta.hanghaeboardproject2.chat.ChatRoom;
import com.sparta.hanghaeboardproject2.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

  private final ObjectMapper objectMapper;
  private final ChatService chatService;

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String payload = message.getPayload();
    ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
    ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
//    room.handleActions(session, chatMessage, chatService);
  }
}
