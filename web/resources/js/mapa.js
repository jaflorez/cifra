var map, toolbar, symbol, geomTask, configOptions, graphic, symbol, CapaProyectos;
var urlproy = "https://hermes.invias.gov.co/arcgis/rest/services/Proyectos_Planeacion/Proyectos/FeatureServer/0";
var idProyecto = ""; /*Eliminar los valores*/
var idTramo = "";
var fields = ["cod_proy", "cod_tram", "OBJECTID"];

require([
    "dojo/_base/lang", "dojo/dom", "dojo/html", "dojo/_base/json", "esri/layers/FeatureLayer", "esri/tasks/query",
    "esri/map",
    "esri/arcgis/utils",
    "esri/toolbars/draw",
    "esri/graphic",
    "esri/symbols/SimpleMarkerSymbol",
    "esri/symbols/SimpleLineSymbol",
    "esri/symbols/SimpleFillSymbol",
    "dojo/parser", "dijit/registry",
    "esri/Color",
    "dijit/layout/BorderContainer", "dijit/layout/ContentPane",
    "dijit/form/Button", "dijit/WidgetSet", "dojo/domReady!"
], function (lang, dom, html, json, FeatureLayer, Query,
        Map, utils, Draw, Graphic,
        SimpleMarkerSymbol, SimpleLineSymbol, SimpleFillSymbol,
        parser, registry, Color
        ) {
    parser.parse();

    /* map = new Map("map", {
     basemap: "streets",
     center: [-15.469, 36.428],
     zoom: 3
     });*/

    configOptions = {
        //webmap: "4ca69e18d4164252843c445f68ee09e1",
        webmap: "0a6cc047d073417cae6f251c222f5c6a",
        title: "",
        subtitle: "",
        //www.arcgis.com sharing url is used. modify this if yours is different
        sharingurl: "https://www.arcgis.com/sharing/rest/content/items"
    };
    var mapDeferred = esri.arcgis.utils.createMap(configOptions.webmap, "map");
    mapDeferred.then(function (response) {
        map = response.map;


        //map.on("load", createToolbar);

        if (map.loaded) {
            createToolbar(map);

        } else {
            dojo.connect(map, "onLoad", createToolbar);


        }

        map.on("extent-change", changeScale);
        map.graphics.clear();
        if (document.getElementById("form_mapa:contrato_id").value !== "") {
            idProyecto = document.getElementById("form_mapa:contrato_id").value;
            idTramo = document.getElementById("form_mapa:tramo_id").value;

        }
        consultar();
        // loop through all dijits, connect onClick event
        // listeners for buttons to activate drawing tools
        registry.forEach(function (d) {
            // d is a reference to a dijit
            // could be a layout container or a button
            if (d.id === "drbutton") {
                d.on("click", lang.hitch(this, activateTool));
            }
            if (d.id === "Borrarbutton") {
                d.on("click", lang.hitch(this, borrarMapa));
            }
        });
    });
    function changeScale() {
        html.set(dom.byId("EscLabel"), "Escala 1 : " + map.getScale().toFixed(3));

    }
    function consultar() {
        //alert ("se va hacer la consulta");
        CapaProyectos = new FeatureLayer(urlproy, {outFields: fields});
        console.log(CapaProyectos);
        CapaProyectos.setEditable(true);
        var sqlExpression = "cod_proy = '" + idProyecto + "' and cod_tram='" + idTramo + "'";
        console.log(sqlExpression);
        var queryParams = new Query();
        queryParams.outFields = fields;
        queryParams.where = sqlExpression;
        CapaProyectos.queryFeatures(queryParams, getStats, errback);
    }
    function getStats(results) {
        if (results.features.length > 0)
        {
            var stats = results.features[0].geometry;
            symbol = new SimpleLineSymbol();
            graphic = new Graphic(stats, symbol);
            graphic.setAttributes(results.features[0].attributes);
            map.graphics.add(graphic);
            var extend = results.features[0].geometry.getExtent().expand(5.0);
            map.setExtent(extend);
        } else
        {
            console.log("No hay nada");
        }


    }

    function errback(err) {
        console.log("Couldn't retrieve summary statistics. ", err);
    }
    function activateTool() {
        if (map.graphics.graphics.length === 0)
        {
            var tool = dom.byId("drbutton").innerText;
            //label.toUpperCase().replace(/ /g, "_");
            var currentScale = this.map.getScale();
            if (tool == "Linea" && currentScale <= 50000) {
                toolbar.activate(Draw["POLYLINE"]);
                map.hideZoomSlider();
            } else {
                alert("a esta escala 1:" + currentScale + "No se permite hacer el dibujo, debe ser una escala mayor a 1:5000");
            }
        } else
        {
            alert("Debe borrar la geometria existente antes de poder dibujar una nueva");
        }
    }

    function borrarMapa() {
        CapaProyectos.applyEdits(null, null, [map.graphics.graphics[0]], getresultado, geterror);
        /*En este punto actualizo la geometria*/
        map.graphics.clear();
        document.getElementById("form_mapa:geometria").value = "";
        document.getElementById("form_mapa:btnGuardarGeometria").click();
    }
    function guardargeo(grafico) {
        CapaProyectos.applyEdits([grafico], null, null, getresultado, geterror);
        /*Se actualiza la geometria*/
    }
    function getresultado(ev) {
        console.log("se ejecuta");
    }
    function geterror(err) {
        alert(err);
    }

    function createToolbar(themap) {
        toolbar = new Draw(map);
        toolbar.on("draw-end", addToMap);
        //alert("creando tool");
    }

    function addToMap(evt) {

        toolbar.deactivate();
        map.showZoomSlider();
        switch (evt.geometry.type) {
            case "point":
            case "multipoint":
                symbol = new SimpleMarkerSymbol();
                break;
            case "polyline":
                //symbol = new SimpleLineSymbol();
                symbol  = new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,new Color([0,0,250]), 5);
                //symbol = new SimpleLineSymbol({ color: "lightblue", width: "5px", style: "short-dot"});
                break;
            default:
                symbol = new SimpleFillSymbol();
                break;
        }
        graphic = new Graphic(evt.geometry, symbol);
        //alert("Aqui esta la geometria" + dojo.toJson(graphic.toJson());
        var data = dojo.toJson(evt.geometry);
        document.getElementById("form_mapa:geometria").value = data;
        document.getElementById("form_mapa:btnGuardarGeometria").click();

        console.log(data, typeof data);
        graphic.setAttributes({"cod_proy": idProyecto, "cod_tram": idTramo});
        guardargeo(graphic);
        map.graphics.add(graphic);
        //salvar geometria
        consultar();
    }
});