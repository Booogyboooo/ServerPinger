# ServerPinger
Simple Minecraft server mass scanner

## Java - PingExecutable
An executable jar that can be ran with "java -jar PingLib.jar"

It supports 6 arguments:
  -p   specify a port "-p 25566" (default 25565)
  -T   specify a timeout in milisecounds "-T 500" (default 1000)
  -t   specify how many threads should be used "-t 1000" (default 500)
  -W   specify a discord webhook url "-W exampleurl.com" (default "")
  -s   specify a starting ip "-s 172.32.0.1" (default 172.32.0.0)
  -e   specify a ending ip "-e 191.0.1.254" (default 191.0.1.254)

It is recommended that you try out diffrent ip ranges just remember to never scan any local ip ranges
All ips should also be ipv4 not ipv6

## Java - PingLib
A java library that can be easily added to any java program

(same code as the executable but not exported as a program)

## Other Info

Local ip ranges:

10.x.x.x
172.16.0.0 - 172.31.255.255
192.168.x.x
127.x.x.x
0.x.x.x
255.x.x.x

Probable server ports:

25565 (default minecraft java)
25566
25564
11111
22222
33333
44444
55555

General ipv4 info

1.0.0.0 - 255.255.255.255 (ip range)
1 - 65535 (port range)
