package top.jisy.docs.websocket;

import top.jisy.docs.crdt.Message;
import top.jisy.docs.enums.MessageType;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public Message decode(String jsonMessage) throws DecodeException {
        JsonObject jsonMsg = Json.createReader(new StringReader(jsonMessage)).readObject();

        Message message = new Message();

        message.setUserId(jsonMsg.getInt("userId"));
        message.setDocId(jsonMsg.getInt("docId"));
        message.setCursorPos(jsonMsg.getInt("cursorPos"));
        message.setDocState(jsonMsg.getInt("docState"));
        message.setMsg(jsonMsg.getString("msg") == null ? "" : jsonMsg.getString("msg"));
        message.setMessageType(MessageType.valueOf(jsonMsg.getString("messageType")));

        return message;
    }

    @Override
    public boolean willDecode(String jsonMessage) {
        try {
            Json.createReader(new StringReader(jsonMessage)).readObject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
