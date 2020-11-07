package lr1.model;

import helpers.JsonHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcMsgTest {

    @Test
    public void jsonTest(){
        CalcMsg msg = new CalcMsg(2.5, 0.05);
        String jsonMsg = JsonHelper.serialize(msg);
        System.out.println(jsonMsg);

        CalcMsg deserializedObj = JsonHelper.deserialize(jsonMsg, CalcMsg.class);

        Assertions.assertEquals(2.5, deserializedObj.getX(), 0.0001);
        Assertions.assertEquals(0.05, deserializedObj.getDelta(), 0.0001);

        System.out.println(deserializedObj);
    }

}