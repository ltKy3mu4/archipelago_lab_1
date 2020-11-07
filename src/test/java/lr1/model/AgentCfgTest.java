package lr1.model;

import function.FunctionType;
import helpers.XmlHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentCfgTest {

    @Test
    public void linearCfgTst(){
        AgentCfg cfg = new AgentCfg();
        cfg.setFunction("2*x+2");
        cfg.setType(FunctionType.Linear);
        cfg.setStartFirst(true);

        XmlHelper.writeCfg(cfg, FunctionType.Linear.toString()+".xml");
    }

    @Test
    public void quadroCfgTst(){
        AgentCfg cfg = new AgentCfg();
        cfg.setFunction("-1*x*x+5");
        cfg.setType(FunctionType.Quadratic);
        cfg.setStartFirst(false);

        XmlHelper.writeCfg(cfg, FunctionType.Quadratic.toString()+".xml");
    }

    @Test
    public void sinCfgTst(){
        AgentCfg cfg = new AgentCfg();
        cfg.setFunction("sin(x)");
        cfg.setType(FunctionType.Sinusoidal);
        cfg.setStartFirst(false);

        XmlHelper.writeCfg(cfg, FunctionType.Sinusoidal.toString()+".xml");
    }



}