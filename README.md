# ServerPinger
Simple Minecraft server mass scanner

## Java - PingExecutable
An executable jar that can be ran with "java -jar PingLib.jar"

<b> It supports 6 arguments: <br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>-p</b>   specify a port "-p 25566" (default 25565) <br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>-T</b>   specify a timeout in milisecounds "-T 500" (default 1000) <br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>-t</b>   specify how many threads should be used "-t 1000" (default 500) <br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>-W</b>   specify a discord webhook url "-W exampleurl.com" (default "") <br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>-s</b>   specify a starting ip "-s 172.32.0.1" (default 172.32.0.0) <br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>-e</b>   specify a ending ip "-e 191.0.1.254" (default 191.0.1.254) <br>

It is recommended that you try out diffrent ip ranges just remember to never scan any local ip ranges and all ips should be ipv4 not ipv6

## Java - PingLib
A java library that can be easily added to any java program

(same code as the executable but not exported as a program)

## Other Info

<b> Local ip ranges: </b> <br>
&nbsp;&nbsp;&nbsp;&nbsp; 10.x.x.x <br>
&nbsp;&nbsp;&nbsp;&nbsp; 172.16.0.0 - 172.31.255.255 <br>
&nbsp;&nbsp;&nbsp;&nbsp; 192.168.x.x <br>
&nbsp;&nbsp;&nbsp;&nbsp; 127.x.x.x <br>
&nbsp;&nbsp;&nbsp;&nbsp; 0.x.x.x <br>
&nbsp;&nbsp;&nbsp;&nbsp; 255.x.x.x <br>

<b> Probable server ports: </b> <br>
&nbsp;&nbsp;&nbsp;&nbsp; 25565 (default minecraft java) <br>
&nbsp;&nbsp;&nbsp;&nbsp; 25566 (extremely close to the default port) <br>
&nbsp;&nbsp;&nbsp;&nbsp; 25564 <br>
&nbsp;&nbsp;&nbsp;&nbsp; 11111 (people like to do only one number) <br>
&nbsp;&nbsp;&nbsp;&nbsp; 22222 <br>
&nbsp;&nbsp;&nbsp;&nbsp; 33333 <br>
&nbsp;&nbsp;&nbsp;&nbsp; 44444 <br>
&nbsp;&nbsp;&nbsp;&nbsp; 55555 <br>

<b> General ipv4 info </b> <br>
&nbsp;&nbsp;&nbsp;&nbsp; 1.0.0.0 - 255.255.255.255 (ip range) <br>
&nbsp;&nbsp;&nbsp;&nbsp; 1 - 65535 (port range)
