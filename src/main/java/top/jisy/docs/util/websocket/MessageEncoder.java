package top.jisy.docs.util.websocket;

import top.jisy.docs.crdt.Message;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(Message message) throws EncodeException {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("userId", message.getUserId())
                .add("docId", message.getDocId())
                .add("cursorPos", message.getCursorPos())
                .add("docState", message.getDocState())
                .add("msg", message.getMsg() == null ? "" : message.getMsg())
                .add("messageType", message.getMessageType().toString())
                .build();

        return jsonObject.toString();
    }
}
