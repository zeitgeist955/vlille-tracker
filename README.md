Info
Dataset : https://opendata.lillemetropole.fr/explore/dataset/vlille-realtime/api/?disjunctive.libelle&disjunctive.nom&disjunctive.etat
URL : https://opendata.lillemetropole.fr/api/explore/v2.1/catalog/datasets/vlille-realtime/records?limit=20

TODO
1) ping de l'url du dataset -> à déclencher périodiquement
2) OK Ajouter un producer kafka à ce programme
3) OK Créer un topic kafka pour réceptionner les données
4) OK Envoyer le résultat du ping via le producer au topic kafka : un message par station
5) Dans un programme séparé : créer un consumer kafka qui parcours les messages du topic 
6) Dans l'autre programme : màj les dispo suivant les messages consommés