package lr1.behaviours;

import helpers.JsonHelper;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lr1.model.CalcMsg;
import lr1.model.Protocols;

public class TransitionMsgReceiverBehaviour extends Behaviour {

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol(Protocols.transition)
        );

        ACLMessage req = getAgent().receive(mt);
        if (req != null) {
            CalcMsg newData = JsonHelper.deserialize(req.getContent(), CalcMsg.class);
            getAgent().addBehaviour(new CalcRequestSenderBehavior(newData.getX(), newData.getDelta()));
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
