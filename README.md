\# TP4 - Web Services SOAP



\## 👩‍💻 Auteur

Fatima Essaady



\---



\## 📋 Description

Ce projet implémente un \*\*Web Service SOAP\*\* de calculatrice avec les opérations arithmétiques de base:

\- Addition ➕

\- Soustraction ➖

\- Multiplication ✖️

\- Division ➗ (avec gestion de l'erreur division par zéro)



L'architecture suit le modèle \*\*client/serveur\*\* avec \*\*JAX-WS\*\*.



\---



\## 🖥️ Exécution



\### 1. Serveur SOAP

Le serveur écoute sur le port 9000 et expose le WSDL.



!\[Serveur SOAP](screenshots/serveur.png)



\*Le serveur démarre et attend les connexions des clients.\*



\### 2. Client Java

Le client envoie des requêtes SOAP au serveur et affiche les résultats.



!\[Client SOAP](screenshots/client.png)



\*Le client teste les 4 opérations et la division par zéro.\*



\---



\## ✅ Résultats



| Opération | Entrées | Résultat |

|-----------|---------|----------|

| Addition | 15 + 25 | 40.0 |

| Soustraction | 50 - 20 | 30.0 |

| Multiplication | 12 × 8 | 96.0 |

| Division | 100 ÷ 4 | 25.0 |

| Division par zéro | 10 ÷ 0 | ❌ Erreur HTTP 500 |



\---



\## 🛠️ Technologies utilisées

\- \*\*Java 23\*\* - Langage de programmation

\- \*\*JAX-WS\*\* - API pour les Web Services SOAP

\- \*\*Maven\*\* - Gestion de projet

\- \*\*SoapUI\*\* - Test du service

\- \*\*Git/GitHub\*\* - Versionnement



\---



\## 🚀 Comment exécuter



\### Démarrer le serveur

```bash

cd TP4/calculatrice-soap

mvn exec:java -Dexec.mainClass="org.example.ws.ServeurSOAP"

