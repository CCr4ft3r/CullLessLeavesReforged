# Cull Less Leaves Reforged (Unofficial)
Cull Less Leaves Reforged is a unoffical Forge port of [Cull Less Leaves](https://www.curseforge.com/minecraft/mc-mods/cull-less-leaves).

This mod gives you a **9 percent performance improvement in fps.**

### Important Notes and Requests
The original copyright owner **isXanderIsDev** who created the Fabric version of Cull (Less) Leaves.

Please do not report issues with this port to the original owner or to their GitHub repo. Please report it to [mine](https://github.com/CCr4ft3r/CullLessLeavesReforged/issues).<br>
Please do not join their discord or post a comment on their mod page in order to get support for my port. <br>
Please post your questions here.

This port currently does not change any of the original optimizations. On code side the only differences are the following ones:

<li>I adapted the class references and mixin injection points to match it to SRG and Forge</li>
<li>I removed one of the original embedded libraries by using the default mixin annotation classes</li>
<li>I removed some compatibility modes for one mod that does not exist for Forge.</li>
This port is fully compatible with Rubidium and Oculus. It also should work with Optifine however it will be not officially supported by me.

### How it works

Instead of leaving just the outer layer of leaves, Cull Less Leaves also renders a certain amount
of layers defined in the config.

**[Works best with Rubidium](https://www.curseforge.com/minecraft/mc-mods/rubidium)**

![comparison](https://i.imgur.com/AtKUGxU.png)

Rubidium Configuration

![rubidium config](https://i.imgur.com/UbWqA7b.png)