package unibo.naiveactors24;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.IApplMsgHandler;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ApplMsgHandler;
import unibo.basicomm23.utils.CommUtils;
 

/* parte sommersa
 * ===========================================================================
 * Realizza la gestione dei messaggi su una connessione
 * Nel caso di request da componente Alien remoto, inserisce nel messaggio 
 * un rifermento alla connessione, per rendere possibile 
 * il corretto invio della risposta.
 * ===========================================================================
 */

public class ContextMsgHandler  extends ApplMsgHandler implements IApplMsgHandler {
    protected String pfx = "    --- ";
    
    private ActorContext24 ctx;
    public ContextMsgHandler(String name, ActorContext24 ctx) {
        super(name);
        this.ctx=ctx;
    }

    @Override
    public void elaborate(IApplMessage msg, Interaction conn) { // elaboro i msg dal mondo esterno
        try {
            CommUtils.outmagenta(pfx+ name + " | elaborate " + msg);
            if (msg.isRequest()) elabRequest(msg, conn);
            else elabNonRequest(msg, conn);
        }catch( Exception e){
            CommUtils.outred(name + " | elaborate ERROR " + e.getMessage());
        }
    }

    // implementazione sommersa --> dall'esterno mi arriva un messaggio con l'idea
    // di comunicare con l'attore, non con il server tcp. Quando arriva il messaggio, 
    // prima devo vedere se nel contesto ho un attore con quel nome
    protected void elabNonRequest( IApplMessage msg, Interaction conn ) throws Exception {
        ActorBasic24 a = ctx.getActor( msg.msgReceiver()) ;
        if( a != null ) {    
             a.msgQueue.put( msg );   
        }else{
            String errorMsg = name + " | actor unknown:"+msg.msgReceiver();
            CommUtils.outred(errorMsg);
            throw new Exception(errorMsg);
        }
    }

    protected void elabRequest( IApplMessage msg, Interaction conn ) throws Exception {
    	CommUtils.outmagenta(pfx+ name + " | setConn in requestmsg: " + conn);
    	msg.setConn(conn);
        elabNonRequest(msg,conn);
    }
}
