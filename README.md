Zielsetzung
Das Ziel ist es ein REST – Interface zu implementieren, Bei den möglichen Frameworks stehen .NET(C#) oder Java zur Auswahl. Dabei sind die folgenden Anforderungen zu erfüllen:
•	Es soll möglich sein, Personen und ihre Lieblingsfarbe über das Interface zu verwalten
•	Die Daten sollen aus einer CSV Datei lesbar sein, ohne dass die CSV angepasst werden muss
•	Alle Personen mit exakten Lieblingsfarben können über das Interface identifiziert werden
Einige Beispieldatensätze finden sich in sample-input.csv. Die Zahlen der ersten Spalte sollen den folgenden Farben entsprechen:
ID	Farbe
1	blau
2	grün
3	violett
4	rot
5	gelb
6	türkis
7	weiß
Das Ausgabeformat der Daten ist als application/json festgelegt. Die Schnittstelle soll folgende Endpunkte anbieten:
GET /persons
[{
"id" : 1,
"name" : "Hans",
"lastname": "Müller",
"zipcode" : "67742",
"city" : "Lauterecken",
"color" : "blau"
},{
"id" : 2,
...
}]
GET /persons/{id}
Hinweis: als ID kann hier die Zeilennummer verwendet werden.
{
"id" : 1,
"name" : "Hans",
"lastname": "Müller",
"zipcode" : "67742",
"city" : "Lauterecken",
"color" : "blau"
}
GET /persons/color/{color}
[{
"id" : 1,
"name" : "Hans",
"lastname": "Müller",
"zipcode" : "67742",
"city" : "Lauterecken",
"color" : "blau"
},{
"id" : 2,
...
}]
Akzeptanzkriterien
1.	Die CSV Datei wurde eingelesen, und wird programmintern durch eine dem Schema entsprechende Modellklasse repräsentiert.
2.	Der Zugriff auf die Datensätze so abstrahiert, dass eine andere Datenquelle angebunden werden kann, ohne den Aufruf anpassen zu müssen.
3.	Die oben beschriebene REST-Schnittstelle wurde implementiert und liefert die korrekten Antworten.
4.	Der Zugriff auf die Datensätze, bzw. auf die zugreifende Klasse wird über Dependency Injection gehandhabt.
5.	Die REST-Schnittstelle ist mit Unit-Tests getestet.
6.	Die sample-input.csv wurde nicht verändert
Bonuspunkte
•	Implementierung als MSBuild Projekt für kontinuierliche Integration auf TFS (C#/.NET) oder als Maven/Gradle Projekt (Java)
•	Implementieren Sie eine zusätzliche Methode POST/ Personen, die eine zusätzliche Aufzeichnung zur Datenquelle hinzufügen
•	Anbindung einer zweiten Datenquelle (z.B. Datenbank via Entity Framework)
--------------------------------------------------------------------------------------------------------------------------------------------------

Ich muss für Sie Tipps zum Projekt schreiben:


1- Das Projekt ist im Maven-Format geschrieben und kann mithilfe von Maven-Skripten ausgeführt werden.


2- Das Projekt wird in zwei Modi unter Verwendung der Oracle-Datenbank und Ihrer Datei geschrieben. Natürlich denke ich, dass aufgrund der Variabilität der Parameter eine weitere Datenbank hinzugefügt werden kann und dies möglicherweise kein Problem darstellt. Ich habe dieses Problem nicht überprüft und entschuldige mich dafür, weil ich so beschäftigt bin.

3- Sie können die Projektparameter in der config.properties im folgenden Pfad ändern und so das Projektverhalten ändern.

          assecorAssessmentProject\src\main\resources\config.properties

4- Es ist im Skriptprojekt enthalten, das sich auf Oracle-Datenbanktabellen bezieht.

5- Anweisungen zum Starten des Projekts in Form der Arbeit mit der File:

a- Ändern Sie den Parameter config.properties in file
b-  mvn clean                 Zum Reinigen aller vorherigen Informationen
c-  mvn site                    Zum Erstellen von java doc
d-  mvn package           Zum Erstellen  target und war 




6- Anweisungen zum Starten des Projekts in Form der Arbeit mit Oracle Data Base:

a- Ändern Sie den Parameter config.properties in oracle.

b- mvn install:install-file
 –Dfile=D:/JavaEE/assecorAssessmentProject/src/main/webapp/lib/ojdbc7.jar 
-DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0 -Dpackaging=jar

D:/JavaEE/assecorAssessmentProject/src/main/webapp/lib/ojdbc7.jar  :  Projekt path

c-  mvn clean                 Zum Reinigen aller vorherigen Informationen
d-  mvn site                    Zum Erstellen von java doc
e-  mvn package             Zum Erstellen  target und war 

