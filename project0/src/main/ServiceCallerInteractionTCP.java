package main;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.BasicMsgUtil;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class ServiceCallerInteractionTCP {
		
 	/*1*/private ProtocolType protocol = ProtocolType.tcp;
 	/*2*/private IApplMessage req = BasicMsgUtil.buildRequest("client", "dofibo", 
 			"dofibo(12)", "servicemath");
 	/*3*/private String hostAddr = "130.136.113.239";
 	/*4*/private String entry    = "8011";

 	/*5*/private Interaction conn = ConnectionFactory.createClientSupport(
 	                    protocol, hostAddr, entry);
		
	public void doJob() {
	 try {
		/*6*/  sendRequestSynch( req, conn, protocol );
		/*7*/  sendRequestAsynch( req, conn, protocol );
		/*8*/  Thread.sleep(5000);
		/*9*/  System.exit(0);
	 }catch(Exception e){
	    CommUtils.outred("ERROR " + e.getMessage() );
	 }      
   }
	  	
	protected  void sendRequestSynch( IApplMessage m, Interaction conn, ProtocolType protocol  )
		throws Exception {
		String answer = "";
		answer = conn.request(req.toString()); 
		CommUtils.outmagenta( protocol + ": sendRequestSynch answer = " + answer );   	
    }  

    protected  void sendRequestAsynch( IApplMessage m, Interaction conn, ProtocolType protocol  ) 
    		throws Exception {	
    	/*1*/ conn.forward(req.toString());
    	/*2*/ String answer = conn.receiveMsg();
		CommUtils.outmagenta( protocol + ": sendRequestAsynch answer = " + answer  );   	
    }  
	    
    public static void main( String[] args) {
    	new ServiceCallerInteractionTCP().doJob();
    }

}