package lr1.behaviours;

import helpers.DfHelper;
import helpers.JsonHelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lr1.model.CalcMsg;
import lr1.model.Protocols;
import lr1.model.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CalcRequestSenderBehavior extends Behaviour {
    private int requiredResponsesCount = 0;
    private int receivedReposesCount = 0;
    private List<CalcMsg> responses = new ArrayList<>();
    private double x, delta;
    private final double minDelta = 0.01;

    public CalcRequestSenderBehavior(double x, double delta) {
        this.x = x;
        this.delta = delta;
    }

    @Override
    public void onStart() {
        List<AID> agents = DfHelper.searchForAgents(Services.Calculation_service.toString(), getAgent());
        requiredResponsesCount = agents.size();
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.setContent(JsonHelper.serialize(new CalcMsg(x, delta)));
        request.setProtocol(Protocols.calculateF);
        for (AID agent : agents) {
            request.addReceiver(agent);
        }
        getAgent().send(request);
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol(Protocols.calculateF)
        );
        ACLMessage reply = getAgent().receive(mt);
        if (reply != null) {
            CalcMsg response = JsonHelper.deserialize(reply.getContent(), CalcMsg.class);
            responses.add(response);
            receivedReposesCount++;

        } else {
            block();
        }

    }

    @Override
    public boolean done() {
        return receivedReposesCount == requiredResponsesCount;
    }

    @Override
    public int onEnd() {
        double sumYXMinusDelta = 0;
        double sumYX = 0;
        double sumYXPlusDelta = 0;


        for (CalcMsg response : responses) {
            List<Double> results = response.getResults();
            sumYXMinusDelta += results.get(0);
            sumYX += results.get(1);
            sumYXPlusDelta += results.get(2);
        }

        if (sumYXMinusDelta >= sumYX && sumYXMinusDelta >= sumYXPlusDelta) {
            x = x - delta;
        }

        if (sumYXPlusDelta >= sumYX && sumYXPlusDelta >= sumYXMinusDelta) {
            x = x + delta;
        }

        if (sumYX >= sumYXMinusDelta && sumYX >= sumYXPlusDelta) {
            delta /= 2;
        }

        if (delta < minDelta) {
            System.out.println("Calculation ended; resluts : x=" + x + "; y=" + sumYX);
        } else {
            sendToNextAgent();
        }

        return super.onEnd();
    }

    private void sendToNextAgent() {
        ACLMessage transitionMsg = new ACLMessage(ACLMessage.INFORM);
        transitionMsg.setProtocol(Protocols.transition);
        transitionMsg.setContent(JsonHelper.serialize(new CalcMsg(x, delta)));

        List<AID> agents = DfHelper.searchForAgents(Services.Calculation_service.toString(), getAgent());
        agents.remove(getAgent().getAID());

        Random r = new Random();
        int random = r.nextInt(agents.size());
        transitionMsg.addReceiver(agents.get(random));
        getAgent().send(transitionMsg);
    }
}
