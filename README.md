## VLille-Tracker-Producer
Alimente un topic Kafka avec les informations de disponibilité des VLille depuis un dataset fourni par ilevia

## Info
Dataset : https://opendata.lillemetropole.fr/explore/dataset/vlille-realtime/api/?disjunctive.libelle&disjunctive.nom&disjunctive.etat
URL : https://opendata.lillemetropole.fr/api/explore/v2.1/catalog/datasets/vlille-realtime/records?limit=20

## Etapes du projet
1) Récupère les informations du dataset à chaque mise à jour de celui ci (hardcodé, toute les minutes)  
-> En plusieurs ping car un plafond de station récupéré par ping à l'API (100 actuellement pour 289 stations)
2) Transforme les infos de chaque station en message Kafka, au format JSON
3) Publie ces messages dans un topic Kafka qui tourne dans une image docker en local