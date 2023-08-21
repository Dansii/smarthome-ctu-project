# SmartHome

**Funkční požadavky** *(podle zadání)*

F1: Všechny třídy, se kterými pracujeme, jsou ve složce "alive", "devices", "home". ✔

F2: Všechna zařízení mají API na ovládání. Devicy mají API na ovladaní přimo ve svých třídách. ✔

F3: Spotřebiče mají svojí spotřebu ve 4. stavech a to jsou *ON*, *BUSY*, *BROKEN* a *OFF*. Na regulování těchto stavů použiváme desigh pattern "State Machine", který se nachazí ve složce "devices". ✔

F4: Devicy, které jsou ve složce "action", "weather" mají API na sběr dat o sobě. ✔

F5: Osoby a zviířata mohou ovlivňovat třidy spotřebiců pomocí speciálních funkcí , které jsou v těch třídách. ✔

F6. Jednotlivá zařízení a osoby se v každém okamžiku vyskytují v jedném patře (pokud nesportují nebo nedělají libovolnou další akci) a náhodně generují eventy (ji, používá zařízení, jde sportovat apod.). ✔

F7: Entira world generuje eventy, krétě jsou odbavovány vhodnými zařízení. V aplikaci jsou alarm zařízení, která taky generuji eventy.
Pokud zařízení se rozbije, vygeneruje event, který bude zpracovaný entitou osoby. ✔

F8: Vygenerování HouseConfigurationReport, EventReport, ActivityAndUsageReport, ConsumptionReport do textového souboru. Třídy generující reporty jsou ve složce "reports". ✔ 

F9: Je funkce action() ve třídě "ActionDeviceBrokenState" ve složce "devices.action.state", v teto funkce je logika pro opravu devicy, osoba zkouma manual a pak opravuje devicy. Pokud je to ditě, tak zavola někoho dospeleho pomoct. ✔  

F10: Ve třídě "Creature" jsou proměnna wish, ktera je zodpovědna za použití deviců nebo sportovního vybavení. To všechno kontroluje funkce doSomething(), která je taky ve třídě "Creature". ✔  


**Design patterns**

SINGLETON  
Třída "House" vytvořena právě jednou, jakožto jedina budova.


LISTENER  
Předky třídy AlarmListener představují listenery s metodou onApplicationEvent(T event), kde T je předkem AbstractAlarmEvent.
Používá se pro změnu štábu při aktivaci alarmu.


VISITOR
Visitor je implementovaný mezi třídami dědícími od AbstractAlarmEvent a třídou EmergencyCenterVisitor. Používá se pro detekci, jaké alarmy byli aktivované a jaké služby musejí se zavolat.


STRATEGY    
Pattern je implementovaný u třídy House. Používá se pro změnu chování obyvatelů domu a devicu při havarijní situaci.


STATE MACHINE  
Slouží při používání spotřebiců k pohybu mezi stavy *ON*, *BUSY*, *BROKEN* a *OFF*.


**Nefunkční požadavky**
- Není autentizace ani autorizace. ✔
- Běží pouze v jedné JVM. ✔
- JavaDoc je v kořenu repozitáře. ✔ 
- Reporty jsou generovány do textového souboru v kořenove složce. ✔
- Konfigurace domu, zařízení a obyvatel domu nahrani z třidy HomeFactory a pomoci metod setUpHouse(int adultsNumber, int childNumbers, int petsNumber, HouseSize houseSize). ✔

**Požadované vystupy**
  
- Všechny diagramy jsou v hlavní složce projektu na GITu (UML a Use Case). ✔
- Podrobní JavaDoc je taky v hlavní složce projektu na GITu. ✔ 
- Ve třidě "HomeFactory" je funkce, která obsahuje konfigurace. ✔
- Reporty pro různé konfigurace lze získat spuštěním programu s různými parametry.
