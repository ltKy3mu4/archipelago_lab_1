package lr1;

import function.FunctionHandler;
import function.FunctionInjector;
import helpers.DfHelper;
import helpers.XmlHelper;
import jade.core.Agent;
import lr1.behaviours.CalcRequestReceiverBehaviour;
import lr1.behaviours.CalcRequestSenderBehavior;
import lr1.behaviours.TransitionMsgReceiverBehaviour;
import lr1.model.AgentCfg;
import lr1.model.Services;

public class FunctionAgent extends Agent {

    @Override
    protected void setup() {
        DfHelper.registerItself(Services.Calculation_service.toString(), this);

        AgentCfg agentCfg = XmlHelper.readCfg(AgentCfg.class, this.getLocalName() + ".xml");
        FunctionHandler handler = FunctionInjector.getHandler(agentCfg.getType());
        handler.setFunction(agentCfg.getFunction());

        addBehaviour(new CalcRequestReceiverBehaviour(handler));
        addBehaviour(new TransitionMsgReceiverBehaviour());

        if (agentCfg.isStartFirst()){
            addBehaviour(new CalcRequestSenderBehavior(-4, 2));
        }
    }


    private String determineCfgName() {
        String cfgName = "";
        switch (getLocalName()){
            case "Linear":
                return cfgName="Linear.xml";
            case "Quadratic":
                   return cfgName="Quadratic.xml";
            case "Sinusoidal":
                return cfgName="Sinusoidal.xml";
            default:
                throw new RuntimeException("can not found config for agent name : "+getLocalName());
        }
    }
}
