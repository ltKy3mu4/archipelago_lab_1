package agents.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class MyFirstTickerBehaviour extends TickerBehaviour {

    public MyFirstTickerBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        ACLMessage proposeMsg = new ACLMessage(ACLMessage.PROPOSE);
        proposeMsg.setContent("propose hello");
        proposeMsg.setProtocol("test");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("hello");
        msg.setProtocol("test");

        if (getAgent().getLocalName().equals("Smith1")) {
            msg.addReceiver(new AID("Smith2", false));
            proposeMsg.addReceiver(new AID("Smith2", false));
        }else {
            msg.addReceiver(new AID("Smith1", false));
            proposeMsg.addReceiver(new AID("Smith1", false));
        }
        getAgent().send(msg);
        getAgent().send(proposeMsg);
    }

}
