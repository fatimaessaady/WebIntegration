TP4 - Web Services SOAP





Fatima Essaady



\---
📋 Description

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


<img width="1919" height="606" alt="serveur" src="https://github.com/user-attachments/assets/151bcd75-5848-4fe1-88cd-87f98fed4d9e" />





\*Le serveur démarre et attend les connexions des clients.\*



 2. Client Java

Le client envoie des requêtes SOAP au serveur et affiche les résultats.


<img width="1222" height="274" alt="client" src="https://github.com/user-attachments/assets/884716e6-6653-4fc8-b628-6e12c45960c4" />




\*Le client teste les 4 opérations et la division par zéro.



\---



✅ Résultats



| Opération | Entrées | Résultat |

|-----------|---------|----------|

| Addition | 15 + 25 | 40.0 |

| Soustraction | 50 - 20 | 30.0 |

| Multiplication | 12 × 8 | 96.0 |

| Division | 100 ÷ 4 | 25.0 |

| Division par zéro | 10 ÷ 0 | ❌ Erreur HTTP 500 |



\---



 Technologies utilisées

\- \*\*Java 23\*\* - Langage de programmation

\- \*\*JAX-WS\*\* - API pour les Web Services SOAP

\- \*\*Maven\*\* - Gestion de projet

\- \*\*SoapUI\*\* - Test du service

\- \*\*Git/GitHub\*\* - Versionnement



\---


 Partie 2: Banque SOAP

🖥️ Serveur Banque
Le serveur écoute sur le port 9001 et expose le WSDL.

<img width="1918" height="771" alt="Screenshot 2026-03-24 232000" src="https://github.com/user-attachments/assets/36050aa7-817c-4251-a992-2cc534beefb1" />


Le fichier WSDL du service Banque avec les opérations: consulterSolde, retirer, deposer, getCompte.

✅ Client Banque
Test des opérations bancaires sur les comptes.
<img width="964" height="229" alt="Screenshot 2026-03-24 232144" src="https://github.com/user-attachments/assets/ce86c630-120d-458d-8c52-9c91db833d49" />


 Partie 3: ElectroShop SOAP

🖥️ Serveur ElectroShop
Le serveur écoute sur le port 9002 et expose le WSDL avec les opérations:
- ajouterVente - Ajouter une vente
- consulterCA - Calculer le chiffre d'affaires
- topProduits - Retourner les meilleurs produits
- predireVentes - Prédiction par régression linéaire

<img width="1907" height="536" alt="Screenshot 2026-03-24 234127" src="https://github.com/user-attachments/assets/5ffd82ce-a10f-42f9-8f90-2e78c31d48af" />


Serveur démarré avec 10 ventes de test initialisées.

 Client ElectroShop
Test des fonctionnalités analytiques.

<img width="1466" height="233" alt="image" src="https://github.com/user-attachments/assets/e6556403-8f5b-4491-b2cb-bfe645131dae" />


 Comment exécuter



Démarrer le serveur

```bash

cd TP4/calculatrice-soap

mvn exec:java -Dexec.mainClass="org.example.ws.ServeurSOAP"






