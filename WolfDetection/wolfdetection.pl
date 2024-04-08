%====================================================================================
% wolfdetection description   
%====================================================================================
dispatch( obstacledetected, obstacledetected(DISTANCE) ). %wolf detected
dispatch( ledinfo, ledinfo(INFO) ).
%====================================================================================
context(ctxwolfdetection, "localhost",  "TCP", "8013").
 qactor( wolfdetectionservice, ctxwolfdetection, "it.unibo.wolfdetectionservice.Wolfdetectionservice").
 static(wolfdetectionservice).
  qactor( sonar, ctxwolfdetection, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( camera, ctxwolfdetection, "it.unibo.camera.Camera").
 static(camera).
