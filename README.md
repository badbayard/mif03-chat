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

#### VM
- http://192.168.75.33:8080/v1/
- http://192.168.75.33:8080/v2/
