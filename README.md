# mif03-chat

tag TP - 1.4
##### question 1.2 Quel est le type de redirection que vous devez employer pour cela ? 
On ne traite pas les données d'un formulaire donc on ne passe pas par un 
doPost, donc on fait un doGet

L'application est fonctionnelle pour cette étape a l'exception d'une 
defaillance pour la deconnexion, quand un utilisateur se déconnecte, le 
prochain utilisateur se vera sur sa page de connexion le dernier billet 
de l'utilisateur precedent.


##### Notre MVC push-based
les Vues se trouvent dans le dossier JSP, le contrôleur est séparé dans plusieurs 
classe java se trouvent dans le package servlets, et le model se trouve dans le package classes, 
le contrôleur est chargé d'affecter le model et d'orienter les vues.

##### Notre MVC
Les Vues se trouvent dans le dossier JSP, le contrôleur est séparé 
dans plusieurs classe java se trouvent dans le package servlets, 
et le model se trouve dans le package classes, le contrôleur est 
chargé d'affecter le model et d'orienter les vues.


##### Gestion du cache
Les ressources statiques sont bien mises en caches lors des rafraichissement,
mais on a un souci avec les ressources dynamiques , notre site n'est requeté qu'avec des 
POST et n'appelle pas les GET. 
La gestion des headers HTTP a été effectué sur la servlet Init.
La gestion des cookies est implémenté mais "casse le site" , car en effet le 
doGet n'est pas appelé en temps normal. Il est implémenté sur les commentaires.

##### REST
Beaucoup de cas d'URL non geré, actuellement les GET sont fonctionnels (normalement) , 
si on crées des utilisateurs via l'application puis on les récuperes :
- http://localhost:8080/Users/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0b3RvIiwiaW5kZXgiOjAsImdyb3VwZSI6InR1dHUifQ.yYxblwbTUUY6eITMGX5ypyH-zPOCG_kAIvF5dRuQriw
- http://localhost:8080/Users/toto/gr

Mais nous avons tout de meme réussi à implémenter : 
- quelque chose qui ressemble a du REST (nous ne gerons que les GET et les POST)
- connexion stateless
- JWT (en partie)

BEAUCOUP de fonctionnalitées sont encore totalement buggées (notamment les commentaires).
Nous avons fais au mieux et ce TP sera surement poursuivi d'ici le TP5. 
(to be continued ...)

##### Programmation côté client
Notre client dispose de différents fichier qui sont:
- ajax.js
- index.html
- jquery-3.4.1.min.js
- mustache.js
- time.jsp

l'interface du client se trouve dans index.html, et le code JS pour lancer et traiter le ajax se trouve dans le fichier
ajax.js, le client dispose de feuille de style bootstrap.
Elle se compose d'un menu avec différents éléments (accueil, groupes, groupe, billet, utilisateurs,déconnexion).
- Accueil : on trouve un champ pour enregistrer le pseudo qui l'ajoute avec une requete ajax sur la vm
- Groupes : si un utilisateur est connecté , il permet de lister les groupes pour notre utilisateur , on peux egalement créer un nouveau groupe.
- Groupe : avant de cliquer sur groupe il faut etre connecté, permet de créer ou récuperer un billet.
On récupere le billet en laissant les champs du formulaire vide.
- Billet : permet d'ajouter un commentaire et de voir la liste des commentaires.
- Utilisateur : permet d'afficher la liste des utilisateurs.
- Déconnexion: permet de déconnecter l'utilisateur en cours.


###### cas d'utilisation
pour l'utilisation il faut d'abord créer un utilisateur ---> créer un groupe ---> créer un billet
quand on a créer un utilisateur on peut ensuite faire les GET pour avoir la liste de groupes ou utilisateur.

Le code de l'horloge se trouve dans ajax.js, mais n'est pas lancé.

##### VM
- http://192.168.75.33:8080/v1/
- https://192.168.75.33/api/v1/
- http://192.168.75.33:8080/v2/
- https://192.168.75.33/api/v2/
- http://192.168.75.33:8080/v3/
- https://192.168.75.33/api/v3/ (ouvre sur le index classique et pas sur le client)
- http://192.168.75.33:8080/v4/ (second rendu du Tp client)
- https://192.168.75.33/api/v4

pour accéder au client :

- https://192.168.75.33/api/v3/client/index.html (page blanche on ne peut pas se balader dans le client en https)
- https://192.168.75.33/api/v4/client/index.html

ou 

- http://192.168.75.33:8080/v3//client/index.html (marche et vas sur le index.html du client)
- http://192.168.75.33:8080/v4//client/index.html

nginx (redirige sur le client pour les requetes AJAX)
- https://192.168.75.33/


#### performance

##### en local sur tomcat
###### application sans optmisaion

|  HTML      |    App shell    |    CRP |
| ------------- |: -------------: | ---------: |
| 46,7399291992| 390,9899291992 |398,4899291992 |


###### avec l'attribut async

|  HTML      |    App shell    |    CRP |
| ------------- |: -------------: | ---------: |
| 42,5799560547| 357,3299560547 |364,3299560547 |
| 9% -> amélioration |9% -> amélioration |9% -> amélioration |

##### avec minimisation des ressources critiques 

|  HTML      |    App shell    |    CRP |
| ------------- |: -------------: | ---------: |
| 45,7274780273| 408,4774780273 |416,2274780273 |
| -7% |-14%|-14%|

##### sur la VM avec nginx
###### application sans optmisaion
Pour ces tests on passe par le VPN de l'université.

|  HTML      |    App shell    |    CRP |
| ------------- |: -------------: | ---------: |
| 80,5406494141 | 517,0406494141 |523,7906494141|

###### avec l'attribut async
|  HTML      |    App shell    |    CRP |
| ------------- |: -------------: | ---------: |
| 77,7641601563 | 498,0141601563|506,5141601563|
|4% -> amélioration |4% -> amélioration|4% -> amélioration|

###### avec minimisation des ressources critiques
|  HTML      |    App shell    |    CRP |
| ------------- |: -------------: | ---------: |
| 61,0983276367| 462,0983276367|471,0983276367|
|21% -> amélioration|8% -> amélioration|7% -> amélioration|



