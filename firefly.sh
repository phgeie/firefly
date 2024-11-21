#!/bin/bash

# Farben für die Ausgabe
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # Kein Farbschema

echo -e "${GREEN}Starte Firefly Build und Start Skript...${NC}"

# Schritt 1: Maven Build für Spring Boot
echo -e "${GREEN}Baue Spring Boot Anwendung mit Maven...${NC}"
(cd demo && mvn clean install) || { echo -e "${RED}Maven Build fehlgeschlagen.${NC}"; exit 1; }

# Schritt 2: Angular-Abhängigkeiten installieren
echo -e "${GREEN}Installiere Angular-Abhängigkeiten...${NC}"
(cd serviceportalworkspace-02_inventory && npm install && npm install --save-dev @angular-devkit/build-angular) || { echo -e "${RED}NPM Install fehlgeschlagen.${NC}"; exit 1; }

# Schritt 3: Spring Boot Anwendung starten
echo -e "${GREEN}Starte Spring Boot Anwendung...${NC}"
(cd demo && mvn spring-boot:run) &
SPRING_PID=$!
echo -e "${GREEN}Spring Boot läuft (PID: $SPRING_PID).${NC}"

# Schritt 4: Angular-Anwendung starten
echo -e "${GREEN}Starte Angular-Anwendung...${NC}"
(cd serviceportalworkspace-02_inventory && ng serve Firefly) &
ANGULAR_PID=$!
echo -e "${GREEN}Angular läuft (PID: $ANGULAR_PID).${NC}"

# Exit-Handler bei Beendigung des Skripts
trap "echo -e '${RED}Beende Anwendungen...${NC}'; kill $SPRING_PID $ANGULAR_PID; exit" INT

# Warten, bis die Prozesse beendet werden
wait
