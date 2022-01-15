**Saleuine Großhandel**

Seitdem du Claudia und Karl-Heinz mit ihrem Fischmarkt geholfen hast, ist einige Zeit vergangen. Das Geschäft läuft sehr gut und die Saleuine haben einen fairen Preis für ihre Produkte gefunden. Jetzt haben sie sich entschieden, ihr Angebot dem globalen Pinguinmarkt zu öffnen, was jedoch mit einigen neuen Pflichten verbunden ist. Um nämlich im Großhandel mitzuspielen, müssen einige Auflagen der Pinguinbehörden erfüllt werden. So müssen z.B. Sardellen ein gewisses Alter und Gewicht erreicht haben, bevor sie verkauft werden dürfen. Außerdem müssen Claudia und Karl-Heinz jederzeit eine Statistik über nicht genutztes Essen abgeben können. Die beiden haben sich auch schon eine Struktur überlegt, wie sich das umsetzen lässt, brauchen für die Implementierung allerdings wieder deine Hilfe!

Hinweis: Die Aufgabenstellung ist an einigen Stellen bewusst offen gestaltet. Du musst selbst entscheiden, wann du zusätzliche Attribute und Methoden benötigt. Versuche außerdem, redundanten Code zu vermeiden und überschreibe Methoden nur falls nötig es sinnvoll ist. Weitere Infos hierzu auf Zulip (eine Kopie des Posts gibt es außerdem unter dem FAQ)

PinguFood
Zuerst bitten sie dich, eine praktikable Darstellung für ihre angebotenen Waren zu implementieren:

**PinguFood Attribute, Konstruktor und Getter**

Implementiere die im UML-Diagramm angegebenen Attribute, zugehörigen Getter und einen Konstruktor, der initiale Werte für age und weight (in dieser Reihenfolge) erwartet. Du kannst außerdem davon ausgehen, dass dem Konstruktor nur sinnvolle Werte übergeben werden. Gleiches gilt für alle Konstruktoren dieser Aufgabe.
age wird in dieser Aufgabe in Jahren und weight in (ganzen) Gramm angegeben.

**Anchovie Konstanten und Konstruktor**

Der Konstruktor von Anchovie soll die gleichen Parameter wie der von PinguFood übernehmen. Die groß geschriebenen und unterstrichenen Variablen im UML-Diagramm sind static Konstanten. Du musst sie direkt bei der Deklaration initialisieren.

- MIN_AGE soll 1 betragen

- MIN_WEIGHT soll 5 betragen

**Crustacean Konstruktor**

Wenn von Krustentieren als PinguFood die Rede ist, so ist vor allem Krill gemeint. Deshalb übernimmt der Konstruktor von Crustacean lediglich einen Wert für weight. age soll hingegen immer mit 0 initialisiert werden.

**Sardine Attribute, Konstanten und Konstruktor**

Implementiere die für Sardine im UML-Diagramm gegebenen Attribute und Konstanten, sowie einen Konstruktor, der nach Werten für age und weight auch einen für length erwartet (in dieser Reihenfolge), und die Getter-Methode für length.

- MIN_AGE soll 1 betragen

- MIN_WEIGHT soll 100 betragen

- MIN_LENGTH soll 14 betragen

**isEdible**
 
Die isEdible-Methode gibt an, ob die Nahrung gemäß den Pingurichtlinien verkauft und gegessen werden darf.
Da PinguFood erst zugelassen werden muss, soll diese Methode standardmäßig false zurückgeben.

- Anchovie dürfen nur verkauft und gegessen werden, sobald sie das vorgeschriebene Mindestalter (MIN_AGE) und -Gewicht (MIN_WEIGHT) erreicht haben.

- Crustacean dürfen immer verkauft und gegessen werden.

- Wie Anchovie sind auch Sardine nur dann für den Verkauf und Verzehr freigegeben, wenn sie die entsprechenden Auflagen erfüllen. In diesem Fall also Mindestalter, -Gewicht und -Länge.

**toString**

Implementiere jetzt die toString-Methode wie folgt:

- Für PinguFood sollen die allgemein verfügbaren Informationen wie folgt zurückgeben werden: Alter:⎵<age>⎵Jahre,⎵Gewicht:⎵<weight>g
- Für Anchovie soll folgendes zurückgegeben werden: Sardelle(Alter:⎵<age>⎵Jahre,⎵Gewicht:⎵<weight>g)
- Für Crustacean dies: Krill(<weight>g)
- Und für Sardine: Sardine(Alter:⎵<age>⎵Jahre,⎵Gewicht:⎵<weight>g,⎵Länge:⎵<length>)
  
**TradeOrder**
  
Nun, da die verschiedenen Waren implementiert sind, kannst du dich den Bestellungen widmen. Claudia und Karl-Heinz bieten drei verschiedene Bestellungstypen an: Einzeltier Bestellungen (TradeOrder), Zielgewicht Bestellungen (WeightOrder), sowie Mengen-Bestellungen (AmountOrder). Auch hier haben sie dir den ersten Schritt abgenommen und stellen dir eine passende Hierarchie zur Verfügung.

Wie du schon im UML-Diagramm erkennen kannst, haben Claudia und Karl-Heinz aus Woche 02 gelernt und verwenden kein double mehr, um Geldbeträge zu speichern. Stattdessen setzen sie auf deine gesammelte Erfahrung und lassen dich mit BigDecimal arbeiten. BigDecimal-Objekte sind unveränderlich. Jede Operation erzeugt ein neues BigDecimal-Objekt. Um double in BigDecimal umzuwandeln, gibt es die Methode BigDecimal.valueOf(). Operationen werden mittels bigDecimal1.operation(bigDecimal2); ausgeführt (operation duch entsprechenden Funktionsnamen ersetzen). Welche Operationen und nützlichen Konstanten es gibt, kannst du in der JavaDoc nachlesen. Auf den Tutoriumsfolien gibt es außerdem noch ein kleines Beispiel zur Verwendung von BigDecimal.

Mit diesen Informationen ausgestattet kannst du jetzt auch mit der Implementierung fortfahren.

**Order Attribute und Konstruktoren**
  
Implementiere die drei im UML-Diagramm gegebenen Klassen mit den angegebenen Attributen. Die targetXXX Attribute sollen dabei final sein. Für die Konstruktoren soll gelten:
  
- TradeOrder: keine Parameter, totalCost und currentWeight werden mit 0, bzw. dem BigDecimal equivalent initialisiert.
- WeightOrder: erwartet einen initialen Wert für targetWeight
- AmountOrder: erwartet initiale Werte für die target-Attribute in alphabetischer Reihenfolge (Anchovies, Crustaceans, Sardines). Die current-Attribute werden mit 0 initialisiert.
  
**orderType**
  
Implementiere jetzt die Methode orderType, sodass sie je nach aufrufendem Objekt folgende Strings zurückgibt:
  
- für TradeOrder: Einzeln
- für WeightOrder: Zielgewicht:⎵<targetWeight>g
- für AmountOrder: Anzahl:⎵[<targetAmountAnchovies>,<targetAmountCrustaceans>,<targetAmountSardines>]
 
**toString**
  
Implementiere jetzt die Methode toString, so dass je nach aufrufendem Objekt folgende Strings zurückgegeben werden:
  
- TradeOrder: Die⎵Bestellung(Einzeln)⎵hat⎵ein⎵Gesamtgewicht⎵von⎵<currentWeight>g⎵und⎵kostet⎵<totalCost>PD.
- WeightOrder:Die⎵Bestellung(Zielgewicht:⎵<targetWeight>g)⎵hat⎵ein⎵Gesamtgewicht⎵von⎵<currentWeight>g⎵und⎵kostet⎵<totalCost>PD.
- AmountOrder: Die⎵Bestellung(Anzahl:⎵[<targetAmountAnchovies>,<targetAmountCrustaceans>,<targetAmountSardines>])⎵hat⎵ein⎵Gesamtgewicht⎵von⎵<currentWeight>g⎵und⎵kostet⎵<totalCost>PD.
 
**isOrderFulfilled**
  
Die Methode isOrderFulfilled soll true zurückgeben, falls alle für die Bestellung relevanten Lieferungen eingegangen sind (was später mit supplyOrder passieren wird). Genauer heißt das:
  
- TradeOrder: es wurde bereits ein PinguFood-Objekt geliefert.
- WeightOrder: mindestens das geforderte Gewicht wurde erreicht.
- AmountOrder: die geforderten Mengen für Sardellen, Krustentiere und Sardinen wurden erreicht.

**supplyOrder**
  
Die Methode supplyOrder erhält von unseren Saleuinen ein Objekt von Typ PinguFood, sowie die entsprechenden Kosten und soll damit die entsprechende Bestellung aktualisieren. Die Bestellung wird nicht aktualisiert, falls 1.) die Bestellung bereits erfüllt ist (oder in dem Fall, dass für AmountOrder z.B. ein Anchovie-Objekt übergeben wird und targetAmountAnchovies bereits erreicht wurde. Entsprechendes gilt natürlich auch für Crustacean und Sardine) oder 2.) das übergebene PinguFood nicht zum Verzehr geeignet ist. In diesen Fällen soll false zurückgegeben werden, sonst true.
  
Damit hast du nun auch die Bestellungen vollständig implementiert. Jetzt fehlt noch das Logistikzentrum von Claudia und Karl-Heinz.

**PinguFoodLogistics**
  
Für ihr Logistikzentrum geben dir Claudia und Karl-Heinz folgendes Diagramm vor:

Hinweis: Die Klasse PinguFoodLogistics erhältst du bereits als Teil des Templates zusammen mit einigen Methoden, die es dir erlauben, Sardellen, Krustentiere und Sardinen gezielt oder zufällig zu erstellen. Es ist wichtig, dass du nichts an diesen Methoden änderst, da die Tests sonst falsche Ergebnisse liefern und du Punkte verlierst. (Um Compilerfehler im Template zu vermeiden und dir das Testen unfertiger Implementierungen zu ermöglichen, sind die Methoden auskommentiert. Du kannst den Kommentarblock entfernen, sobald die Konstruktoren von PinguFood und den zugehörigen Unterklassen implementiert sind.)

**PinguFoodLogistics Attribute und Konstruktor**
  
Implementiere die im UML-Diagramm angegebenen Attribute, sowie einen Konstruktor, der initiale Werte für ppgAnchovies, ppgCrustaceans und ppgSardines übernimmt (in dieser Reihenfolge). ppg steht dabei für „price per gram“.
  
Die Klasse TradeOrderQueue erhälst du bereits als Teil des Templates. Sie bietet alle für die Aufgabe benötigten Methoden (Konstruktor, isEmpty, size, add, poll), die wie in der Standard Java Queue funktionieren.

Die Klasse ist nur dazu da, dass du nicht mit Generics arbeiten musst, die noch nicht in der Vorlesung oder den P-Aufgaben besprochen wurden.

Solltest du bereits mehr Erfahrung haben und Generics schon kennen, verwende trotzdem die vorgegebene Klasse. Andernfalls werden die Tests nicht funktionieren.

**acceptNewOrder**
  
acceptNewOrder soll das übergebene TradeOrder-Objekt dem orderBook am Ende hinzufügen.
  
**registerUnusedFood und printWasteStatistics**
  
Die Methoden registerUnusedFood und printWasteStatistics sollen Auskunft über bisher beim Abarbeiten von Bestellungen "verschwendetes" (bzw. ungenutztes) Essen geben. Es sollen dabei Stückzahl und Gesamtgewicht des bisher ungenutzten PinguFoods, sowie der durch Nichtverkauf dieser Waren eingebußte Profit aufsummiert ausgegeben werden können. registerUnusedFood übernimmt dabei ein Objekt von Typ PinguFood. Dieses repräsentiert eine Ware, die nicht mehr verwendet wird/werden kann. Die Methode ist dafür zuständig, die später für printWasteStatistics benötigten Informationen über diese Ware abzuspeichern.
  
printWasteStatistics soll folgende Ausgabe auf der Konsole erzeugen: Bisher⎵konnten⎵<Anzahl des ungenutzten PinguFood>⎵Tiere⎵mit⎵einem⎵Gesamtgewicht⎵von⎵<Gesamtgewicht des ungenutzten PinguFood>g⎵nicht⎵verwertet⎵werden.\nClaudia⎵und⎵Karl-Heinz⎵ist⎵dadurch⎵ein⎵Profit⎵von⎵<Gesamtwert des ungenutzten PinguFood>PD⎵entgangen.
  
Der Wert eines PinguFood-Objekts berechnet sich durch weight*ppg
  
Die Statistik über ungenutztes PinguFood wird nie zurückgesetzt!
  
Für die Ausgabe soll nicht gerundet werden (was dank BigDecimal auch nicht notwendig ist).
  
**clearOrderBook**
  
Jetzt bist du fast am Ziel. Es fehlt nur noch die Methode clearOrderBook. Diese soll folgendes machen:

Die Anzahl der Bestellungen wird wie folgt auf der Konsole ausgegeben: Es⎵können⎵<Anzahl Bestellungen in orderBook>⎵Bestellungen⎵abgearbeitet⎵werden.

Die Bestellungen werden der Reihe nach abgearbeitet. Dabei ist es wichtig, dass so wenig PinguFood wie möglich verschwendet wird. Unsere Saleuine haben bisher leider keine Methode entwickelt, in ihrem Logistikzentrum zu testen, ob PinguFood essbar ist. Daher wird jedes von ihnen erzeugte PinguFood immer an die aktuell abgearbeitete Bestellung übergeben und sie erfahren erst im Nachhinein, ob es zur Erfüllung der Bestellung nutzbar war. Durch diese Methode gehen leider auch essbares, aber von der Bestellung nicht mehr benötigtes PinguFood verloren. Update 27.11. Claudia und Karl-Heinz ist aufgefallen, dass du das Ganze auch so implementieren kannst (und deshalb auch sollst), dass kein essbares PinguFood verloren geht.

Claudia und Karl-Heinz wollen den Mix aus PinguFood immer so groß wie möglich halten, weshalb sie außer bei AmountOrder immer zufälliges PinguFood verschicken. Bei AmountOrder erfüllen sie erst den vollständigen Bedarf an Anchovie, dann an Crustacean und schließlich an Sardine.

Nachdem eine Bestellung abgearbeitet wurde, wird die Information der Bestellung (toString) auf der Konsole ausgegeben.

Vergiss nicht, ungenutztes PinguFood in die Statistik aufzunehmen!

Bei Bestellungen des Typs WeightOrder lässt es sich dabei nicht vermeiden, dass das erwünschte Gewicht (deutlich) überschritten wird, was allerdings einkalkuliert und kein Problem ist.
  
Es gibt vier Methoden zum Erstellen von PinguFood (siehe Hinweis weiter oben). Du solltest alle vier benutzen.

Hinweis: die Zufallszahlen, mit deren Hilfe die gegebenen Methoden PinguFood generieren, werden standardmäßig bei jedem Programmstart in der selben Reihenfolge generiert. Dadurch kannst du dein Programm besser testen. Die Public-Tests und das gegebene Beispiel verwenden die gleiche Reihenfolge, die Private-Tests jedoch andere.
