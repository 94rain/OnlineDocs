package top.jisy.docs.enums;

public enum MessageType {

    Insert("Insert"),
    Delete("Delete"),
    UserJoined("UserJoined"),
    UserLeft("UserLeft"),
    ContentInit("ContentInit"),
    DocumentTitle("DocumentTitle"),
    UsersInit("UsersInit"),
    ChatMessage("ChatMessage"),
    WrongDocId("WrongDocId");

    private String messageType;

    private MessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return this.messageType;
    }
}
