# Haltestellenanzeige
------------------------------------
Aufgabenstellung

Inhalt
Auf einer digitalen Anzeigetafel werden die nächsten Verbindungen 
angezeigt. Jede Verbindung stellt die Liniennummer, das Fahrtziel und 
die Zeit bis zur Abfahrt dar.

  Die Anzeigetafel zeigt den Namen der Haltestelle, das Logo des 
  Verkehrsunternehmens oder -verbunds, die aktuelle Zeit, den Bahnsteig 
  bzw. die Abfahrtsplattform und stellt Zusatzinformationen wie bspw. 
  eine Verzögerung oder Unterbrechung mit gesonderter, farbiger 
  Hervorhebung dar.

  Die Abfahrtszeiten für die Haltestelle stehen als strukturierte Daten
  über eine SQL-Datenbank bereit. Verzögerungen und Zusatzinformationen
  stehen ebenfalls darüber bereit.

Komponenten für die technische Umsetzung
DBMS:         PostgreSQL
Anzeigetafel: Webbrowser im Kiosk-Modus
Steuerung:    Webserver mit Skripten zur Darstellung (HTML/CSS)

Team 1: Bereitstellung der Daten (Fahrplan und Verzögerungen)
Team 2: Darstellung der Anzeige
Team 3: Koordinator zwischen Team 1 und 2, Projektleitung und
Ansprechpartner zum Auftraggeber
