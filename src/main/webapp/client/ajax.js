
function hello() {
    console.log("hello world");
}

function refresh() {
    setTimeout("refresh();",5000);
}

function select(action) {

    if(action == "billet") {

        var billet = {
            titre: "mon billet",
            contenue: " voici mon contenue",
            auteur: "toto",
            commentaire: ["c1", "c2", "c3", " il fait beau on est en retard"]
        };
        $.ajax({
           //url:"https://192.168.75.13/api/v2/billet",
            url:"http://localhost:8080/groupes/M1IF03",
           type: "GET",
            dataType:"json",
            sucess : function (data, statut) {
                console.log("je suis une " + statut);
                console.log(data);
            },
            error: function (resultat, statut, error) {
                console.log(statut);
            }
        });

        //var output = Mustache.render("titre {{titre}} contenue {{contenue}} auteur {{auteur}} commentaire {{commentaire}}", billet);
        var output_titre = Mustache.render("{{titre}}",billet);
        var output_contenue = Mustache.render("{{contenue}}",billet);
        var output_auteur = Mustache.render("{{auteur}}",billet);
        var output_commentaire = Mustache.render("{{commentaire}}",billet);

        $('#bltTitre').html(output_titre);
        $('#commentList').html(output_commentaire);
        $('#bltContenu').html(output_contenue);
        $('#bltAuteur').html(output_auteur);


    }

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
            },
                {
                    titre: "mon billet 3",
                    contenue: " voici mon contenue 3",
                    auteur: "toto 3",
                    commentaire: ["c1", "c2", "c3", " il fait beau on est en retard"]
                }]
        };
        $.ajax({
            //url:"https://192.168.75.13/api/v2/billet",
            url:"http://localhost:8080/groupes/M1IF03",
            type: "GET",
            dataType:"json",
            sucess : function (data, statut) {
                console.log(data);
            },
            error: function (resultat, statut, error) {
                console.log(error);
            }
        });
        var output = Mustache.render("{{description}}", Groupe);
        $('#grpDesc').html(output);
    }

    var commentaire = {
        auteur:"vercingetorix",
        texte:"j'ai gagne gergovie"
    };


    if(action == "groupes") {
        var Groupes = {
            groupes: ["Arverne", "Etrusque"]
        };
        var output = Mustache.render("Groupes {{groupes}}", Groupes);
        $('#groupesList').html(output);
    }

    var Billets = {
        billets:["b1","b2"]
    };

    var Commentaires = {
        commentaires: ["yolo1", "yolo2"]
    };
    if(action == "users"){
        var Users = {
            users: ["Asterix", "Obelix","Panoramix"]
        };
        var output = Mustache.render("Users {{users}}", Users);
        $('#usersList').html(output);
    }

    if(action == "pseudo") {
        var Pseudo = {
            pseudo: "le roi de la Gaulle"
        };
        var output = Mustache.render("Pseudo {{pseudo}}",Pseudo);
        $('#mu').html(output);
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
