# javaBeam
A Client Server Architecture for Drawing Pictures on a remote Computer. We built it for teaching other students Java.
You just have to start the server.jar on a PC with a beamer attached to and use the client.jar to connect to it. The client.jar provides varius high-level functions to draw basic geometry figures.

start with this small example, wich draws a smiley on the remote screen:

```java
JavaBeamClient beamer = new JavaBeamClient("the.Ip.Of.The.Remote.Computer"); //the remote screen
  
beamer.drawCircle(500, 300, 200, Color.YELLOW); //the yellow outline
  
beamer.drawCircle(500 + 70, 250, 30, Color.BLACK); //now the Eyes 
beamer.drawCircle(500 - 70, 250, 30, Color.BLACK);
	
for (int x = -100; x <= 100; x += 1) { //and finally the mouth
	beamer.drawCircle(500 + x, (int) (420 - 0.005 * x * x), 20, Color.BLACK);
}
