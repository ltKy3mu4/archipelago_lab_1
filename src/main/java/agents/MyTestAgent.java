package agents;

import agents.behaviours.ReceiverBehavior;
import agents.behaviours.MyFirstTickerBehaviour;
import jade.core.Agent;

public class MyTestAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println(this.getLocalName() + " : - i was born");
        addBehaviour(new ReceiverBehavior());
        addBehaviour(new MyFirstTickerBehaviour(this, 3000));
    }
}
