package lr1.behaviours;

import function.FunctionHandler;
import helpers.JsonHelper;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lr1.model.CalcMsg;
import lr1.model.Protocols;

import java.util.Arrays;
import java.util.List;

public class CalcRequestReceiverBehaviour extends Behaviour {

    private final FunctionHandler handler;

    public CalcRequestReceiverBehaviour(FunctionHandler handler) {
        this.handler = handler;
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchProtocol(Protocols.calculateF),
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST)
        );
        ACLMessage request = getAgent().receive(mt);
        if (request != null){
            CalcMsg req = JsonHelper.deserialize(request.getContent(), CalcMsg.class);
            double yXMinusDelta = handler.calculate(req.getX() - req.getDelta());
            double yX = handler.calculate(req.getX());
            double yXPlusDelta = handler.calculate(req.getX() + req.getDelta());
            List<Double> results = Arrays.asList(yXMinusDelta, yX, yXPlusDelta);
            CalcMsg resp = new CalcMsg(results);
            String stingResponse = JsonHelper.serialize(resp);

            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent(stingResponse);
            getAgent().send(reply);
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
