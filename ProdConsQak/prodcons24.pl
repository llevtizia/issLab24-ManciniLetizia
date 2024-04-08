%====================================================================================
% prodcons24 description   
%====================================================================================
dispatch( distance, distance(D) ). %dispatch
request( distance, distance(D) ). %request
dispatch( info, info(SOURCE,TERM) ). %atteso dall'observer
%====================================================================================
context(ctxprodcons, "localhost",  "TCP", "8014").
 qactor( producer1, ctxprodcons, "it.unibo.producer1.Producer1").
 static(producer1).
  qactor( producer2, ctxprodcons, "it.unibo.producer2.Producer2").
 static(producer2).
  qactor( consumer, ctxprodcons, "it.unibo.consumer.Consumer").
 static(consumer).
  qactor( consumerobserver, ctxprodcons, "it.unibo.consumerobserver.Consumerobserver").
 static(consumerobserver).
