#!/bin/bash

# Farben für die Ausgabe
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # Kein Farbschema

# Spring Boot starten
echo -e "${GREEN}Starte Spring Boot Anwendung...${NC}"
(cd demo && mvn spring-boot:run) &
SPRING_PID=$!
echo -e "${GREEN}Spring Boot läuft (PID: $SPRING_PID)${NC}"

# Angular starten
echo -e "${GREEN}Starte Angular Anwendung...${NC}"
(cd serviceportalworkspace-02_inventory && ng serve Firefly) &
ANGULAR_PID=$!
echo -e "${GREEN}Angular läuft (PID: $ANGULAR_PID)${NC}"

# Exit-Handler bei Beendigung des Skripts
trap "echo -e '${RED}Beende Anwendungen...${NC}'; kill $SPRING_PID $ANGULAR_PID; exit" INT

# Warten, bis die Prozesse beendet werden
wait