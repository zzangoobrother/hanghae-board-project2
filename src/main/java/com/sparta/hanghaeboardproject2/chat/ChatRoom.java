package com.sparta.hanghaeboardproject2.chat;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {
  private String roomId;
  private String name;
//  private Set<WebSocketSession> socketSessions = new HashSet<>();

  public static ChatRoom create(String name) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoom.roomId = UUID.randomUUID().toString();
    chatRoom.name = name;
    return chatRoom;
  }
//
//  @Builder
//  public ChatRoom(String roomId, String name) {
//    this.roomId = roomId;
//    this.name = name;
//  }
//
//  public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
//    if (chatMessage.getType().equals(MessageType.ENTER)) {
//      socketSessions.add(session);
//      chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
//    }
//    sendMessage(chatMessage, chatService);
//  }
//
//  public <T> void sendMessage(T message, ChatService chatService) {
//    socketSessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
//  }
}
