package main.interaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class TestRequest {
	private static Interaction connSupport;
	
	@BeforeClass
	public static void activateConsumer() {
		connSupport = ConnectionFactory.createClientSupport(
				          ProtocolType.tcp, "130.136.113.239", "8888");
	}
	
	/*
	@Test
	public void testRequesAfterRequest() {
		 CommUtils.outmagenta("testRequesAfterRequest ");
		 
		IApplMessage req_synch  = CommUtils.buildRequest( "letizia", "distance", "distance(20)", "consumer");
		IApplMessage req_asynch = CommUtils.buildRequest( "letizia", "distance", "distance(30)", "consumer");
 		
		try {
			connSupport.forward(req_asynch);
			
			IApplMessage reply = connSupport.request(req_synch); //bloccante
			CommUtils.outblue("reply: " + reply);
			String answer = reply.msgContent();
			assertEquals(answer, "ack(distance(20))");
			 
		} catch (Exception e) {
			fail("testRequest " + e.getMessage());
 		}
	}
	*/
	
	@Test
	public void testAsynchRequesAfterRequest() {
		 CommUtils.outmagenta("testAsynchRequesAfterRequest ");
		 
		 IApplMessage req1 = CommUtils.buildRequest( "letizia", "distance", "distance(10)", "consumer");
		 IApplMessage req2 = CommUtils.buildRequest( "letizia", "distance", "distance(20)", "consumer");
 		
		try {
			connSupport.forward(req1);
			connSupport.forward(req2);
			
			String reply1 = connSupport.receiveMsg();
			CommUtils.outblue("reply1: " + reply1);
			IApplMessage m1 = new ApplMessage(reply1);
			assertEquals(m1.msgContent(), "ack(distance(10))");
			
			String reply2 = connSupport.receiveMsg();
			CommUtils.outblue("reply2: " + reply2);
			IApplMessage m2 = new ApplMessage(reply2);
			assertEquals(m2.msgContent(), "ack(distance(20))");
			
			 
		} catch (Exception e) {
			fail("testRequest " + e.getMessage());
 		}
	}
	
	
}