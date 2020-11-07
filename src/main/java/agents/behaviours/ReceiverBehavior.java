package agents.behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceiverBehavior extends Behaviour {

    @Override
    public void onStart() {
        System.out.println("Behaviour started");
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.or(
                        MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                        MessageTemplate.MatchPerformative(ACLMessage.PROPOSE)
                ),
                MessageTemplate.MatchProtocol("test")
        );
        ACLMessage msg = getAgent().receive(mt);
        if (msg != null){
            System.out.println(getAgent().getLocalName()+ " - i received msg : "+msg.getContent());
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }

    @Override
    public int onEnd() {
        System.out.println("behaviour ended");
        return super.onEnd();
    }
}
