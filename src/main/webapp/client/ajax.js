var token;
var name_groupe="";
function hello() {
    console.log("hello world");
}

function refresh() {
    setTimeout("refresh();",5000);
}

function gettoken() {
    $.ajax({
        url:"https://192.168.75.13/api/v2/users/login",
        type: "POST",
        contentType:"application/json",
        headers: {
            "Accept":"application/json",
            'Authorization': token,
        },
        data: "{ \"pseudo\" : \"toto\" }",
        sucess : function (data,response) {
        },
        error: function (resultat, statut, error) {
        }

    }).done(function (data,response , head) {
        token = head.getResponseHeader("Authorization");
    });
}
//gettoken();

function select(action) {

    if(action == "groupe") {

        var Groupe = {
            nom: "Jesus",
            description: "je suis nee a noel",
            proprietaire: "moi",
            membres: ["jean", "pierre", "juda", "paul"],
            billets: [{
                titre: "mon billet 2",
                contenue: " voici mon contenue2",
                auteur: "toto 2",
                commentaire: ["c1", "c2", "c3", " il fait beau on est en retard"]
            }, {
                    titre: "mon billet 3",
                    contenue: " voici mon contenue 3",
                    auteur: "toto 3",
                    commentaire: ["c1", "c2", "c3", " il fait beau on est en retard"]
                }],
        };

        /*
        $.ajax({
            url:"https://192.168.75.13/api/v2/groupes/toto",
            type: "POST",
            contentType:"application/json",
            headers: {
                "Accept":"application/json",
                'Authorization': token,
            },
            data: "{ \"pseudo\" : \""+pseudo+"\" }",
            error: function (resultat, statut, error) {
            }
        }).done(function (data,response , head) {
            console.log("ok POST groupe")
        });
        */
        var output = Mustache.render("{{description}}", Groupe);
        var output_billets = Mustache.render( "{{#billets}}" + "<cite contenteditable=\"true\"> {{titre}} <cite> <br/> {{/billets}}"  ,Groupe);
        $('#grpDesc').html(output);
        $('#bltList').html(output_billets);



    }

    var commentaire = {
        auteur:"vercingetorix",
        texte:"j'ai gagne gergovie"
    };



    if(action == "groupes") {
        name_groupe = null;
        name_groupe =  $('#groupe').val();
        var desc = $('#desc').val();
        $.ajax({
            url:"https://192.168.75.13/api/v2/groupes",
            type: "GET",
            headers: {
                "Accept":"application/json",
            },
            sucess : function (data) {
                console.log("j'ai reussi");
                console.log("voici la data " + data);
            },
            error: function (resultat, statut, error) {
                console.log("token : " + token)
                console.log(statut);
            }
        }).done(function (data,response , head) {
            console.log("Get groupe OK")
            console.log(response);
            console.log("data" + data);
            var Groupes = {
                groupes: data
            };
            var output_groupes = Mustache.render("Groupes : " +
                "<li class=\"list-group-item\">"+"{{#groupes}} " + "<br/> {{.}} " + "{{/groupes}}" + "</li>", Groupes);
            $('#groupesList').html(output_groupes);
        });

        if(name_groupe != ""){
            $.ajax({
                url:"https://192.168.75.13/api/v2/groupes",
                type: "POST",
                contentType:"application/json",
                headers: {
                    "Accept":"application/json",
                    'Authorization': token,
                },
                data: "{ \"nom\" : \""+name_groupe+"\" " + ", \"description\" : \""+desc+"\" }",
                error: function (resultat, statut, error) {
                }
            }).done(function (data,response , head) {
                console.log("ok POST groupe")
            });
        }
    }

    var Billets = {
        billets: ["b1", "b2"]
    };

    var Commentaires = {
        commentaires: ["yolo1", "yolo2"]
    };

    if(action == "users"){
        $.ajax({
            url:"https://192.168.75.13/api/v2/users",
            type: "GET",
            headers: {
                "Accept":"application/json",
                "Authorization": token
            },

            error: function (resultat, statut, error) {
                console.log("token : " + token)
                console.log(statut);
            }
            }).done(function (data,response , head) {
                console.log("Get user OK")
                console.log(response);
                console.log("data" + data);
                var Users = {
                    users: data
                };
                var output = Mustache.render("{{#users}} " + "<br/> {{.}} " + "{{/users}}", Users);
                $('#usersList').html(output);
            });
    }



    if(action == "pseudo") {

        var pseudo =  $('#pseudo').val();
        $.ajax({
            url:"https://192.168.75.13/api/v2/users/login",
            type: "POST",
            contentType:"application/json",
            headers: {
                "Accept":"application/json",
                'Authorization': token,
            },
            data: "{ \"pseudo\" : \""+pseudo+"\" }",
            error: function (resultat, statut, error) {
            }

        }).done(function (data,response , head) {
            token = head.getResponseHeader("Authorization");
            var Pseudo = {
                pseudo: pseudo
            };
            var output = Mustache.render("Pseudo {{pseudo}}",Pseudo);
            $('#mu').html(output);
        });

    }



    if(action == "billet") {
        console.log(name_groupe);
        var billet = {
            titre: "mon billet",
            contenue: " voici mon contenue",
            auteur: "toto",
            commentaire: ["c1", "c2", "c3", " il fait beau on est en retard"]
        };

        $.ajax({
            url:"https://192.168.75.13/api/v2/groupes/"+name_groupe+"/billets",
            type:"GET",
            contentType:"application/json",
            headers: {
                "Accept":"application/json",
                'Authorization': token,
            },
            error: function (resultat, statut, error) {
                console.log(statut);
            }
        }).done(function (data,response , head) {
            console.log(head.getResponseHeader("Authorization"));
            token = head.getResponseHeader("Authorization");
        });


        /*
        $.ajax({
            url:"https://192.168.75.13/api/v2/users/login",
            type: "POST",
            contentType:"application/json",
            headers: {
                "Accept":"application/json",
                'Authorization': token,
                //"Authorization": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b3RvIiwiYXVkIjoiaHR0cHM6Ly8xOTIuMTY4Ljc1LjEzL2FwaS92MiIsImNvciI6IkNvcnJlY3Rpb24iLCJpc3MiOiJNZXMgQ29wYWlucyIsImV4cCI6MTU3NDg3NDk4NH0.-WrGFuY-9BlSe6HuGIbmW-zmgXH7D7kEu1lMDjaIc_k"
                //mettre autho pour get les autres sinon on peut recup que les groupes


            },
            data: "{ \"pseudo\" : \"toto\" }",
            error: function (resultat, statut, error) {
                console.log("je suis la");
                console.log(statut);
            }

        }).done(function (data,response , head) {
            console.log(head.getResponseHeader("Authorization"));
            token = head.getResponseHeader("Authorization");
        });



        $.ajax({
           url:"https://192.168.75.13/api/v2/groupes/toto",
           type: "GET",
            headers: {
               "Accept":"application/json",
                //'Authorization': token,
                //"Authorization": token,
                beforeSend: function(request) {
                    request.setRequestHeader("Authorization", token);
                },
                //mettre autho pour get les autres sinon on peut recup que les groupes


            },
            error: function (resultat, statut, error) {
                console.log("token : " + token)
                console.log(statut);
            }
        }).done(function (data,response , head) {
            console.log("ok dans le Get")
        });


        //var output = Mustache.render("titre {{titre}} contenue {{contenue}} auteur {{auteur}} commentaire {{commentaire}}", billet);
        var output_titre = Mustache.render("{{titre}}",billet);
        var output_contenue = Mustache.render("{{contenue}}",billet);
        var output_auteur = Mustache.render("{{auteur}}",billet);
        //var output_commentaire = Mustache.render("{{commentaire}}",billet);
        var output_commentaire = Mustache.render("{{#commentaire}} " + "<br/> {{.}} " + "{{/commentaire}}", billet);

        $('#bltTitre').html(output_titre);
        $('#commentList').html(output_commentaire);
        $('#bltContenu').html(output_contenue);
        $('#bltAuteur').html(output_auteur);
        */
    }

    if(action == "commentaire"){
        console.log("je suis dans le if de commentaire");
        var commentaire =  $('#commentaire').val();
        console.log("contenue du commentaire : "+commentaire);
    }
}










// -----------------------------------  horloge------------------------------------------

//Bilblioth�que de fonctions AJAX permettant l'envoi de requ�tes au serveur de mani�re asynchrone
//et le traitement (ajout au corpos du document appelant) de r�ponses en XML conformes � la structure d�crite dans l'�nonc� du devoir.

//--------------------Fonctions principales---------------------

//fonction principale, qui envoie la  requ�te au serveur de fa�on asynchrone et positionne la fonction qui va traiter les donn�es
function loadXMLAsynchroneously(method, request, parameters, id)
{
    //initialisation de l'objet XMLXhttpRequest
    var xhr = initRequete ();

    //sp�cification de la fonction de traitement � appeler au retour serveur (car chargement asynchrone)
    var XMLDoc = null;
    xhr.onreadystatechange = function() { getXMLDocument(xhr, XMLDoc, id); };

    //envoi de la requ�te de chargement du fichier XML au serveur
    //le dernier param�tre est true ; le chargement du fichier se fera en asynchrone
    xhr.open(method, request, true);
    //encodage des param�tres dans la requ�te, si la m�thode est post
    if(parameters && (method == "post" || method == "POST"))
        xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    xhr.send(parameters);
}

//autre fonction principale, plus simple, qui envoie la requ�te au serveur de fa�on asynchrone et ne se pr�occupe pas de la r�ponse
//remarque : l'utilisation de cette fonction n'est pas n�cessaire pour r�aliser le devoir.
function sendRequestAsynchroneously(method, request, parameters)
{
    //initialisation de l'objet XMLXhttpRequest
    var xhr = initRequete ();

    //envoi de la requ�te de chargement du fichier XML au serveur
    //le dernier param�tre est true ; le chargement du fichier se fera en asynchrone
    xhr.open(method, request, true);
    //encodage des param�tres dans la requ�te, si la m�thode est post
    if(parameters && (method == "post" || method == "POST"))
        xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    xhr.send(parameters);
}

//--------------------------fonctions secondaires---------------------------

//fonction appel�e lors de la r�ception de la r�ponse, si la fonction principale loadXMLAsynchroneously() a �t� utilis�e.
function getXMLDocument(xhr, XMLDoc, id)
{
    // teste si la r�ponse est disponible
    if (xhr.readyState==4) {
        // teste si la r�ponse est arriv�e et contient des donn�es (code HTTP = 200 : OK)
        if (xhr.status == 200) {
            // teste si la r�ponse est arriv�e en XML ou en texte (peut arriver pour certaines configurations d'Apache)
            if (xhr.responseXML != null) {
                XMLDoc= xhr.responseXML;
            } else if (xhr.responseText != null) {
                //si la r�ponse est en texte, transformation en XML (voir fonction fa�ade plus bas)
                XMLDoc= parseFromString(xhr.responseText);
            }
            //D�commentez la ligne suivante pour voir le contenu XML obtenu (ne marche qu'avec FF)
            //alert((new XMLSerializer()).serializeToString(XMLDoc));

            //appel de la fonction de traitement qui va ajouter les donn�es au corps de la page (� �crire)
            traiteXML (XMLDoc, id);

            //teste si le code de statut est autre que le code renvoy� en cas d'absence de nouveaux messages.
            //Remarque : le code 1223 provient d'un bug avec IE : http://trac.dojotoolkit.org/ticket/2418
        } else if (xhr.status >= 400 && xhr.status != 1223) {
            alert("Un probl�me est survenu avec la requ�te : ");
        }
    }
}

//----------------------Fonctions fa�ades----------------------------
//permettent de masquer les diff�rences entre les navigateurs
//remarque : ces fonctions ont uniquement �t� test�es avec FF et IE7

//fonction fa�ade qui teste le type de navigateur et renvoie un objet capable de se charger de l'envoi de la requ�te.
function initRequete()
{
    var xhr = null;
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    }
    else if (window.ActiveXObject)
    {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xhr;
}

//fonction fa�ade qui re�oit une cha�ne de caract�res et la parse en XML pour renvoyer un objet DOM.
//remarque : le XML doit �tre bien form�, sans quoi l'erreur de parsing bloque l'ex�cution du script.
function parseFromString (docTXT) {
    // code for IE
    if (window.ActiveXObject)
    {
        var XMLDoc=new ActiveXObject("Microsoft.XMLDOM");
        XMLDoc.async="false";
        XMLDoc.loadXML(docTXT);
    }
    // code for Mozilla, Firefox, Opera, etc.
    else
    {
        var parser=new DOMParser();
        var XMLDoc=parser.parseFromString(docTXT,"text/xml");
    }
    return XMLDoc;
}

//fonction fa�ade qui renvoie le texte contenu dans un �l�ment XML
function getXMLTextContent(source)
{
    if (source.textContent != null) {
        //Gecko
        return source.textContent;
    } else {
        //IE
        return source.text;
    }
}
function traiteXML(_XMLDoc, _id)
{
    //var result = $(_XMLDoc).find("h").text();
    /*
    var heure = _XMLDoc.getElementsByTagName("t").toString();
    console.log(heure);
    document.getElementById(_id).innerHTML = "<p>"+heure[1] + "</p>";
    */
    /*
    var t = document.createTextNode(_XMLDoc.toString());
    document.getElementById(_id).innerHTML = t;
    */
    var h = _XMLDoc.getElementsByTagName("h")[0].childNodes[0].nodeValue;
    var m = _XMLDoc.getElementsByTagName("m")[0].childNodes[0].nodeValue;
    var s = _XMLDoc.getElementsByTagName("s")[0].childNodes[0].nodeValue;

    //var h = _XMLDoc.
    console.log(h);
    document.getElementById(_id).innerHTML = "<p>"+ "heure =" +h +"minute = "+ m +"seconde = " + s + "</p>";
    var xsltprocessor = new XSLTProcessor();
    transform(_XMLDoc,xsltprocessor,_id);
    //window.location.reload();
    /*
    var xsltprocessor = new XSLTProcessor();
    var myXMLHTTPRequest = new XMLHttpRequest();
    var xslRef = myXMLHTTPRequest.responseXML;
    xsltprocessor.importStylesheet(xslRef);
    */
    /*
    for(prop in h){

        console.log(prop);
    }
    */
}
function transform(XMLDoc, XSLDoc, id) {
    if(XMLDoc == null || XSLDoc == null){return;}
    try{
        if(window.ActiveXObject){
            var target = document.getElementById(id);
            target.innerHTML = traiteXML.transformNode(xsl);
        }else if(window.XSLTProcessor) {
            var fragment;
            var xsltProcessor = new XSLTProcessor();
            xsltProcessor.importStylesheet(xsl);
            fragment = xsltProcessor.transformToFragment(xml,document);
            var target = document.getElementById(id);
            target.appendChild(fragment);
        }
    }catch(e){
        return e;
    }

}
